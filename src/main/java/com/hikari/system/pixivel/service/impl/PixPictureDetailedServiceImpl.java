package com.hikari.system.pixivel.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.hikari.system.pixivel.entity.PixPictureDetailed;
import com.hikari.system.pixivel.mapper.PixPictureDetailedMapper;
import com.hikari.system.pixivel.service.PixPictureDetailedService;

/**
 * PixPictureDetailedServiceImpl
 *
 * @author lkc39miku_cn
 */
@Service
public class PixPictureDetailedServiceImpl implements PixPictureDetailedService {

    @Resource
    private PixPictureDetailedMapper pixPictureDetailedMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixPictureDetailedMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixPictureDetailed record) {
        return pixPictureDetailedMapper.insert(record);
    }

    @Override
    public PixPictureDetailed selectByPrimaryKey(String id) {
        return pixPictureDetailedMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixPictureDetailed record) {
        return pixPictureDetailedMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixPictureDetailed> list) {
        return pixPictureDetailedMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixPictureDetailed> list) {
        return pixPictureDetailedMapper.batchInsert(list);
    }

}

