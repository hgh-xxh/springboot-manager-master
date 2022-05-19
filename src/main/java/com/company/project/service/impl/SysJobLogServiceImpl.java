package com.company.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.SysJobLogEntity;
import com.company.project.mapper.SysJobLogMapper;
import com.company.project.service.SysJobLogService;
import org.springframework.stereotype.Service;

/**
 * 定时任务 服务类
 *
 */
@Service("sysJobLogService")
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLogEntity> implements SysJobLogService {


}