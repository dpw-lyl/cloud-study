package com.dpw.lyl.join.good.job.system.server.controller;

import com.dpw.lyl.join.good.job.foundation.constant.CacheConstants;
import com.dpw.lyl.join.good.job.foundation.utils.poi.ExcelUtil;
import com.dpw.lyl.join.good.job.foundation.web.controller.BaseController;
import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.web.page.TableDataInfo;
import com.dpw.lyl.join.good.job.foundation.log.annotation.Log;
import com.dpw.lyl.join.good.job.foundation.log.enums.BusinessType;
import com.dpw.lyl.join.good.job.foundation.redis.service.RedisService;
import com.dpw.lyl.join.good.job.foundation.security.annotation.InnerAuth;
import com.dpw.lyl.join.good.job.foundation.security.annotation.RequiresPermissions;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysLoginInfo;
import com.dpw.lyl.join.good.job.system.server.service.ISysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统访问记录
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/sysLoginInfo")
public class SysLoginInfoController extends BaseController {
    @Autowired
    private ISysLoginInfoService SysLoginInfoService;

    @Autowired
    private RedisService redisService;

    @RequiresPermissions("system:loginInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(SysLoginInfo SysLoginInfo) {
        startPage();
        List<SysLoginInfo> list = SysLoginInfoService.selectSysLoginInfoList(SysLoginInfo);
        return getDataTable(list);
    }

    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:loginInfo:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLoginInfo SysLoginInfo) {
        List<SysLoginInfo> list = SysLoginInfoService.selectSysLoginInfoList(SysLoginInfo);
        ExcelUtil<SysLoginInfo> util = new ExcelUtil<SysLoginInfo>(SysLoginInfo.class);
        util.exportExcel(response, list, "登录日志");
    }

    @RequiresPermissions("system:loginInfo:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public MsgResponse remove(@PathVariable Long[] infoIds) {
        return MsgResponse.buildSuccess(SysLoginInfoService.deleteSysLoginInfoByIds(infoIds));
    }

    @RequiresPermissions("system:loginInfo:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/clean")
    public MsgResponse clean() {
        SysLoginInfoService.cleanSysLoginInfo();
        return MsgResponse.buildSuccess();
    }

    @RequiresPermissions("system:loginInfo:unlock")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @GetMapping("/unlock/{userName}")
    public MsgResponse unlock(@PathVariable("userName") String userName) {
        redisService.deleteObject(CacheConstants.PWD_ERR_CNT_KEY + userName);
        return MsgResponse.buildSuccess();
    }

    @InnerAuth
    @PostMapping
    public MsgResponse add(@RequestBody SysLoginInfo SysLoginInfo) {
        return MsgResponse.buildSuccess(SysLoginInfoService.insertSysLoginInfo(SysLoginInfo));
    }
}
