package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.common.utils.ExportExcelUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.zwsc.ZChargeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zwsc/charge")
public class ZChargeDataController {
    
    @Autowired
    private ZChargeDataService chargeService;
    
    @RequestMapping("/index")
    public String toChargeIndex(){
        return "zwsc/charge/index";
    }
    
    /**
     * 按日查询返回充值数据图表
     * @param request 条件map, 包括版本, 时间范围
     * @return
     */
    @RequestMapping("/getTrendChart")
    @ResponseBody
    public ResultJson<Map<String, Object>> getChargeTrendByDay(HttpServletRequest request){
        
        Map<String, Object> paramMap = handleParams(request);
        return chargeService.getTrendEcharts(paramMap);
    }
    
    /**
     * 导出按日统计充值数据
     * @param request
     * @return
     */
    @RequestMapping("/exportTrend")
    public void exportChargeTrend(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = chargeService.exportTrendChart(paramMap);
        String name = "充值统计";
        List<String> titles = new ArrayList<>();
        titles.add("日志");
        titles.add("充值金额");
        titles.add("充值次数");
        titles.add("充值用户数");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("timeCycle"));
            rowData.add(item.get("chargeAmt"));
            rowData.add(item.get("chargeTime"));
            rowData.add(item.get("chargeUsers"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }
    
    /**
     * 分页获取充值方式统计表格数据
     * @param request
     * @return
     */
    @RequestMapping("/getWayPageData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getChargeWayTable(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return chargeService.getWayPageData(paramMap);
    }
    
    /**
     * 获取充值方式统计数据总页数
     * @param request
     * @return
     */
    @RequestMapping("/getWayTotalPages")
    @ResponseBody
    public ResultJson<Integer> getChargeWayTotalPages(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return chargeService.getWayTotalPages(paramMap);
    }
    
    /**
     * 获取赠送铜币数分页数据
     * @param request
     * @return
     */
    @RequestMapping("/getGivePageData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getGiveAmtTable(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return chargeService.getGivePageData(paramMap);
    }
    
    /**
     * 获取充值赠送额度统计数据总页数
     * @param request
     * @return
     */
    @RequestMapping("/getGiveTotalPages")
    @ResponseBody
    public ResultJson<Integer> getChargeGiveTotalPages(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return chargeService.getGiveTotalPages(paramMap);
    }
    
    /**
     * 充值数据页面的参数处理 
     * @param request
     * @return
     */
    private Map<String, Object> handleParams(HttpServletRequest request){
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String cnidsStr = request.getParameter("cnids");
        String version = request.getParameter("version");

        String[] cnids = null;
        //获得并处理填写的渠道号
        if(!"".equals(cnidsStr)){
            cnids = cnidsStr.split("\\+");
        }
        
        if("-1".equals(version)){
            version = "";
        }
        Map<String, Object> paramMap = new HashMap<>();
        if(request.getParameter("timeCycle") != null){
            Integer timeCycle = Integer.valueOf(request.getParameter("timeCycle"));
            paramMap.put("timeCycle", timeCycle);
        }
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        paramMap.put("cnids", cnids);
        paramMap.put("version", version);
        return paramMap;        
    }
    
    /**
     * 跳转到某种充值方式的详细页面
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/toWayDetail")
    public String viewWayDetail(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        Map<String, Object> paramMap = handleParams(request);
        Integer wayCode = Integer.valueOf(request.getParameter("wayCode"));
        String dt = request.getParameter("startTime");
        String chargeWay = new String(request.getParameter("chargeWay").getBytes("iso-8859-1"), "utf-8");
        paramMap.put("wayCode", wayCode);
        int sum = chargeService.getWaySumMoney(paramMap);
        model.addAttribute("params", paramMap);
        model.addAttribute("sumMoney", sum);
        model.addAttribute("chargeWay", chargeWay);
        model.addAttribute("dt", dt);
        //获得充值方式的总额
        return "zwsc/charge/way_detail";
    }
    
    @RequestMapping("/getWayDetail")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getWayDetailData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer wayCode = Integer.valueOf(request.getParameter("wayCode"));
        paramMap.put("wayCode", wayCode);
        return chargeService.getWayDetailData(paramMap);   
    }

    /**
     * 充值方式导出
     * @param request
     * @param response
     */
    @RequestMapping("/exportWay")
    public void exprotWayData(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = chargeService.exportWayData(paramMap);
        String name = "充值方式统计";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("充值方式");
        titles.add("金额");
        titles.add("金额占比");
        titles.add("PV");
        titles.add("PV占比");
        titles.add("UV");
        titles.add("UV占比");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("dt"));
            rowData.add(item.get("chargeWay"));
            rowData.add(item.get("chargeMoney"));
            rowData.add(item.get("moneyRate"));
            rowData.add(item.get("chargePV"));
            rowData.add(item.get("pvRate"));
            rowData.add(item.get("chargeUV"));
            rowData.add(item.get("uvRate"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }
    
}
