package com.hikari.project.pixivel.service;

import java.util.List;
import com.hikari.project.pixivel.entity.PixPictureCollection;

/**
 * PixPictureCollectionService
 * @author lkc39miku_cn
 */  
public interface PixPictureCollectionService{


    int deleteByPrimaryKey(String id);

    int insert(PixPictureCollection record);

    PixPictureCollection selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixPictureCollection record);

    int updateBatchSelective(List<PixPictureCollection> list);

    int batchInsert(List<PixPictureCollection> list);

    List<PixPictureCollection> selectList(PixPictureCollection pixPictureCollection);

    List<PixPictureCollection> selectByCollect();

    List<PixPictureCollection> selectByPraise();

    List<PixPictureCollection> selectByIdList(List<String> idList);

    List<PixPictureCollection> selectByAuthor(String authorId);

    int refreshPraise(String id);
}
