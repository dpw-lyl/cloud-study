package com.dpw.lyl.join.good.job.system.server.service;

import com.dpw.lyl.join.good.job.foundation.domain.system.SysOperationLog;

import java.util.List;

/**
 * 操作日志 服务层
 * 
 * @author ruoyi
 */
public interface ISysOperationLogService
{
    /**
     * 新增操作日志
     * 
     * @param OperationLog 操作日志对象
     * @return 结果
     */
    public int insertOperationLog(SysOperationLog OperationLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param OperationLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperationLog> selectOperationLogList(SysOperationLog OperationLog);

    /**
     * 批量删除系统操作日志
     * 
     * @param operationIds 需要删除的操作日志ID
     * @return 结果
     */
    public int deleteOperationLogByIds(Long[] operationIds);

    /**
     * 查询操作日志详细
     * 
     * @param operationId 操作ID
     * @return 操作日志对象
     */
    public SysOperationLog selectOperationLogById(Long operationId);

    /**
     * 清空操作日志
     */
    public void cleanOperationLog();
}
