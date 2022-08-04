package com.hikari.project.pixivel.mapper;

import com.hikari.project.pixivel.entity.PixPictureCollection;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixPictureCollectionMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface PixPictureCollectionMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixPictureCollection record);

    PixPictureCollection selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixPictureCollection record);

    int updateBatchSelective(List<PixPictureCollection> list);

    int batchInsert(@Param("list") List<PixPictureCollection> list);

    List<PixPictureCollection> selectList(PixPictureCollection pixPictureCollection);

    List<PixPictureCollection> selectByIdList(@Param(value = "idList") List<String> idList);

    List<PixPictureCollection> selectByCollect();

    List<PixPictureCollection> selectByPraise();

    List<PixPictureCollection> selectByAuthor(String authorId);
}