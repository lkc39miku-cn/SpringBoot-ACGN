package com.hikari.project.pixivel.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hikari.project.pixivel.mapper.PixTagMapper;
import com.hikari.project.pixivel.entity.PixTag;
import java.util.List;
import com.hikari.project.pixivel.service.PixTagService;

/**
 * PixTagServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class PixTagServiceImpl implements PixTagService{

    @Resource
    private PixTagMapper pixTagMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixTagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixTag record) {
        return pixTagMapper.insert(record);
    }

    @Override
    public PixTag selectByPrimaryKey(String id) {
        return pixTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixTag record) {
        return pixTagMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixTag> list) {
        return pixTagMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixTag> list) {
        return pixTagMapper.batchInsert(list);
    }

}
