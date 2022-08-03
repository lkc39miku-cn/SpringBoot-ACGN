package com.hikari.system.pixivel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import com.hikari.system.pixivel.mapper.PixUserCollectMapper;
import com.hikari.system.pixivel.entity.PixUserCollect;
import com.hikari.system.pixivel.service.PixUserCollectService;

/**
 * PixUserCollectServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class PixUserCollectServiceImpl implements PixUserCollectService{

    @Resource
    private PixUserCollectMapper pixUserCollectMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixUserCollectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixUserCollect record) {
        record.setCreateTime(LocalDateTime.now());
        return pixUserCollectMapper.insert(record);
    }

    @Override
    public PixUserCollect selectByPrimaryKey(String id) {
        return pixUserCollectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixUserCollect record) {
        return pixUserCollectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixUserCollect> list) {
        return pixUserCollectMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixUserCollect> list) {
        return pixUserCollectMapper.batchInsert(list);
    }

    @Override
    public PageInfo<PixUserCollect> selectList(PixUserCollect pixUserCollect) {
        return PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> pixUserCollectMapper.selectList(pixUserCollect));
    }

    @Override
    public List<String> selectTag() {
        return pixUserCollectMapper.selectTag("1");
    }

    @Override
    public int updateTagBatch(PixUserCollect pixUserCollect) {
        return pixUserCollectMapper.updateTagBatch(pixUserCollect);
    }
}
