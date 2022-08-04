package com.hikari.project.system.service;

import java.util.List;
import com.hikari.project.system.entity.SysPost;
    
/**
 * SysPostService
 * @author lkc39miku_cn
 */  
public interface SysPostService {


    int deleteByPrimaryKey(String id);

    int insert(SysPost record);

    SysPost selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPost record);

    int updateBatchSelective(List<SysPost> list);

    int batchInsert(List<SysPost> list);

    List<SysPost> selectList(SysPost sysPost);

    boolean checkPostName(String name);

    boolean checkPostCode(String code);

    boolean checkStaffPost(String id);

    boolean checkPostName(String name, String id);

    boolean checkPostCode(String code, String id);

    List<SysPost> selectNotList(SysPost sysPost);
}
