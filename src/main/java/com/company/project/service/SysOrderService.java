package com.company.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.SysOrderEntity;
import com.company.project.entity.SysShopEntity;
import com.company.project.vo.req.OrderSearchVo;
import com.company.project.vo.req.ShopSearchVo;

/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-12 10:58:10
 */
public interface SysOrderService extends IService<SysOrderEntity> {
    IPage<SysOrderEntity> search(OrderSearchVo orderSearchVo);
}

