package cn.advu.workflow.web.example.mail;

import cn.advu.workflow.common.utils.mail.MailBean;
import cn.advu.workflow.common.utils.mail.MailUtilVelocity;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiqz on 2017/7/17.
 */
@Controller
@RequestMapping("/example/mail")
public class MailController {
    private static Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    @Value("${mail.from}")
    private String mailFrom;

    @RequestMapping(value = "/index")
    public String index(Model model) {
        return "/example/mail";
    }


    @RequestMapping("sendMail")
    @ResponseBody
    public ResultJson sendMail(String emailAddr, String emailSubject){
        ResultJson result = new ResultJson();

        LOGGER.info("sendMail input:{}, {}", emailAddr, emailSubject);

        MailBean mailBean = new MailBean();
        mailBean.setFrom(mailFrom);
        mailBean.setToEmails(new String[]{emailAddr});
        mailBean.setSubject(emailSubject);
        mailBean.setTemplate("/mails/demo.vm");//设置的邮件模板
        Map data=new HashMap();
        data.put("username", "test-user");
        data.put("url", "http://127.0.0.1:9000");
        data.put("email", "test@test.com");
        mailBean.setData(data);


        try {
            MailUtilVelocity.getInstance().send(mailBean);
        } catch (MessagingException e) {
            LOGGER.warn("", e);
            e.printStackTrace();
        }


        result.setCode(WebConstants.OPERATION_SUCCESS);
        result.setInfo("发送成功");
        return result;
    }

    @RequestMapping("sendSms")
    @ResponseBody
    public ResultJson sendSms(String smsAddr, String smsContent){
        ResultJson result = new ResultJson();

        LOGGER.info("sendSms input:{}, {}", smsAddr, smsContent);

        result.setCode(WebConstants.OPERATION_SUCCESS);
        result.setInfo("发送成功");
        return result;
    }
}
