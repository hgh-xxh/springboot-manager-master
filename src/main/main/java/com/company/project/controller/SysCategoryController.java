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

import com.company.project.entity.SysCategoryEntity;
import com.company.project.service.SysCategoryService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2022-02-11 22:10:18
 */
@Controller
@RequestMapping("/")
public class SysCategoryController {
    @Autowired
    private SysCategoryService sysCategoryService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysCategory")
    public String sysCategory() {
        return "syscategory/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysCategory/add")
    @RequiresPermissions("sysCategory:add")
    @ResponseBody
    public DataResult add(@RequestBody SysCategoryEntity sysCategory){
        sysCategoryService.save(sysCategory);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysCategory/delete")
    @RequiresPermissions("sysCategory:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysCategoryService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysCategory/update")
    @RequiresPermissions("sysCategory:update")
    @ResponseBody
    public DataResult update(@RequestBody SysCategoryEntity sysCategory){
        sysCategoryService.updateById(sysCategory);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysCategory/listByPage")
    @RequiresPermissions("sysCategory:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysCategoryEntity sysCategory){
        Page page = new Page(sysCategory.getPage(), sysCategory.getLimit());
        LambdaQueryWrapper<SysCategoryEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysCategoryEntity::getId, sysCategory.getId());
        IPage<SysCategoryEntity> iPage = sysCategoryService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
