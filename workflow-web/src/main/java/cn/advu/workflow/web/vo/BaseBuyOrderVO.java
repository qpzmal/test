package cn.advu.workflow.web.vo;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import org.activiti.engine.task.Task;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * Created by weiqz on 2017/7/2.
 */
public class BaseBuyOrderVO {


    private BaseBuyOrder baseBuyOrder;

    // 流程任务
    private Task task;

    private Map<String, Object> variables;

    // 运行中的流程实例
    private ProcessInstance processInstance;

    // 历史的流程实例
    private HistoricProcessInstance historicProcessInstance;

    // 流程定义
    private ProcessDefinition processDefinition;


    public void setBaseBuyOrder(BaseBuyOrder baseBuyOrder) {
        this.baseBuyOrder = baseBuyOrder;
    }

    public void setTask(Task task) {
        this.task = task;
    }


    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public void setProcessInstance(ProcessInstance processInstance) {
        this.processInstance = processInstance;
    }

    public void setHistoricProcessInstance(HistoricProcessInstance historicProcessInstance) {
        this.historicProcessInstance = historicProcessInstance;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }

    public BaseBuyOrder getBaseBuyOrder() {
        return baseBuyOrder;
    }

    public Task getTask() {
        return task;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public ProcessInstance getProcessInstance() {
        return processInstance;
    }

    public HistoricProcessInstance getHistoricProcessInstance() {
        return historicProcessInstance;
    }

    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

}
