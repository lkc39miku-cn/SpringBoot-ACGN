package com.hikari.system.pixivel.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.hikari.system.pixivel.mapper.PixUserDetailedMapper;
import com.hikari.system.pixivel.entity.PixUserDetailed;
import com.hikari.system.pixivel.service.PixUserDetailedService;

/**
 * PixUserDetailedServiceImpl
 *
 * @author lkc39miku_cn
 */
@Service
public class PixUserDetailedServiceImpl implements PixUserDetailedService {

    @Resource
    private PixUserDetailedMapper pixUserDetailedMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixUserDetailedMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixUserDetailed record) {
        return pixUserDetailedMapper.insert(record);
    }

    @Override
    public PixUserDetailed selectByPrimaryKey(String id) {
        return pixUserDetailedMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixUserDetailed record) {
        return pixUserDetailedMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixUserDetailed> list) {
        return pixUserDetailedMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixUserDetailed> list) {
        return pixUserDetailedMapper.batchInsert(list);
    }

    @Override
    public PixUserDetailed selectByAuthorId(String authorId) {
        return pixUserDetailedMapper.selectByAuthorId(authorId);
    }
}


