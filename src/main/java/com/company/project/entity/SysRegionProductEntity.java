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
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-14 20:24:18
 */
@Data
@TableName("sys_region_product")
public class SysRegionProductEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private Integer id;

	/**
	 * 种植地区id
	 */
	@TableField("region_id")
	private Integer regionId;

	/**
	 * 产品id
	 */
	@TableField("product_id")
	private Integer productId;


}
