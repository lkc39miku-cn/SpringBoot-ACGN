package com.hikari.project.monitor.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.hikari.project.monitor.mapper.SysLoginOsMapper;
import com.hikari.project.monitor.entity.SysLoginOs;
import com.hikari.project.monitor.service.impl.SysLoginOsService;

/**
 * SysLoginOsServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysLoginOsServiceImpl implements SysLoginOsService{

    @Resource
    private SysLoginOsMapper sysLoginOsMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysLoginOsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysLoginOs record) {
        return sysLoginOsMapper.insert(record);
    }

    @Override
    public SysLoginOs selectByPrimaryKey(String id) {
        return sysLoginOsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysLoginOs record) {
        return sysLoginOsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<SysLoginOs> list) {
        return sysLoginOsMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<SysLoginOs> list) {
        return sysLoginOsMapper.batchInsert(list);
    }

}
