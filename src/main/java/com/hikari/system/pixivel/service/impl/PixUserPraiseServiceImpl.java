package com.hikari.system.pixivel.service.impl;

import com.hikari.system.pixivel.entity.vo.PixUserPraiseVo;
import com.hikari.system.pixivel.mapper.PixPictureCollectionMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import com.hikari.system.pixivel.entity.PixUserPraise;
import com.hikari.system.pixivel.mapper.PixUserPraiseMapper;
import com.hikari.system.pixivel.service.PixUserPraiseService;

/**
 * PixUserPraiseServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class PixUserPraiseServiceImpl implements PixUserPraiseService{

    @Resource
    private PixUserPraiseMapper pixUserPraiseMapper;

    @Resource
    private PixPictureCollectionMapper pixPictureCollectionMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixUserPraiseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixUserPraise record) {
        record.setCreateTime(LocalDateTime.now());
        return pixUserPraiseMapper.insert(record);
    }

    @Override
    public PixUserPraise selectByPrimaryKey(String id) {
        return pixUserPraiseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixUserPraise record) {
        return pixUserPraiseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixUserPraise> list) {
        return pixUserPraiseMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixUserPraise> list) {
        return pixUserPraiseMapper.batchInsert(list);
    }

    @Override
    public PixUserPraiseVo selectList() {
        List<PixUserPraise> pixUserPraiseList = pixUserPraiseMapper.selectList("1");
        return new PixUserPraiseVo()
                .setList(pixPictureCollectionMapper.selectByIdList(pixUserPraiseList.stream().map(PixUserPraise::getPictureCollectionId).toList()));
    }
}
