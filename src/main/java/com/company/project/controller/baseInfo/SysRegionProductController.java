package com.company.project.controller.baseInfo;

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

import com.company.project.entity.SysRegionProductEntity;
import com.company.project.service.SysRegionProductService;



/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-14 20:24:18
 */
@Controller
@RequestMapping("/")
public class SysRegionProductController {
    @Autowired
    private SysRegionProductService sysRegionProductService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysRegionProduct")
    public String sysRegionProduct() {
        return "sysregionproduct/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysRegionProduct/add")
    @RequiresPermissions("sysRegionProduct:add")
    @ResponseBody
    public DataResult add(@RequestBody SysRegionProductEntity sysRegionProduct){
        sysRegionProductService.save(sysRegionProduct);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysRegionProduct/delete")
    @RequiresPermissions("sysRegionProduct:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysRegionProductService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysRegionProduct/update")
    @RequiresPermissions("sysRegionProduct:update")
    @ResponseBody
    public DataResult update(@RequestBody SysRegionProductEntity sysRegionProduct){
        sysRegionProductService.updateById(sysRegionProduct);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysRegionProduct/listByPage")
    @RequiresPermissions("sysRegionProduct:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysRegionProductEntity sysRegionProduct){
        Page page = new Page(sysRegionProduct.getPage(), sysRegionProduct.getLimit());
        LambdaQueryWrapper<SysRegionProductEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysRegionProductEntity::getId, sysRegionProduct.getId());
        IPage<SysRegionProductEntity> iPage = sysRegionProductService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
