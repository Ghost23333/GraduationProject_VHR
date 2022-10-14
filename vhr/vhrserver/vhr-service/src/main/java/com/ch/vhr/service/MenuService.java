package com.ch.vhr.service;

import com.ch.vhr.mapper.MenuMapper;
import com.ch.vhr.mapper.MenuRoleMapper;
import com.ch.vhr.model.Hr;
import com.ch.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName MenuService.java
 * @Description TODO
 * @createTime 2022年03月09日 21:53:00
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;
    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getId());
    }

//    @Cacheable  项目上线后一点要加上
    public List<Menu> getMenusWithRoles(){
        return menuMapper.getMenusWithRoles();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        if(mids == null || mids.length == 0)
            return true;
        Integer result = menuRoleMapper.insertRecord(rid,mids);
        return result == mids.length;
    }
}
