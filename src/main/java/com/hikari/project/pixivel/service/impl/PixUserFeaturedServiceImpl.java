package com.hikari.project.pixivel.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import com.hikari.project.pixivel.mapper.PixUserFeaturedMapper;
import com.hikari.project.pixivel.entity.PixUserFeatured;
import com.hikari.project.pixivel.service.PixUserFeaturedService;

/**
 * PixUserFeaturedServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class PixUserFeaturedServiceImpl implements PixUserFeaturedService{

    @Resource
    private PixUserFeaturedMapper pixUserFeaturedMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixUserFeaturedMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixUserFeatured record) {
        record.setCreateTime(LocalDateTime.now());
        return pixUserFeaturedMapper.insert(record);
    }

    @Override
    public PixUserFeatured selectByPrimaryKey(String id) {
        return pixUserFeaturedMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixUserFeatured record) {
        return pixUserFeaturedMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixUserFeatured> list) {
        return pixUserFeaturedMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixUserFeatured> list) {
        return pixUserFeaturedMapper.batchInsert(list);
    }

}
