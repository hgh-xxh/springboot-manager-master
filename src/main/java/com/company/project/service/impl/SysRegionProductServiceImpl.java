package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysRegionProductMapper;
import com.company.project.entity.SysRegionProductEntity;
import com.company.project.service.SysRegionProductService;


@Service("sysRegionProductService")
public class SysRegionProductServiceImpl extends ServiceImpl<SysRegionProductMapper, SysRegionProductEntity> implements SysRegionProductService {


}