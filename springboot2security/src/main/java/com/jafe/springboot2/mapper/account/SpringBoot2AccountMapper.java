package com.jafe.springboot2.mapper.account;

import com.jafe.springboot2.entity.account.SpringBoot2Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpringBoot2AccountMapper {
    List<SpringBoot2Account> findAll();
}