package com.hikari.project.system.service.impl;

import com.hikari.commons.key.NumberKey;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import com.hikari.project.system.mapper.SysPostMapper;
import com.hikari.project.system.entity.SysPost;
import com.hikari.project.system.service.SysPostService;

/**
 * SysPostServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysPostServiceImpl implements SysPostService{

    @Resource
    private SysPostMapper sysPostMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysPostMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysPost record) {
        record.setCreateTime(LocalDateTime.now());
        return sysPostMapper.insert(record);
    }

    @Override
    public SysPost selectByPrimaryKey(String id) {
        return sysPostMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysPost record) {
        return sysPostMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<SysPost> list) {
        return sysPostMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<SysPost> list) {
        return sysPostMapper.batchInsert(list);
    }

    @Override
    public List<SysPost> selectList(SysPost sysPost) {
        return sysPostMapper.selectList(sysPost);
    }

    @Override
    public boolean checkPostName(String name) {
        return selectList(new SysPost().setName(name)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkPostCode(String code) {
        return selectList(new SysPost().setCode(code)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkStaffPost(String id) {
        return sysPostMapper.checkStaffPost(id) > NumberKey.ZERO;
    }

    @Override
    public boolean checkPostName(String name, String id) {
        return selectNotList(new SysPost().setName(name).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkPostCode(String code, String id) {
        return selectNotList(new SysPost().setCode(code).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public List<SysPost> selectNotList(SysPost sysPost) {
        return sysPostMapper.selectNotList(sysPost);
    }
}
