package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.SysTransferMapper;
import com.company.project.entity.SysTransferEntity;
import com.company.project.service.SysTransferService;


@Service("sysTransferService")
public class SysTransferServiceImpl extends ServiceImpl<SysTransferMapper, SysTransferEntity> implements SysTransferService {


}