package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.entity.SysShopEntity;
import com.company.project.mapper.SysShopMapper;
import com.company.project.vo.req.ShopSearchVo;
import com.company.project.vo.req.storeSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysStoreMapper;
import com.company.project.entity.SysStoreEntity;
import com.company.project.service.SysStoreService;


@Service("sysStoreService")
public class SysStoreServiceImpl extends ServiceImpl<SysStoreMapper, SysStoreEntity> implements SysStoreService {

    @Autowired
    private SysShopMapper sysShopMapper;
    @Autowired
    private SysStoreMapper sysStoreMapper;

    public  IPage<SysStoreEntity> search(storeSearchVo vo){
        Page page = new Page(vo.getPage(), vo.getLimit());
        LambdaQueryWrapper<SysStoreEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!vo.getStoreName().isEmpty()) {
            queryWrapper.like(SysStoreEntity::getStoreName, vo.getStoreName());
        }
        if (!vo.getStoreManager().isEmpty()) {
            queryWrapper.like(SysStoreEntity::getStoreManager, vo.getStoreManager());
        }
        if (!vo.getStoreId().isEmpty()) {
            queryWrapper.like(SysStoreEntity::getStoreId, vo.getStoreId());
        }
        IPage<SysStoreEntity> iPage =sysStoreMapper.selectPage(page,queryWrapper);

        return iPage;
    }
}