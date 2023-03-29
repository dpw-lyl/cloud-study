package com.dpw.lyl.join.good.job.iot.redis.netty.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.octv.cloud.ota.api.request.OtaCarDriverPickupMessageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年03月15日 09:09:59
 * @version: 1.0.0
 * @Description: 司机接驾路线上报
 */
@Data
public class OtaCarDriverPickupMessage implements Serializable {

    private static final long serialVersionUID = -8086173703284062140L;

    /**
     *订单号
     */
    @JsonProperty("orderNo")
    private String orderNo;

    /**
     * 合作方订单号
     */
    @JsonProperty("partnerOrderNo")
    private String partnerOrderNo;

    /**
     * 上报导航时刻时间戳
     */
    @JsonProperty("eventTime")
    private long eventTime;

    /**
     * 请求过期时间戳
     */
    @JsonProperty("expiredTime")
    private long expiredTime;

    /**
     * 事件id，唯一标识
     */
    @JsonProperty("eventId")
    private String eventId;

    /**
     * 导航上报状态，1 首次上报;2 中途上报;3 最终上报(接驾完成后上报)
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 车牌号
     */
    @JsonProperty("plateNumber")
    private String plateNumber;

    /**
     * 接送驾标识，1 送驾; 空或其他值 接驾
     */
    @JsonProperty("routeType")
    private Integer routeType;

    /**
     * 上报导航时刻司机位置
     */
    @JsonProperty("driverLocation")
    private DriverLocation driverLocation;

    @JsonProperty("routeData")
    private RouteData routeData;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class DriverLocation implements Serializable {

        private static final long serialVersionUID = 5882501364595812386L;
        /**
         * 司机id
         */
        @JsonProperty("driverId")
        private Integer driverId;

        /**
         * 经纬度，如116.5173486328125,39.77426839192708
         */
        @JsonProperty("location")
        private String location;

        /**
         * 坐标点采集时间 单位：毫秒
         */
        @JsonProperty("driverTimeStamp")
        private String driverTimeStamp;

        /**
         * 车头方向 [0,360) ,0表示正北，顺时针
         */
        @JsonProperty("driverDirection")
        private double driverDirection;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class RouteData implements Serializable {

        private static final long serialVersionUID = 8604261754201975014L;
        /**
         * 距离 单位：米，status=3时不传
         */
        @JsonProperty("routeLength")
        private Integer routeLength;

        /**
         * 时间 单位：秒，status=3时不传
         */
        @JsonProperty("routeTime")
        private Integer routeTime;

        /**
         * 路线所有点（经纬度），status=3时不传，例：121.388767,31.246757;121.389414,31.247860;
         */
        @JsonProperty("coordList")
        private String coordList;

        /**
         * 车头方向 [0,360) ,0表示正北，顺时针
         */
        @JsonProperty("trafficList")
        private List<OtaCarDriverPickupMessageRequest.TrafficData> trafficList;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class TrafficData implements Serializable {

        private static final long serialVersionUID = 6732296725237584222L;
        /**
         * 路况类型 0未知状态，1畅通，2缓行，3阻塞，4严重拥堵
         */
        private Integer level;

        /**
         * 路况对应的段
         */
        private List<Integer> index;
    }

}
