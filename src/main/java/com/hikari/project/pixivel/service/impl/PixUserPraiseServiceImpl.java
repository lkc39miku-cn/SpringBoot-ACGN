package com.hikari.project.pixivel.service.impl;

import com.hikari.commons.key.NumberKey;
import com.hikari.framework.manager.AsyncManager;
import com.hikari.project.pixivel.entity.vo.PixUserPraiseVo;
import com.hikari.project.pixivel.mapper.PixPictureCollectionMapper;
import com.hikari.project.pixivel.service.async.PixUserPraiseAsync;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.hikari.project.pixivel.entity.PixUserPraise;
import com.hikari.project.pixivel.mapper.PixUserPraiseMapper;
import com.hikari.project.pixivel.service.PixUserPraiseService;

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
        AsyncManager.me().execute(PixUserPraiseAsync.refreshPraisePictureCollection(record.getPictureCollectionId()));
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
        List<PixUserPraise> pixUserPraiseList = pixUserPraiseMapper.selectList(new PixUserPraise()
                .setPixUserId("1"));
        return new PixUserPraiseVo()
                .setList(pixPictureCollectionMapper.selectByIdList(pixUserPraiseList.stream().map(PixUserPraise::getPictureCollectionId).toList()));
    }

    @Override
    public boolean isPraise(String pictureId) {
        return pixUserPraiseMapper.selectList(new PixUserPraise().setPictureCollectionId(pictureId)
                .setPixUserId("1")).size() > NumberKey.ZERO;
    }
}
