package com.company.project.controller.baseInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.SysRegionEntity;
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
import java.util.Comparator;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.SysLinkEntity;
import com.company.project.service.SysLinkService;



/**
 * 
 *

 */
@Controller
@RequestMapping("/")
public class SysLinkController {
    @Autowired
    private SysLinkService sysLinkService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysLink")
    public String sysLink() {
        return "syslink/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysLink/add")
    @RequiresPermissions("sysLink:add")
    @ResponseBody
    public DataResult add(@RequestBody SysLinkEntity sysLink){
        sysLinkService.save(sysLink);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysLink/delete")
    @RequiresPermissions("sysLink:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysLinkService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysLink/update")
    @RequiresPermissions("sysLink:update")
    @ResponseBody
    public DataResult update(@RequestBody SysLinkEntity sysLink){
        sysLinkService.updateById(sysLink);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysLink/listByPage")
    @RequiresPermissions("sysLink:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysLinkEntity sysLink){
        Page page = new Page(sysLink.getPage(), sysLink.getLimit());
        LambdaQueryWrapper<SysLinkEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysLinkEntity::getId, sysLink.getId());
        IPage<SysLinkEntity> iPage = sysLinkService.page(page, queryWrapper);
        //模糊查找
        if(sysLink.getLinkName()!=null){
            List<SysLinkEntity> list=new ArrayList<>();
            iPage.getRecords().forEach(item->{
                if(item.getLinkName().contains(sysLink.getLinkName())){
                    list.add(item);
                }
            });
            iPage.setRecords(list);
        }
        iPage.getRecords().sort(new Comparator<SysLinkEntity>() {
            @Override
            public int compare(SysLinkEntity o1, SysLinkEntity o2) {
                if(o1.getLinkOrder()>o2.getLinkOrder()) return 1;
                else return -1;
            }
        });
        return DataResult.success(iPage);
    }

}
