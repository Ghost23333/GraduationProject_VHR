package com.ch.vhr.service;

import com.ch.vhr.mapper.DepartmentMapper;
import com.ch.vhr.mapper.EmployeeMapper;
import com.ch.vhr.mapper.EmployeeremoveMapper;
import com.ch.vhr.mapper.PositionMapper;
import com.ch.vhr.model.Employee;
import com.ch.vhr.model.Employeeec;
import com.ch.vhr.model.Employeeremove;
import com.ch.vhr.model.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName PerMvService.java
 * @Description TODO
 * @createTime 2022年04月24日 12:27:00
 */

@Service
public class PerMvService {
    @Autowired
    EmployeeremoveMapper employeeremoveMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    PositionMapper positionMapper;
    @Transactional
    public int addPerMv(Employeeremove employeeremove) {
        employeeremove.setRemoveDate(new Date());
        Employee employee = new Employee();
        employee.setId(employeeremove.getEid());
        employee.setDepartmentId(employeeremove.getAfterDepId());
        employee.setPosId(employeeremove.getAfterPosId());
        employeeMapper.updateByPrimaryKeySelective(employee);
        int result = employeeremoveMapper.insertSelective(employeeremove);
        return result;
    }

    public List<Employeeremove> getAllPerMvByEid(@Param("eid") Integer eid) {
        List<Employeeremove> result =  employeeremoveMapper.getAllPerMvByEid(eid);
        for (Employeeremove employeeremove : result) {
            int afterDepId = employeeremove.getAfterDepId();
            int afterPosId = employeeremove.getAfterPosId();
            int beforeDepId = employeeremove.getBeforeDepId();
            int beforePosId = employeeremove.getBeforePosId();
            employeeremove.setAfterDepName(departmentMapper.getDepNameById(afterDepId));
            employeeremove.setBeforeDepName(departmentMapper.getDepNameById(beforeDepId));
            employeeremove.setAfterPosName(positionMapper.getPosNameById(afterPosId));
            employeeremove.setBeforePosName(positionMapper.getPosNameById(beforePosId));
        }
        return result;
    }
}
