package com.pazlbackup.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogbackupController {
    private final static Logger logger = LoggerFactory.getLogger(LogbackupController.class);

    @RequestMapping("/")
    String home(){
        return "Hello world";
    }

}
