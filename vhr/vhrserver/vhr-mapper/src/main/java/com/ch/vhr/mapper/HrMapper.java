package com.ch.vhr.mapper;

import com.ch.vhr.model.Hr;
import com.ch.vhr.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    List<Role> getHrRolesById();

    Hr loadUserByName(String username);

    List<Hr> getAllHr(@Param("id") Integer id,@Param("keywords") String keywords);
}