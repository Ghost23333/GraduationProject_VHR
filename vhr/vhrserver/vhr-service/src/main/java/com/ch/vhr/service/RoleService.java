package com.ch.vhr.service;

import com.ch.vhr.mapper.RoleMapper;
import com.ch.vhr.model.Menu;
import com.ch.vhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName RoleService.java
 * @Description TODO
 * @createTime 2022年04月11日 10:47:00
 */
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;


    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }
}
