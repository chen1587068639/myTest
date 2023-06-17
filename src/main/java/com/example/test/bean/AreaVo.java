package com.example.test.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class AreaVo implements Serializable {

    /**
     * 区域id
     */
    private Long areaId;

    /**
     * 名称
     */
    private String areaName;

    /**
     * 类型 2-网点区域 3-禁还区 4-禁行区
     */
    private Integer areaType;

    /**
     * 类型集合
     */
    private List<Integer> areaTypeList;

    /**
     * 运营区id
     */
    private Long operateAreaId;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 品牌ID
     */
    private Long brandsId;

    /**
     * qpp来源
     */
    private Integer appFrom;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 地区编码
     */
    private String adCode;

    /**
     * 形状 1-圆形 2-多边形
     */
    private Integer shapeType;

    /**
     * 半径 单位m 圆形网点需要
     */
    private Integer radius;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 经度, 纬度
     */
    private Double[] centerLocation;

    /**
     * 状态(1:有效,2:无效)
     */
    private Integer status;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer delFlag;

    /**
     * 距离
     */
    private Double distance;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 停车点纬度
     */
    private Double stopAreaLatitude;

    /**
     * 停车点经度
     */
    private Double stopAreaLongitude;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 最大距离
     */
    private Double maxDistance;

    /**
     * 车辆ID
     */
    private Long bikeId;
    /**
     * 中控编码
     */
    private String imei;

    /**
     * 最小距离
     */
    private Double minDistance;

    /**
     * 操作来源
     */
    private String systemFrom;

    /**
     * 是否在运营区内
     */
    private Boolean isInOperateArea ;

    /**
     * 是否在禁还区域内
     */
    private Boolean isInForbidReturn;

    /**
     * 是否在服务区域内
     */
    private Boolean isInServiceArea;

    /**
     * 是否在进行区域内
     */
    private Boolean isInForbidTravel;

    /**
     * 是否在还车区域内
     */
    private Boolean isInReturnCarArea;

    /**
     * 是否在推荐还车区域内
     */
    private Boolean isInRecommendReturnCarArea;

    /**
     * 还车网点的ID
     */
    private Long returnAreaId;

    /**
     * 还车状态
     */
    private Integer returnCarStatus;

    /**
     *  调度费（服务区域外的调度费）
     */
    private BigDecimal dispatchAmount;

    /**
     * 调度费（服务区域内,网点外的调度费,仅适用于网点还）
     */
    private BigDecimal dispatchAmountService;

    /**
     * 调度费（禁行区内调度费）
     */
    private BigDecimal dispatchAmountBanRun;

    /**
     * 调度费 (禁停区内调度费)
     * dispatch_amount_ban_return
     */
    private BigDecimal dispatchAmountBanReturn;

    /**
     * 还车方式
     */
    private Integer returnMode;

    /**
     * 车辆不存在经纬度
     */
    private Boolean bikeNoGps;

    /**
     * 手机不存在经纬度
     */
    private Boolean mobileNoGps;

    /**
     * 车辆盒子上报经纬度的时间
     */
    private Date gpsTime;

    /**
     * 车辆的中心坐标
     */
    private Double[] bikeCenterLocation;

    /**
     * 推荐还车点ID
     */
    private Long recommendReturnAreaId;

    /**
     * 禁行区ID
     */
    private Long forbidTravelAreaId;

    /**
     * 禁还区ID
     */
    private Long forbidReturnAreaId;

    /**
     * 服务区域ID
     */
    private Long serviceAreaId;

    /**
     * 调度费类型
     */
    private Integer dispatchType;

    /**
     * 缓存地区的钥匙
     */
    private String cacheAreaKey;

    /**
     * 缩放级别
     */
    private Integer zoomLevel;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 运营区域ID集合
     */
    private List<Long> operateAreaIds;

    /**
     * 是否进行排序查询
     */
    private Boolean sortFlag;

    /**
     * 是否进行深度查询（按照区域密度由小至大，依次扩大查询范围）
     */
    private Boolean depthFlag;

    private Long timestamp;
    /**
     * 停车点增加车辆数
     */
    private Long addBikeNum;
    /**
     * 停车位方位角, 0-360度，*100的结果
     */
    private Integer azimuth;
    /**
     * 是否是添加原始精准坐标
     */

    private Boolean needAddPinpoint =false;
    /**
     * 是否已添加了精准坐标
     */
    private Integer addPinpoint;
    /**
     * 用户是否需要挪车到还车区域内
     * 如果为false, 表示停车区域校验通过。通过的原因可能是因为普通定位或高精度定位开关没开，可能是因为用户已经把车停到了还车范围内。
     */
    private Boolean needMoveBikeToReturnArea;
    /**
     * 用户是否需要挪车到道钉附近
     * 如果为false, 表示道钉校验通过。通过的原因可能是因为道钉开关没开，可能是因为用户已经把车停到了道钉范围内。
     */
    private Boolean needMoveBikeToSpike;

}

