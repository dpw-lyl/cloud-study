package com.dpw.lyl.join.good.job.foundation.domain.system;

import com.dpw.lyl.join.good.job.foundation.annotation.Excel;
import com.dpw.lyl.join.good.job.foundation.annotation.Excel.ColumnType;
import com.dpw.lyl.join.good.job.foundation.web.domain.BaseEntity;
import lombok.*;

/**
 * 字典数据表 sys_dict_data
 *
 * @author ruoyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysDictData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @Excel(name = "字典编码", cellType = ColumnType.NUMERIC)
    private Long dictCode;

    /**
     * 字典排序
     */
    @Excel(name = "字典排序", cellType = ColumnType.NUMERIC)
    private Long dictSort;

    /**
     * 字典标签
     */
    @Excel(name = "字典标签")
    private String dictLabel;

    /**
     * 字典键值
     */
    @Excel(name = "字典键值")
    private String dictValue;

    /**
     * 字典类型
     */
    @Excel(name = "字典类型")
    private String dictType;

    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;

    /**
     * 表格字典样式
     */
    private String listClass;

    /**
     * 是否默认（Y是 N否）
     */
    @Excel(name = "是否默认", readConverterExp = "Y=是,N=否")
    private String isDefault;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

}
