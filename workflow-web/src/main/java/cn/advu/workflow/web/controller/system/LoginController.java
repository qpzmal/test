package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.common.golbal.AjaxJson;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.common.exception.LoginException;
import cn.advu.workflow.web.common.loginContext.LoginAccount;
import cn.advu.workflow.web.common.loginContext.LoginTools;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.facade.workflow.ActivitiFacade;
import cn.advu.workflow.web.service.system.LoginService;
import org.apache.commons.lang.StringUtils;
import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.*;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Random;


/**
 * 登录controller
 * @author Niu Qianghong
 *
 */
@Controller
public class LoginController {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private ActivitiFacade activitiFacade;


    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request, HttpSession session){
        return "index";
    }

    @RequestMapping("/login/in")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        //校验是否存在cookie
        //校验cookie里用户的有效性
        //有效跳转到首页，无效跳转到登录页

        String returnURL = RequestUtil.getStringParamDef(request, "returnUrl", Constants.Login.INDEX_URL);

        LOGGER.info("toLogin()...returnUrl:" + returnURL);

        try {
            String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);

            if (StringUtils.isNotBlank(loginCookie)) {

                LoginUser loginUser = LoginTools.parseLoginUser(loginCookie);

                //验证不通过会抛出异常
                loginService.validLoginUser(loginUser);
                //登录状态校验通过，跳转首页
                response.sendRedirect(returnURL);
                return null;
            }

        } catch (LoginException e) {
            LOGGER.error("登录失败：" + e.getMessage());
        }
        model.addAttribute("returnUrl", returnURL);

        return "login";
    }


    @RequestMapping("/login/dologin")
    @ResponseBody
    public AjaxJson doLogin(
            String uname, String passwd, String vcode, Integer remember,
            HttpServletRequest request, HttpServletResponse response) {

        AjaxJson ajaxJson = new AjaxJson();
        LoginAccount account = new LoginAccount();
        try {
            LOGGER.info("name:{}, pw:{}", uname, passwd);

            //登录失败抛出异常
            LoginUser loginUser = loginService.login(uname, passwd, vcode, request);

            if(loginUser != null ){
                account.setUser(loginUser);
                // 获取用户菜单信息
                loginService.queryUserFunction(account);
            } else {
                LOGGER.warn("loginuser is null. loginname is :{}, pw:{}", uname, passwd);
            }

            String cookieStr = LoginTools.toCookieStr(loginUser);

            if (remember != null && remember.intValue() == 1) {
                // 30天cookie有效期
                RequestUtil.addCookie(response, Constants.Login.LOGIN_COOKIE_KEY, cookieStr, Constants.Login.COOKIE_EXPIRY_30_DAYS);
            } else {

                RequestUtil.addCookie(response, Constants.Login.LOGIN_COOKIE_KEY, cookieStr);
            }

//            RedisClient redisClient = (RedisClient) SpringContextUtil.getBean("redisClient");
//            String str = redisClient.getStr("login_permissions_key_" + loginUser.getUserId().toString());
//            if( str == null || str.equals("")){
//                List<SysPermission> permissions = account.getPermissions();
//                JSONArray json = JSONArray.fromObject(permissions);
//                redisClient.set("login_permissions_key_"+loginUser.getUserId().toString(),json.toString());
//            }
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("登录成功");
        } catch (Exception ex) {


            LOGGER.info("登录失败: uname=" + uname, ex);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(ex.getMessage());

        }
        LOGGER.debug("login info：{}", ajaxJson);
        return ajaxJson;
    }


    /**
     * 生成验证码图片io流
     */
    @RequestMapping(value = "/login/generateImage")
    public void ImageCaptcha(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        switch (random.nextInt(5)) {
            case 0:
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
        }

        setResponseHeaders(response);
        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());

        LoginTools.setVCode(token, request);
    }


    protected void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }


    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    private static Random random = new Random();

    static {
        cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("0123456789");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setHeight(40);
        cs.setWidth(98);
        cs.setWordFactory(wf);
    }
}
