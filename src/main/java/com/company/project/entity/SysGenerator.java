package com.company.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Date;

/**
 * 代码生成

 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Configuration
public class SysGenerator extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tableName;

    private Date createTime;

    private String tableComment;

}
