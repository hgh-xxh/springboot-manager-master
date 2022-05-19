package com.company.project.mapper;

import com.company.project.common.utils.PasswordUtils;
import com.company.project.entity.SysCategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 
 *
 */
@Repository
public interface SysCategoryMapper extends BaseMapper<SysCategoryEntity> {
    @Select("delete from sys_product where category_id = #{id}")
    public void deleteProductByCategoryId(String id);
}
