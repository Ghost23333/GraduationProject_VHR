package com.ch.vhr.controller.emp;

import com.ch.vhr.model.*;
import com.ch.vhr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName EmpBasicController.java
 * @Description TODO
 * @createTime 2022年04月14日 10:59:00
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    NationService nationService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keywords) {
        return employeeService.getEmployeeByPage(page, size,keywords);
    }

    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee){
        if(employeeService.addEmployee(employee) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
    @GetMapping("/nations")
    public List<Nation> getAllNation(){
        return nationService.getAllNation();
    }

    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus(){
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevel(){
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("positions")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkID")
    public String getMaxWorkID(){
        return String.format("%08d",employeeService.getMaxWorkID()+1);
    }

    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmpByEid(@PathVariable Integer id) {
        if (employeeService.deleteEmpByEid(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateEmp(employee) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }


}
