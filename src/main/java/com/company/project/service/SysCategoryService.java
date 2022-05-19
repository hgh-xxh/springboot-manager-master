package com.company.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.SysCategoryEntity;
import com.company.project.entity.SysGenerator;
import com.company.project.vo.req.sysCategoryVo;

/**
 * 
 *
 */
public interface SysCategoryService extends IService<SysCategoryEntity> {

    IPage<SysCategoryEntity> search(sysCategoryVo sysCategoryVo);
    public void deleteProductByCategoryId(String id);
}

