package com.company.project.controller.baseInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.SysGenerator;
import com.company.project.entity.SysShopEntity;
import com.company.project.mapper.SysCategoryMapper;
import com.company.project.vo.req.ShopSearchVo;
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
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.SysCategoryEntity;
import com.company.project.service.SysCategoryService;



/**
 * 
 *

 */
@Controller
@RequestMapping("/")
public class SysCategoryController {
    @Autowired
    private SysCategoryService sysCategoryService;
    @Autowired
    private SysCategoryMapper sysCategoryMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysCategory")
    public String sysCategory() {
        System.out.println("aaa");
        return "syscategory/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysCategory/add")
    @RequiresPermissions("sysCategory:add")
    @ResponseBody
    public DataResult add(@RequestBody SysCategoryEntity sysCategory){
        System.out.println(sysCategory.toString());
        sysCategoryService.save(sysCategory);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysCategory/delete")
    @RequiresPermissions("sysCategory:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysCategoryService.removeByIds(ids);
        ids.forEach(id->{
            sysCategoryService.deleteProductByCategoryId(id);
        });
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
    @ApiOperation(value = "查询分类数据")
    @GetMapping("sysCategory/findCategoryList")
    @ResponseBody
    public DataResult findCategoryList(){
        List<SysCategoryEntity> list =sysCategoryMapper.selectList(null);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCategoryStatus().equals("2")){
                list.remove(i);
            }
        }

        return DataResult.success(list);
    }

    @ApiOperation(value = "按分类名查找")
    @PostMapping("sysCategory/search")
    @ResponseBody
    public DataResult search(@RequestBody sysCategoryVo sysCategoryVo){

        //查询条件示例
        IPage<SysCategoryEntity> iPage = sysCategoryService.search(sysCategoryVo);

        return DataResult.success(iPage);
    }

}
