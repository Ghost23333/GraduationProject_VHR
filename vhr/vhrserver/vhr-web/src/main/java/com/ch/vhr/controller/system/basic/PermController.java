package com.ch.vhr.controller.system.basic;

import com.ch.vhr.model.Menu;
import com.ch.vhr.model.RespBean;
import com.ch.vhr.model.Role;
import com.ch.vhr.service.MenuService;
import com.ch.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName PermissController.java
 * @Description TODO
 * @createTime 2022年04月11日 10:34:00
 */
@RestController
@RequestMapping("/system/basic/perm")
public class PermController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer []mids){
        if(menuService.updateMenuRole(rid,mids)){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
