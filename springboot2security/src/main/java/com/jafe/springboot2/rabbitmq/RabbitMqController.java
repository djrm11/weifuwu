package com.jafe.springboot2.rabbitmq;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jafe.springboot2.entity.SpringBoot2User;
import com.jafe.springboot2.entity.account.SpringBoot2Account;
import com.jafe.springboot2.service.SpringBoot2UserService;
import com.jafe.springboot2.service.account.SpringBoot2AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Autowired
    AckSenderService senderService;

    /**
     * @return
     */
    @GetMapping(value = "ackSend")
    public String ackSend() {
        senderService.send();

        return "ok";
    }

}
