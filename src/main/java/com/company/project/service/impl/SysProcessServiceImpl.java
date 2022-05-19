package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.entity.BaseEntity;
import com.company.project.entity.SysStoreIoEntity;
import com.company.project.mapper.SysStoreIoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysProcessMapper;
import com.company.project.entity.SysProcessEntity;
import com.company.project.service.SysProcessService;


@Service("sysProcessService")
public class SysProcessServiceImpl extends ServiceImpl<SysProcessMapper, SysProcessEntity> implements SysProcessService {
    @Autowired
    private SysProcessMapper sysProcessMapper;
    public IPage<SysProcessEntity> search(String processType){
        BaseEntity b=new BaseEntity();
        Page page = new Page(b.getPage(), b.getLimit());
        LambdaQueryWrapper<SysProcessEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!processType.isEmpty()) {
            queryWrapper.like(SysProcessEntity::getProcessType, processType);
        }

        IPage<SysProcessEntity> iPage =sysProcessMapper.selectPage(page,queryWrapper);

        return iPage;
    }

}