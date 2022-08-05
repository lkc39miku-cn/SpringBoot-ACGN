package com.hikari.project.pixivel.controller;

import com.github.pagehelper.PageInfo;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.project.pixivel.entity.PixUserCollect;
import com.hikari.project.pixivel.entity.vo.PixUserCollectVo;
import com.hikari.project.pixivel.service.impl.PixPictureCollectionServiceImpl;
import com.hikari.project.pixivel.service.impl.PixUserCollectServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (spring_cloud.pix_user_collect)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.pix_user_collect")
public class PixUserCollectController {
    /**
     * 服务对象
     */
    @Resource
    private PixUserCollectServiceImpl pixUserCollectServiceImpl;

    @Resource
    private PixPictureCollectionServiceImpl pixPictureCollectionServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Deprecated
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public Result<PixUserCollect> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(pixUserCollectServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 分页查询数据
     * @param pixUserCollect 实体对象
     * @return 分页对象
     */
    @GetMapping("select")
    @ApiOperation(value = "分页查询数据", notes = "分页查询数据")
    public Result<PixUserCollectVo> selectList(PixUserCollect pixUserCollect) {
        PageInfo<PixUserCollect> pageInfo = pixUserCollectServiceImpl.selectList(pixUserCollect);

        PixUserCollectVo pixUserCollectVo = new PixUserCollectVo()
                .setList(pixPictureCollectionServiceImpl.selectByIdList(pageInfo.getList().stream().map(PixUserCollect::getPictureCollectionId).toList()));

        return Result.success(pixUserCollectVo, pageInfo.getTotal());
    }

    /**
     * 添加数据
     * @param pixUserCollect 实体对象
     * @return 添加结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "添加数据", notes = "添加数据")
    public Result<String> insert(@RequestBody PixUserCollect pixUserCollect) {
        return CompareExecute.compare(pixUserCollectServiceImpl.insert(pixUserCollect), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(pixUserCollectServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 更新数据
     * @param pixUserCollect 实体对象
     * @return 更新结果
     */
    @PutMapping("update")
    @ApiOperation(value = "更新数据", notes = "更新数据")
    public Result<String> update(@RequestBody PixUserCollect pixUserCollect) {
        return CompareExecute.compare(pixUserCollectServiceImpl.updateByPrimaryKeySelective(pixUserCollect), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 获取收藏标签
     * @return 标签
     */
    @GetMapping("select/tag")
    @ApiOperation(value = "获取收藏标签", notes = "获取收藏标签")
    public Result<List<String>> selectTag() {
        return Result.success(pixUserCollectServiceImpl.selectTag());
    }

    /**
     * 批量标签收藏
     * @param pixUserCollect 实体对象
     * @return 更新结果
     */
    @PutMapping("update/tag/batch")
    @ApiOperation(value = "批量标签收藏", notes = "批量标签收藏")
    public Result<String> updateTagBatch(@RequestBody PixUserCollect pixUserCollect) {
        return CompareExecute.compare(pixUserCollectServiceImpl.updateTagBatch(pixUserCollect), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 取消收藏
     * @param id 图片集合
     * @return 是否成功
     */
    @DeleteMapping("user/delete/{id}")
    @ApiOperation(value = "取消收藏", notes = "取消收藏")
    public Result<String> userDelete(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(pixUserCollectServiceImpl.deleteByUser(id), CompareExecute.ExecuteStatus.DELETE);
    }
}
