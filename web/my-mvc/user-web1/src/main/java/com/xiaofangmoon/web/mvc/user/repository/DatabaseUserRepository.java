package com.xiaofangmoon.web.mvc.user.repository;


import com.xiaofangmoon.web.mvc.user.domain.User;
import com.xiaofangmoon.web.mvc.user.function.ThrowableFunction;
import com.xiaofangmoon.web.mvc.user.sql.DBConnectionManager;
import lombok.Data;
import org.apache.commons.lang3.ClassUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;


@Data
public class DatabaseUserRepository implements UserRepository {

    private static Logger logger = Logger.getLogger(DatabaseUserRepository.class.getName());

    /**
     * 通用处理方式
     */
    private static Consumer<Throwable> COMMON_EXCEPTION_HANDLER = e -> logger.log(Level.SEVERE, e.getMessage());

    public static final String INSERT_USER_DML_SQL =
            "INSERT INTO users(name,password,email,phoneNumber) VALUES " +
                    "(?,?,?,?)";

    public static final String QUERY_ALL_USERS_DML_SQL = "SELECT id,name,password,email,phoneNumber FROM users";

    @Resource(name = "jdbc/UserPlatformDB")
    private DataSource dataSource;

    @Resource(name = "bean/DBConnectionManager")
    private DBConnectionManager dbConnectionManager;


    /**
     * 数据类型与 ResultSet 方法名映射
     */
    static Map<Class, String> resultSetMethodMappings = new HashMap<>();

    static Map<Class, String> preparedStatementMethodMappings = new HashMap<>();

    static {
        resultSetMethodMappings.put(Long.class, "getLong");
        resultSetMethodMappings.put(String.class, "getString");

        preparedStatementMethodMappings.put(Long.class, "setLong"); // long
        preparedStatementMethodMappings.put(String.class, "setString"); //


    }

    public DatabaseUserRepository() {
    }

    public DatabaseUserRepository(DBConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    private Connection getConnection() {
        return dbConnectionManager.getConnection();
    }

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public Integer registerUser(User user) {
        return executeUpdate("insert into users (name,password) values(?,?) ", null, COMMON_EXCEPTION_HANDLER, user.getName(), user.getPassword());
    }

    @Override
    public boolean deleteById(Long userId) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User getById(Long userId) {
        return executeQuery("SELECT id,name,password,email,phone FROM users WHERE id=? ",
                resultSet -> {
                    while (resultSet.next()) {
                        System.out.println("gogo");
                        return getUser(resultSet);
                    }
                    return null;
                }, COMMON_EXCEPTION_HANDLER, userId);
    }

    @Override
    public User getByNameAndPassword(String userName, String password) {
        return executeQuery("SELECT id,name,password,email,phone FROM users WHERE name=? and password=?",
                resultSet -> {
                    // TODO
                    return new User();
                }, COMMON_EXCEPTION_HANDLER, userName, password);
    }

    @Override
    public Collection<User> getAll() {
        return executeQuery("SELECT id,name,password,email,phone FROM users", resultSet -> {
            // BeanInfo -> IntrospectionException
            BeanInfo userBeanInfo = Introspector.getBeanInfo(User.class, Object.class);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) { // 如果存在并且游标滚动 // SQLException
                User user = getUser(resultSet);
                users.add(user);
            }
            return users;
        }, COMMON_EXCEPTION_HANDLER);
    }

    /**
     * @param sql
     * @param function
     * @param <T>
     * @return
     */
    protected <T> T executeQuery(String sql, ThrowableFunction<ResultSet, T> function,
                                 Consumer<Throwable> exceptionHandler, Object... args) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                Class argType = arg.getClass();
                Class wrapperType = ClassUtils.wrapperToPrimitive(argType);
                if (wrapperType == null) {
                    wrapperType = argType;
                }
                Integer a = 1;
                // Boolean -> boolean
                String methodName = preparedStatementMethodMappings.get(argType);
                Method method = PreparedStatement.class.getMethod(methodName, int.class, wrapperType);
                method.invoke(preparedStatement, i + 1, arg);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            // 返回一个 POJO List -> ResultSet -> POJO List
            // ResultSet -> T
            return function.apply(resultSet);
        } catch (Throwable e) {
            exceptionHandler.accept(e);
        }
        return null;
    }

    /**
     * @param sql
     * @param function
     * @param <T>
     * @return
     */
    protected <T> Integer executeUpdate(String sql, ThrowableFunction<ResultSet, T> function,
                                        Consumer<Throwable> exceptionHandler, Object... args) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                Class argType = arg.getClass();
                Class wrapperType = ClassUtils.wrapperToPrimitive(argType);
                if (wrapperType == null) {
                    wrapperType = argType;
                }
                Integer a = 1;
                // Boolean -> boolean
                String methodName = preparedStatementMethodMappings.get(argType);
                Method method = PreparedStatement.class.getMethod(methodName, int.class, wrapperType);
                method.invoke(preparedStatement, i + 1, arg);
            }
            return preparedStatement.executeUpdate();
        } catch (Throwable e) {
            exceptionHandler.accept(e);
        }
        return null;
    }


    private static String mapColumnLabel(String fieldName) {
        return fieldName;
    }


    User getUser(ResultSet resultSet) throws Exception {
        User user = new User();
        // BeanInfo
        BeanInfo userBeanInfo = Introspector.getBeanInfo(User.class, Object.class);
        // 所有的 Properties 信息
        for (PropertyDescriptor propertyDescriptor : userBeanInfo.getPropertyDescriptors()) {
            System.out.println(propertyDescriptor.getName() + " , " + propertyDescriptor.getPropertyType());
        }
        for (PropertyDescriptor propertyDescriptor : userBeanInfo.getPropertyDescriptors()) {
            String fieldName = propertyDescriptor.getName();
            Class fieldType = propertyDescriptor.getPropertyType();
            String methodName = resultSetMethodMappings.get(fieldType);
            // 可能存在映射关系（不过此处是相等的）
            String columnLabel = mapColumnLabel(fieldName);
            Method resultSetMethod = ResultSet.class.getMethod(methodName, String.class);
            // 通过放射调用 getXXX(String) 方法
            Object resultValue = resultSetMethod.invoke(resultSet, columnLabel);
            // 获取 User 类 Setter方法
            // PropertyDescriptor ReadMethod 等于 Getter 方法
            // PropertyDescriptor WriteMethod 等于 Setter 方法
            Method setterMethodFromUser = propertyDescriptor.getWriteMethod();
            // 以 id 为例，  user.setId(resultSet.getLong("id"));
            setterMethodFromUser.invoke(user, resultValue);
        }

        System.out.println(user);
        return user;
    }


}
