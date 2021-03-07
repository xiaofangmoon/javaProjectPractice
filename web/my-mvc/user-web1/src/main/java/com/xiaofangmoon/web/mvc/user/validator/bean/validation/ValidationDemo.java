package com.xiaofangmoon.web.mvc.user.validator.bean.validation;

import com.xiaofangmoon.web.mvc.user.domain.User;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;

/**
 * @author: xiaofang
 * @createTime: 2021/03/07 20:42
 * @description:
 */
public class ValidationDemo {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("zh","CN"));
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Locale aDefault = Locale.getDefault();
        System.out.println(aDefault);

        Validator validator = factory.getValidator();
        User user = new User();
        user.setName("1111111");

        Set<ConstraintViolation<User>> validate = validator.validate(user);
        for (ConstraintViolation<User> userConstraintViolation : validate) {
            System.out.println(userConstraintViolation);
        }


    }
}
