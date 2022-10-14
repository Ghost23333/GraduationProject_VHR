package com.ch.vhr.controller;
import com.ch.vhr.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description TODO
 * @createTime 2022年03月06日 13:41:00
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录,请登录");
    }
}
