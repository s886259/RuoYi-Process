package com.ruoyi.activiti.service;

import com.ruoyi.activiti.domain.BizDeviceRepair;

import java.util.List;

/**
 * Created by tangpeng on 2022-05-22
 */
public interface IBizDeviceRepairService {
    /**
     * 查询设备故障日报
     *
     * @param id 电子日报ID
     * @return 电子日报
     */
    BizDeviceRepair selectById(Long id);

    /**
     * 查询设备故障日报
     *
     * @param instanceId 流程ID
     * @return 设备故障日报
     */
    BizDeviceRepair selectByInstanceId(String instanceId);

    /**
     * 查询设备故障日报列表
     *
     * @param bizDeviceRepair 设备故障日报
     * @return 设备故障日报集合
     */
    List<BizDeviceRepair> selectList(BizDeviceRepair bizDeviceRepair);

    /**
     * 新增设备故障日报
     *
     * @param bizDeviceRepair 设备故障日报
     * @return 结果
     */
    int insert(BizDeviceRepair bizDeviceRepair);

    /**
     * 修改设备故障日报
     *
     * @param bizDeviceRepair 设备故障日报
     * @return 结果
     */
    int update(BizDeviceRepair bizDeviceRepair);

    /**
     * 提交
     *
     * @param id
     */
    void submitApply(Long id);
}
