package com.ruoyi.activiti.mapper;

import com.ruoyi.activiti.domain.BizDeviceRepair;

import java.util.List;

/**
 * Created by tangpeng on 2022-05-22
 */
public interface BizDeviceRepairMapper {
    BizDeviceRepair selectById(Long id);

    BizDeviceRepair selectByInstanceId(String instanceId);

    List<BizDeviceRepair> selectList(BizDeviceRepair bizLeave);

    int insert(BizDeviceRepair bizLeave);

    int update(BizDeviceRepair bizLeave);
}
