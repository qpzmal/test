package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.common.golbal.AjaxJson;
import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.domain.database.ChannelDataScale;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.common.loginContext.LoginTools;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.service.zwsc.ZChannelDataScaleService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 中文书城渠道分成
 */
@Controller
@RequestMapping("/zwsc/channelshare")
public class ZwscChannelShareController {
    private static final Logger LOGGER = Logger.getLogger(ZwscChannelShareController.class);
    @Autowired
    public ZChannelDataScaleService zChannelDataScaleService;
    
    /**
     * 获取中文书城所有的渠道分成列表
     * @return
     */
    @RequestMapping(value = "/getchannelsharelist")
    public String getProductVersions(Model mode, HttpServletRequest request){
        Integer userId  = null;
        //当前用户
        String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);
        if (StringUtils.isNotBlank(loginCookie)) {
            LoginUser loginUser = LoginTools.parseLoginUser(loginCookie);
            userId = loginUser.getUserId().intValue();
        }

        String cnid=request.getParameter("cnid");
        String scaleType=request.getParameter("scaleType");//分成类型
        Page page=new Page();
        LOGGER.info("索引:"+request.getParameter("pageIndex"));
        System.out.println(request.getParameter("pageIndex"));
        page.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")==null? "1":request.getParameter("pageIndex")));
        page.setPageSize(Integer.parseInt(request.getParameter("pageSize")==null? "10":request.getParameter("pageSize")));
        page=   zChannelDataScaleService.getChannelDateScalesPages(cnid,page,scaleType,userId);
        request.setAttribute("page",page);
        request.setAttribute("scaleType",scaleType);
        return "zwsc/channelshare/index";
    }
    @RequestMapping(value="toAddChannelDataScale")
    public String toAddChannelDataScale(HttpServletRequest request){
        List<Integer> scales =new ArrayList<>();
        for(int i=0;i<=100 ;i++){
            scales.add(i);
        }
        String scaleType=request.getParameter("scaleType");//分成类型
        request.setAttribute("scales",scales);
        request.setAttribute("scaleType",scaleType);
        String type=request.getParameter("type");
        if("1".equals(type)){//已经存在分成比例
            String cnid=request.getParameter("cnid");
            request.setAttribute("msg",cnid+"渠道已经存在该时间段的分成比例");
        }
        return "zwsc/channelshare/addchannelsharescale";
    }
    @RequestMapping(value="/addchanneldatashare")
    public String addChannelDataScale(HttpServletRequest request, ChannelDataScale channelDateScale){
      try {
          String requestUri = request.getRequestURI();
          String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);

          if (StringUtils.isBlank(loginCookie)) {
              return "redirect:/index";
          }

          LoginUser user = LoginTools.parseLoginUser(loginCookie);
         // LoginUser user = (LoginUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
          LOGGER.info("userId:" + user.getUserId() + "userName:" + user.getUserName());
          Integer updateUser = Integer.parseInt(user.getUserId() + "");
          channelDateScale.setUpdateUser(updateUser);
          String[] cnids=channelDateScale.getCnid().split(",");
          for(int i=0;i<cnids.length;i++) {
              channelDateScale.setCnid(cnids[i]);
              //先检查这个渠道这个时间段是否已经配置了渠道分成比例，如果有给出反馈，如果没有就添加
              ChannelDataScale  ExchannelDataScale = zChannelDataScaleService.findExchannelDataScale(channelDateScale);
              if(ExchannelDataScale!=null){
                  //已经存在分成比例了
                  return "redirect:/zwsc/channelshare/toAddChannelDataScale?scaleType="+channelDateScale.getScaleType()+"&type=1&cnid="+cnids[i]+"";
              }
          }
          for(int i=0;i<cnids.length;i++) {
              channelDateScale.setCnid(cnids[i]);
              zChannelDataScaleService.addChannelScale(channelDateScale);
          }
      }catch (Exception e){
          e.printStackTrace();
          LOGGER.info("添加异常");
      }
        return "redirect:/zwsc/channelshare/getchannelsharelist?scaleType="+channelDateScale.getScaleType()+"";
    }
    public String toUpdateChannelDataScale(HttpServletRequest request){
        Integer id= Integer.parseInt(request.getParameter("id"));
        ChannelDataScale channelDataScale=  zChannelDataScaleService.getChannelDataScaleById(id);
        return "";
    }
    @RequestMapping(value = "toUpdatechanneldata",method = RequestMethod.GET)
    public String toUpdatechanneldata(HttpServletRequest request, Integer id){
        LOGGER.info("修改的id："+id);
        ChannelDataScale channelDataScale=zChannelDataScaleService.getChannelDataScaleById(id);
        List<Integer> scales =new ArrayList<>();
        for(int i=0;i<=100 ;i++){
            scales.add(i);
        }
        request.setAttribute("scales",scales);
        request.setAttribute("channelDataScale",channelDataScale);
        return "zwsc/channelshare/toupdatechanneldata";
    }
    /**
     * 修改渠道分成
     * @param request
     * @param channelDateScale
     * @return
     */
    @RequestMapping(value="/updatechanneldatashare")
    public String updateChannelDataScale(HttpServletRequest request, ChannelDataScale channelDateScale){
        try {

            String requestUri = request.getRequestURI();
            String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);

            if (StringUtils.isBlank(loginCookie)) {
                return "redirect:/index";
            }

            LoginUser user = LoginTools.parseLoginUser(loginCookie);

           // LoginUser user = (LoginUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
            LOGGER.info("userId:" + user.getUserId() + "userName:" + user.getUserName());
            Integer updateUser = Integer.parseInt(user.getUserId() + "");
            channelDateScale.setUpdateUser(updateUser);
            String[] cnids=channelDateScale.getCnid().split(",");
            for(int i=0;i<cnids.length;i++) {
                channelDateScale.setCnid(cnids[i]);
                zChannelDataScaleService.updateChannelScale(channelDateScale);
            }
        }catch (Exception e){
           LOGGER.error("修改渠道分成出错");
            e.printStackTrace();
        }
        return "redirect:/zwsc/channelshare/getchannelsharelist";
    }
    @RequestMapping(value="/deletechanneldatashare" ,method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson deleteChannelDataScale(HttpServletRequest request,Integer id){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            String requestUri = request.getRequestURI();
            String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);

            LoginUser user = LoginTools.parseLoginUser(loginCookie);
           // LoginUser user = (LoginUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
            LOGGER.info("用户Id:" + user.getUserId() + "用户名:" + user.getUserName());

            zChannelDataScaleService.deleteChannelScale(id);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除成功");
        }catch (Exception e){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除失败");
        }
        return ajaxJson;
    }
}
