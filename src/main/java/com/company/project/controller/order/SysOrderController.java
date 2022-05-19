package com.company.project.controller.order;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.*;
import com.company.project.mapper.*;
import com.company.project.vo.req.OrderSearchVo;
import com.company.project.vo.req.ShopSearchVo;
import com.company.project.vo.resp.orderStoreVO;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.service.SysOrderService;



/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-12 10:58:10
 */
@Controller
@RequestMapping("/")
public class SysOrderController {
    @Autowired
    private SysOrderService sysOrderService;
    @Autowired
    private SysProductMapper sysProductMapper;
    @Autowired
    private SysStoreIoMapper sysStoreIoMapper;
    @Autowired
    private SysStoreMapper sysStoreMapper;
    @Autowired
    private SysShopMapper sysShopMapper;
    @Autowired
    private SysOrderMapper sysOrderMapper;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysOrder")
    public String sysOrder() {
        return "sysorder/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysOrder/add")
    @RequiresPermissions("sysOrder:add")
    @ResponseBody
    public DataResult add(@RequestBody SysOrderEntity sysOrder){
        //System.out.println(sysOrder.toString());
        //SysOrderEntity(id=null, storeioId=14, storeName=null, shopId=9, orderOffer=, orderOfferphone=, orderReceivename=null, orderReceivephone=null, orderReceiveadress=null,
        // orderProductid=3, orderProductName=null, orderNumber=null, orderPrice=null, orderState=发货中, orderTime=null, orderDesc=)
        SysStoreIoEntity sysStoreIoEntity=sysStoreIoMapper.selectById(sysOrder.getStoreioId());
        sysOrder.setStoreName(sysStoreMapper.selectById(sysStoreIoEntity.getStoreioStoreid()).getStoreName());
        Double orderNum=sysStoreIoEntity.getStoreioNum();
        //shop
        SysShopEntity sysShopEntity=sysShopMapper.selectById(sysOrder.getShopId());
        sysOrder.setOrderReceivename(sysShopEntity.getShopPeople());
        sysOrder.setOrderReceiveadress(sysShopEntity.getShopProvince()+sysShopEntity.getShopCity()+sysShopEntity.getShopCounty()+sysShopEntity.getShopAddress());
        sysOrder.setOrderReceivephone(sysShopEntity.getShopPhone());
        //product
        SysProductEntity sysProductEntity=sysProductMapper.selectById(sysOrder.getOrderProductid());
        sysOrder.setOrderProductName(sysProductEntity.getProductName());
        sysOrder.setOrderNumber(String.valueOf(orderNum));
        sysOrder.setOrderPrice(Double.valueOf(orderNum)*Double.valueOf(sysProductEntity.getProductPrice()));
        sysOrder.setOrderTime(new Date());
        sysOrderService.save(sysOrder);
        return DataResult.success();
    }
    @ApiOperation(value = "删除")
    @DeleteMapping("sysOrder/delete")
    @RequiresPermissions("sysOrder:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysOrderService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysOrder/update")
    @RequiresPermissions("sysOrder:update")
    @ResponseBody
    public DataResult update(@RequestBody SysOrderEntity sysOrder){
        System.out.println(sysOrder.toString());
        //sysOrderService.updateById(sysOrder);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysOrder/listByPage")
    @RequiresPermissions("sysOrder:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody SysOrderEntity sysOrder){
        Page page = new Page(sysOrder.getPage(), sysOrder.getLimit());
        LambdaQueryWrapper<SysOrderEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(SysOrderEntity::getId, sysOrder.getId());
        IPage<SysOrderEntity> iPage = sysOrderService.page(page, queryWrapper);
        //System.out.println(iPage);
//        if(iPage.getRecords()!=null){
//            for(SysOrderEntity item:iPage.getRecords()){
//                item.setOrderProductName(sysProductMapper.selectById(item.getOrderProductid()).getProductName());
//                String storeId=sysStoreIoMapper.selectById(item.getStoreioId()).getStoreioStoreid();
//                item.setStoreName(sysStoreMapper.selectById(storeId).getStoreName());
//            }
//        }

        return DataResult.success(iPage);
    }
    @GetMapping("sysOrder/init1")
    @ResponseBody
    public DataResult init1(){

        //出库id,仓库名称，产品名称，出库数量
        List<SysStoreEntity> storeList =sysStoreMapper.selectList(null);
        List<SysStoreIoEntity> storeIoList=sysStoreIoMapper.selectList(null);
//        System.out.println(storeList);
//        System.out.println(storeIoList);
//        System.out.println("------------------------");
        List<orderStoreVO> list1=new ArrayList<>();
        for(SysStoreIoEntity ioItem:storeIoList){
            for(SysStoreEntity storeItem:storeList){
               // System.out.println(ioItem.getStoreioStoreid().equals(String.valueOf(storeItem.getStoreId())) +"   "+ioItem.getStoreioStoreid()+"  "+storeItem.getStoreId());
                if(ioItem.getStoreioStoreid().equals(String.valueOf(storeItem.getStoreId()))){
                    orderStoreVO a=new orderStoreVO();
                    a.setdata(ioItem,storeItem);
//                    a.setSysStoreEntity(storeItem);
//                    a.setSysStoreIoEntity(ioItem);
                   // System.out.println(storeItem.toString()+" "+ioItem.toString());
                    list1.add(a);
                }
            }
        }
        //System.out.println(list1);
        return DataResult.success(list1);
    }
    @GetMapping("sysOrder/init2")
    @ResponseBody
    public DataResult init2(){
        return DataResult.success(sysShopMapper.selectList(null));
    }
    @GetMapping("sysOrder/init3")
    @ResponseBody
    public DataResult init3(){
        return DataResult.success(sysProductMapper.selectList(null));
    }

    @PostMapping("sysOrder/search")
    @ResponseBody
    public DataResult search(@RequestBody OrderSearchVo orderSearchVo){
        System.out.println(orderSearchVo.toString()+"hhhhhhhh");

       // List<SysOrderEntity> records = sysOrderService.search(orderSearchVo).getRecords();

        return DataResult.success(sysOrderService.search(orderSearchVo));
    }
}
