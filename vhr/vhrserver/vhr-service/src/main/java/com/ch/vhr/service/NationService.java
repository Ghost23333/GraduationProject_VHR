package com.ch.vhr.service;

import com.ch.vhr.mapper.NationMapper;
import com.ch.vhr.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName NationService.java
 * @Description TODO
 * @createTime 2022年04月15日 14:24:00
 */
@Service
public class NationService {
    @Autowired
    NationMapper nationMapper;
    public List<Nation> getAllNation() {
        return nationMapper.getAllNation();

    }
}
