package com.jafe.springboot2.mapper;

import com.jafe.springboot2.entity.SpringBoot2User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpringBoot2UserMapper {
    List<SpringBoot2User> findAll();
}