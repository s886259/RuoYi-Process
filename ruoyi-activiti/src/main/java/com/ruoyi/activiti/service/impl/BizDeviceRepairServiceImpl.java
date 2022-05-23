package com.ruoyi.activiti.service.impl;

import com.github.pagehelper.Page;
import com.ruoyi.activiti.domain.BizDeviceRepair;
import com.ruoyi.activiti.mapper.BizDeviceRepairMapper;
import com.ruoyi.activiti.service.IBizDeviceRepairService;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tangpeng on 2022-05-22
 */
@Service
@Transactional
public class BizDeviceRepairServiceImpl implements IBizDeviceRepairService {
    @Autowired
    private BizDeviceRepairMapper bizDeviceRepairMapper;

    /**
     * 查询设备故障日报
     *
     * @param id 设备故障日报ID
     * @return 设备故障日报
     */
    @Override
    public BizDeviceRepair selectById(Long id) {
        return bizDeviceRepairMapper.selectById(id);
    }

    /**
     * 查询设备故障日报
     *
     * @param instanceId 流程ID
     * @return 设备故障日报
     */
    @Override
    public BizDeviceRepair selectByInstanceId(String instanceId) {
        return bizDeviceRepairMapper.selectByInstanceId(instanceId);
    }

    /**
     * 查询设备故障日报列表
     *
     * @param bizLeave 设备故障日报
     * @return 设备故障日报
     */
    @Override
    public List<BizDeviceRepair> selectList(BizDeviceRepair bizLeave) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();

        // PageHelper 仅对第一个 List 分页
        Page<BizDeviceRepair> list = (Page<BizDeviceRepair>) bizDeviceRepairMapper.selectList(bizLeave);
        return list;
    }

    /**
     * 新增设备故障日报
     *
     * @param bizLeave 设备故障日报
     * @return 结果
     */
    @Override
    public int insert(BizDeviceRepair bizLeave) {
        bizLeave.setCreateBy(ShiroUtils.getLoginName());
        bizLeave.setCreateTime(DateUtils.getNowDate());
        return bizDeviceRepairMapper.insert(bizLeave);
    }

    /**
     * 修改设备故障日报
     *
     * @param bizLeave 设备故障日报
     * @return 结果
     */
    @Override
    public int update(BizDeviceRepair bizLeave) {
        bizLeave.setUpdateTime(DateUtils.getNowDate());
        return bizDeviceRepairMapper.update(bizLeave);
    }

    /**
     * 提交
     *
     * @param id
     */
    @Override
    public void submitApply(Long id) {
        BizDeviceRepair bizDeviceRepair = new BizDeviceRepair();
        bizDeviceRepair.setId(id);
        bizDeviceRepair.setStatus(1);
        bizDeviceRepairMapper.update(bizDeviceRepair);
    }
}