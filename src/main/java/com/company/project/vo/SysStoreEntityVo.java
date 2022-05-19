package com.company.project.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2022-04-05 16:24:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysStoreEntityVo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */

	private Integer storeId;

	/**
	 * 仓库名称
	 */
	private String storeName;

	/**
	 * 仓库分类
	 */

	private String storeCategory;

	/**
	 * 仓库产品id
	 */
	private Integer storeProductName;

	/**
	 * 仓库管理员
	 */

	private String storeManager;

	/**
	 * 仓库地址
	 */

	private String storeAddress;

	/**
	 * 管理员联系方式
	 */
	private String storeManagerPhone;

	/**
	 * 库存
	 */

	private String storeReserveNum;

	/**
	 * 仓库单位
	 */
	private String storeUnit;


}
