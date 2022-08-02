package com.hikari.system.user.mapper;

import com.hikari.system.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * UserMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateBatchSelective(List<User> list);

    int batchInsert(@Param("list") List<User> list);
}