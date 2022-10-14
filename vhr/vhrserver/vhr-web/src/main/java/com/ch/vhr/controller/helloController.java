package com.ch.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName helloController.java
 * @Description TODO
 * @createTime 2022年03月05日 18:33:00
 */

@RestController
public class helloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return "/employee/basic/**";
    }
    @GetMapping("/personnel/salary/hello")
    public String hello3(){
        return "/personnel/salary/**";
    }


}
