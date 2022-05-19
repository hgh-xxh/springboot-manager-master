package com.company.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.entity.SysGenerator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 代码生成 Mapper
 *

 */
@Repository
public interface GeneratorMapper extends BaseMapper<SysGenerator> {

    IPage<SysGenerator> selectAllTables(Page<SysGenerator> page, @Param(value = "vo") SysGenerator vo);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
