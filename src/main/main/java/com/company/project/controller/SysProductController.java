package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.SysProductEntity;
import com.company.project.service.SysProductService;



/**
 * 系统产品
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2022-02-11 12:25:31
 */
@Controller
@RequestMapping("/")
public class SysProductController {
    @Autowired
    private SysProductService sysProductService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysProduct")
    public String sysProduct() {
        return "sysproduct/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysProduct/add")
    @RequiresPermissions("sysProduct:add")
    @ResponseBody
    public DataResult add(@RequestBody SysProductEntity sysProduct){
        sysProductService.save(sysProduct);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysProduct/delete")
    @RequiresPermissions("sysProduct:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysProductService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysProduct/update")
    @RequiresPermissions("sysProduct:update")
    @ResponseBody
    public DataResult update(@RequestBody SysProductEntity sysProduct){
        sysProductService.updateById(sysProduct);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysProduct/listByPage")
    @RequiresPermissions("sysProduct:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysProductEntity sysProduct){
        Page page = new Page(sysProduct.getPage(), sysProduct.getLimit());
        LambdaQueryWrapper<SysProductEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysProductEntity::getId, sysProduct.getId());
        IPage<SysProductEntity> iPage = sysProductService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
