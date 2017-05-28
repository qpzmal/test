package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.common.utils.ExportExcelUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.controller.BaseController;
import cn.advu.workflow.web.service.zwsc.ZVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zwsc/consume/vip")
public class ZConsumeOfVIPController extends BaseController{
    
    @Autowired
    private ZVipService vipService;
    
    @RequestMapping("/index")
    public String toIndex(){
        return "zwsc/consume/vip/index";
    }
    
    /**
     * 获得消费额度图和总览数据
     * @param request
     * @return
     */
    @RequestMapping("/getAmtChart")
    @ResponseBody
    public ResultJson<Map<String, Object>> getAmtChart(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return vipService.getAmtChart(paramMap);
    }
    
    /**
     * 获取vip开通数据
     * @param request
     * @return
     */
    @RequestMapping("/getOpenData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getOpenData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return vipService.getOpenData(paramMap);
    }

    /**
     * 获取vip开通数据总条数
     * @param request
     * @return
     */
    @RequestMapping("getOpenTotalPages")
    @ResponseBody
    public ResultJson<Integer> getOpenTotalPages(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return vipService.getOpenTotalPages(paramMap);
    }

    /**
     * 导出vip开通数据
     * @param request
     * @return
     */
    @RequestMapping("/exportOpenData")
    public void exportOpenData(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = vipService.exportOpenData(paramMap);
        String name = "vip开通数据";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("vip类型");
        titles.add("开通次数");
        titles.add("次数占页面PV比");
        titles.add("开通用户数");
        titles.add("占页面UV比例");
        titles.add("消费金额");
        titles.add("占总VIP消费比例");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("dt"));
            rowData.add(item.get("vipDays") + "天");
            rowData.add(item.get("timeSum"));
            rowData.add(item.get("timeRate") + "%");
            rowData.add(item.get("userSum"));
            rowData.add(item.get("userRate") + "%");
            rowData.add(item.get("moneySum"));
            rowData.add(item.get("moneyRate") + "%");
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }

    /**
     * vip赠送数据
     * @param request
     * @return
     */
    @RequestMapping("/getDonateData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getDonateData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return vipService.getDonateData(paramMap);
    }

    /**
     * vip赠送总条数
     * @param request
     * @return
     */
    @RequestMapping("getDonateTotalPages")
    @ResponseBody
    public ResultJson<Integer> getDonateTotalPages(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return vipService.getDonateTotalPages(paramMap);
    }

    /**
     * vip消费数据导出
     * @param request
     * @param response
     */
    @RequestMapping("exportChart")
    public void exportChart(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = vipService.exportChart(paramMap);
        String name ="vip数据";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("消费总额");
        titles.add("开通次数");
        titles.add("开通用户数");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowsData = new ArrayList<>();
            rowsData.add(item.get("timeCycle"));
            rowsData.add(item.get("moneySum"));
            rowsData.add(item.get("pvSum"));
            rowsData.add(item.get("uvSum"));
            data.add(rowsData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }

    /**
     * vip开通导出
     * @param request
     * @param response
     */
    @RequestMapping("exportDonate")
    public void exportDonate(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = vipService.exportDonate(paramMap);
        String name ="vip赠送数据";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("VIP类型");
        titles.add("开通次数");
        titles.add("次数占页面PV比");
        titles.add("开通用户数");
        titles.add("占页面UV比例");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("dt"));
            rowData.add(item.get("vipDays") + "天");
            rowData.add(item.get("timeSum"));
            rowData.add(item.get("timeRate") + "%");
            rowData.add(item.get("userSum"));
            rowData.add(item.get("userRate") + "%");
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }



}
