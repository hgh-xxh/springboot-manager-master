package com.company.project.controller.baseInfo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.code.QRCodeEncoder;
import com.company.project.common.config.FileUploadProperties;
import com.company.project.common.exception.BusinessException;
import com.company.project.common.utils.DateUtils;
import com.company.project.entity.*;
import com.company.project.mapper.*;
import com.company.project.service.HttpSessionService;
import com.company.project.service.RedisService;
import com.company.project.service.impl.SysFilesServiceImpl;
import com.company.project.vo.resp.storeIoVO;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.util.*;

import com.company.project.common.utils.DataResult;

import com.company.project.service.SysLinkService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 *

 */
@Controller
@RequestMapping("/")
public class test {
    @Resource
    private FileUploadProperties fileUploadProperties;
    @Autowired
    private SysFilesServiceImpl sysFilesService;

    @Autowired
    private SysOrderMapper sysOrderMapper;
    @Autowired
    private SysProductMapper sysProductMapper;
    @Autowired
    private SysLinkMapper sysLinkMapper;
    @Autowired
    private SysLinkOperateMapper sysLinkOperateMapper;
    @Autowired
    private SysShopMapper sysShopMapper;
    @Autowired
    private SysStoreIoMapper sysStoreIoMapper;
    @Autowired
    private SysTransferMapper sysTransferMapper;
    @Autowired
    private SysRegionMapper sysRegionMapper;
    @Autowired
    private SysStoreMapper sysStoreMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRegionProductMapper sysRegionProductMapper;
    @Autowired
    private SysTraceMapper sysTraceMapper;
    /**
     * ???????????????
     */
    @GetMapping("test/a")
    @ResponseBody
    public DataResult sysLink(@RequestParam String orderId) {
        //???????????????
        String createTime = DateUtils.format(new Date(), DateUtils.DATEPATTERN);
        String newPath = fileUploadProperties.getPath() + createTime +"/";
        File uploadDirectory = new File(newPath);
        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }
        try {
            String fileName =UUID.randomUUID().toString().replace("-", "")+".png";
            //id???filename???????????????????????????
            //String fileNameNew = UUID.randomUUID().toString().replace("-", "") + getFileType(fileName);
            String newFilePathName = newPath + fileName;
            System.out.println(newFilePathName+" "+newPath);
            String url = fileUploadProperties.getUrl() + "/" + createTime + "/" + fileName;
            String content="http://127.0.0.1:8080/test/b?orderId="+orderId;
            QRCodeEncoder encoder = new QRCodeEncoder();
            encoder.encoderQRCode(
                    content, //??????
                    newFilePathName, //????????????
                    "png",   //????????????
                    "UTF-8", //????????????
                    4,      //??????
                    null,   //??????
                    null, //?????????
                    null, //?????????
                    6,    //????????????
                    null, //????????????
                    null  //??????????????????
            );
            //??????????????????
            SysFilesEntity sysFilesEntity = new SysFilesEntity();
            sysFilesEntity.setFileName(fileName);
            sysFilesEntity.setFilePath(newFilePathName);
            sysFilesEntity.setUrl(url);
            sysFilesService.save(sysFilesEntity);
        } catch (Exception e) {
            throw new BusinessException("??????????????????");
        }
        return DataResult.success();


    }
    @RequestMapping("test/b")
    public String erweima(@RequestParam String orderId,HttpServletRequest request ) {
       // System.out.println(orderId+"aaa");
        HttpSession session = request.getSession();
        session.setAttribute("orderId",orderId);
        LambdaQueryWrapper<SysTraceEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysTraceEntity::getTraceOrderid, orderId);
        //System.out.println(sysTraceMapper.selectList(queryWrapper));
        //System.out.println("hahaha"+sysTraceMapper.selectList(queryWrapper).size());
        if(sysTraceMapper.selectList(queryWrapper).size()!=0){
            return "trace/index";
        }
        else {
            return "error/404";
        }
        //System.out.println(session.getAttribute("orderId"));

    }
    @RequestMapping("test/getOrderId")
    @ResponseBody
    public DataResult getOrderId(HttpServletRequest request) {
        String oid=String.valueOf(request.getSession().getAttribute("orderId"));
       // System.out.println(oid+"aaaaaaaaaa");
        return DataResult.success(oid);
    }
    @GetMapping("test/getInfo")
    @ResponseBody
    public DataResult getInfo(@RequestParam String orderId) {
        System.out.println(orderId);
        List<Object> list=new ArrayList<>();
        //????????????
        SysOrderEntity order=sysOrderMapper.selectById(orderId);
        //????????????
        SysProductEntity product=sysProductMapper.selectById(order.getOrderProductid());

        LambdaQueryWrapper<SysRegionProductEntity> queryWrapper1 = Wrappers.lambdaQuery();
        queryWrapper1.eq(SysRegionProductEntity::getProductId,product.getProductId());
        SysRegionProductEntity sysRegionProductEntity=sysRegionProductMapper.selectOne(queryWrapper1);
        SysRegionEntity regionEntity=sysRegionMapper.selectById(sysRegionProductEntity.getRegionId());
        //????????????
        LambdaQueryWrapper<SysLinkOperateEntity> queryWrapper2 = Wrappers.lambdaQuery();
        queryWrapper2.eq(SysLinkOperateEntity::getOrderId,orderId);
        List<SysLinkOperateEntity> linkList=sysLinkOperateMapper.selectList(queryWrapper2);
//        SysRegionEntity regionEntity=sysRegionMapper.selectById(sysRegionProductEntity.getRegionId());
//        List<SysLinkEntity> linkList=sysLinkMapper.selectList(null);
        //????????????
        LambdaQueryWrapper<SysTransferEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysTransferEntity::getTransferOrderId,orderId);
        List<SysTransferEntity> transferList=sysTransferMapper.selectList(queryWrapper);
        //?????????
        SysShopEntity shop=sysShopMapper.selectById(order.getShopId());
        list.add(product);
        list.add(regionEntity);
        list.add(linkList);
        list.add(transferList);
        list.add(shop);
        System.out.println(list);
        return DataResult.success(list);
    }
    @RequestMapping("test/getBigCreen/OrderInfo")
    @ResponseBody
    public DataResult getBigCreenInfo(HttpServletRequest request) {
        List<SysOrderEntity> orderList=sysOrderMapper.selectList(null);
        double ans[][]=new double[4][2];
        DateUtils dateUtils=new DateUtils();
        for(SysOrderEntity order:orderList){
            Date now=new Date();
            Date orderTime=order.getOrderTime();
            long days =(now.getTime()-orderTime.getTime())/1000/3600/24;
            //System.out.println("days "+days);
            if(days<1){
                ans[3][0]+=1;
                ans[3][1]+=order.getOrderPrice();
            }
            if(days<30){
                ans[2][0]+=1;
                ans[2][1]+=order.getOrderPrice();
            }
            if(days<90){
                ans[1][0]+=1;
                ans[1][1]+=order.getOrderPrice();
            }
            if(days<365){
                ans[0][0]+=1;
                ans[0][1]+=order.getOrderPrice();
            }
        }
        return DataResult.success(ans);
    }
    @RequestMapping("test/getBigCreen/moneyInfo")
    @ResponseBody
    public DataResult moneyInfo(HttpServletRequest request) {
        List<Object> res=new ArrayList<>();
        List<SysOrderEntity> orderList=sysOrderMapper.selectList(null);
        Map<String,Integer> mp=new HashMap<>();
        double ans[]=new double[13];
        int year=new Date().getYear();
        for(SysOrderEntity order:orderList){
            String key=order.getOrderProductName();
            if(mp.containsKey(key)){
                mp.replace(key,mp.get(key)+1);
            }
            else {
                mp.put(key,1);
            }
            Date orderTime=order.getOrderTime();
            //System.out.println(year+" "+orderTime.getYear()+" "+orderTime.getMonth());
            if(year==orderTime.getYear()){
                ans[orderTime.getMonth()+1]+=order.getOrderPrice();
            }
        }
        mp=sortMap(mp);

        res.add(ans);
        int i=0;
        for(String key:mp.keySet()){
            if(i==4) break;
            i++;
            res.add(key);
            res.add(String.valueOf(mp.get(key)*1.0/orderList.size()*100).substring(0,4));
        }
        res.add(orderList.size());
        res.add(sysStoreMapper.selectList(null).size());
        res.add(sysUserMapper.selectList(null).size());
        res.add(sysProductMapper.selectList(null).size());
        // System.out.println(oid+"aaaaaaaaaa");
        return DataResult.success(res);
    }
    @RequestMapping("test/getBigCreen/MapInfo")
    @ResponseBody
    public DataResult getMapInfo(HttpServletRequest request) {
        List<SysOrderEntity> orderList=sysOrderMapper.selectList(null);
        Map<String,Integer> mp=new HashMap<>();

        int cnt=0;
        for(SysOrderEntity order:orderList){
            SysShopEntity sysShopEntity = sysShopMapper.selectById(order.getShopId());
            if(!mp.containsKey(sysShopEntity.getShopCity())){
                mp.put(sysShopEntity.getShopCity(),1);
            }
            else {
                mp.replace(sysShopEntity.getShopCity(),mp.get(sysShopEntity.getShopCity())+1);
            }
            //System.out.println(order.getShopId()+" "+sysShopEntity.toString());
//            res[cnt][0]=sysShopEntity.getShopCity();
//            res[cnt][1]=order.getOrderNumber();
//            cnt++;
        }
        String res[][]=new String[mp.size()][2];
        for(String key:mp.keySet()){
            res[cnt][0]=key;
            res[cnt][1]=String.valueOf(mp.get(key));
            cnt++;
        }
        return DataResult.success(res);
    }
    @RequestMapping("test/getBigCreen/getStoreIo")
    @ResponseBody
    public DataResult getStoreIo(HttpServletRequest request) {
        List<SysStoreIoEntity> list=sysStoreIoMapper.selectList(null);
        List<SysStoreEntity> list1=sysStoreMapper.selectList(null);
        List<storeIoVO> ans=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list1.size();j++){
                //System.out.println(list.get(i).getStoreioStoreid()+"  "+list1.get(j).getStoreId()+" "+String.valueOf(list.get(i).getStoreioStoreid()).equals(String.valueOf(list1.get(j).getStoreId())));
                if(String.valueOf(list.get(i).getStoreioStoreid()).equals(String.valueOf(list1.get(j).getStoreId()))){
                    SysStoreIoEntity ioEntity=list.get(i);
                    storeIoVO a=new storeIoVO(list1.get(j).getStoreName(),ioEntity.getStoreioType(),ioEntity.getStoreioNum(),ioEntity.getStoreioTime());
                    ans.add(a);
                }
            }
        }
        return DataResult.success(ans);
    }
    public static Map<String, Integer> sortMap(Map<String, Integer> map) {
        //??????Map???entrySet??????????????????list????????????
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        //??????Collections???sort?????????list??????
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //??????????????????????????????
                return o2.getValue() - o1.getValue();
            }
        });
        //??????????????????list??????????????????LinkedHashMap???????????????LinkedHashMap?????????????????????????????????
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String,Integer> e : entryList
        ) {
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }
}

