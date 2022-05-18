package com.ruoyi.activiti.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 请假业务对象 biz_leave
 *
 * @author Xianlu Tech
 * @date 2019-10-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BizLeave extends BaseEntity {
    /** 主键ID */
    private Long id;

    /** 请假类型 */
    @Excel(name = "请假类型")
    private String type;

    /** 生产线 */
    @Excel(name = "生产线")
    private Integer prodLine;

    /** 班次 AB班 */
    @Excel(name = "班次")
    private Integer className;

    /** 班制 白晚班 */
    @Excel(name = "班制")
    private Integer classType;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 标题 */
    @Excel(name = "基本信息")
    private List<Map<String,Object>> gridDatas;

    /** 不良内容 */
    @Excel(name = "不良内容")
    private String badReason;

    /** 特计 */
    @Excel(name = "特计")
    private String reason;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 请假时长，单位秒 */
    @Excel(name = "请假时长，单位秒")
    private Long totalTime;

    /** 流程实例ID */
    @Excel(name = "流程实例ID")
    private String instanceId;

    /** 申请人 */
    @Excel(name = "申请人")
    private String applyUser;

    /** 申请时间 */
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 实际开始时间 */
    private Date realityStartTime;

    /** 实际结束时间 */
    private Date realityEndTime;

    /**
     * 时间(分钟)
     */
    private Integer sumMinute;
    /**
     * 工时(小时)
     */
    private Double sumHour;
    /**
     * 計画数
     */
    private Integer sumPlanNum;
    /**
     * 阶段生产
     */
    private Integer sumProdNum;
    /**
     * 总生产
     */
    private Integer sumTotalProdNum;
    /**
     * 実績统计数
     */
    private Integer sumRealNum;
    /**
     * 不良数
     */
    private Integer sumBadNum;
    /**
     * 试验品
     */
    private Integer sumTestNum;
    /**
     * 再利用
     */
    private Integer sumReuse;
    /**
     * 直接作业人员
     */
    private Integer sumDirectOperator;
    /**
     * 直间作业人员
     */
    private Integer sumIndirectOperator;
    /**
     * 速成率=实际统计数/计划数 *100%
     */
    private Double sumPlanRate;
    /**
     * 2-稼动
     * 16-部品等待
     * 11-切换车种
     * 10-品质停止
     * 5-设备停止
     * 12-试做
     * 9-无计划
     * 19-其他
     */
    private Map<String,Object> sumWorkTimeTypes;
    /**
     * 良品比例
     */
    private Double sumGoodProdRate;
    /**
     * 工时达成率
     */
    private Double sumHourRate;
}
