package cn.advu.workflow.web.controller.cxb;

import cn.advu.workflow.common.utils.ExportExcelUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.controller.BaseController;
import cn.advu.workflow.web.service.cxb.CxbPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创新版消耗积分controller
 * @author Niu Qianghong
 *
 */
@Controller
@RequestMapping("cxb/consumePoint")
public class CConsumePointController extends BaseController{
    
    @Autowired
    private CxbPointService pointService;
    
    @RequestMapping("/index")
    public String toIndex(){
        return "/cxb/consumePoint/index";
    }
    
    /**
     * 获取消耗积分额度图数据
     * @param request
     * @return
     */
    @RequestMapping("/getChart")
    @ResponseBody
    public ResultJson<Map<String, Object>> getChart(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return pointService.getConsumeAmtChart(paramMap);
    }
    
    /**
     * 获取消耗积分方式数据
     * @param request
     * @return
     */
    @RequestMapping("/getTypeData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getTypeData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return pointService.getConsumeTypeData(paramMap);
    }

    /**
     * 获取消耗积分方式数据总条数
     * @param request
     * @return
     */
    @RequestMapping("/getTypeDataCnt")
    @ResponseBody
    public ResultJson<Integer> getTypeDataCnt(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return pointService.getTypeDataCnt(paramMap);
    }
    
    /**
     * 导出消耗积分方式数据
     * @return
     */
    @RequestMapping("/exportTypeData")
    public void exportTypeData(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = pointService.exportConsumeTypeData(paramMap);
        String name = "积分消耗";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("消耗方式");
        titles.add("积分数");
        titles.add("次数");
        titles.add("用户数");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("dt").toString());
            rowData.add(item.get("consumeType").toString());
            rowData.add(item.get("pointSum").toString());
            rowData.add(item.get("timeSum").toString());
            rowData.add(item.get("userSum").toString());
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }
    
}
