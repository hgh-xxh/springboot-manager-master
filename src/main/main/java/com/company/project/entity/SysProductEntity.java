package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 系统产品
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2022-02-11 12:25:31
 */
@Data
@TableName("sys_product")
public class SysProductEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("product_id")
	private Integer productId;

	/**
	 * 
	 */
	@TableField("category_id")
	private String categoryId;

	/**
	 * 
	 */
	@TableField("product_name")
	private String productName;

	/**
	 * 
	 */
	@TableField("product_status")
	private String productStatus;

	/**
	 * 
	 */
	@TableField("product_remark")
	private String productRemark;

	/**
	 * 
	 */
	@TableField("product_taste")
	private String productTaste;

	/**
	 * 标准号
	 */
	@TableField("product_stdNumber")
	private String productStdNumber;

	/**
	 * 使用方法
	 */
	@TableField("product_fun")
	private String productFun;

	/**
	 * 储存
	 */
	@TableField("product_storage")
	private String productStorage;

	/**
	 * 
	 */
	@TableField("product_image")
	private String productImage;


}
