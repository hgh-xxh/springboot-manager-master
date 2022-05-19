package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2022-02-11 22:10:18
 */
@Data
@TableName("sys_category")
public class SysCategoryEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类Id
	 */
	@TableId("category_id")
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


}
