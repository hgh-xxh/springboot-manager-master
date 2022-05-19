package com.company.project.vo.req;

import com.company.project.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
@Data
public class ShopSearchVo extends BaseEntity implements Serializable {
    @ApiModelProperty(value = "商店名称")
    private String shopName;
    @ApiModelProperty(value = "管理人姓名")
    private String shopPeople;
    @ApiModelProperty(value = "请输入手机号")
    private String shopPhone;
    @ApiModelProperty(value = "请选择账号状态")
    private String shopStatus;

}
