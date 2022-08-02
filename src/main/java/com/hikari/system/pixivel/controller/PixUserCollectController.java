package com.hikari.system.pixivel.controller;

import com.github.pagehelper.PageInfo;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.system.pixivel.entity.PixUserCollect;
import com.hikari.system.pixivel.entity.vo.PixUserCollectVo;
import com.hikari.system.pixivel.service.impl.PixPictureCollectionServiceImpl;
import com.hikari.system.pixivel.service.impl.PixUserCollectServiceImpl;
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
    public Result<PixUserCollect> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(pixUserCollectServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 分页查询数据
     * @param pixUserCollect 实体对象
     * @return 分页对象
     */
    @GetMapping("select")
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
}
