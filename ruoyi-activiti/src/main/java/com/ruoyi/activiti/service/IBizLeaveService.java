package com.ruoyi.activiti.service;

import com.ruoyi.activiti.domain.BizLeaveVo;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.List;
import java.util.Map;

/**
 * 电子日报Service接口
 *
 * @author Xianlu Tech
 * @date 2019-10-11
 */
public interface IBizLeaveService {
    /**
     * 查询电子日报
     *
     * @param id 电子日报ID
     * @return 电子日报
     */
    BizLeaveVo selectBizLeaveById(Long id);

    /**
     * 查询电子日报
     *
     * @param instanceId 流程ID
     * @return 电子日报
     */
    BizLeaveVo selectBizLeaveByInstanceId(String instanceId);

    /**
     * 查询电子日报列表
     *
     * @param BizLeaveVo 电子日报
     * @return 电子日报集合
     */
    List<BizLeaveVo> selectBizLeaveList(BizLeaveVo BizLeaveVo);

    /**
     * 新增电子日报
     *
     * @param BizLeaveVo 电子日报
     * @return 结果
     */
    int insertBizLeave(BizLeaveVo BizLeaveVo);

    /**
     * 修改电子日报
     *
     * @param BizLeaveVo 电子日报
     * @return 结果
     */
    int updateBizLeave(BizLeaveVo BizLeaveVo);

    /**
     * 批量删除电子日报
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizLeaveByIds(String ids);

    /**
     * 删除电子日报信息
     *
     * @param id 电子日报ID
     * @return 结果
     */
    int deleteBizLeaveById(Long id);

    /**
     * 启动流程
     * @param entity
     * @param applyUserId
     * @return
     */
    ProcessInstance submitApply(BizLeaveVo entity, String applyUserId, String key, Map<String, Object> variables);

    /**
     * 查询我的待办列表
     * @param userId
     * @return
     */
    List<BizLeaveVo> findTodoTasks(BizLeaveVo leave, String userId);

    List<BizLeaveVo> findDoneTasks(BizLeaveVo bizLeaveVo, String userId);
}
