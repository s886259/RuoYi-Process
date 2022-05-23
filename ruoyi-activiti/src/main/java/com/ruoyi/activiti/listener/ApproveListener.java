package com.ruoyi.activiti.listener;

import com.ruoyi.activiti.domain.BizDeviceRepair;
import com.ruoyi.activiti.domain.BizLeaveVo;
import com.ruoyi.activiti.service.IBizDeviceRepairService;
import com.ruoyi.activiti.service.IBizLeaveService;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * Created by tangpeng on 2022-05-22
 */
@Component
public class ApproveListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) {
        //获取审批内容
        BizLeaveVo bizLeaveVo = getBizLeaveVo(delegateExecution.getProcessInstanceId());
        List<Map<String, Object>> repairList = bizLeaveVo.getGridDatas().stream()
                .filter(i -> Objects.equals(i.get("workTimeType"), "5"))
                .collect(toList());
        if (CollectionUtils.isEmpty(repairList)) {
            return;
        }
        IBizDeviceRepairService bizDeviceRepairService = SpringUtils.getBean(IBizDeviceRepairService.class);
        repairList.forEach(repair -> {
            //插入设备故障日报
            BizDeviceRepair bizDeviceRepair = new BizDeviceRepair()
                    .setProdLine(bizLeaveVo.getProdLine())
                    .setClassName(bizLeaveVo.getClassName())
                    .setClassType(bizLeaveVo.getClassType())
                    .setStartTime((String) repair.get("startTime"))
                    .setEndTime((String) repair.get("endTime"))
                    .setInstanceId(bizLeaveVo.getInstanceId())
                    .setMinute(Integer.valueOf((String) repair.get("minute")))
                    .setRepairDeviceNum((String) repair.get("repairDeviceNum"))
                    .setRepairDeviceName((String) repair.get("repairDeviceName"))
                    .setRepairReason((String) repair.get("repairReason"))
                    .setRepairOperator((String) repair.get("repairOperator"));
            bizDeviceRepairService.insert(bizDeviceRepair);
        });
    }

    private BizLeaveVo getBizLeaveVo(String processInstanceId) {
        IBizLeaveService bizLeaveService = SpringUtils.getBean(IBizLeaveService.class);
        BizLeaveVo bizLeaveVo = bizLeaveService.selectBizLeaveByInstanceId(processInstanceId);
        return bizLeaveVo;
    }
}
