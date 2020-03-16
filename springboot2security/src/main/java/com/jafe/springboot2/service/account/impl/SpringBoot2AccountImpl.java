package com.jafe.springboot2.service.account.impl;

import com.jafe.springboot2.entity.account.SpringBoot2Account;
import com.jafe.springboot2.mapper.account.SpringBoot2AccountMapper;
import com.jafe.springboot2.service.account.SpringBoot2AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("springBoot2AccountImpl")
public class SpringBoot2AccountImpl implements SpringBoot2AccountService {

    @Autowired
    private SpringBoot2AccountMapper springBoot2AccountMapper;

    @Override
    public List<SpringBoot2Account> findAll() {
        return springBoot2AccountMapper.findAll();
    }
}