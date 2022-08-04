package com.hikari.project.pixivel.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.hikari.project.pixivel.entity.PixUserPrimary;
import com.hikari.project.pixivel.mapper.PixUserPrimaryMapper;
import com.hikari.project.pixivel.service.PixUserPrimaryService;

/**
 * PixUserPrimaryServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class PixUserPrimaryServiceImpl implements PixUserPrimaryService{

    @Resource
    private PixUserPrimaryMapper pixUserPrimaryMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixUserPrimaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixUserPrimary record) {
        return pixUserPrimaryMapper.insert(record);
    }

    @Override
    public PixUserPrimary selectByPrimaryKey(String id) {
        return pixUserPrimaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixUserPrimary record) {
        return pixUserPrimaryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixUserPrimary> list) {
        return pixUserPrimaryMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixUserPrimary> list) {
        return pixUserPrimaryMapper.batchInsert(list);
    }

}
