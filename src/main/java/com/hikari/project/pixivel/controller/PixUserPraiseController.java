package com.hikari.project.pixivel.controller;

import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.project.pixivel.entity.PixUserPraise;
import com.hikari.project.pixivel.entity.vo.PixUserPraiseVo;
import com.hikari.project.pixivel.service.impl.PixUserPraiseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.pix_user_praise)表控制层
 *
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_user_praise")
@RestController
@RequestMapping("/spring_cloud.pix_user_praise")
public class PixUserPraiseController {
    /**
     * 服务对象
     */
    @Resource
    private PixUserPraiseServiceImpl pixUserPraiseServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Deprecated
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public Result<PixUserPraise> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(pixUserPraiseServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 查询数据
     * @return 数据
     */
    @GetMapping("select")
    @ApiOperation(value = "查询数据", notes = "查询数据")
    public Result<PixUserPraiseVo> selectList() {
        return Result.success(pixUserPraiseServiceImpl.selectList());
    }

    /**
     * 是否点赞
     * @param pictureId id
     * @return 结果
     */
    @GetMapping("is/praise")
    @ApiOperation(value = "是否点赞", notes = "是否点赞")
    public Result<Boolean> isPraise(@RequestParam(value = "pictureId") String pictureId) {
        return Result.success(pixUserPraiseServiceImpl.isPraise(pictureId));
    }



    /**
     * 添加数据
     * @param pixUserPraise 实体对象
     * @return 添加结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "添加数据", notes = "添加数据")
    public Result<String> insert(@RequestBody PixUserPraise pixUserPraise) {
        return CompareExecute.compare(pixUserPraiseServiceImpl.insert(pixUserPraise), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(pixUserPraiseServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 取消点赞
     * @param id 图片集合
     * @return 是否成功
     */
    @DeleteMapping("user/delete/{id}")
    @ApiOperation(value = "取消点赞", notes = "取消点赞")
    public Result<String> userDelete(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(pixUserPraiseServiceImpl.deleteByUser(id), CompareExecute.ExecuteStatus.DELETE);
    }
}
