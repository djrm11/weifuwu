package com.yzf.wfw.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan(basePackages={"com.*"})
public class Application {
    @GetMapping("/")
    public String hello() {
        return "hello, spring security";
    }
    @RequestMapping("/weifuwu")
    String home(){
        return "Hello world";
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
