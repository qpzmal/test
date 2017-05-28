package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.common.utils.ExportExcelUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.controller.BaseController;
import cn.advu.workflow.web.service.zwsc.ZChannelService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/zwsc/channel")
public class ZChannelController extends BaseController{


    private static Logger LOGGER = Logger.getLogger(ZChannelController.class);

    @Autowired
    private ZChannelService channelService;
 
    @RequestMapping("/detail")
    public String toDetail(HttpServletRequest request, Model model){
        String dt = request.getParameter("dt");
        model.addAttribute("dt", dt);
        return "zwsc/channel/detail";
    }
    
    /**
     * 获取详细数据总页数
     * @return
     */
    @RequestMapping("/getDetailTotalPages")
    @ResponseBody
    public ResultJson<Integer> getDetailTotalPages(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return channelService.getDetailTotalPages(paramMap);        
    }
    
    /**
     * 获取详细分页数据
     * @return
     */
    @RequestMapping("/getDetailPageData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getDetailPageData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return channelService.getDetailPageData(paramMap);
    }

    /**
     * 导出详细数据
     * @param request
     * @return
     */
    @RequestMapping("/exportDetailData")
    public void exportDetailData(HttpServletRequest request,HttpServletResponse response){
        try {
            Map<String, Object> paramMap = handleParams(request);
            List<Map<String, Object>> maps = channelService.exportDetail(paramMap);
            String name = "数据总览渠道详细";
            List<String> titles = new ArrayList<>();
            titles.add("渠道");
            titles.add("累计用户");
            titles.add("启动用户");
            titles.add("新增用户");
            titles.add("启动次数");
            titles.add("充值用户");
            titles.add("充值额度");
            titles.add("消费铜币用户");
            titles.add("消费真钱用户");
            titles.add("消费真钱");
            titles.add("消费铜币");
            titles.add("消费代金券");
            titles.add("阅读图书");
            titles.add("阅读章节");
            titles.add("启动留存");
            titles.add("新增留存");
            List<List<Object>> data = new ArrayList<>();
            for(Map<String, Object> item:maps){
                List<Object> rowData = new ArrayList<>();
                rowData.add(item.get("cnid"));
                rowData.add(item.get("accumUsers"));
                rowData.add(item.get("qdUsers"));
                rowData.add(item.get("newUsers"));
                rowData.add(item.get("qdTimes"));
                rowData.add(item.get("chargeUsers"));
                rowData.add(item.get("chargeMoney"));
                rowData.add(item.get("consumeUsers"));
                rowData.add(item.get("consumerMoneyUser"));
                rowData.add(item.get("realMoney"));
                rowData.add(item.get("copperCoin"));
                rowData.add(item.get("vouchers"));
                rowData.add(item.get("bookNum"));
                rowData.add(item.get("chapterNum"));
                rowData.add(item.get("qdRemain"));
                rowData.add(item.get("addRemain"));
                data.add(rowData);
            }
            ExportExcelUtil.export(response,name,titles,data);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    

    @RequestMapping("/index")
    public String toIndex(){
        return "zwsc/channel/index";
    }
    
    /**
     * 获取渠道用户数据for生成表格
     * @param request
     * @return
     */
    @RequestMapping("/getUserDataChart")
    @ResponseBody
    public ResultJson<Echarts<BigDecimal>> getUserData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return channelService.getUserDataChart(paramMap);
    }
    
    /**
     * 导出用户数据
     * @param request
     * @return
     */
    @RequestMapping("/exportUserData")
    public void exportUserData(HttpServletRequest request,HttpServletResponse response){
        try {
            Map<String, Object> paramMap = handleParams(request);
            List<Map<String, Object>> maps = channelService.exportUserData(paramMap);
            String name ="渠道用户数据";
            List<String> titles = new ArrayList<>();
            titles.add("日期");
            titles.add("启动用户");
            titles.add("新增用户");
            titles.add("充值用户");
            titles.add("消费用户");
            List<List<Object>> data = new ArrayList<>();
            for(Map<String, Object> item:maps){
                List<Object> rowData = new ArrayList<>();
                rowData.add(item.get("timeCycle"));
                rowData.add(item.get("qdUsers"));
                rowData.add(item.get("newUsers"));
                rowData.add(item.get("chargeUsers"));
                rowData.add(item.get("consumeUsers"));
                data.add(rowData);
            }
            ExportExcelUtil.export(response,name,titles,data);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    

    /**
     * 获取总览数据总页数
     * @param request
     * @return
     */
    @RequestMapping("/getSummaryPageCnt")
    @ResponseBody
    public ResultJson<Integer> getSummaryPageCnt(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return channelService.getSummaryPageCnt(paramMap);
    }

    /**
     * 数据总览列表
      * @param request
     * @return
     */
    @RequestMapping("/getSummaryPageData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getSummaryPageData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        paramMap.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
        return channelService.getSummaryPageData(paramMap);
    }

    /**
     * 导出数据总览
     * @param request
     * @param response
     */
    @RequestMapping("exportSummaryPageData")
    public void exportSummaryPageData(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, Object> paramMap = handleParams(request);
            List<Map<String, Object>> maps = channelService.exportSummaryPageData(paramMap);
            String name = "数据总览";
            List<String> titles = new ArrayList<>();
            titles.add("日期");
            titles.add("累计用户");
            titles.add("启动用户");
            titles.add("新增用户");
            titles.add("启动次数");
            titles.add("充值用户");
            titles.add("充值额度");
            titles.add("消费铜币用户");
            titles.add("消费真钱用户");
            titles.add("消费真钱");
            titles.add("消费铜币");
            titles.add("消费代金券");
            titles.add("阅读图书");
            titles.add("阅读章节");
            titles.add("启动留存");
            titles.add("新增留存");
            List<List<Object>> data = new ArrayList<>();
            for(Map<String, Object> item:maps){
                List<Object> rowData = new ArrayList<>();
                rowData.add(item.get("dt"));
                rowData.add(item.get("accumUsers"));
                rowData.add(item.get("qdUsers"));
                rowData.add(item.get("newUsers"));
                rowData.add(item.get("qdTimes"));
                rowData.add(item.get("chargeUsers"));
                rowData.add(item.get("chargeMoney"));
                rowData.add(item.get("consumeUsers"));
                rowData.add(item.get("consumerMoneyUser"));
                rowData.add(item.get("realMoney"));
                rowData.add(item.get("copperCoin"));
                rowData.add(item.get("vouchers"));
                rowData.add(item.get("bookNum"));
                rowData.add(item.get("chapterNum"));
                rowData.add(item.get("qdRemain"));
                rowData.add(item.get("addRemain"));
                data.add(rowData);
            }
            ExportExcelUtil.export(response,name,titles,data);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }
    @RequestMapping("channelDetail")
    public String channelDetail(){
        return "zwsc/channel/channelDetail";
    }

    @RequestMapping("getChannelDetailPages")
    @ResponseBody
    public ResultJson<Integer> getChannelDetailPages(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Map<String, Object> mapData = getBeforeTime(paramMap);
        return channelService.getChannelDetailPages(mapData);
    }

    @RequestMapping("/getChannelDetailPageData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getChannelDetailPageData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        Map<String, Object> mapData = getBeforeTime(paramMap);
        mapData.put("pageNo", pageNo);
        return channelService.getChannelDetailPageData(mapData);
    }

    @RequestMapping("/exportChannelDetail")
    public void exportChannelDetail(HttpServletRequest request,HttpServletResponse response){
        try {
            Map<String, Object> paramMap = handleParams(request);
            List<Map<String, Object>> maps = channelService.exportChannelDetail(paramMap);
            String name = "渠道详细日期分类";
            List<String> titles = new ArrayList<>();
            titles.add("渠道");
            titles.add("日期");
            titles.add("累计用户");
            titles.add("启动用户");
            titles.add("新增用户");
            titles.add("启动次数");
            titles.add("充值用户");
            titles.add("充值额度");
            titles.add("消费铜币用户");
            titles.add("消费真钱用户");
            titles.add("消费真钱");
            titles.add("消费铜币");
            titles.add("消费代金券");
            titles.add("阅读图书");
            titles.add("阅读章节");
            titles.add("启动留存");
            titles.add("新增留存");
            List<List<Object>> data = new ArrayList<>();
            for(Map<String, Object> item:maps){
                List<Object> rowData = new ArrayList<>();
                rowData.add(item.get("cnid"));
                rowData.add(item.get("dt"));
                rowData.add(item.get("accumUsers"));
                rowData.add(item.get("qdUsers"));
                rowData.add(item.get("newUsers"));
                rowData.add(item.get("qdTimes"));
                rowData.add(item.get("chargeUsers"));
                rowData.add(item.get("chargeMoney"));
                rowData.add(item.get("consumeUsers"));
                rowData.add(item.get("consumerMoneyUser"));
                rowData.add(item.get("realMoney"));
                rowData.add(item.get("copperCoin"));
                rowData.add(item.get("vouchers"));
                rowData.add(item.get("bookNum"));
                rowData.add(item.get("chapterNum"));
                rowData.add(item.get("qdRemain"));
                rowData.add(item.get("addRemain"));
                data.add(rowData);
            }
            ExportExcelUtil.export(response,name,titles,data);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * 开始、结束时间为空，将前一天时间赋予
     * @param paramMap
     * @return
     */
    public Map<String, Object> getBeforeTime(Map<String, Object> paramMap){
        String startTime = (String) paramMap.get("startTime");
        String endTime =  (String) paramMap.get("endTime");
        if(StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)){

            paramMap.remove("startTime");
            paramMap.remove("endTime");

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
            Date date = new Date();   //当前时间
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(date);//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
            date = calendar.getTime();   //得到前一天的时间
            String deforeDate  = sdf.format(date);    //格式化前一天

            paramMap.put("startTime",deforeDate);
            paramMap.put("endTime",deforeDate);
        }

        return paramMap;
    }


}
