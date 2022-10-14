package com.ch.vhr.controller.per;

import com.ch.vhr.model.Employeeec;
import com.ch.vhr.model.Employeeremove;
import com.ch.vhr.model.RespBean;
import com.ch.vhr.service.PerMvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName PerMvController.java
 * @Description TODO
 * @createTime 2022年04月24日 12:27:00
 */
@RestController
@RequestMapping("/personnel/remove")
public class PerMvController {
    @Autowired
    PerMvService perMvService;
    @PostMapping("/")
    public RespBean addPerMv(@RequestBody Employeeremove employeeremove){
        if(perMvService.addPerMv(employeeremove) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @GetMapping("/{eid}")
    public List<Employeeremove> getAllPerMvByEid(@PathVariable Integer eid){
        return perMvService.getAllPerMvByEid(eid);
    }
}
