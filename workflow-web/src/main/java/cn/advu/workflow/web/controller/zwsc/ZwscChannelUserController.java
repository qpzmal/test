package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.common.golbal.AjaxJson;
import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.domain.database.ChannelDataScale;
import cn.advu.workflow.domain.database.ChannelUser;
import cn.advu.workflow.domain.database.ZwscBaseData;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.common.loginContext.LoginTools;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.common.util.ChannelDataUtil;
import cn.advu.workflow.web.common.util.DateUtil;
import cn.advu.workflow.web.service.SysUserService;
import cn.advu.workflow.web.service.ZwscDataService;
import cn.advu.workflow.web.service.zwsc.ZChannelDataScaleService;
import cn.advu.workflow.web.service.zwsc.ZChannelUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 中文书城渠道分成
 */
@Controller
@RequestMapping("/zwsc/channeluser")
public class ZwscChannelUserController {
    private static final Logger LOGGER = Logger.getLogger(ZwscChannelUserController.class);
    @Autowired
    public ZChannelUserService zChannelUserService;
    @Autowired
    public ZChannelDataScaleService zChannelDataScaleService;
    @Autowired
    private ZwscDataService zdataService;
    @Autowired
    private SysUserService userService;
    /**
     * 获取中文书城所有的渠道用户列表
     * @return
     */
    @RequestMapping(value = "/getchanneluserlist")
    public String getProductVersions(Model mode, HttpServletRequest request){
        Integer userId =  null;
        String cnid=request.getParameter("cnid");
        String userName=request.getParameter("username");
        String nickName=request.getParameter("nickname");
        String userType=request.getParameter("userType");//用户类型
        //获取当前用户
        String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);
        if (StringUtils.isNotBlank(loginCookie)) {
            LoginUser loginUser = LoginTools.parseLoginUser(loginCookie);
            userId = loginUser.getUserId().intValue();
        }

        Page page=new Page();
        LOGGER.info("索引:"+request.getParameter("pageIndex"));
        System.out.println(request.getParameter("pageIndex"));
        page.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")==null? "1":request.getParameter("pageIndex")));
        page.setPageSize(Integer.parseInt(request.getParameter("pageSize")==null? "10":request.getParameter("pageSize")));
        //Page channelUserPages = zChannelUserService.getChannelUserPages(cnid, page, userType,userId);
        Page channelUserPages=zChannelUserService.getChannelUserListPages(cnid,userName,nickName,page,userType,userId);
        page = channelUserPages;
        request.setAttribute("page",page);
        request.setAttribute("userType",userType);
        return "zwsc/channeluser/index";
    }
    @RequestMapping(value="toAddChanneluser")
    public String toAddChanneluser(HttpServletRequest request){
        String userType=request.getParameter("userType");//用户类型
        request.setAttribute("userType",userType);
        return "zwsc/channeluser/addchanneluser";
    }

    /**
     * 添加渠道用户
     * @param request
     * @param channeluser
     * @return
     */
    @RequestMapping(value="/addchanneluser")
    public String addChannelDataScale(HttpServletRequest request, ChannelUser channeluser){
      try {
          String newuser=request.getParameter("newuser")==null?"B1":request.getParameter("newuser");
          String activityuser=request.getParameter("activityuser")==null?"B2":request.getParameter("activityuser");
          String income=request.getParameter("income")==null?"B3":request.getParameter("income");
          String cloumper=newuser+","+activityuser+","+income;
          String requestUri = request.getRequestURI();
          String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);

          if (StringUtils.isBlank(loginCookie)) {
              return "redirect:/index";
          }

          LoginUser user = LoginTools.parseLoginUser(loginCookie);
         // LoginUser user = (LoginUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
          LOGGER.info("userId:" + user.getUserId() + "userName:" + user.getUserName());
          Integer updateUser = Integer.parseInt(user.getUserId() + "");
          channeluser.setUpdateUser(updateUser);
      /*    String[] cnids=channeluser.getCnid().split(",");
          for(int i=0;i<cnids.length;i++) {
              channelDat.setCnid(cnids[i]);*/
                channeluser.setPermid("A");
                channeluser.setColumnper(cloumper);
              zChannelUserService.addChannelUser(channeluser);
       //   }
      }catch (Exception e){
          e.printStackTrace();
          LOGGER.info("添加异常");
      }
        return "redirect:/zwsc/channeluser/getchanneluserlist?userType="+channeluser.getUserType()+"";
    }

    /**
     * 更新渠道用户
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "toUpdatechanneluser",method = RequestMethod.GET)
    public String toUpdatechanneluser(HttpServletRequest request, Integer id){
        LOGGER.info("修改的id："+id);
        ChannelUser channelUser=zChannelUserService.getChannelUserById(id);
        String[] columns=channelUser.getColumnper().split(",");
        request.setAttribute("channelUser",channelUser);
        request.setAttribute("newuser",columns[0]);
        request.setAttribute("activityuser",columns[1]);
        request.setAttribute("income",columns[2]);
        return "zwsc/channeluser/toupdatechanneluser";
    }
  /**
     * 修改渠道分成
     * @param request
     *
     * @return
     */
    @RequestMapping(value="/updatechanneluser")
    public String updateChannelUser(HttpServletRequest request, ChannelUser channelUser){
        try {
            String requestUri = request.getRequestURI();
            String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);

            if (StringUtils.isBlank(loginCookie)) {
                return "redirect:/index";
            }
            String newuser=request.getParameter("newuser")==null?"B1":request.getParameter("newuser");
            String activityuser=request.getParameter("activityuser")==null?"B2":request.getParameter("activityuser");
            String income=request.getParameter("income")==null?"B3":request.getParameter("income");
            String cloumper=newuser+","+activityuser+","+income;
            LoginUser user = LoginTools.parseLoginUser(loginCookie);
           // LoginUser user = (LoginUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
            LOGGER.info("userId:" + user.getUserId() + "userName:" + user.getUserName());
            Integer updateUser = Integer.parseInt(user.getUserId() + "");
            channelUser.setUpdateUser(updateUser);
            channelUser.setColumnper(cloumper);
            zChannelUserService.updateChannelUser(channelUser);
        }catch (Exception e){
           LOGGER.error("修改渠道分成出错");
            e.printStackTrace();
        }
        return "redirect:/zwsc/channeluser/getchanneluserlist";
    }

    /**
     * 删除渠道用户
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value="/deletechanneluser" ,method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson deleteChannelDataScale(HttpServletRequest request,Integer id){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            String requestUri = request.getRequestURI();
            String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);
            LoginUser user = LoginTools.parseLoginUser(loginCookie);
           // LoginUser user = (LoginUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
            LOGGER.info("用户Id:" + user.getUserId() + "用户名:" + user.getUserName());
            zChannelUserService.deleteChannelUser(id);
            LOGGER.info("删除渠道用户id"+id);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除成功");
        }catch (Exception e){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除失败");
        }
        return ajaxJson;
    }

    /**
     * 渠道用户登录跳转
     * @param request
     * @return
     */
    @RequestMapping(value="login")
    public String channeluser(HttpServletRequest request){

        return "zwsc/channeluser/channellogin";
    }



    @RequestMapping(value = "doLogin",method = RequestMethod.POST)
    public String doLogin(String userName, String password, HttpServletRequest request) {
        ChannelUser user=  zChannelUserService.getChannelUserByNameAndPass(userName,password);
        Map<String,String> map = new HashMap<String,String>();
        if(user!=null){
            String[] cnids=null;
            if(user.getCnid()!=null && user.getCnid()!="") {
                 cnids = user.getCnid().split(",");
            }
//            //循环cnid获取cnid名称
//            for(String cnid :cnids ){
//                String channelName = zChannelUserService.getChannelName(cnid);
//                map.put(cnid,channelName);
//            }
            request.getSession().setAttribute("cnids",cnids);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("map",map);
            if("pc".equals(user.getUserType())){
                return "pc/channeluser/channel";
            }
            return "zwsc/channeluser/channel";
        }
        request.getSession().setAttribute("msg","error userName or password");
        return "redirect:/zwsc/channeluser/login";
    }

    /**
     * 渠道用户获得查询数据
     * @param request
     * @return
     */
    @RequestMapping(value = "getChannelUserData")
    public String getChannelUserData(HttpServletRequest request){
        ChannelUser u=(ChannelUser)request.getSession().getAttribute("user");
        if(u==null){
            return "redirect:/zwsc/channeluser/login";
        }
        String cloumnper=u.getColumnper();
        String[] cloumns=cloumnper.split(",");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        String cnid=request.getParameter("cnid");
        String cycle=request.getParameter("cycle");
        try {
            LOGGER.info("渠道用户查询---");
            if (startTime == "" || startTime == null || endTime == "" || endTime == null || cnid=="" || cnid==null) {
                return "zwsc/channeluser/channel";
            }
            Page page = new Page();
            page.setPageIndex(Integer.parseInt((request.getParameter("pageIndex") == null || request.getParameter("pageIndex") == "") ? "1" : request.getParameter("pageIndex")));
            page.setPageSize(Integer.parseInt((request.getParameter("pageSize") == null || request.getParameter("pageSize") == "") ? "20" : request.getParameter("pageSize")));
            DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
            String[] cnids = null;
            if ("0".equals(cnid)) {
                LOGGER.info("cnids:" + request.getSession().getAttribute("cnids"));
                cnids = (String[]) request.getSession().getAttribute("cnids");
            } else {
                cnids = new String[1];
                cnids[0] = cnid;
                request.setAttribute("channelid", cnid);
            }
            //计算两个日期之间的时间
            DateUtil dateUtil = new DateUtil();
/*
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String nowdate=sdf.format(d);
*/
            Date date=new Date();//取时间
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE,-1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
            date=calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date);
            List<ChannelDataUtil> channelDatas = new ArrayList<>();
            Calendar now = Calendar.getInstance();
            int hour = now.get(Calendar.HOUR_OF_DAY);
            //如果按月来先循环月再循环日
            if("2".equals(cycle)){
                String[] months=dateUtil.getMonthNum(startTime.substring(0,7),endTime.substring(0,7));
                //遍历月
                for(int k=0;k<months.length;k++) {
                    String[] dates=null;
                    String mothone="01,03,05,07,08,10,12";
                    String mothtwo="04,06,09,11";
                    if(mothone.contains(months[k].substring(5,7))) {
                        dates = dateUtil.getDateStr(months[k] + "-01", months[k] + "-31");
                    }else if(mothtwo.contains(months[k].substring(5,7))){
                        dates = dateUtil.getDateStr(months[k] + "-01", months[k] + "-30");
                    }else{
                        dates = dateUtil.getDateStr(months[k] + "-01", months[k] + "-28");
                    }

                    ChannelDataUtil channelDataUtil = new ChannelDataUtil();
                    channelDataUtil.setDate(months[k]);
                    int newUser = 0;
                    int activeUser = 0;
                    int income = 0;
                    for (int i = 0; i < dates.length; i++) {//先循环日期
                        /*if ((dateString.equals(dates[i]) && hour < 12 && !"cpahz".equals(u.getUserName())) && !"cpshz".equals(u.getUserName())) {
                            LOGGER.info("查询日期：dates[i]--" + dates[i] + "查询时间：" + hour);
                        } else*/if(true) {

                            for (int j = 0; j < cnids.length; j++) {//再遍历渠道
                                ChannelDataScale channelDataScale = zChannelDataScaleService.getChannelShareScale(dates[i], dates[i], cnids[j]);
                                ZwscBaseData zwscBaseData = null;
                                if (channelDataScale != null) {
                                    LOGGER.info("查询时间：dates[i]--" + dates[i] + "查询渠道：cnids[j}" + cnids[j]);
                                    zwscBaseData = zdataService.getZwscBaseDataByChannelAndDate(dates[i], dates[i], cnids[j]);
                                    if (zwscBaseData != null) {
                                        newUser += Integer.valueOf(channelDataScale.getNewUserScale() * zwscBaseData.getNewUser() / 100);
                                        activeUser += Integer.valueOf(channelDataScale.getActiveUserScale() * zwscBaseData.getQdUV() / 100);
                                        income += Integer.valueOf(channelDataScale.getIncomeScale() * zwscBaseData.getChargeMoney());
                                        LOGGER.info("income"+income);
                                    }
                                }
                            }
                        }

                    }
                    channelDataUtil.setNewUser(newUser);
                    channelDataUtil.setActiveUser(activeUser);
                    float num= (float)income/10000;
                    channelDataUtil.setIncome(df.format(num));
                    channelDatas.add(channelDataUtil);
                }
            }else {

                String[] dates = dateUtil.getDateStr(startTime, endTime);

                for (int i = 0; i < dates.length; i++) {//先循环日期
                    ChannelDataUtil channelDataUtil = new ChannelDataUtil();
                    channelDataUtil.setDate(dates[i]);
                    int newUser = 0;
                    int activeUser = 0;
                    int income = 0;
                    if ((dateString.equals(dates[i]) && hour < 12 && !"cpahz".equals(u.getUserName())) && !"cpshz".equals(u.getUserName())) {
                        LOGGER.info("查询日期：dates[i]--" + dates[i] + "查询时间：" + hour);
                    } else {

                        for (int j = 0; j < cnids.length; j++) {//再遍历渠道
                            ChannelDataScale channelDataScale = zChannelDataScaleService.getChannelShareScale(dates[i], dates[i], cnids[j]);
                            ZwscBaseData zwscBaseData = null;
                            if (channelDataScale != null) {
                                LOGGER.info("查询时间：dates[i]--" + dates[i] + "查询渠道：cnids[j}" + cnids[j]);
                                zwscBaseData = zdataService.getZwscBaseDataByChannelAndDate(dates[i], dates[i], cnids[j]);
                                if (zwscBaseData != null) {
                                    newUser += Integer.valueOf(channelDataScale.getNewUserScale() * zwscBaseData.getNewUser() / 100);
                                    activeUser += Integer.valueOf(channelDataScale.getActiveUserScale() * zwscBaseData.getQdUV() / 100);
                                    income += Integer.valueOf(channelDataScale.getIncomeScale() * zwscBaseData.getChargeMoney());
                                }
                            }
                        }
                    }
                    channelDataUtil.setNewUser(newUser);
                    channelDataUtil.setActiveUser(activeUser);
                    float num= (float)income/10000;
                    channelDataUtil.setIncome(df.format(num));
                    channelDatas.add(channelDataUtil);
                }
            }//else
            page.setTotal(channelDatas.size());
            List<ChannelDataUtil> list = new ArrayList<>();
            int index = (page.getPageIndex() - 1) * page.getPageSize();
            int j = 0;
            for (int i = index; j < page.getPageSize() && i < channelDatas.size(); i++) {
                list.add(channelDatas.get(i));
                j++;
            }
            page.setData(list);
            request.setAttribute("page", page);
            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("channelDatas", channelDatas);
            request.setAttribute("newuser",cloumns[0]);
            request.setAttribute("activityuser",cloumns[1]);
            request.setAttribute("income",cloumns[2]);
            request.setAttribute("cycle",cycle);
        }catch(Exception e){
            LOGGER.error("查询渠道数据出错。。。");
            return "zwsc/channeluser/channel";
        }
        return "zwsc/channeluser/channel";
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @RequestMapping(value = "loginout")
    public String loginout(HttpServletRequest request){
        return "redirect:/zwsc/channeluser/login";
    }
}

