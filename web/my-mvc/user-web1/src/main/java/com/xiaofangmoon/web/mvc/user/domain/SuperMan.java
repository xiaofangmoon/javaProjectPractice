package com.xiaofangmoon.web.mvc.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xiaofang
 * @createTime 2021/03/14 14:43
 * @description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SuperMan {

    Integer id;
    String name;
    Person person;


}
