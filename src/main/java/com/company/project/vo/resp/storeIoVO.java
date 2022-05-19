package com.company.project.vo.resp;

import com.company.project.entity.SysStoreEntity;
import com.company.project.entity.SysStoreIoEntity;
import lombok.Data;

import java.util.Date;

@Data
public class storeIoVO {



    private String storeName;
    private String storeIoType;



    private Double storeioNum;
    private Date storeIoTime;

    public storeIoVO(String storeName, String storeioType, Double storeioNum, Date storeioTime) {
        this.storeName=storeName;
        this.storeIoType=storeioType;
        this.storeioNum=storeioNum;
        this.storeIoTime=storeioTime;

    }
}