package com.hikari.system.system.service;

import java.util.List;
import com.hikari.system.system.entity.SysStaffPost;
    
/**
 * SysStaffPostService
 * @author lkc39miku_cn
 */  
public interface SysStaffPostService{


    int insert(SysStaffPost record);

    int batchInsert(List<SysStaffPost> list);

}
