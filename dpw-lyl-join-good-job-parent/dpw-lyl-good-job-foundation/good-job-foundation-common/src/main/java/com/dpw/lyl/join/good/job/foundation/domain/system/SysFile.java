package com.dpw.lyl.join.good.job.foundation.domain.system;

import lombok.*;

/**
 * 文件信息
 *
 * @author ruoyi
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysFile {
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

}
