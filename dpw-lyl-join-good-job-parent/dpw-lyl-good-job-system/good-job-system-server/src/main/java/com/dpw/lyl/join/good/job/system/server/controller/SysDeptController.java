package com.dpw.lyl.join.good.job.system.server.controller;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.constant.UserConstants;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysDept;
import com.dpw.lyl.join.good.job.foundation.utils.StringUtils;
import com.dpw.lyl.join.good.job.foundation.web.controller.BaseController;

import com.dpw.lyl.join.good.job.foundation.log.annotation.Log;
import com.dpw.lyl.join.good.job.foundation.log.enums.BusinessType;
import com.dpw.lyl.join.good.job.foundation.security.annotation.RequiresPermissions;
import com.dpw.lyl.join.good.job.foundation.security.utils.SecurityUtils;

import com.dpw.lyl.join.good.job.system.server.service.ISysDeptService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 部门信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @RequiresPermissions("system:dept:list")
    @GetMapping("/list")
    public MsgResponse list(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return MsgResponse.buildSuccess(depts);
    }

    /**
     * 查询部门列表（排除节点）
     */
    @RequiresPermissions("system:dept:list")
    @GetMapping("/list/exclude/{deptId}")
    public MsgResponse excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        depts.removeIf(d -> d.getDeptId().intValue() == deptId || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""));
        return MsgResponse.buildSuccess(depts);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @RequiresPermissions("system:dept:query")
    @GetMapping(value = "/{deptId}")
    public MsgResponse getInfo(@PathVariable Long deptId) {
        deptService.checkDeptDataScope(deptId);
        return MsgResponse.buildSuccess(deptService.selectDeptById(deptId));
    }

    /**
     * 新增部门
     */
    @RequiresPermissions("system:dept:add")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public MsgResponse add(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return MsgResponse.buildFail("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        return MsgResponse.buildSuccess(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @RequiresPermissions("system:dept:edit")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public MsgResponse edit(@Validated @RequestBody SysDept dept) {
        Long deptId = dept.getDeptId();
        deptService.checkDeptDataScope(deptId);
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return MsgResponse.buildFail("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        } else if (dept.getParentId().equals(deptId)) {
            return MsgResponse.buildFail("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        } else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus()) && deptService.selectNormalChildrenDeptById(deptId) > 0) {
            return MsgResponse.buildFail("该部门包含未停用的子部门！");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());
        return MsgResponse.buildSuccess(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     */
    @RequiresPermissions("system:dept:remove")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public MsgResponse remove(@PathVariable Long deptId) {
        if (deptService.hasChildByDeptId(deptId)) {
            return MsgResponse.buildFail("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return MsgResponse.buildFail("部门存在用户,不允许删除");
        }
        deptService.checkDeptDataScope(deptId);
        return MsgResponse.buildSuccess(deptService.deleteDeptById(deptId));
    }
}
