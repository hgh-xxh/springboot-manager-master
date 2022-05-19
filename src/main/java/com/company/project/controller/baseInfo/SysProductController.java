package com.company.project.controller.baseInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.SysCategoryEntity;
import com.company.project.mapper.SysCategoryMapper;
import com.company.project.mapper.SysProductMapper;
import com.company.project.vo.req.ProductVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.project.common.utils.DataResult;

import com.company.project.entity.SysProductEntity;
import com.company.project.service.SysProductService;



/**
 * 系统产品
 *

 */
@Controller
@RequestMapping("/")
public class SysProductController {
    @Autowired
    private SysProductService sysProductService;
    @Autowired
    private SysCategoryMapper sysCategoryMapper;
    @Autowired
    private SysProductMapper sysProductMapper;

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

    @ApiOperation(value = "测试")
    @PutMapping("sysProduct/aa")

    @ResponseBody
    public DataResult aa(){

        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysProduct/listByPage")
    @RequiresPermissions("sysProduct:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysProductEntity sysProduct){
        Page page = new Page(sysProduct.getPage(), sysProduct.getLimit());
        //LambdaQueryWrapper<SysProductEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysProductEntity::getId, sysProduct.getId());
        //IPage<SysProductEntity> iPage = sysProductService.page(page, queryWrapper);
        //根据id查分类名
        Map<Integer,String> mp=new HashMap<>();
        List<SysCategoryEntity> sysCategoryEntities = sysCategoryMapper.selectList(null);
        sysCategoryEntities.forEach(item->{
            mp.put(item.getCategoryId(),item.getCategoryName());
        });

        //查询产品序列
        IPage<SysProductEntity> iPage=sysProductMapper.selectPage(page,null);
        iPage.getRecords().forEach(item->{
        //    System.out.println(item.getCategoryId());
            String name=mp.get(item.getCategoryId());
            item.setCategoryName(name);
           // System.out.println(item.toString()+"---------"+name);
        });
//        //查询产品序列
//        List<SysProductEntity> sysProductEntities = sysProductMapper.selectList(null);
//        //返回的数组
//        List<ProductVo> productVoList=new ArrayList<>();
//        sysProductEntities.forEach(product->{
//            ProductVo item=(ProductVo)product;
//            item.setCategoryName(mp.get(product.getCategoryId()));
//            productVoList.add(item);
//            System.out.println(item.toString());
//        });
        return DataResult.success(iPage);
    }

}
