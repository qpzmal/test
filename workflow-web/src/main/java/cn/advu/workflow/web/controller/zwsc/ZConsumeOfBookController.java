package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.common.utils.ExportExcelUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.zwsc.ZConsumeBookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购买图书页面controller
 * @author Niu Qianghong
 *
 */
@Controller
@RequestMapping("zwsc/consume/book")
public class ZConsumeOfBookController {

    @Autowired
    private ZConsumeBookService cbService;
    
    @RequestMapping("/index")
    public String toBookIndex(){
        return "zwsc/consume/book/index";
    }
    
    @RequestMapping("getTypeTotalPages")
    @ResponseBody
    public ResultJson<Integer> getTypeTotalPages(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return cbService.getTypeTotalPages(paramMap);
    }    
    
    /**
     * 分页获取购买类型统计数据
     * @param request
     * @return
     */
    @RequestMapping("/getTypeDataByPage")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getTypeDataByPage(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        return cbService.getTypeDataByPage(paramMap, pageNo);
    }
    
    @RequestMapping("/getAmtChart")
    @ResponseBody
    public ResultJson<Map<String, Object>> getAmtChartData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return cbService.getAmtChartData(paramMap);
    }
    
    @RequestMapping("/exportTypeData")
    public void exportTypeData(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = cbService.exportTypeData(paramMap);
        String name = "图书购买类型统计";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("后10章购买次数");
        titles.add("后10章用户数");
        titles.add("后40章购买次数");
        titles.add("后40章用户数");
        titles.add("后100章购买次数");
        titles.add("后100章用户数");
        titles.add("未购全部购买次数");
        titles.add("未购全部用户数");
        titles.add("单章购买购买次数");
        titles.add("单章购买用户数");
        titles.add("整本购买次数");
        titles.add("整本用户数");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("dt"));
            rowData.add(item.get("chapter10PV"));
            rowData.add(item.get("chapter10UV"));
            rowData.add(item.get("chapter40PV"));
            rowData.add(item.get("chapter40UV"));
            rowData.add(item.get("chapter100PV"));
            rowData.add(item.get("chapter100UV"));
            rowData.add(item.get("notAllPV"));
            rowData.add(item.get("notAllUV"));
            rowData.add(item.get("chapter1PV"));
            rowData.add(item.get("chapter1UV"));
            rowData.add(item.get("allChapterPV"));
            rowData.add(item.get("allChapterUV"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }
    
    /**
     * 页面参数处理 
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
        if(StringUtils.isNotBlank(cnidsStr)){
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
    


    @RequestMapping("exportChart")
    public void exportChart(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = cbService.exportChart(paramMap);
        String name ="购买图书消费数据";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("铜币");
        titles.add("代金券");
        titles.add("购买次数");
        titles.add("购买用户");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("timeCycle"));
            rowData.add(item.get("totalMoney"));
            rowData.add(item.get("totalCash"));
            rowData.add(item.get("totalPV"));
            rowData.add(item.get("totalUV"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }
    
}
