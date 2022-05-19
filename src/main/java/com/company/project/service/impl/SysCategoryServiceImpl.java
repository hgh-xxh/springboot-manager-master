package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.entity.SysGenerator;
import com.company.project.entity.SysShopEntity;
import com.company.project.vo.req.sysCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysCategoryMapper;
import com.company.project.entity.SysCategoryEntity;
import com.company.project.service.SysCategoryService;

import java.util.List;


@Service("sysCategoryService")
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryMapper, SysCategoryEntity> implements SysCategoryService {
   @Autowired SysCategoryMapper mapper;
    public IPage<SysCategoryEntity> search(sysCategoryVo vo){
        Page page = new Page(vo.getPage(), vo.getLimit());
        LambdaQueryWrapper<SysCategoryEntity> queryWrapper = Wrappers.lambdaQuery();
        if(!vo.getCategoryName().isEmpty())
        queryWrapper.eq(SysCategoryEntity::getCategoryName,vo.getCategoryName() );
        IPage<SysCategoryEntity> iPage =mapper.selectPage(page,queryWrapper);
        return iPage;
    }
    public void deleteProductByCategoryId(String id){
        mapper.deleteProductByCategoryId(id);
    }
}