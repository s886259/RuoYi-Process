package com.ruoyi.activiti.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 设备故障日报对象 biz_device_repair
 *
 * @author Xianlu Tech
 * @date 2019-10-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BizDeviceRepair extends BaseEntity {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 生产线
     */
    @Excel(name = "生产线")
    private Integer prodLine;

    /**
     * 班次 AB班
     */
    @Excel(name = "班次")
    private Integer className;

    /**
     * 班制 白晚班
     */
    @Excel(name = "班制")
    private Integer classType;

    /**
     * 开始时间
     */
    @Excel(name = "开始时间")
    private String startTime;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间")
    private String endTime;

    /**
     * 流程实例ID
     */
    @Excel(name = "流程实例ID")
    private String instanceId;

    /**
     * Failure Mode
     */
    @Excel(name = "failureMode")
    private String failureMode;

    /**
     * 时长
     */
    @Excel(name = "时长")
    private Integer minute;

    /**
     * 设备编号
     */
    @Excel(name = "设备编号")
    private String repairDeviceNum;

    /**
     * 设备名称
     */
    @Excel(name = "设备名称")
    private String repairDeviceName;

    /**
     * 原因
     */
    @Excel(name = "原因")
    private String repairReason;

    /**
     * 维修人员
     */
    @Excel(name = "维修人员")
    private String repairOperator;

    /**
     * 区域
     */
    @Excel(name = "区域")
    private String region;

    /**
     * 周
     */
    @Excel(name = "周")
    private String week;

    /**
     * 月
     */
    @Excel(name = "月")
    private String month;

    /**
     * 绩效区域
     */
    @Excel(name = "绩效区域")
    private String scoreRegion;

    /**
     * 责任人
     */
    @Excel(name = "责任人")
    private List<String> responsiblePerson;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;
}
