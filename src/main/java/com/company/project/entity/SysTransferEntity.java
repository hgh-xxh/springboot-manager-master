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
 * @date 2022-04-13 15:57:17
 */
@Data
@TableName("sys_transfer")
public class SysTransferEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 物流id
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 物流信息所属订单id
	 */
	@TableField("transfer_orderId")
	private Integer transferOrderId;

	/**
	 * 物流信息描述
	 */
	@TableField("transfer_desc")
	private String transferDesc;

	/**
	 * 物流信息时间
	 */
	@TableField("transfer_time")
	private Date transferTime;

	/**
	 * 物流信息状态
	 */
	@TableField("transfer_state")
	private String transferState;


}
