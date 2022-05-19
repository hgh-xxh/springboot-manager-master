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
 * @date 2022-04-11 16:39:36
 */
@Data
@TableName("sys_process")
public class SysProcessEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 仓库id
	 */
	@TableField("store_id")
	private Integer storeId;

	/**
	 * 加工类型
	 */
	@TableField("process_type")
	private String processType;

	/**
	 * 加工原因
	 */
	@TableField("process_reason")
	private String processReason;

	/**
	 * 操作工
	 */
	@TableField("process_operater")
	private String processOperater;

	/**
	 * 描述
	 */
	@TableField("process_desc")
	private String processDesc;

	/**
	 * 状态
	 */
	@TableField("process_state")
	private String processState;

	/**
	 * 时间
	 */
	@TableField("process_time")
	private Date processTime;


}
