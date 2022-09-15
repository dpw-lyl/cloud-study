package com.dpw.lyl.join.good.job.foundation.common.api.factory;

import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.ResponseCodeEnum;
import com.dpw.lyl.join.good.job.foundation.biz.BizException;
import com.dpw.lyl.join.good.job.foundation.common.api.RemoteFileService;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务降级处理
 *
 * @author ruoyi
 */
@Component
@Slf4j
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService> {


    @Override
    public RemoteFileService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService() {
            @Override
            public MsgResponse<SysFile> upload(MultipartFile file) {
                return MsgResponse.buildBizExceptionNoData(new BizException(ResponseCodeEnum.SYSTEM_ERROR.getReturnCode(), "上传文件失败:" + throwable.getMessage()));
            }
        };
    }
}
