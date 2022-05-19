package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 
 *
 * @author huguanghong
 * @email 994138979@mail.com
 * @date 2022-04-11 14:47:59
 */
@Data
@TableName("sys_store_io")
public class SysStoreIoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 仓库id
	 */
	@TableField("storeio_storeid")
	private String storeioStoreid;

	/**
	 * 操作类型
	 */
	@TableField("storeio_type")
	private String storeioType;

	/**
	 * 操作原因
	 */
	@TableField("storeio_reason")
	private String storeioReason;

	/**
	 * 操作员
	 */
	@TableField("storeio_operater")
	private String storeioOperater;

	/**
	 * 出入库数量
	 */
	@TableField("storeio_num")
	private Double storeioNum;

	/**
	 * 操作描述
	 */
	@TableField("storeio_desc")
	private String storeioDesc;

	/**
	 * 操作时间
	 */
	@TableField("storeio_time")
	private Date storeioTime;


}
