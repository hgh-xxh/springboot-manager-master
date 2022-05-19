package com.company.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.SysProcessEntity;

/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-11 15:47:09
 */
public interface SysProcessService extends IService<SysProcessEntity> {

    IPage<SysProcessEntity> search(String processType);
}

