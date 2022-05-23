package com.ruoyi.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.activiti.domain.BizLeaveVo;

import java.util.List;

/**
 * 电子日报业务Mapper接口
 *
 * @author Xianlu Tech
 * @date 2019-10-11
 */
public interface BizLeaveMapper extends BaseMapper {
    /**
     * 查询电子日报业务
     *
     * @param id 电子日报业务ID
     * @return 请假业务
     */
    BizLeaveVo selectBizLeaveById(Long id);

    /**
     * 查询电子日报业务
     *
     * @param instanceId 电子日报业务ID
     * @return 电子日报业务
     */
    BizLeaveVo selectBizLeaveByInstanceId(String instanceId);

    /**
     * 查询电子日报业务列表
     *
     * @param bizLeave 电子日报业务
     * @return 电子日报业务集合
     */
    List<BizLeaveVo> selectBizLeaveList(BizLeaveVo bizLeave);

    /**
     * 新增电子日报业务
     *
     * @param bizLeave 请电子日报业务
     * @return 结果
     */
    int insertBizLeave(BizLeaveVo bizLeave);

    /**
     * 修改电子日报业务
     *
     * @param bizLeave 电子日报业务
     * @return 结果
     */
    int updateBizLeave(BizLeaveVo bizLeave);

    /**
     * 删除电子日报业务
     *
     * @param id 电子日报业务ID
     * @return 结果
     */
    int deleteBizLeaveById(Long id);

    /**
     * 批量删除电子日报业务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizLeaveByIds(String[] ids);
}
