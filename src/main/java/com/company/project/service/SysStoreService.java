package com.company.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.SysStoreEntity;
import com.company.project.vo.req.storeSearchVo;

/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-05 17:31:11
 */
public interface SysStoreService extends IService<SysStoreEntity> {

    IPage<SysStoreEntity> search(storeSearchVo storeSearchVo);
}

