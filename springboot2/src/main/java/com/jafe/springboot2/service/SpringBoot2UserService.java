package com.jafe.springboot2.service;

import com.jafe.springboot2.entity.SpringBoot2User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SpringBoot2UserService{
    List<SpringBoot2User> findAll();
}