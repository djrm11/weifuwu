package com.jafe.springboot2.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private SpringBoot2UserService springBoot2UserService;

    @Autowired
    private SpringBoot2AccountService springBoot2AccountService;

    @RequestMapping("/listAccount")
    public String listCategory(Model m,
                               @RequestParam(value = "start",defaultValue = "0")int start,
                               @RequestParam(value = "size",defaultValue = "5")int size){
        log.info("AccountController.listAccount:{},{}",start,size);
// 接受前端传过来的，起始页，每页记录数这两个参数，将其转换为整数
//        int startPage= Integer.parseInt((String)condition.get("page"));
//        int pageSize= Integer.parseInt((String)condition.get("limit"));

//  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page page= PageHelper.startPage(start, size);
//  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("id ASC");
// 从数据库查询，这里返回的的allUser就已经分页成功了
        List<SpringBoot2Account> allAccount = springBoot2AccountService.findAll();

// 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        long total = page.getTotal();
        PageInfo<SpringBoot2Account> pageInfo = new PageInfo<>(allAccount);
        m.addAttribute("page",pageInfo);
        return "/listAccount";
    }
    @RequestMapping("account/findAll")
    public List<SpringBoot2Account> findAccountAll(){
        return springBoot2AccountService.findAll();
    }

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
