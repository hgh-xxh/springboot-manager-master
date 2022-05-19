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
 * @date 2022-04-13 18:59:29
 */
@Data
@TableName("sys_trace")
public class SysTraceEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 订单id
	 */
	@TableField("trace_orderid")
	private Integer traceOrderid;

	/**
	 * 二维码
	 */
	@TableField("trace_qrcode_src")
	private String traceQrcodeSrc;

	/**
	 * 创造时间
	 */
	@TableField("trace_createtime")
	private Date traceCreatetime;


}
