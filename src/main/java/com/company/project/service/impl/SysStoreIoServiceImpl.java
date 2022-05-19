package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.entity.BaseEntity;
import com.company.project.entity.SysShopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysStoreIoMapper;
import com.company.project.entity.SysStoreIoEntity;
import com.company.project.service.SysStoreIoService;

import javax.swing.text.html.parser.Entity;


@Service("sysStoreIoService")
public class SysStoreIoServiceImpl extends ServiceImpl<SysStoreIoMapper, SysStoreIoEntity> implements SysStoreIoService {
    @Autowired
    private SysStoreIoMapper sysStoreIoMapper;
    public IPage<SysStoreIoEntity> search(String storeioOperater,String storeioNum){
        BaseEntity b=new BaseEntity();
        Page page = new Page(b.getPage(), b.getLimit());
        LambdaQueryWrapper<SysStoreIoEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!storeioOperater.isEmpty()) {
            queryWrapper.like(SysStoreIoEntity::getStoreioOperater, storeioOperater);
        }
        if (!storeioNum.isEmpty()) {
            queryWrapper.like(SysStoreIoEntity::getStoreioNum, storeioNum);
        }
        IPage<SysStoreIoEntity> iPage =sysStoreIoMapper.selectPage(page,queryWrapper);

        return iPage;
    }

}