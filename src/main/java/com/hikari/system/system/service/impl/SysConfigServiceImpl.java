package com.hikari.system.system.service.impl;

import com.hikari.commons.key.NumberKey;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import com.hikari.system.system.entity.SysConfig;

import java.time.LocalDateTime;
import java.util.List;
import com.hikari.system.system.mapper.SysConfigMapper;
import com.hikari.system.system.service.SysConfigService;

/**
 * SysConfigServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysConfigServiceImpl implements SysConfigService{

    @PostConstruct
    public void init() {
        System.out.println("SysConfigServiceImpl init");
    }

    @Resource
    private SysConfigMapper sysConfigMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysConfigMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysConfig record) {
        record.setCreateTime(LocalDateTime.now());
        return sysConfigMapper.insert(record);
    }

    @Override
    public SysConfig selectByPrimaryKey(String id) {
        return sysConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysConfig record) {
        record.setUpdateTime(LocalDateTime.now());
        return sysConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<SysConfig> list) {
        return sysConfigMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<SysConfig> list) {
        return sysConfigMapper.batchInsert(list);
    }

    @Override
    public List<SysConfig> selectList(SysConfig sysConfig) {
        return sysConfigMapper.selectList(sysConfig);
    }

    @Override
    public int deleteBatch(List<String> ids) {
        return sysConfigMapper.deleteBatch(ids);
    }

    @Override
    public boolean checkConfigName(String name) {
        return selectList(new SysConfig().setName(name)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkConfigKey(String configKey) {
        return selectList(new SysConfig().setConfigKey(configKey)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkConfigName(String name, String id) {
        return selectNotList(new SysConfig().setName(name).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkConfigKey(String configKey, String id) {
        return selectNotList(new SysConfig().setConfigKey(configKey).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public List<SysConfig> selectNotList(SysConfig sysConfig) {
        return sysConfigMapper.selectNotList(sysConfig);
    }
}
