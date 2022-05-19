package com.company.project.vo.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.company.project.entity.SysProductEntity;
import lombok.Data;

@Data
public class ProductVo extends SysProductEntity {


    /**
     *
     */

    private String categoryName;
    private String   updateProduct;


}
