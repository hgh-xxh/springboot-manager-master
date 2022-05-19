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

 */
@Data
@TableName("sys_region")
public class SysRegionEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 种植地区id
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 种植地区名称
	 */
	@TableField("region_name")
	private String regionName;

	/**
	 * 地区位置
	 */
	@TableField("region_address")
	private String regionAddress;

	/**
	 * 地区描述
	 */
	@TableField("region_describe")
	private String regionDescribe;

	/**
	 * 种植农作物数量
	 */
	@TableField("region_num")
	private Integer regionNum;

	/**
	 * 年平均气温
	 */
	@TableField("region_avgt")
	private String regionAvgt;

	/**
	 * 昼夜温差
	 */
	@TableField("region_dift")
	private String regionDift;

	/**
	 * 年降雨量
	 */
	@TableField("region_rain")
	private String regionRain;


}
