package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 经销商
 *

 */
@Data
@TableName("sys_shop")
public class SysShopEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 经销商Id
	 */
	@TableId(value = "shop_id",type = IdType.AUTO)
	private Integer shopId;

	/**
	 * 商店名称
	 */
	@TableField("shop_name")
	private String shopName;

	/**
	 * 管理人
	 */
	@TableField("shop_people")
	private String shopPeople;

	/**
	 * 手机号
	 */
	@TableField("shop_phone")
	private String shopPhone;

	/**
	 * 省
	 */
	@TableField("shop_province")
	private String shopProvince;

	/**
	 * 市
	 */
	@TableField("shop_city")
	private String shopCity;

	/**
	 * 县
	 */
	@TableField("shop_county")
	private String shopCounty;

	/**
	 * 地址
	 */
	@TableField("shop_address")
	private String shopAddress;

	/**
	 * 状态
	 */
	@TableField("shop_status")
	private String shopStatus;

	/**
	 * 描述
	 */
	@TableField("shop_desc")
	private String shopDesc;


}
