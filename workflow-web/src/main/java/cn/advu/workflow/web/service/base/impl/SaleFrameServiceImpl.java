package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.enums.CustomTypeEnum;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderFrameRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.AreaManager;
import cn.advu.workflow.web.manager.CpmManager;
import cn.advu.workflow.web.manager.CustomMananger;
import cn.advu.workflow.web.service.base.SaleFrameService;
import cn.advu.workflow.web.util.AssertUtil;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
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
public class SaleFrameServiceImpl extends AbstractOrderService implements SaleFrameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleFrameServiceImpl.class);

    @Autowired
    private BaseExecuteOrderFrameRepo baseExecuteOrderFrameRepo;

    @Autowired
    CustomMananger customMananger;

    @Autowired
    AreaManager areaManager;

    @Autowired
    CpmManager cpmManager;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;


    @Override
    public ResultJson<List<BaseExecuteOrderFrame>> findAll(BaseExecuteOrderFrame param) {
        ResultJson<List<BaseExecuteOrderFrame>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseExecuteOrderFrameRepo.findAll(param));
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseExecuteOrderFrame baseExecuteOrderFrame) {

        AssertUtil.assertNotNullOrEmpty(baseExecuteOrderFrame.getSignType(), "签约类型");

        String customSignName = baseExecuteOrderFrame.getCustomSignName();
        String customAdverserName = baseExecuteOrderFrame.getCustomAdverserName();

        AssertUtil.assertNotNullOrEmpty(customSignName, "签约公司");
        AssertUtil.assertNotNullOrEmpty(customAdverserName, "广告主");

        customSignName = customSignName.trim();
        customAdverserName = customAdverserName.trim();

        AssertUtil.assertNotNullOrEmpty(customSignName, "签约公司");
        AssertUtil.assertNotNullOrEmpty(customAdverserName, "广告主");


        BaseCustom customSign = customMananger.findByName(customSignName);
        if (customSign == null) {
            customSign = new BaseCustom();
            customSign.setName(customSignName);
            customSign.setCustomType(baseExecuteOrderFrame.getSignType());
            customMananger.add(customSign);
        }

        baseExecuteOrderFrame.setCustomSignId(customSign.getId());

        Integer customAdverserId = customSign.getId();

        if (customSignName.equals(customAdverserName) && CustomTypeEnum.FA.getValue().equalsIgnoreCase(baseExecuteOrderFrame.getSignType())) {
            throw new ServiceException(MessageConstants.ADVERSER_IS_4A);
        }
        if ( CustomTypeEnum.FA.getValue().equalsIgnoreCase(baseExecuteOrderFrame.getSignType())) {
            BaseCustom customAdverser = customMananger.findByName(customAdverserName);
            baseExecuteOrderFrame.setCustomSignId(customSign.getId());
            if (customAdverser == null) {
                customAdverser = new BaseCustom();
                customAdverser.setName(customAdverserName);
                customAdverser.setCustomType(CustomTypeEnum.MA.getValue());
                customAdverser.setParentId(customSign.getId());
                customMananger.add(customAdverser);
            }
            customAdverserId = customAdverser.getId();
        }

        baseExecuteOrderFrame.setCustomAdverserId(customAdverserId);

        AssertUtil.assertNotNull(baseExecuteOrderFrame.getCustomSignId(), MessageConstants.SIGN_CUSTOM_TYPE_IS_NULL);

        // 编码
        String orderNumSeqStr = this.buildOrderNumSeqStr();
        Integer signCustomId = baseExecuteOrderFrame.getCustomSignId();
        BaseCustom signCustom = customMananger.findById(signCustomId);
        BaseArea baseArea = areaManager.findById(baseExecuteOrderFrame.getAreaId());
        String areaCode = baseArea.getCode();
        String orderNum = "S" + findCustomType(signCustom.getCustomType()) + areaCode + orderNumSeqStr;
        baseExecuteOrderFrame.setOrderNum(orderNum);
        // 补充编码
        if (StringUtils.isEmpty(baseExecuteOrderFrame.getSecOrderNum())) {
            baseExecuteOrderFrame.setSecOrderNum(orderNum);
        }
        String userId = UserThreadLocalContext.getCurrentUser().getUserId();
        baseExecuteOrderFrame.setUserId(Integer.valueOf(userId));

        // CPM
        buildExecuteFrameCpm(baseExecuteOrderFrame);

        Integer insertCount = baseExecuteOrderFrameRepo.addSelective(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建需求单失败!");
        }

        // 发起工作流
        return this.startWorkFlow(baseExecuteOrderFrame);
    }


    @Override
    public ResultJson<Integer> update(BaseExecuteOrderFrame baseExecuteOrderFrame) {

        // 补充编码
        if (StringUtils.isEmpty(baseExecuteOrderFrame.getSecOrderNum())) {
            baseExecuteOrderFrame.setSecOrderNum(baseExecuteOrderFrame.getOrderNum());
        }


        AssertUtil.assertNotNullOrEmpty(baseExecuteOrderFrame.getSignType(), "签约类型");
        String customSignName = baseExecuteOrderFrame.getCustomSignName();
        String customAdverserName = baseExecuteOrderFrame.getCustomAdverserName();

        AssertUtil.assertNotNullOrEmpty(customSignName, "签约公司");
        AssertUtil.assertNotNullOrEmpty(customAdverserName, "广告主");

        customSignName = customSignName.trim();
        customAdverserName = customAdverserName.trim();

        AssertUtil.assertNotNullOrEmpty(customSignName, "签约公司");
        AssertUtil.assertNotNullOrEmpty(customAdverserName, "广告主");


        BaseCustom customSign = customMananger.findByName(customSignName);
        if (customSign == null) {
            customSign = new BaseCustom();
            customSign.setName(customSignName);
            customSign.setCustomType(baseExecuteOrderFrame.getSignType());
            customMananger.add(customSign);
        }

        baseExecuteOrderFrame.setCustomSignId(customSign.getId());

        Integer customAdverserId = customSign.getId();

        if (customSignName.equals(customAdverserName) && CustomTypeEnum.FA.getValue().equalsIgnoreCase(baseExecuteOrderFrame.getSignType())) {
            throw new ServiceException(MessageConstants.ADVERSER_IS_4A);
        }
        if ( CustomTypeEnum.FA.getValue().equalsIgnoreCase(baseExecuteOrderFrame.getSignType())) {
            BaseCustom customAdverser = customMananger.findByName(customAdverserName);
            baseExecuteOrderFrame.setCustomSignId(customSign.getId());
            if (customAdverser == null) {
                customAdverser = new BaseCustom();
                customAdverser.setName(customAdverserName);
                customAdverser.setCustomType(CustomTypeEnum.MA.getValue());
                customAdverser.setParentId(customSign.getId());
                customMananger.add(customAdverser);
            }
            customAdverserId = customAdverser.getId();
        }
        baseExecuteOrderFrame.setCustomAdverserId(customAdverserId);
        AssertUtil.assertNotNull(baseExecuteOrderFrame.getCustomSignId(), MessageConstants.SIGN_CUSTOM_TYPE_IS_NULL);
        // 补充编码
        if (StringUtils.isEmpty(baseExecuteOrderFrame.getSecOrderNum())) {
            baseExecuteOrderFrame.setSecOrderNum(baseExecuteOrderFrame.getOrderNum());
        }
        // CPM
        buildExecuteFrameCpm(baseExecuteOrderFrame);

        if (baseExecuteOrderFrame.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseExecuteOrderFrameRepo.update(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新需求单失败!");
        }

        // 发起工作流
        return this.startWorkFlow(baseExecuteOrderFrame);
    }

    @Override
    public ResultJson<Integer> updateSelective(BaseExecuteOrderFrame baseExecuteOrderFrame) {
        Integer insertCount = baseExecuteOrderFrameRepo.updateSelective(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新销售框架单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseExecuteOrderFrame> findById(Integer id) {
        ResultJson<BaseExecuteOrderFrame> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        BaseExecuteOrderFrame baseExecuteOrderFrame = baseExecuteOrderFrameRepo.findOne(id);
        result.setData(baseExecuteOrderFrame);

        List<BaseOrderCpmVO> cpmList = cpmManager.findExecuteOrderFrameCpm(baseExecuteOrderFrame.getId());
        baseExecuteOrderFrame.setBaseOrderCpmList(cpmList);

        return result;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {

        BaseExecuteOrderFrame baseExecuteOrderFrame = baseExecuteOrderFrameRepo.findOne(id);
        List<BaseOrderCpmVO> cpmList = cpmManager.findOrderCustomCpm(id);
        baseExecuteOrderFrame.setBaseOrderCpmList(cpmList);

        Integer count = baseExecuteOrderFrameRepo.logicRemove(baseExecuteOrderFrame);
        if (count == 0) {
            throw new ServiceException("需求单不存在！");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);

    }

    private ResultJson<Integer> startWorkFlow(BaseExecuteOrderFrame baseExecuteOrderFrame) {
        LOGGER.info("startWorkFlow-flowType:{}", baseExecuteOrderFrame.getFlowType());
        if (WebConstants.WorkFlow.START.equals(baseExecuteOrderFrame.getFlowType())) {
            String userName = UserThreadLocalContext.getCurrentUser().getUserName();
            String processKey = WebConstants.WORKFLOW_SALE_FRAME;
            LOGGER.debug("userName:{}, processKey:{}", userName, processKey);

            try {
                // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
                identityService.setAuthenticatedUserId(userName);

                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, baseExecuteOrderFrame.getId() + "", new HashMap<String, Object>());
                LOGGER.info(" processInstanceId:{}", processInstance.getId());

                baseExecuteOrderFrame.setProcessInstanceId(processInstance.getId());
                baseExecuteOrderFrame.setStatus(WebConstants.WorkFlow.STATUS_0);
                baseExecuteOrderFrameRepo.updateSelective(baseExecuteOrderFrame);

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
