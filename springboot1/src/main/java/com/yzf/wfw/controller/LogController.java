package com.yzf.wfw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {
    @RequestMapping("/log")
    String home(){
        log.info("logback 访问hello");
        log.error("logback 访问hello");
        return "Hello World!";
    }

}
