package com.ch.vhr.service;

import com.ch.vhr.mapper.SalaryMapper;
import com.ch.vhr.model.Salary;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName SalaryService.java
 * @Description TODO
 * @createTime 2022年04月22日 10:12:00
 */
@Service
public class SalaryService {
    @Autowired
    SalaryMapper salaryMapper;

    public List<Salary> getAllSalaries() {
        return salaryMapper.getAllSalaries();
    }

    public int addSalary(Salary salary) {
        salary.setCreateDate(new Date());
        return salaryMapper.insertSelective(salary);
    }

    public int deleteSalary(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    public int updateSalary(Salary salary) {
        return salaryMapper.updateByPrimaryKeySelective(salary);
    }

    public Salary getSalaryByEid(@Param("id") Integer id) {
        return salaryMapper.getSalaryByEid(id);
    }
}
