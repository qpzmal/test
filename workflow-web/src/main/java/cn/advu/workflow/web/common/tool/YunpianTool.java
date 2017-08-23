package cn.advu.workflow.web.common.tool;

import cn.advu.workflow.common.utils.HttpPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiqz on 2017/8/23.
 * 使用www.yunpian.com服务发送短信
 *
 * https://www.yunpian.com/api2.0/scene-notify.html
 *
 */
public class YunpianTool {

    private static Logger LOGGER = LoggerFactory.getLogger(YunpianTool.class);

    // 成功注册后登录云片官网,进入后台可查看
    private static final String API_KEY = "";

    // 批量发送--批量发送相同内容
    private static final String URL_BATCH_SEND = "https://sms.yunpian.com/v2/sms/batch_send.json";

    // 批量个性化发送--批量发送不同内容
    private static final String URL_MULTI_SEND = "https://sms.yunpian.com/v2/sms/multi_send.json";

    public static final String CONTEXT_1913610 = "【喂呦科技】您有一个由#demand_user_name#发起的申请，请您及时处理！";

    /**
     * 批量发送短信,相同内容多个号码,智能匹配短信模板
     *
     * @param text   需要使用已审核通过的模板或者默认模板
     * @param mobile 接收的手机号,多个手机号用英文逗号隔开
     * @return json格式字符串
     */
    public static String batchSend(String text, String mobile) {
        Map<String, String> params = new HashMap<>();//请求参数集合
        params.put("apikey", API_KEY);
        params.put("text", text);
        params.put("mobile", mobile);
        String httpResult = HttpPoolUtil.sendHttpPost(params, URL_MULTI_SEND);

        LOGGER.info("batchSend-result:{}", httpResult);
        return httpResult;//请自行使用post方式请求,可使用Apache HttpClient
    }
}
