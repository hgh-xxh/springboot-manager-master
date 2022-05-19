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
@TableName("sys_link")
public class SysLinkEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 环节id
	 */
	@TableId(value="id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 环节名称
	 */
	@TableField("link_name")
	private String linkName;

	/**
	 * 环节序号
	 */
	@TableField("link_order")
	private Long linkOrder;

	/**
	 * 环节描述
	 */
	@TableField("link_desc")
	private String linkDesc;


}
