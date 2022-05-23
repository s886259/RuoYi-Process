package com.ruoyi.activiti.controller;

import com.ruoyi.activiti.domain.BizDeviceRepair;
import com.ruoyi.activiti.domain.BizLeaveVo;
import com.ruoyi.activiti.service.IBizDeviceRepairService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 设备故障日报Controller
 *
 * @author Xianlu Tech
 * @date 2019-10-11
 */
@Controller
@RequestMapping("/leaveCounterSign")
public class BizDeviceRepairController extends BaseController {
    private String prefix = "leaveCounterSign";

    @Autowired
    private IBizDeviceRepairService bizDeviceRepairService;

    @GetMapping()
    public String leave(ModelMap mmap) {
        mmap.put("currentUser", ShiroUtils.getSysUser());
        return prefix + "/leave";
    }

    /**
     * 查询设备故障列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizDeviceRepair bizLeave) {
        startPage();
        List<BizDeviceRepair> list = bizDeviceRepairService.selectList(bizLeave);
        return getDataTable(list);
    }

    /**
     * 新增设备故障
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 修改设备故障
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        BizDeviceRepair bizLeave = bizDeviceRepairService.selectById(id);
        mmap.put("bizLeave", bizLeave);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备故障
     */
    @Log(title = "设备故障", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizDeviceRepair bizLeave) {
        return toAjax(bizDeviceRepairService.update(bizLeave));
    }


    /**
     * 提交申请
     */
    @Log(title = "设备故障", businessType = BusinessType.UPDATE)
    @PostMapping("/submitApply")
    @ResponseBody
    public AjaxResult submitApply(Long id) {
        bizDeviceRepairService.submitApply(id);
        return success();
    }
}
