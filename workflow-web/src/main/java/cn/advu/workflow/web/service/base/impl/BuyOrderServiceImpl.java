package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.repo.fcf_vu.BaseBuyOrderRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.facade.workflow.ActivitiFacade;
import cn.advu.workflow.web.service.base.BuyOrderService;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class BuyOrderServiceImpl implements BuyOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuyOrderServiceImpl.class);

    @Autowired
    private BaseBuyOrderRepo baseBuyOrderRepo;




    @Autowired
    private ActivitiFacade activitiFacade;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService;

    @Override
    public ResultJson<List<BaseBuyOrder>> findAll() {
        ResultJson<List<BaseBuyOrder>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseBuyOrderRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseBuyOrder baseBuyOrder) {

        Integer insertCount = baseBuyOrderRepo.addSelective(baseBuyOrder);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建采购单失败!");
        }

        String userName = UserThreadLocalContext.getCurrentUser().getUserName();
        String processKey = WebConstants.WORKFLOW_BUY; // TODO 测试用
        LOGGER.debug("userName:{}, processKey:{}", userName, processKey);

        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
            identityService.setAuthenticatedUserId(userName);

            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, baseBuyOrder.getId() + "", new HashMap<String, Object>());
            LOGGER.info(" processInstanceId:{}", processInstance.getId());

            baseBuyOrder.setProcessInstanceId(processInstance.getId());
            baseBuyOrderRepo.updateSelective(baseBuyOrder);

        } catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                LOGGER.warn("没有部署[ " + processKey + " ]流程!", e);
                return new ResultJson<>(WebConstants.OPERATION_FAILURE, "没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>");
            } else {
                LOGGER.error("启动[ " + processKey + " ]流程失败：", e);
                return new ResultJson<>(WebConstants.OPERATION_FAILURE, "系统内部错误！");
            }
        } catch (Exception e) {
            LOGGER.error("启动[ " + processKey + " ]流程失败：", e);
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "系统内部错误！");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> update(BaseBuyOrder baseBuyOrder) {
        if (baseBuyOrder.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseBuyOrderRepo.updateSelective(baseBuyOrder);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新采购单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseBuyOrder> findById(Integer id) {
        ResultJson<BaseBuyOrder> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseBuyOrderRepo.findOne(id));
        return result;
    }
}
