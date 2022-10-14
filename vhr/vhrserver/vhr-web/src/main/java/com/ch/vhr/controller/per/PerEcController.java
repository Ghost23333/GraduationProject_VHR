package com.ch.vhr.controller.per;


import com.ch.vhr.model.Employeeec;
import com.ch.vhr.model.RespBean;
import com.ch.vhr.service.PerEcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName PerEcController.java
 * @Description TODO
 * @createTime 2022年04月23日 13:18:00
 */

@RestController
@RequestMapping("/personnel/ec")
public class PerEcController {
    @Autowired
    PerEcService perEcService;

    @PostMapping("/")
    public RespBean addPerEc(@RequestBody Employeeec employeeec){
        if(perEcService.addPerEc(employeeec) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
    @GetMapping("/{eid}")
    public List<Employeeec> getAllPerEcByEid(@PathVariable Integer eid){
        return perEcService.getAllPerEcByEid(eid);
    }
}
