package com.company.project.mapper;

import com.company.project.entity.SysProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 系统产品
 * 

 */
@Repository
public interface SysProductMapper extends BaseMapper<SysProductEntity> {
    @Select("update sys_product set product_image=#{src} where product_id=#{id}")
	public void updateProductImage(String id,String src);
}
