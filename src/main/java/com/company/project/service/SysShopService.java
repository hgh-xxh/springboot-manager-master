package com.company.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.SysShopEntity;
import com.company.project.vo.req.ShopSearchVo;

/**
 * 经销商
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2022-02-13 10:27:46
 */
public interface SysShopService extends IService<SysShopEntity> {
    IPage<SysShopEntity> search(ShopSearchVo shopSearchVo);
}

