package com.hikari.project.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hikari.project.system.mapper.SysStaffPostMapper;
import java.util.List;
import com.hikari.project.system.entity.SysStaffPost;
import com.hikari.project.system.service.SysStaffPostService;

/**
 * SysStaffPostServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysStaffPostServiceImpl implements SysStaffPostService{

    @Resource
    private SysStaffPostMapper sysStaffPostMapper;

    @Override
    public int insert(SysStaffPost record) {
        return sysStaffPostMapper.insert(record);
    }

    @Override
    public int batchInsert(List<SysStaffPost> list) {
        return sysStaffPostMapper.batchInsert(list);
    }

}
