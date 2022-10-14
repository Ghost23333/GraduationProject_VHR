package com.ch.vhr.mapper;

import com.ch.vhr.model.Employeeec;
import com.ch.vhr.model.Employeeremove;

import java.util.List;

public interface EmployeeremoveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employeeremove record);

    int insertSelective(Employeeremove record);

    Employeeremove selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeeremove record);

    int updateByPrimaryKey(Employeeremove record);

    List<Employeeremove> getAllPerMvByEid(Integer eid);
}