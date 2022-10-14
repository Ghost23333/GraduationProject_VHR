package com.ch.vhr.controller.system.basic;

import com.ch.vhr.model.JobLevel;
import com.ch.vhr.model.Position;
import com.ch.vhr.model.RespBean;
import com.ch.vhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName JobTitleController.java
 * @Description TODO
 * @createTime 2022年04月08日 14:55:00
 */

@RestController
@RequestMapping("/system/basic/jobTitle")
public class JobTitleController {
    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        jobLevel.setEnabled(true);
        jobLevel.setCreateDate(new Date());
        if(jobLevelService.addJobLevel(jobLevel) == 1){
            return RespBean.ok("添加成功!");
        }else {
            return RespBean.error("添加失败!");
        }
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        if(jobLevelService.updateJobLevel(jobLevel) == 1){
            return RespBean.ok("更新成功!");
        }else {
            return RespBean.error("更新失败!");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id){
        if(jobLevelService.deleteJobLevelById(id) == 1){
            return RespBean.ok("删除成功!");
        }else {
            return RespBean.error("删除失败!");
        }
    }
}
