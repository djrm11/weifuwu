package com.jafe.springboot2.entity.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SpringBoot2Account implements Serializable {
    private Long id;//编号
    private String mobileNum;//用户名
    private String systemId;//密码
    //省略get set方法
}