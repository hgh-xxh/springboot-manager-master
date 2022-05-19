package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysTraceMapper;
import com.company.project.entity.SysTraceEntity;
import com.company.project.service.SysTraceService;


@Service("sysTraceService")
public class SysTraceServiceImpl extends ServiceImpl<SysTraceMapper, SysTraceEntity> implements SysTraceService {


}