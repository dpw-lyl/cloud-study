package com.dpw.lyl.join.good.job.iot.redis.netty.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: dengpw
 * @createTime: 2023年03月15日 09:07:07
 * @version: 1.0.0
 * @Description: 打车状态通知
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class OtaCarOrderStatusMessage extends BaseMessage {


    private static final long serialVersionUID = -207359066677580603L;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * octv订单号
     */
    private String orderNo;

    /**
     * 车辆颜色，黑
     */
    private String vehicleColor;

    /**
     * 车辆图片
     */
    private String vehiclePic;

    /**
     * 司机电话
     */
    private String driverPhone;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 车组名称，奥迪A6
     */
    private String modelName;

    /**
     * 车牌号，冀FA0296
     */
    private String licensePlates;

    /**
     * 车型，舒适型
     */
    private String groupName;

    /**
     * 车型id
     */
    private Integer groupId;

    /**
     * 司机评分
     */
    private String driverRate;

    /**
     * 取消费用
     */
    private String cancelFee;

    /**
     * 订单总金额
     */
    private String total;

    private CustomerServiceInfoBO customerServiceInfo;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class CustomerServiceInfoBO implements Serializable {
        private static final long serialVersionUID = 845296928818451244L;

        /**
         * 取消原因
         */
        private String cancelReason;

        /**
         * 客服编号
         */
        private Integer opId;
        /**
         * 客服姓名
         */
        private String opName;
    }

}
