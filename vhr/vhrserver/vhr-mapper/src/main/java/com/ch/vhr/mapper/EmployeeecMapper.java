package com.ch.vhr.mapper;

import com.ch.vhr.model.Employee;
import com.ch.vhr.model.Employeeec;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employeeec record);

    Employeeec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeeec record);

    int updateByPrimaryKey(Employeeec record);

    int insertSelective(Employeeec record);

    List<Employeeec> getAllPerEcByEid(Integer eid);
}