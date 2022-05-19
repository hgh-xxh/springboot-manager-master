package com.company.project.controller.baseInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.SysRegionEntity;
import com.company.project.service.SysRegionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;
import com.company.project.common.utils.DataResult;





/**
 * 
 *

 */
@Controller
@RequestMapping("/")
public class SysRegionController {
    @Autowired
    private SysRegionService sysRegionService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysRegion")
    public String sysRegion() {
        return "sysregion/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysRegion/add")
    @RequiresPermissions("sysRegion:add")
    @ResponseBody
    public DataResult add(@RequestBody SysRegionEntity sysRegion){
        sysRegionService.save(sysRegion);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysRegion/delete")
    @RequiresPermissions("sysRegion:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysRegionService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysRegion/update")
    @RequiresPermissions("sysRegion:update")
    @ResponseBody
    public DataResult update(@RequestBody SysRegionEntity sysRegion){
        sysRegionService.updateById(sysRegion);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysRegion/listByPage")
    @RequiresPermissions("sysRegion:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysRegionEntity sysRegion){
        System.out.println(sysRegion.toString()+"-----------------");
        Page page = new Page(sysRegion.getPage(), sysRegion.getLimit());
        LambdaQueryWrapper<SysRegionEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysRegionEntity::getId, sysRegion.getId());
        IPage<SysRegionEntity> iPage = sysRegionService.page(page, queryWrapper);
        if(sysRegion.getRegionName()!=null){
            List<SysRegionEntity> list=new ArrayList<>();
            iPage.getRecords().forEach(item->{
                if(item.getRegionName().contains(sysRegion.getRegionName())){
                    list.add(item);
                }
            });
            iPage.setRecords(list);
        }

        return DataResult.success(iPage);
    }

}
