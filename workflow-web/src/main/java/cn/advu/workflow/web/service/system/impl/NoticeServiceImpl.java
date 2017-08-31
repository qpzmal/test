package cn.advu.workflow.web.service.system.impl;

import cn.advu.workflow.common.utils.mail.MailBean;
import cn.advu.workflow.common.utils.mail.MailUtilVelocity;
import cn.advu.workflow.dao.fcf_vu.SysFuctionMapper;
import cn.advu.workflow.dao.fcf_vu.SysUserMapper;
import cn.advu.workflow.domain.fcf_vu.SysInfo;
import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import cn.advu.workflow.repo.fcf_vu.SysRoleRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRoleRepo;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.common.tool.YunpianTool;
import cn.advu.workflow.web.example.mail.sms163.SendNeteaseTemplate;
import cn.advu.workflow.web.manager.SysInfoMananger;
import cn.advu.workflow.web.service.system.NoticeService;
import net.sf.json.JSONArray;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {
    private static Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);


    @Value("${mail.from}")
    private String mailFrom;

    @Autowired
    private SysRoleRepo sysRoleRepo;

    @Autowired
    private SysUserRoleRepo sysUserRoleRepo;

    @Autowired
    private SysUserRepo sysUserRepo;

    @Autowired
    private SysInfoMananger sysInfoMananger;

    @Autowired
    protected TaskService taskService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysFuctionMapper sysFuctionMapper;

    public void doNotify(String taskId, String template) {
        LOGGER.info("taskId:{}, template:{}", taskId, template);

        // 获取审核人列表
        List<IdentityLink> identityLinkList =  taskService.getIdentityLinksForTask(taskId);
        LOGGER.info("identityLinkList-size:{}", identityLinkList.size());
        for (IdentityLink identityLink : identityLinkList) {
            LOGGER.debug("任务ID：" + identityLink.getTaskId());
            LOGGER.debug("流程实例ID：" + identityLink.getProcessInstanceId());
            LOGGER.debug("用户ID：" + identityLink.getUserId());
            LOGGER.debug("工作流角色ID：" + identityLink.getGroupId());
            LOGGER.debug("#########################################");

            SysRole sysRole = sysRoleRepo.queryByActivitiName(identityLink.getGroupId());

            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            if (sysRole == null) {
                SysUserRole sysUserRole = new SysUserRole();
                SysUser sysUser = sysUserRepo.findByIdAndName(null, identityLink.getUserId());
                sysUserRole.setAdmins(sysUser.getId());
                sysUserRoleList.add(sysUserRole);
            } else {
                sysUserRoleList = sysUserRoleRepo.findRoleUser(sysRole.getId());
            }

            List<String> mailList = new ArrayList<>();
            List<String> mobileList = new ArrayList<>();
            for (SysUserRole user:sysUserRoleList) {
                SysUser dbUser = sysUserRepo.findOne(user.getAdmins());
                mailList.add(dbUser.getEmail());
                mobileList.add(dbUser.getMobile());
            }

            SysInfo sysInfo = sysInfoMananger.querySysInfo();
            if (1 == sysInfo.getEmailSwitch()) {
                // 发送邮件
                sendMail(mailList, template);
            }

            if (1 == sysInfo.getSmsSwitch()) {
                // 发送短信
//            sendSmsByNetease(mobileList);
                sendSmsByYunpian(mobileList);
            }

        }
    }


    private void sendMail(List<String> mailList, String template) {
        // 发送邮件
        MailBean mailBean = new MailBean();
        mailBean.setFrom(mailFrom);
        mailBean.setToEmails((String[]) mailList.toArray(new String[mailList.size()]));
        if (WebConstants.Notify.TEMPLATE_DEMAND.equals(template)) {
            mailBean.setSubject("您有新申请可处理");
            mailBean.setTemplate("/mails/demand.vm");//设置的邮件模板
        } else if (WebConstants.Notify.TEMPLATE_REMIND.equals(template)) {
            mailBean.setSubject("您有新的收款提醒");
            mailBean.setTemplate("/mails/reminder.vm");//设置的邮件模板

        } else {
            LOGGER.warn("错误的模版类型：{}", template);
        }
        Map data=new HashMap();
        data.put("username", UserThreadLocalContext.getCurrentUser().getUserName());
        mailBean.setData(data);
        try {
            MailUtilVelocity.getInstance().send(mailBean);
        } catch (MessagingException e) {
            LOGGER.warn("", e);
            e.printStackTrace();
        }

    }

    // 使用网易短信服务
    private void sendSmsByNetease(List<String> mobileList) {
        // 发送短信
        List<String> paramsList = new ArrayList<>();
        String smsContent = UserThreadLocalContext.getCurrentUser().getUserName();
        paramsList.add(smsContent);
        paramsList.add("【"+ smsContent +"】");

        try {
            SendNeteaseTemplate.send(JSONArray.fromObject(mobileList.toArray()).toString(), JSONArray.fromObject(paramsList.toArray()).toString());
        } catch (Exception e) {
            LOGGER.error("短信发送出错：", e);
        }
    }
    // 使用云片短信服务
    private void sendSmsByYunpian(List<String> mobileList) {
        // 发送短信
        String param = UserThreadLocalContext.getCurrentUser().getUserName();
        String text = YunpianTool.CONTEXT_1913610.replace("#demand_user_name#", param);

        StringBuffer sb = new StringBuffer();
        for(String mobile:mobileList) {
            sb.append(mobile).append(",");
        }
        String mobiles = sb.toString();
        mobiles = mobiles.substring(0, mobiles.length()-1);

        try {
            YunpianTool.batchSend(text, mobiles);
        } catch (Exception e) {
            LOGGER.error("短信发送出错：", e);
        }
    }

}
