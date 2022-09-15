package com.dpw.lyl.join.good.job.system.server.service.impl;

import com.dpw.lyl.join.good.job.foundation.domain.system.SysLoginInfo;
import com.dpw.lyl.join.good.job.system.server.mapper.SysLoginInfoMapper;
import com.dpw.lyl.join.good.job.system.server.service.ISysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysLoginInfoServiceImpl implements ISysLoginInfoService {

    @Autowired
    private SysLoginInfoMapper SysLoginInfoMapper;

    /**
     * 新增系统登录日志
     *
     * @param SysLoginInfo 访问日志对象
     */
    @Override
    public int insertSysLoginInfo(SysLoginInfo SysLoginInfo) {
        return SysLoginInfoMapper.insertSysLoginInfo(SysLoginInfo);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param SysLoginInfo 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginInfo> selectSysLoginInfoList(SysLoginInfo SysLoginInfo) {
        return SysLoginInfoMapper.selectSysLoginInfoList(SysLoginInfo);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public int deleteSysLoginInfoByIds(Long[] infoIds) {
        return SysLoginInfoMapper.deleteSysLoginInfoByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanSysLoginInfo() {
        SysLoginInfoMapper.cleanSysLoginInfo();
    }
}
