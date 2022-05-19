package com.company.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.SysStoreIoEntity;

/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-11 14:47:59
 */
public interface SysStoreIoService extends IService<SysStoreIoEntity> {

    IPage<SysStoreIoEntity> search(String storeioOperater ,String storeioNum);
}

