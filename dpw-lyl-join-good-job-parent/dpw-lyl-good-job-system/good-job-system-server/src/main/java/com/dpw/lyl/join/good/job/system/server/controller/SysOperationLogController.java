package com.dpw.lyl.join.good.job.system.server.controller;

import com.dpw.lyl.join.good.job.foundation.domain.system.SysOperationLog;
import com.dpw.lyl.join.good.job.foundation.utils.poi.ExcelUtil;
import com.dpw.lyl.join.good.job.foundation.web.controller.BaseController;
import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.web.page.TableDataInfo;
import com.dpw.lyl.join.good.job.foundation.log.annotation.Log;
import com.dpw.lyl.join.good.job.foundation.log.enums.BusinessType;
import com.dpw.lyl.join.good.job.foundation.security.annotation.InnerAuth;
import com.dpw.lyl.join.good.job.foundation.security.annotation.RequiresPermissions;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysOperationLog;
import com.dpw.lyl.join.good.job.system.server.service.ISysOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 操作日志记录
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/sysOperationLog")
public class SysOperationLogController extends BaseController {
    @Autowired
    private ISysOperationLogService OperationLogService;

    @RequiresPermissions("system:operationLog:list")
    @GetMapping("/list")
    public TableDataInfo list(SysOperationLog OperationLog) {
        startPage();
        List<SysOperationLog> list = OperationLogService.selectOperationLogList(OperationLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:operationLog:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOperationLog OperationLog) {
        List<SysOperationLog> list = OperationLogService.selectOperationLogList(OperationLog);
        ExcelUtil<SysOperationLog> util = new ExcelUtil<SysOperationLog>(SysOperationLog.class);
        util.exportExcel(response, list, "操作日志");
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:operationLog:remove")
    @DeleteMapping("/{operationIds}")
    public MsgResponse remove(@PathVariable Long[] operationIds) {
        return MsgResponse.buildSuccess(OperationLogService.deleteOperationLogByIds(operationIds));
    }

    @RequiresPermissions("system:operationLog:remove")
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public MsgResponse clean() {
        OperationLogService.cleanOperationLog();
        return MsgResponse.buildSuccess();
    }

    @InnerAuth
    @PostMapping
    public MsgResponse add(@RequestBody SysOperationLog OperationLog) {
        return MsgResponse.buildSuccess(OperationLogService.insertOperationLog(OperationLog));
    }
}
