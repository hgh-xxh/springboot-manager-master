package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysRegionMapper;
import com.company.project.entity.SysRegionEntity;
import com.company.project.service.SysRegionService;


@Service("sysRegionService")
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper, SysRegionEntity> implements SysRegionService {


}