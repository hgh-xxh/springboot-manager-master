package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysCategoryMapper;
import com.company.project.entity.SysCategoryEntity;
import com.company.project.service.SysCategoryService;


@Service("sysCategoryService")
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryMapper, SysCategoryEntity> implements SysCategoryService {


}