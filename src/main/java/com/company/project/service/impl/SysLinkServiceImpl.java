package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysLinkMapper;
import com.company.project.entity.SysLinkEntity;
import com.company.project.service.SysLinkService;


@Service("sysLinkService")
public class SysLinkServiceImpl extends ServiceImpl<SysLinkMapper, SysLinkEntity> implements SysLinkService {


}