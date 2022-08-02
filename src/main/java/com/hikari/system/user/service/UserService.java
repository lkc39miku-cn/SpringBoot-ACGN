package com.hikari.system.user.service;


import com.hikari.system.user.entity.User;

import java.util.List;
    
/**
 * UserService
 * @author lkc39miku_cn
 */  
public interface UserService{


    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateBatchSelective(List<User> list);

    int batchInsert(List<User> list);

}
