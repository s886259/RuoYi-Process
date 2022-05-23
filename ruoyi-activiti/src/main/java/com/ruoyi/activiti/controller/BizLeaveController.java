package com.ruoyi.activiti.controller;

import com.ruoyi.activiti.domain.BizLeaveVo;
import com.ruoyi.activiti.service.IBizLeaveService;
import com.ruoyi.activiti.service.IProcessService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysRoleService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 电子日报Controller
 *
 * @author Xianlu Tech
 * @date 2019-10-11
 */
@Controller
@RequestMapping("/leave")
public class BizLeaveController extends BaseController {
    private String prefix = "leave";

    @Autowired
    private IBizLeaveService bizLeaveService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IProcessService processService;
    @Autowired
    private ISysRoleService roleService;

    @GetMapping()
    public String leave(ModelMap mmap) {
        mmap.put("currentUser", ShiroUtils.getSysUser());
        return prefix + "/leave";
    }

    /**
     * 查询电子日报列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizLeaveVo bizLeave) {
//        if (!SysUser.isAdmin(ShiroUtils.getUserId())) {
//            bizLeave.setCreateBy(ShiroUtils.getLoginName());
//        }
        Set<String> roleKeys = roleService.selectRoleKeys(ShiroUtils.getUserId());
        if (roleKeys.contains("deptLeader")) {
            bizLeave.getParams().put("deptLeader", true);
        } else {
            bizLeave.setCreateBy(ShiroUtils.getLoginName());
        }
        bizLeave.setType("leave");
        startPage();
        List<BizLeaveVo> list = bizLeaveService.selectBizLeaveList(bizLeave);
        return getDataTable(list);
    }

    /**
     * 导出电子日报列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizLeaveVo bizLeave) {
        bizLeave.setType("leave");
        List<BizLeaveVo> list = bizLeaveService.selectBizLeaveList(bizLeave);
        ExcelUtil<BizLeaveVo> util = new ExcelUtil<BizLeaveVo>(BizLeaveVo.class);
        return util.exportExcel(list, "leave");
    }

    /**
     * 新增电子日报
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存电子日报
     */
    @Log(title = "电子日报", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody BizLeaveVo bizLeave) {
        Long userId = ShiroUtils.getUserId();
        if (SysUser.isAdmin(userId)) {
            return error("提交申请失败：不允许管理员提交申请！");
        }
        bizLeave.setType("leave");
        return toAjax(bizLeaveService.insertBizLeave(bizLeave));
    }

    /**
     * 修改电子日报
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam(value = "disabled", required = false) Boolean disabled,
                       ModelMap mmap) {
        BizLeaveVo bizLeave = bizLeaveService.selectBizLeaveById(id);
        mmap.put("bizLeave", bizLeave);
        mmap.put("disabled", Optional.ofNullable(disabled).orElse(false));
        return prefix + "/edit";
    }

    /**
     * 修改保存电子日报
     */
    @Log(title = "电子日报", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody BizLeaveVo bizLeave) {
        return toAjax(bizLeaveService.updateBizLeave(bizLeave));
    }

    /**
     * 删除电子日报
     */
    @Log(title = "电子日报", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(bizLeaveService.deleteBizLeaveByIds(ids));
    }

    /**
     * 提交申请
     */
    @Log(title = "电子日报", businessType = BusinessType.UPDATE)
    @PostMapping("/submitApply")
    @ResponseBody
    public AjaxResult submitApply(Long id) {
        BizLeaveVo leave = bizLeaveService.selectBizLeaveById(id);
        String applyUserId = ShiroUtils.getLoginName();
        bizLeaveService.submitApply(leave, applyUserId, "leave", new HashMap<>());
        return success();
    }

    @GetMapping("/leaveTodo")
    public String todoView() {
        return prefix + "/leaveTodo";
    }

    /**
     * 我的待办列表
     *
     * @return
     */
    @PostMapping("/taskList")
    @ResponseBody
    public TableDataInfo taskList(BizLeaveVo bizLeave) {
        bizLeave.setType("leave");
        List<BizLeaveVo> list = bizLeaveService.findTodoTasks(bizLeave, ShiroUtils.getLoginName());
        return getDataTable(list);
    }

    /**
     * 加载审批弹窗
     *
     * @param taskId
     * @param mmap
     * @return
     */
    @GetMapping("/showVerifyDialog/{taskId}")
    public String showVerifyDialog(@PathVariable("taskId") String taskId, ModelMap mmap) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        BizLeaveVo bizLeave = bizLeaveService.selectBizLeaveById(new Long(processInstance.getBusinessKey()));
        mmap.put("bizLeave", bizLeave);
        mmap.put("taskId", taskId);
        String verifyName = task.getTaskDefinitionKey().substring(0, 1).toUpperCase() + task.getTaskDefinitionKey().substring(1);
        return prefix + "/task" + verifyName;
    }

    @GetMapping("/showFormDialog/{instanceId}")
    public String showFormDialog(@PathVariable("instanceId") String instanceId, ModelMap mmap) {
        String businessKey = processService.findBusinessKeyByInstanceId(instanceId);
        BizLeaveVo bizLeaveVo = bizLeaveService.selectBizLeaveById(new Long(businessKey));
        mmap.put("bizLeave", bizLeaveVo);
        return prefix + "/view";
    }

    /**
     * 完成任务
     *
     * @return
     */
    @PostMapping(value = "/complete/{taskId}")
    @ResponseBody
    public AjaxResult complete(@PathVariable("taskId") String taskId, @RequestBody BizLeaveVo leave, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        processService.complete(taskId, leave.getInstanceId(),null, null, "leave", map, request);
        bizLeaveService.updateBizLeave(leave);
        return success("任务已完成");
    }

    /**
     * 自动绑定页面字段
     */
    @ModelAttribute("preloadLeave")
    public BizLeaveVo getLeave(@RequestParam(value = "id", required = false) Long id, HttpSession session) {
        if (id != null) {
            return bizLeaveService.selectBizLeaveById(id);
        }
        return new BizLeaveVo();
    }

    @GetMapping("/leaveDone")
    public String doneView() {
        return prefix + "/leaveDone";
    }

    /**
     * 我的已办列表
     *
     * @param bizLeave
     * @return
     */
    @PostMapping("/taskDoneList")
    @ResponseBody
    public TableDataInfo taskDoneList(BizLeaveVo bizLeave) {
        bizLeave.setType("leave");
        List<BizLeaveVo> list = bizLeaveService.findDoneTasks(bizLeave, ShiroUtils.getLoginName());
        return getDataTable(list);
    }

}
