package com.hikari.project.pixivel.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.project.pixivel.entity.PixPicture;
import com.hikari.project.pixivel.entity.PixPictureCollection;
import com.hikari.project.pixivel.service.impl.PixPictureCollectionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * (spring_cloud.pix_picture_collection)表控制层
 *
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_picture_collection")
@RestController
@RequestMapping("/spring_cloud.pix_picture_collection")
public class PixPictureCollectionController {
    /**
     * 服务对象
     */
    @Resource
    private PixPictureCollectionServiceImpl pixPictureCollectionServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public Result<PixPictureCollection> selectOne(@PathVariable(value = "id") String id) {
        PixPictureCollection pixPictureCollection = pixPictureCollectionServiceImpl.selectByPrimaryKey(id);
        if (Objects.nonNull(pixPictureCollection)) {
            pixPictureCollection.setPixPictureList(pixPictureCollection.getPixPictureList().stream().sorted(Comparator.comparing(PixPicture::getSort)).toList());
        }
        return Result.success(pixPictureCollection);
    }

    /**
     * 分页查询数据
     * @param pixPictureCollection 实体对象
     * @return 分页对象
     */
    @GetMapping("select")
    @ApiOperation(value = "分页查询数据", notes = "分页查询数据")
    public Result<List<PixPictureCollection>> selectList(PixPictureCollection pixPictureCollection) {
        PageInfo<PixPictureCollection> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> pixPictureCollectionServiceImpl.selectList(pixPictureCollection));
        pageInfo.getList().forEach(v -> {
            v.setPixPictureList(v.getPixPictureList().stream().sorted(Comparator.comparing(PixPicture::getSort)).toList());
        });
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 最近收藏最多20条数据
     * @return 数据
     */
    @GetMapping("select/by/collect")
    @ApiOperation(value = "最近收藏最多", notes = "最近收藏最多")
    public Result<List<PixPictureCollection>> selectByCollect() {
        return Result.success(pixPictureCollectionServiceImpl.selectByCollect());
    }

    /**
     * 最近点赞最多20条数据
     * @return 数据
     */
    @GetMapping("select/by/praise")
    @ApiOperation(value = "最近点赞最多", notes = "最近点赞最多")
    public Result<List<PixPictureCollection>> selectByPraise() {
        return Result.success(pixPictureCollectionServiceImpl.selectByPraise());
    }

    /**
     * 新增数据
     * @param pixPictureCollection 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "添加数据", notes = "添加数据")
    public Result<String> insert(@RequestBody PixPictureCollection pixPictureCollection) {
        return CompareExecute.compare(pixPictureCollectionServiceImpl.insert(pixPictureCollection), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 修改数据
     * @param pixPictureCollection 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    @ApiOperation(value = "修改数据", notes = "修改数据")
    public Result<String> update(@RequestBody PixPictureCollection pixPictureCollection) {
        return CompareExecute.compare(pixPictureCollectionServiceImpl.updateByPrimaryKeySelective(pixPictureCollection), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(pixPictureCollectionServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 作者ID搜索其下作品
     * @param authorId 作者ID
     * @return 数据
     */
    @GetMapping("select/by/author/{authorId}")
    @ApiOperation(value = "作者ID搜索其下作品", notes = "作者ID搜索其下作品")
    public Result<List<PixPictureCollection>> selectByAuthor(@PathVariable(value = "authorId") String authorId) {
        return Result.success(pixPictureCollectionServiceImpl.selectByAuthor(authorId));
    }

}
