package com.hikari.system.pixivel.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hikari.system.pixivel.entity.PixMapping;
import java.util.List;
import com.hikari.system.pixivel.mapper.PixMappingMapper;
import com.hikari.system.pixivel.service.PixMappingService;

/**
 * PixMappingServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class PixMappingServiceImpl implements PixMappingService{

    @Resource
    private PixMappingMapper pixMappingMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixMappingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixMapping record) {
        return pixMappingMapper.insert(record);
    }

    @Override
    public PixMapping selectByPrimaryKey(String id) {
        return pixMappingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixMapping record) {
        return pixMappingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixMapping> list) {
        return pixMappingMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixMapping> list) {
        return pixMappingMapper.batchInsert(list);
    }

}
