package com.company.project.vo.req;

import com.company.project.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Date;
@Data
@Configuration
public class sysCategoryVo  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String categoryName;


}
