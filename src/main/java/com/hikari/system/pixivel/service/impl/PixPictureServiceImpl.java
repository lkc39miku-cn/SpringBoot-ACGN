package com.hikari.system.pixivel.service.impl;

import com.hikari.system.pixivel.entity.PixPicture;
import com.hikari.system.pixivel.mapper.PixPictureMapper;
import com.hikari.system.pixivel.service.PixPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PixPictureServiceImpl
 *
 * @author lkc39miku_cn
 */
@Service
public class PixPictureServiceImpl implements PixPictureService {

    @Resource
    private PixPictureMapper pixPictureMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixPictureMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixPicture record) {
        return pixPictureMapper.insert(record);
    }

    @Override
    public PixPicture selectByPrimaryKey(String id) {
        return pixPictureMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixPicture record) {
        return pixPictureMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixPicture> list) {
        return pixPictureMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixPicture> list) {
        return pixPictureMapper.batchInsert(list);
    }

}

