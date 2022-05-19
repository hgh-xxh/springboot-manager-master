package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysProductMapper;
import com.company.project.entity.SysProductEntity;
import com.company.project.service.SysProductService;


@Service("sysProductService")
public class SysProductServiceImpl extends ServiceImpl<SysProductMapper, SysProductEntity> implements SysProductService {


}