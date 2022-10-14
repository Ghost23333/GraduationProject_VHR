package com.ch.vhr.mapper;

import com.ch.vhr.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    String getDepNameById(Integer id);
    List<Department> getAllDepartmentsByParentId(Integer pid);
}