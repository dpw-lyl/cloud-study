package com.dpw.lyl.join.good.job.system.server.controller;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.ResponseCodeEnum;
import com.dpw.lyl.join.good.job.foundation.biz.BizException;
import com.dpw.lyl.join.good.job.foundation.constant.UserConstants;
import com.dpw.lyl.join.good.job.foundation.log.annotation.Log;
import com.dpw.lyl.join.good.job.foundation.log.enums.BusinessType;
import com.dpw.lyl.join.good.job.foundation.security.annotation.RequiresPermissions;
import com.dpw.lyl.join.good.job.foundation.security.utils.SecurityUtils;
import com.dpw.lyl.join.good.job.foundation.utils.poi.ExcelUtil;
import com.dpw.lyl.join.good.job.foundation.web.controller.BaseController;
import com.dpw.lyl.join.good.job.foundation.web.page.TableDataInfo;
import com.dpw.lyl.join.good.job.system.server.domain.SysConfig;
import com.dpw.lyl.join.good.job.system.server.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;
import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/config")
public class SysConfigController extends BaseController {
    @Autowired
    private ISysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @RequiresPermissions("system:config:list")
    @GetMapping("/list")
    public TableDataInfo list(SysConfig config) {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:config:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysConfig config) {
        List<SysConfig> list = configService.selectConfigList(config);
        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
        util.exportExcel(response, list, "参数数据");
    }

    /**
     * 根据参数编号获取详细信息
     */
    @GetMapping(value = "/{configId}")
    public MsgResponse getInfo(@PathVariable Long configId) {
        return MsgResponse.buildSuccess(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public MsgResponse getConfigKey(@PathVariable String configKey) {
        return MsgResponse.buildSuccess(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @RequiresPermissions("system:config:add")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping
    public MsgResponse add(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return MsgResponse.buildFail("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setCreateBy(SecurityUtils.getUsername());
        return MsgResponse.buildSuccess(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @RequiresPermissions("system:config:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public MsgResponse edit(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return MsgResponse.buildBizExceptionNoData(new BizException(ResponseCodeEnum.SYSTEM_ERROR.getReturnCode(),"修改参数'" + config.getConfigName() + "'失败，参数键名已存在"));
        }
        config.setUpdateBy(SecurityUtils.getUsername());
        return MsgResponse.buildSuccess(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public MsgResponse remove(@PathVariable Long[] configIds) {
        configService.deleteConfigByIds(configIds);
        return MsgResponse.buildSuccess();
    }

    /**
     * 刷新参数缓存
     */
    @RequiresPermissions("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public MsgResponse refreshCache() {
        configService.resetConfigCache();
        return MsgResponse.buildSuccess();
    }
}
