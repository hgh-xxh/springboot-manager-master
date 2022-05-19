package com.company.project.controller.baseInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.BaseEntity;
import com.company.project.entity.SysStoreIoEntity;
import com.company.project.entity.SysTraceEntity;
import com.company.project.mapper.SysLinkOperateMapper;
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

import com.company.project.entity.SysLinkOperateEntity;
import com.company.project.service.SysLinkOperateService;



/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-14 21:23:52
 */
@Controller
@RequestMapping("/")
public class SysLinkOperateController {
    @Autowired
    private SysLinkOperateService sysLinkOperateService;
    @Autowired
    private SysLinkOperateMapper sysLinkOperateMapper;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysLinkOperate")
    public String sysLinkOperate() {
        return "syslinkoperate/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysLinkOperate/add")
    @RequiresPermissions("sysLinkOperate:add")
    @ResponseBody
    public DataResult add(@RequestBody SysLinkOperateEntity sysLinkOperate){
        sysLinkOperate.setLinkDate(new Date());
        sysLinkOperateService.save(sysLinkOperate);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysLinkOperate/delete")
    @RequiresPermissions("sysLinkOperate:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysLinkOperateService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysLinkOperate/update")
    @RequiresPermissions("sysLinkOperate:update")
    @ResponseBody
    public DataResult update(@RequestBody SysLinkOperateEntity sysLinkOperate){
        sysLinkOperateService.updateById(sysLinkOperate);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysLinkOperate/listByPage")
    @RequiresPermissions("sysLinkOperate:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysLinkOperateEntity sysLinkOperate){
        Page page = new Page(sysLinkOperate.getPage(), sysLinkOperate.getLimit());
        LambdaQueryWrapper<SysLinkOperateEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysLinkOperateEntity::getId, sysLinkOperate.getId());
        IPage<SysLinkOperateEntity> iPage = sysLinkOperateService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    @PostMapping("sysLinkOperate/search")
    @ResponseBody
    public DataResult search(@RequestParam String traceOrderid){
        LambdaQueryWrapper<SysLinkOperateEntity> queryWrapper = Wrappers.lambdaQuery();
        BaseEntity baseEntity=new BaseEntity();
        Page page = new Page(baseEntity.getPage(), baseEntity.getLimit());
        if(!traceOrderid.isEmpty()){
            queryWrapper.like(SysLinkOperateEntity::getOrderId, traceOrderid);
        }
        IPage<SysStoreIoEntity> iPage =sysLinkOperateMapper.selectPage(page,queryWrapper);
        return DataResult.success(iPage);
    }
}
