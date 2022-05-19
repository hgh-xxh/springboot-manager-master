package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 系统产品
 *

 */
@Data
@TableName("sys_product")
public class SysProductEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value="product_id",type = IdType.AUTO)
	private Integer productId;
	/**
	 * 单价（元/kg）
	 */
	@TableField("product_price")
	private Double productPrice;
	/**
	 * 
	 */
	@TableField("category_id")
	private Integer categoryId;
	@TableField(exist = false)
	private String categoryName;

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


	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;
}
