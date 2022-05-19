package com.company.project.controller.store;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.SysStoreIoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.SysProcessEntity;
import com.company.project.service.SysProcessService;



/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-11 15:47:09
 */
@Controller
@RequestMapping("/")
public class SysProcessController {
    @Autowired
    private SysProcessService sysProcessService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysProcess")
    public String sysProcess() {
        return "sysprocess/list1";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysProcess/add")
    @RequiresPermissions("sysProcess:add")
    @ResponseBody
    public DataResult add(@RequestBody SysProcessEntity sysProcess){
        System.out.println(sysProcess.toString());
        sysProcess.setProcessTime(new Date());
        sysProcessService.save(sysProcess);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysProcess/delete")
    @RequiresPermissions("sysProcess:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysProcessService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysProcess/update")
    @RequiresPermissions("sysProcess:update")
    @ResponseBody
    public DataResult update(@RequestBody SysProcessEntity sysProcess){
        sysProcessService.updateById(sysProcess);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysProcess/listByPage")
    @RequiresPermissions("sysProcess:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysProcessEntity sysProcess){
        Page page = new Page(sysProcess.getPage(), sysProcess.getLimit());
        LambdaQueryWrapper<SysProcessEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysProcessEntity::getId, sysProcess.getId());
        IPage<SysProcessEntity> iPage = sysProcessService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    @PostMapping("sysProcess/search")
    @ResponseBody
    public DataResult search(@RequestParam String processType){
        System.out.println(processType+"11111111111");
        IPage<SysProcessEntity> iPage = sysProcessService.search(processType);
        return DataResult.success(iPage);
    }
}
