package com.ch.vhr.utils;

import com.ch.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author chenhong
 * @version 1.0.0
 * @ClassName HrUtils.java
 * @Description TODO
 * @createTime 2022年04月12日 11:07:00
 */

public class HrUtils {
    public static Hr getCurrentHr(){
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
