package com.ch.vhr.service;

import com.ch.vhr.mapper.DepartmentMapper;
import com.ch.vhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName DepartmentService.java
 * @Description TODO
 * @createTime 2022年04月15日 16:00:00
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    public void addDep(Department dep) {
    }

    public void deleteDepById(Department dep) {
    }

    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }
}
