package com.dpw.lyl.join.good.job.system.server.service;

import com.dpw.lyl.join.good.job.foundation.domain.system.SysUser;

import java.util.Set;

/**
 * 权限信息 服务层
 * 
 * @author ruoyi
 */
public interface ISysPermissionService
{
    /**
     * 获取角色数据权限
     * 
     * @param user
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user);

    /**
     * 获取菜单数据权限
     * 
     * @param user
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user);
}
