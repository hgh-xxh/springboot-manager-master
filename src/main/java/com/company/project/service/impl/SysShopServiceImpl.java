package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.entity.SysDept;
import com.company.project.entity.SysUser;
import com.company.project.vo.req.ShopSearchVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysShopMapper;
import com.company.project.entity.SysShopEntity;
import com.company.project.service.SysShopService;

import java.util.List;


@Service("sysShopService")
public class SysShopServiceImpl extends ServiceImpl<SysShopMapper, SysShopEntity> implements SysShopService {
   @Autowired
   private SysShopMapper sysShopMapper;

   public IPage<SysShopEntity> search(ShopSearchVo vo){
       Page page = new Page(vo.getPage(), vo.getLimit());
       LambdaQueryWrapper<SysShopEntity> queryWrapper = Wrappers.lambdaQuery();
       if (!vo.getShopName().isEmpty()) {
           queryWrapper.like(SysShopEntity::getShopName, vo.getShopName());
       }
       if (!vo.getShopPeople().isEmpty()) {
           queryWrapper.like(SysShopEntity::getShopPeople, vo.getShopPeople());
       }
       if (!vo.getShopPhone().isEmpty()) {
           queryWrapper.like(SysShopEntity::getShopPhone, vo.getShopPhone());
       }
       if (!vo.getShopStatus().isEmpty()) {
           queryWrapper.eq(SysShopEntity::getShopStatus, vo.getShopStatus());
       }
       IPage<SysShopEntity> iPage =sysShopMapper.selectPage(page,queryWrapper);

       return iPage;
    }
}