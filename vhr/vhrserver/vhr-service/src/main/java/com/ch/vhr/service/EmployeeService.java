package com.ch.vhr.service;

import com.ch.vhr.mapper.EmployeeMapper;
import com.ch.vhr.mapper.EmployeeecMapper;
import com.ch.vhr.model.Employee;
import com.ch.vhr.model.RespPageBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName EmployeeService.java
 * @Description TODO
 * @createTime 2022年04月14日 11:05:00
 */
@Service
public class EmployeeService {
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    EmployeeMapper employeeMapper;
    public RespPageBean getEmployeeByPage(Integer page, Integer size,String keywords) {
        if(page !=null && size!=null){
            page = (page - 1)*size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page,size,keywords);
        Long total = employeeMapper.getTotal(keywords);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(data);
        respPageBean.setTotal(total);
        return respPageBean;
    }

    public Integer addEmployee(Employee employee) {
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        int result = employeeMapper.insertSelective(employee);
        if(result == 1){
            Employee employee1 = employeeMapper.getEmployeeById(employee.getId());
            rabbitTemplate.convertAndSend("vhr.email.welcome",employee1);

        }
        return result;
    }

    public Integer getMaxWorkID() {
        return employeeMapper.getMaxWorkID();
    }

    public int deleteEmpByEid(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public int updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public RespPageBean getEmployeeByPageWithSalary(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> list = employeeMapper.getEmployeeByPageWithSalary(page, size);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(list);
        respPageBean.setTotal(employeeMapper.getTotal(null));
        return respPageBean;
    }

    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeSalaryById(eid, sid);
    }

}
