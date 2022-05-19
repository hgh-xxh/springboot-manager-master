package com.company.project.controller.order;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.*;
import com.company.project.mapper.SysOrderMapper;
import com.company.project.mapper.SysTransferMapper;
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

import com.company.project.service.SysTransferService;



/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-13 15:57:17
 */
@Controller
@RequestMapping("/")
public class SysTransferController {
    @Autowired
    private SysTransferService sysTransferService;
    @Autowired
    private SysOrderMapper sysOrderMapper;
    @Autowired
    private SysTransferMapper sysTransferMapper;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysTransfer")
    public String sysTransfer() {
        return "systransfer/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysTransfer/add")
    @RequiresPermissions("sysTransfer:add")
    @ResponseBody
    public DataResult add(@RequestBody SysTransferEntity sysTransfer){
        SysOrderEntity sysOrderEntity=sysOrderMapper.selectById(sysTransfer.getTransferOrderId());
        sysOrderEntity.setOrderState(sysTransfer.getTransferState());
        sysOrderMapper.updateById(sysOrderEntity);
        //System.out.println(sysTransfer.toString());
        sysTransfer.setTransferTime(new Date());
        sysTransferService.save(sysTransfer);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysTransfer/delete")
    @RequiresPermissions("sysTransfer:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysTransferService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysTransfer/update")
    @RequiresPermissions("sysTransfer:update")
    @ResponseBody
    public DataResult update(@RequestBody SysTransferEntity sysTransfer){
        //更新订单信息

        sysTransferService.updateById(sysTransfer);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysTransfer/listByPage")
    @RequiresPermissions("sysTransfer:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysTransferEntity sysTransfer){
        Page page = new Page(sysTransfer.getPage(), sysTransfer.getLimit());
        LambdaQueryWrapper<SysTransferEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysTransferEntity::getId, sysTransfer.getId());
        IPage<SysTransferEntity> iPage = sysTransferService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    @GetMapping("sysTransfer/init2")
    @ResponseBody
    public DataResult init2(){

        return DataResult.success(sysOrderMapper.selectList(null));
    }
    @PostMapping("sysTransfer/search")
    @ResponseBody
    public DataResult search(@RequestParam String transferOrderId){
       // System.out.println(transferOrderId+"------------");
        LambdaQueryWrapper<SysTransferEntity> queryWrapper = Wrappers.lambdaQuery();
        BaseEntity baseEntity=new BaseEntity();
        Page page = new Page(baseEntity.getPage(), baseEntity.getLimit());
        if(!transferOrderId.isEmpty()){
            queryWrapper.like(SysTransferEntity::getTransferOrderId, transferOrderId);
        }
        IPage<SysStoreIoEntity> iPage =sysTransferMapper.selectPage(page,queryWrapper);
        return DataResult.success(iPage);
    }
}
