package com.xiaofangmoon.web.mvc.user.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

/**
 * 用户领域对象
 *
 * @author xiaofang
 * @since 1.0
 */
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
//    @GeneratedValue(strategy = AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull(message = "用户名不能为空")
    @Length(min = 2, max = 5)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;
}

