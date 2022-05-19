package com.company.project.controller.store;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.SysCategoryEntity;
import com.company.project.entity.SysStoreEntity;
import com.company.project.mapper.SysStoreIoMapper;
import com.company.project.mapper.SysStoreMapper;
import com.company.project.vo.req.sysCategoryVo;
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

import com.company.project.entity.SysStoreIoEntity;
import com.company.project.service.SysStoreIoService;



/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-11 14:47:59
 */
@Controller
@RequestMapping("/")
public class SysStoreIoController {
    @Autowired
    private SysStoreIoService sysStoreIoService;
    @Autowired
    private SysStoreMapper storeMapper;
    @Autowired
    private SysStoreIoMapper storeIoMapper;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysStoreIo")
    public String sysStoreIo() {
        return "sysstoreio/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysStoreIo/add")
    @RequiresPermissions("sysStoreIo:add")
    @ResponseBody
    public DataResult add(@RequestBody SysStoreIoEntity sysStoreIo){
        //System.out.println(sysStoreIo.toString());
        SysStoreEntity sysStoreEntity=storeMapper.selectById(sysStoreIo.getStoreioStoreid());
        if(sysStoreIo.getStoreioType().equals("出库")){
            Double num=Double.valueOf(sysStoreEntity.getStoreReserveNum())-Double.valueOf(sysStoreIo.getStoreioNum());
            if(num<0){
                //System.out.println("我反悔了！！！！！");
                return DataResult.fail("仓库库存不足");
            }

            sysStoreEntity.setStoreReserveNum(String.valueOf(num));

            storeMapper.updateById(sysStoreEntity);
        }
        else if(sysStoreIo.getStoreioType().equals("入库")){
            Double num=Double.valueOf(sysStoreEntity.getStoreReserveNum())+Double.valueOf(sysStoreIo.getStoreioNum());
            sysStoreEntity.setStoreReserveNum(String.valueOf(num));
            storeMapper.updateById(sysStoreEntity);
        }
        sysStoreIo.setStoreioTime(new Date());
        sysStoreIoService.save(sysStoreIo);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysStoreIo/delete")
    @RequiresPermissions("sysStoreIo:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        for(int i=0;i<ids.size();i++){
            SysStoreIoEntity ioEntity=storeIoMapper.selectById(ids.get(i));
            SysStoreEntity storeEntity=storeMapper.selectById(ioEntity.getStoreioStoreid());
            if(ioEntity.getStoreioType().equals("出库")){
                storeEntity.setStoreReserveNum(String.valueOf(Double.valueOf(storeEntity.getStoreReserveNum())+Double.valueOf(ioEntity.getStoreioNum())));
                storeMapper.updateById(storeEntity);
            }
            else {
                storeEntity.setStoreReserveNum(String.valueOf(Double.valueOf(storeEntity.getStoreReserveNum())-Double.valueOf(ioEntity.getStoreioNum())));
                storeMapper.updateById(storeEntity);
            }
        }
        sysStoreIoService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysStoreIo/update")
    @RequiresPermissions("sysStoreIo:update")
    @ResponseBody
    public DataResult update(@RequestBody SysStoreIoEntity sysStoreIo){
        sysStoreIoService.updateById(sysStoreIo);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysStoreIo/listByPage")
    @RequiresPermissions("sysStoreIo:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysStoreIoEntity sysStoreIo){
        Page page = new Page(sysStoreIo.getPage(), sysStoreIo.getLimit());
        LambdaQueryWrapper<SysStoreIoEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysStoreIoEntity::getId, sysStoreIo.getId());
        IPage<SysStoreIoEntity> iPage = sysStoreIoService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    @GetMapping("sysStoreIo/getStoreId")
    @ResponseBody
    public DataResult getStoreId(){
        List<SysStoreEntity> list =storeMapper.selectList(null);
        return DataResult.success(list);
    }
    @PostMapping("sysStoreIo/search")
    @ResponseBody
    public DataResult search(@RequestParam String storeioOperater,@RequestParam String storeioNum ){
        System.out.println(storeioOperater+"11111111111");
        IPage<SysStoreIoEntity> iPage = sysStoreIoService.search(storeioOperater,storeioNum);
        return DataResult.success(iPage);
    }
}
