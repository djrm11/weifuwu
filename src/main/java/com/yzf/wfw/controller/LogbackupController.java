package com.yzf.wfw.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogbackupController {
//    private final static Logger logger = LoggerFactory.getLogger(LogbackupController.class);

    @RequestMapping("/logbackup/eg")
    String home(){
        log.info("logback 访问hello");
        log.error("logback 访问hello");
        return "Hello World!";
    }

}
