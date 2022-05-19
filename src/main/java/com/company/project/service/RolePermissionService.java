package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.SysRolePermission;
import com.company.project.vo.req.RolePermissionOperationReqVO;

/**
 * 角色权限关联
 *
 */
public interface RolePermissionService extends IService<SysRolePermission> {

    /**
     * 角色绑定权限
     *
     * @param vo vo
     */
    void addRolePermission(RolePermissionOperationReqVO vo);
}
