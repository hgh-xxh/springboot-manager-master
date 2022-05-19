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

 */
@Data
@TableName("sys_link_operate")
public class SysLinkOperateEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 环节名称
	 */
	@TableField("link_name")
	private String linkName;

	@TableField("order_id")
	private String orderId;
	/**
	 * 操作员
	 */
	@TableField("link_operater")
	private String linkOperater;

	/**
	 * 操作时间
	 */
	@TableField("link_date")
	private Date linkDate;

	/**
	 * 操作描述
	 */
	@TableField("link_desc")
	private String linkDesc;


}
