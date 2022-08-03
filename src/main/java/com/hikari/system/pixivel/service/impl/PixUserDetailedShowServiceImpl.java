package com.hikari.system.pixivel.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.hikari.system.pixivel.mapper.PixUserDetailedShowMapper;
import com.hikari.system.pixivel.entity.PixUserDetailedShow;
import com.hikari.system.pixivel.service.PixUserDetailedShowService;

/**
 * PixUserDetailedShowServiceImpl
 *
 * @author lkc39miku_cn
 */
@Service
public class PixUserDetailedShowServiceImpl implements PixUserDetailedShowService {

    @Resource
    private PixUserDetailedShowMapper pixUserDetailedShowMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixUserDetailedShowMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixUserDetailedShow record) {
        return pixUserDetailedShowMapper.insert(record);
    }

    @Override
    public PixUserDetailedShow selectByPrimaryKey(String id) {
        return pixUserDetailedShowMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixUserDetailedShow record) {
        return pixUserDetailedShowMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixUserDetailedShow> list) {
        return pixUserDetailedShowMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixUserDetailedShow> list) {
        return pixUserDetailedShowMapper.batchInsert(list);
    }

    @Override
    public PixUserDetailedShow selectByAuthorId(String authorId) {
        return pixUserDetailedShowMapper.selectByAuthorId(authorId);
    }
}


