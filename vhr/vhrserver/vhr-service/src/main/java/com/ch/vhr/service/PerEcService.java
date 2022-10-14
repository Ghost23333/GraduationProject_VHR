package com.ch.vhr.service;

import com.ch.vhr.mapper.EmployeeecMapper;
import com.ch.vhr.model.Employeeec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName PerEcService.java
 * @Description TODO
 * @createTime 2022年04月23日 13:27:00
 */
@Service
public class PerEcService {
    @Autowired
    EmployeeecMapper employeeecMapper;
    public int addPerEc(Employeeec employeeec) {
        employeeec.setEcDate(new Date());
        return employeeecMapper.insertSelective(employeeec);
    }
    public List<Employeeec> getAllPerEcByEid(Integer eid) {
        return employeeecMapper.getAllPerEcByEid(eid);
    }
}
