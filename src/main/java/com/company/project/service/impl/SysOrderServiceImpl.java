package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.entity.SysShopEntity;
import com.company.project.mapper.SysShopMapper;
import com.company.project.vo.req.OrderSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysOrderMapper;
import com.company.project.entity.SysOrderEntity;
import com.company.project.service.SysOrderService;


@Service("sysOrderService")
public class SysOrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrderEntity> implements SysOrderService {
    @Autowired
    private SysOrderMapper sysOrderMapper;
    public IPage<SysOrderEntity> search(OrderSearchVo vo){
        Page page = new Page(vo.getPage(), vo.getLimit());
        LambdaQueryWrapper<SysOrderEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!vo.getOrderProductName().isEmpty()) {
            queryWrapper.like(SysOrderEntity::getOrderProductName, vo.getOrderProductName());
        }
        if (!vo.getStoreName().isEmpty()) {
            queryWrapper.like(SysOrderEntity::getStoreName, vo.getStoreName());
        }


        IPage<SysOrderEntity> iPage =sysOrderMapper.selectPage(page,queryWrapper);

        return iPage;
    }
}