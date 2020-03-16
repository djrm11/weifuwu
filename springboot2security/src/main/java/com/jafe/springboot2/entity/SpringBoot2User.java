package com.jafe.springboot2.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SpringBoot2User implements Serializable {
    private Long id;//编号
    private String userName;//用户名
    private String password;//密码
    //省略get set方法
}