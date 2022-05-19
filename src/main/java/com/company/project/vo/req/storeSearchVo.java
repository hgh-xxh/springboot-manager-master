package com.company.project.vo.req;
import com.company.project.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
@Data
public class storeSearchVo extends BaseEntity implements Serializable {
    @ApiModelProperty(value = "仓库名称")
    private String storeName;
    @ApiModelProperty(value = "管理人姓名")
    private String storeManager;
    @ApiModelProperty(value = "管理人姓名")
    private String storeId;
}
