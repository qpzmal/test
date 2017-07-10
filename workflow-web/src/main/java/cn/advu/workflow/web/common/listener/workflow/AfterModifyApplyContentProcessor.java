package cn.advu.workflow.web.common.listener.workflow;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.web.service.base.BuyOrderService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 调整申请内容处理器
 *
 * @author HenryYan
 */
@Component
public class AfterModifyApplyContentProcessor implements TaskListener {

    private static final long serialVersionUID = 1L;

    @Autowired
    BuyOrderService buyOrderService;

    @Autowired
    RuntimeService runtimeService;

    /* (non-Javadoc)
     * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
     */
    public void notify(DelegateTask delegateTask) {
        String processInstanceId = delegateTask.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(processInstance.getBusinessKey())).getData();

//        leave.setLeaveType((String) delegateTask.getVariable("leaveType"));
//        leave.setStartTime((Date) delegateTask.getVariable("startTime"));
//        leave.setEndTime((Date) delegateTask.getVariable("endTime"));
//        leave.setReason((String) delegateTask.getVariable("reason"));
//        leaveManager.saveLeave(leave);

        buyOrderService.update(baseBuyOrder);
    }

}
