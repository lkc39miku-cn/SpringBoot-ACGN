package com.hikari.system.pixivel.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.hikari.system.pixivel.entity.PixUserOrder;
import com.hikari.system.pixivel.mapper.PixUserOrderMapper;
import com.hikari.system.pixivel.service.PixUserOrderService;

/**
 * PixUserOrderServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class PixUserOrderServiceImpl implements PixUserOrderService{

    @Resource
    private PixUserOrderMapper pixUserOrderMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixUserOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixUserOrder record) {
        return pixUserOrderMapper.insert(record);
    }

    @Override
    public PixUserOrder selectByPrimaryKey(String id) {
        return pixUserOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixUserOrder record) {
        return pixUserOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixUserOrder> list) {
        return pixUserOrderMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixUserOrder> list) {
        return pixUserOrderMapper.batchInsert(list);
    }

}
