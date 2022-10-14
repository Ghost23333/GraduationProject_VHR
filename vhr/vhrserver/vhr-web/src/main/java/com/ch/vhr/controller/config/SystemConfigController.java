package com.ch.vhr.controller.config;

import com.ch.vhr.model.Menu;
import com.ch.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName SystemConfigController.java
 * @Description TODO
 * @createTime 2022年03月09日 21:51:00
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    @Autowired
    MenuService menuService;
    @GetMapping("/menu")
    public List<Menu> getMenusByHrId(){
        return menuService.getMenusByHrId();
    }
}
