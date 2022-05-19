package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-05 17:31:11
 */
@Data
@TableName("sys_store")
public class SysStoreEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "store_id",type = IdType.AUTO)
	private Integer storeId;

	/**
	 * 仓库名称
	 */
	@TableField("store_name")
	private String storeName;

	/**
	 * 仓库分类
	 */
	@TableField("store_category")
	private String storeCategory;

	/**
	 * 仓库产品id
	 */
	@TableField("store_product_name")
	private String storeProductName;

	/**
	 * 仓库管理员
	 */
	@TableField("store_manager")
	private String storeManager;

	/**
	 * 仓库地址
	 */
	@TableField("store_address")
	private String storeAddress;

	/**
	 * 管理员联系方式
	 */
	@TableField("store_manager_phone")
	private String storeManagerPhone;

	/**
	 * 库存
	 */
	@TableField("store_reserve_num")
	private String storeReserveNum;

	/**
	 * 仓库单位
	 */
	@TableField("store_unit")
	private String storeUnit;


}
