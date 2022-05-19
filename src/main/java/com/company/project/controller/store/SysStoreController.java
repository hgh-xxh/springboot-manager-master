package com.company.project.controller.store;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.SysCategoryEntity;
import com.company.project.entity.SysProductEntity;
import com.company.project.entity.SysShopEntity;
import com.company.project.mapper.SysProductMapper;
import com.company.project.mapper.SysStoreMapper;
import com.company.project.service.SysProductService;
import com.company.project.vo.req.ShopSearchVo;
import com.company.project.vo.req.storeSearchVo;
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

import com.company.project.entity.SysStoreEntity;
import com.company.project.service.SysStoreService;



/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-05 17:31:11
 */
@Controller
@RequestMapping("/")
public class SysStoreController {
    @Autowired
    private SysStoreService sysStoreService;
    @Autowired
    private SysStoreMapper sysStoreMapper;
    @Autowired
    private SysProductService sysProductService;
    @Autowired
    private SysProductMapper sysProductMapper;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysStore")
    public String sysStore() {
        return "sysstore/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysStore/add")
    @RequiresPermissions("sysStore:add")
    @ResponseBody
    public DataResult add(@RequestBody SysStoreEntity sysStore){
        sysStoreService.save(sysStore);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysStore/delete")
    @RequiresPermissions("sysStore:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysStoreService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysStore/update")
    @RequiresPermissions("sysStore:update")
    @ResponseBody
    public DataResult update(@RequestBody SysStoreEntity sysStore){
        LambdaQueryWrapper<SysStoreEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysStoreEntity::getStoreId, sysStore.getStoreId());
//        sysStoreMapper.update(sysStore,queryWrapper);
        sysStoreService.updateById(sysStore);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysStore/listByPage")
    @RequiresPermissions("sysStore:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysStoreEntity sysStore){
        Page page = new Page(sysStore.getPage(), sysStore.getLimit());
        LambdaQueryWrapper<SysStoreEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysStoreEntity::getId, sysStore.getId());
        IPage<SysStoreEntity> iPage = sysStoreService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    @ApiOperation(value = "查询仓库产品数据")
    @GetMapping("sysStore/findProductList")
    @ResponseBody
    public DataResult findCategoryList(){
        List<SysProductEntity> list =sysProductMapper.selectList(null);
//        for(int i=0;i<list.size();i++){
//            if(list.get(i).getProductStatus().equals("2")){
//                list.remove(i);
//            }
//        }
        return DataResult.success(list);
    }
    @ApiOperation(value = "仓库查找")
    @PostMapping("sysStore/search")
    @ResponseBody
    public DataResult search(@RequestBody storeSearchVo storeSearchVo){

//        Page page = new Page(shopSearchVo.getPage(), shopSearchVo.getLimit());
//        LambdaQueryWrapper<SysShopEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        //queryWrapper.eq(SysShopEntity::getShopPeople,shopPeople);
//        IPage<SysShopEntity> iPage = sysShopService.page(page, queryWrapper);
        //System.out.println(storeSearchVo.toString());
       // List<SysStoreEntity> records = sysStoreService.search(storeSearchVo).getRecords();
       // System.out.println(records);
        System.out.println(storeSearchVo.toString());
        return DataResult.success(sysStoreService.search(storeSearchVo));
    }
}
