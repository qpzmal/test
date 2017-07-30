package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrderFrame;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
import cn.advu.workflow.repo.fcf_vu.BaseBuyOrderFrameRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.CpmManager;
import cn.advu.workflow.web.service.base.BuyFrameService;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class BuyFrameServiceImpl extends AbstractOrderService implements BuyFrameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyFrameServiceImpl.class);

    @Autowired
    private BaseBuyOrderFrameRepo baseBuyOrderFrameRepo;
    @Autowired
    CpmManager cpmManager;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;


    @Override
    public ResultJson<List<BaseBuyOrderFrame>> findAll(BaseBuyOrderFrame param) {
        ResultJson<List<BaseBuyOrderFrame>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseBuyOrderFrameRepo.findAll(param));
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseBuyOrderFrame baseExecuteOrderFrame) {

        // 编码
        String orderNumSeqStr = this.buildOrderNumSeqStr();

        String orderNum = "P" + orderNumSeqStr;
        baseExecuteOrderFrame.setOrderNum(orderNum);
        // 补充编码
        if (StringUtils.isEmpty(baseExecuteOrderFrame.getSecOrderNum())) {
            baseExecuteOrderFrame.setSecOrderNum(orderNum);
        }
        String userId = UserThreadLocalContext.getCurrentUser().getUserId();
        baseExecuteOrderFrame.setUserId(Integer.valueOf(userId));

        // CPM
        buildBuyFrameCpm(baseExecuteOrderFrame);

        Integer insertCount = baseBuyOrderFrameRepo.addSelective(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建需求单失败!");
        }

        // 发起工作流
        return this.startWorkFlow(baseExecuteOrderFrame);
    }


    @Override
    public ResultJson<Integer> update(BaseBuyOrderFrame baseExecuteOrderFrame) {

        // CPM
        buildBuyFrameCpm(baseExecuteOrderFrame);

        if (baseExecuteOrderFrame.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseBuyOrderFrameRepo.update(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新需求单失败!");
        }

        // 发起工作流
        return this.startWorkFlow(baseExecuteOrderFrame);
    }

    @Override
    public ResultJson<Integer> updateSelective(BaseBuyOrderFrame baseBuyOrderFrame) {
        Integer insertCount = baseBuyOrderFrameRepo.updateSelective(baseBuyOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新采购框架单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseBuyOrderFrame> findById(Integer id) {
        ResultJson<BaseBuyOrderFrame> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        BaseBuyOrderFrame baseBuyOrderFrame = baseBuyOrderFrameRepo.findOne(id);
        result.setData(baseBuyOrderFrame);

        List<BaseOrderCpmVO> cpmList = cpmManager.findOrderBuyFrameCpm(id);
        baseBuyOrderFrame.setBaseOrderCpmList(cpmList);
        return result;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {
        BaseBuyOrderFrame baseBuyOrderFrame = baseBuyOrderFrameRepo.findOne(id);
        List<BaseOrderCpmVO> cpmList = cpmManager.findOrderBuyCpm(id);
        baseBuyOrderFrame.setBaseOrderCpmList(cpmList);

        Integer count = baseBuyOrderFrameRepo.logicRemove(baseBuyOrderFrame);
        if (count == 0) {
            throw new ServiceException("需求单不存在！");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    private ResultJson<Integer> startWorkFlow(BaseBuyOrderFrame baseBuyOrderFrame) {
        LOGGER.info("startWorkFlow-flowType:{}", baseBuyOrderFrame.getFlowType());
        if (WebConstants.WorkFlow.START.equals(baseBuyOrderFrame.getFlowType())) {
            String userName = UserThreadLocalContext.getCurrentUser().getUserName();
            String processKey = WebConstants.WORKFLOW_BUY_FRAME;
            LOGGER.debug("userName:{}, processKey:{}", userName, processKey);

            try {
                // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
                identityService.setAuthenticatedUserId(userName);

                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, baseBuyOrderFrame.getId() + "", new HashMap<String, Object>());
                LOGGER.info(" processInstanceId:{}", processInstance.getId());

                baseBuyOrderFrame.setProcessInstanceId(processInstance.getId());
                baseBuyOrderFrame.setStatus(WebConstants.WorkFlow.STATUS_0);
                baseBuyOrderFrameRepo.updateSelective(baseBuyOrderFrame);

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
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }
}
