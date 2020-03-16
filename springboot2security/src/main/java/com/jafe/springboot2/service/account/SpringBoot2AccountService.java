package com.jafe.springboot2.service.account;

import com.jafe.springboot2.entity.account.SpringBoot2Account;

import java.util.List;

public interface SpringBoot2AccountService {
    List<SpringBoot2Account> findAll();
}