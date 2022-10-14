package com.ch.vhr.service;

import com.ch.vhr.mapper.PoliticsstatusMapper;
import com.ch.vhr.model.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName PoliticsstatusService.java
 * @Description TODO
 * @createTime 2022年04月15日 14:25:00
 */
@Service
public class PoliticsstatusService {
    @Autowired
    PoliticsstatusMapper politicsstatusMapper;
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusMapper.getAllPoliticsstatus();

    }
}
