package com.hikari.project.pixivel.service.impl;

import com.hikari.project.pixivel.entity.PixUser;
import com.hikari.project.pixivel.mapper.PixUserMapper;
import com.hikari.project.pixivel.service.PixUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * PixUserServiceImpl
 * @author lkc39miku_cn
 */
@Service
public class PixUserServiceImpl implements PixUserService {

    @Resource
    private PixUserMapper pixUserMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixUser record) {
        record.setCreateTime(LocalDateTime.now());
        return pixUserMapper.insert(record);
    }

    @Override
    public PixUser selectByPrimaryKey(String id) {
        return pixUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixUser record) {
        return pixUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int batchInsert(List<PixUser> list) {
        return pixUserMapper.batchInsert(list);
    }

    @Override
    public int updateBatchSelective(List<PixUser> list) {
        return pixUserMapper.updateBatchSelective(list);
    }

    @Override
    public List<PixUser> selectAll(PixUser pixUser) {
        return pixUserMapper.selectAll(pixUser);
    }

    @Override
    public List<PixUser> selectPraise() {
        return pixUserMapper.selectPraise();
    }
}

