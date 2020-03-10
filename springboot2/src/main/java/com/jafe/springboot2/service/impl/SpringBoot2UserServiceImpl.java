package com.jafe.springboot2.service.impl;

import com.jafe.springboot2.entity.SpringBoot2User;
import com.jafe.springboot2.mapper.SpringBoot2UserMapper;
import com.jafe.springboot2.service.SpringBoot2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("springBoot2UserService")
public class SpringBoot2UserServiceImpl implements SpringBoot2UserService {

    @Autowired
    private SpringBoot2UserMapper springBoot2UserMapper;

    @Override
    public List<SpringBoot2User> findAll() {
        return springBoot2UserMapper.findAll();
    }
}