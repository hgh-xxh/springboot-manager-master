package com.company.project.vo.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.company.project.entity.SysStoreEntity;
import com.company.project.entity.SysStoreIoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * HomeRespVO
 *
 */
@Data
public class orderStoreVO {

    private Integer storeioId;


    private String storeName;

    private String storeProductName;

    private Double storeioNum;

    public void setdata(SysStoreIoEntity ioItem,SysStoreEntity storeItem){
        this.storeioId=ioItem.getId();
        this.storeioNum=ioItem.getStoreioNum();
        this.storeName=storeItem.getStoreName();
        this.storeProductName=storeItem.getStoreProductName();
    }
}