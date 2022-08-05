package com.hikari.project.pixivel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.key.NumberKey;
import com.hikari.commons.key.StatusKey;
import com.hikari.framework.exception.service.ServiceException;
import com.hikari.framework.thread.ThreadLocalInfo;
import com.hikari.project.pixivel.entity.PixUser;
import com.hikari.project.pixivel.service.async.PixUserCollectAsync;
import com.hikari.project.pixivel.service.async.PixUserPraiseAsync;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import com.hikari.project.pixivel.mapper.PixUserCollectMapper;
import com.hikari.project.pixivel.entity.PixUserCollect;
import com.hikari.project.pixivel.service.PixUserCollectService;

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
        if (pixUserCollectMapper.selectList(new PixUserCollect()
                .setPixUserId("1")
                .setPictureCollectionId(record.getPictureCollectionId()))
                .size() > NumberKey.ZERO) {
            throw new ServiceException("used_collect", "已收藏该图集");
        }
        record.setPixUserId(((PixUser) ThreadLocalInfo.get()).getUserId());
        record.setCreateTime(LocalDateTime.now());
        if (pixUserCollectMapper.insert(record) > NumberKey.ZERO) {
            PixUserCollectAsync.refreshCollectPictureCollection(record.getPictureCollectionId());
        }
        return StatusKey.SUCCESS_INT;
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

    @Override
    public int deleteByUser(String id) {
        String userId = ((PixUser) ThreadLocalInfo.get()).getUserId();
        if (pixUserCollectMapper.deleteByUser(userId, id) > NumberKey.ZERO) {
            PixUserCollectAsync.refreshDeleteCollectPictureCollection(id);
        }
        return StatusKey.SUCCESS_INT;
    }
}
