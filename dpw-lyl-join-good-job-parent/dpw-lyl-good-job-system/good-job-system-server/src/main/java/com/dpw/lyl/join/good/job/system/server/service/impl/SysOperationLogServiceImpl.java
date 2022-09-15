package com.dpw.lyl.join.good.job.system.server.service.impl;

import com.dpw.lyl.join.good.job.foundation.domain.system.SysOperationLog;
import com.dpw.lyl.join.good.job.system.server.mapper.SysOperationLogMapper;
import com.dpw.lyl.join.good.job.system.server.service.ISysOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysOperationLogServiceImpl implements ISysOperationLogService {
    @Autowired
    private SysOperationLogMapper OperationLogMapper;

    /**
     * 新增操作日志
     *
     * @param OperationLog 操作日志对象
     * @return 结果
     */
    @Override
    public int insertOperationLog(SysOperationLog OperationLog) {
        return OperationLogMapper.insertOperationLog(OperationLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param OperationLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperationLog> selectOperationLogList(SysOperationLog OperationLog) {
        return OperationLogMapper.selectOperationLogList(OperationLog);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperationLogByIds(Long[] operIds) {
        return OperationLogMapper.deleteOperationLogByIds(operIds);
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperationLog selectOperationLogById(Long operId) {
        return OperationLogMapper.selectOperationLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperationLog() {
        OperationLogMapper.cleanOperationLog();
    }
}
