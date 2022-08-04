package com.hikari.project.pixivel.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.project.pixivel.entity.PixUserOrder;
import com.hikari.project.pixivel.service.impl.PixUserOrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (spring_cloud.pix_user_order)表控制层
 *
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_user_order")
@RestController
@RequestMapping("/spring_cloud.pix_user_order")
public class PixUserOrderController {
    /**
     * 服务对象
     */
    @Resource
    private PixUserOrderServiceImpl pixUserOrderServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public Result<PixUserOrder> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(pixUserOrderServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 分页数据
     * @param pixUserOrder
     * @return 全部数据
     */
    @GetMapping("select")
    @ApiOperation(value = "分页数据", notes = "分页数据")
    public Result<List<PixUserOrder>> selectList(PixUserOrder pixUserOrder) {
        PageInfo<PixUserOrder> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> pixUserOrderServiceImpl.selectList(pixUserOrder));
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 添加数据
     * @param pixUserOrder 实例对象
     * @return 结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "添加数据", notes = "添加数据")
    public Result<String> insert(@RequestBody PixUserOrder pixUserOrder) {
        return CompareExecute.compare(pixUserOrderServiceImpl.insert(pixUserOrder), CompareExecute.ExecuteStatus.INSERT);
    }


}
