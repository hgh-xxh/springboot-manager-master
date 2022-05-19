package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 *

 */
@Data
@TableName("sys_category")
public class SysCategoryEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类Id
	 */
	@TableId(value = "category_id",type = IdType.AUTO)
	private Integer categoryId;

	/**
	 * 分类名
	 */
	@TableField("category_name")
	private String categoryName;

	/**
	 * 描述
	 */
	@TableField("category_desc")
	private String categoryDesc;

	/**
	 * 状态
	 */
	@TableField("category_status")
	private String categoryStatus;

	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;

}
