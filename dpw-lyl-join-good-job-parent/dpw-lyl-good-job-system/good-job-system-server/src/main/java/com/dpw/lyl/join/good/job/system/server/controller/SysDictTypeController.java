package com.dpw.lyl.join.good.job.system.server.controller;

import com.dpw.lyl.join.good.job.foundation.constant.UserConstants;
import com.dpw.lyl.join.good.job.foundation.utils.poi.ExcelUtil;
import com.dpw.lyl.join.good.job.foundation.web.controller.BaseController;
import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.web.page.TableDataInfo;
import com.dpw.lyl.join.good.job.foundation.log.annotation.Log;
import com.dpw.lyl.join.good.job.foundation.log.enums.BusinessType;
import com.dpw.lyl.join.good.job.foundation.security.annotation.RequiresPermissions;
import com.dpw.lyl.join.good.job.foundation.security.utils.SecurityUtils;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysDictType;
import com.dpw.lyl.join.good.job.system.server.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/dict/type")
public class SysDictTypeController extends BaseController {
    @Autowired
    private ISysDictTypeService dictTypeService;

    @RequiresPermissions("system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDictType dictType) {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictType dictType) {
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>(SysDictType.class);
        util.exportExcel(response, list, "字典类型");
    }

    /**
     * 查询字典类型详细
     */
    @RequiresPermissions("system:dict:query")
    @GetMapping(value = "/{dictId}")
    public MsgResponse getInfo(@PathVariable Long dictId) {
        return MsgResponse.buildSuccess(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @RequiresPermissions("system:dict:add")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public MsgResponse add(@Validated @RequestBody SysDictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return MsgResponse.buildFail("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtils.getUsername());
        return MsgResponse.buildSuccess(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @RequiresPermissions("system:dict:edit")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public MsgResponse edit(@Validated @RequestBody SysDictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return MsgResponse.buildFail("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(SecurityUtils.getUsername());
        return MsgResponse.buildSuccess(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @RequiresPermissions("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    public MsgResponse remove(@PathVariable Long[] dictIds) {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return MsgResponse.buildSuccess();
    }

    /**
     * 刷新字典缓存
     */
    @RequiresPermissions("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public MsgResponse refreshCache() {
        dictTypeService.resetDictCache();
        return MsgResponse.buildSuccess();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public MsgResponse optionselect() {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return MsgResponse.buildSuccess(dictTypes);
    }
}
