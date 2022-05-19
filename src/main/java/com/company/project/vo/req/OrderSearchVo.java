package com.company.project.vo.req;

import com.company.project.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderSearchVo extends BaseEntity implements Serializable {

    private String storeName;
    private String orderProductName;
    private String orderReceivename;
    private String orderState;

}
