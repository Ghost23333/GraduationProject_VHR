package com.ch.vhr.exception;

import com.ch.vhr.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName GlobleExceptionHander.java
 * @Description TODO
 * @createTime 2022年04月07日 14:10:00
 */

@RestControllerAdvice
public class    GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关联数据,操作失败!");
        }
        return RespBean.error("数据库异常,操作失败!");

    }
}
