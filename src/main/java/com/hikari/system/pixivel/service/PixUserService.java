package com.hikari.system.pixivel.service;

import com.hikari.system.pixivel.entity.PixUser;

import java.util.List;

/**
 * PixUserService
 *
 * @author lkc39miku_cn
 */
public interface PixUserService {


    int deleteByPrimaryKey(String id);

    int insert(PixUser record);

    PixUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUser record);

    int batchInsert(List<PixUser> list);

    int updateBatchSelective(List<PixUser> list);

    List<PixUser> selectAll(PixUser pixUser);
}

