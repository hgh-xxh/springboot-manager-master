package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysLinkOperateMapper;
import com.company.project.entity.SysLinkOperateEntity;
import com.company.project.service.SysLinkOperateService;


@Service("sysLinkOperateService")
public class SysLinkOperateServiceImpl extends ServiceImpl<SysLinkOperateMapper, SysLinkOperateEntity> implements SysLinkOperateService {


}