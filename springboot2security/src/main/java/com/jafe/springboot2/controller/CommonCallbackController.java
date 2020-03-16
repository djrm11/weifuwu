package com.jafe.springboot2.controller;

import com.jafe.springboot2.entity.SpringBoot2User;
import com.jafe.springboot2.service.SpringBoot2UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class CommonCallbackController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private SpringBoot2UserService springBoot2UserService;

    @RequestMapping("user/findAll")
    public List<SpringBoot2User> findAll(){
        return springBoot2UserService.findAll();
    }

    @RequestMapping("/client1/sqserver/redirect")
    public String getToken(@RequestParam String code){
        log.info("receive code {}",code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("code",code);
//        params.add("client_id","aiqiyi");
//        params.add("client_secret","secret");
        params.add("client_id","client_1");
        params.add("client_secret","123456");
        params.add("redirect_uri","http://localhost:18082/client1/sqserver/redirect");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:18081/oauth/token", requestEntity, String.class);
        String token = response.getBody();
        log.info("token => {}",token);
        return token;
    }

}
