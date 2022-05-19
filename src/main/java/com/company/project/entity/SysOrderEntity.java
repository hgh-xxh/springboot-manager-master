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
 * @date 2022-04-12 10:58:10
 */
@Data
@TableName("sys_order")
public class SysOrderEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;



	/**
	 * 出库id
	 */
	@TableField("storeio_id")
	private String storeioId;
	@TableField("store_name")
	private String storeName;
	/**
	 * 经销商id
	 */
	@TableField("shop_id")
	private String shopId;

	/**
	 * 发货商
	 */
	@TableField("order_offer")
	private String orderOffer;

	/**
	 * 发货商联系方式
	 */
	@TableField("order_offerphone")
	private String orderOfferphone;

	/**
	 * 经销商名称
	 */
	@TableField("order_receivename")
	private String orderReceivename;

	/**
	 * 经销商电话
	 */
	@TableField("order_receivephone")
	private String orderReceivephone;

	/**
	 * 收货地址
	 */
	@TableField("order_receiveadress")
	private String orderReceiveadress;

	/**
	 * 产品id
	 */
	@TableField("order_productid")
	private String orderProductid;
	@TableField("order_productname")
	private String orderProductName;
	/**
	 * 订单产品数量
	 */
	@TableField("order_number")
	private String orderNumber;

	/**
	 * 订单总价
	 */
	@TableField("order_price")
	private Double orderPrice;

	/**
	 * 订单状态
	 */
	@TableField("order_state")
	private String orderState;

	/**
	 * 订单时间
	 */
	@TableField("order_time")
	private Date orderTime;

	/**
	 * 订单补充描述
	 */
	@TableField("order_desc")
	private String orderDesc;


}
