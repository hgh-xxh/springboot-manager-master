package com.company.project.controller.baseInfo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.vo.req.ShopSearchVo;
import org.apache.ibatis.annotations.Param;
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

import com.company.project.entity.SysShopEntity;
import com.company.project.service.SysShopService;



/**
 * 经销商
 *

 */
@Controller
@RequestMapping("/")
public class SysShopController {
    @Autowired
    private SysShopService sysShopService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysShop")
    public String sysShop() {
        return "sysshop/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysShop/add")
    @RequiresPermissions("sysShop:add")
    @ResponseBody
    public DataResult add(@RequestBody SysShopEntity sysShop){

        sysShopService.save(sysShop);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysShop/delete")
    @RequiresPermissions("sysShop:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        for (String id : ids) {
            System.out.println(id);
        }
        sysShopService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysShop/update")
    @RequiresPermissions("sysShop:update")
    @ResponseBody
    public DataResult update(@RequestBody SysShopEntity sysShop){
        System.out.println("---------------------");
        System.out.println(sysShop.toString());
        System.out.println("---------------------");
        sysShopService.updateById(sysShop);
        return DataResult.success();
    }


    @GetMapping("test/aa")
    @ResponseBody
    public DataResult update(){
        System.out.println("hahah");

        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysShop/listByPage")
    @RequiresPermissions("sysShop:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysShopEntity sysShop){
        System.out.println("请求分页");
        Page page = new Page(sysShop.getPage(), sysShop.getLimit());
        LambdaQueryWrapper<SysShopEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysShopEntity::getId, sysShop.getId());
        IPage<SysShopEntity> iPage = sysShopService.page(page, queryWrapper);

        return DataResult.success(iPage);
    }

    @ApiOperation(value = "按管理人查找")
    @PostMapping("sysShop/search")
    @ResponseBody
    public DataResult search(@RequestBody ShopSearchVo shopSearchVo){

//        Page page = new Page(shopSearchVo.getPage(), shopSearchVo.getLimit());
//        LambdaQueryWrapper<SysShopEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        //queryWrapper.eq(SysShopEntity::getShopPeople,shopPeople);
//        IPage<SysShopEntity> iPage = sysShopService.page(page, queryWrapper);
        System.out.println(shopSearchVo.toString());
        List<SysShopEntity> records = sysShopService.search(shopSearchVo).getRecords();

        return DataResult.success(sysShopService.search(shopSearchVo));
    }
}
