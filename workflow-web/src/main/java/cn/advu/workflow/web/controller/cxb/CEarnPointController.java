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
 * 创新版赚取积分controller
 * @author Niu Qianghong
 *
 */

@Controller
@RequestMapping("/cxb/earnPoint")
public class CEarnPointController  extends BaseController {
    
    @Autowired
    private CxbPointService pointService;
    
    @RequestMapping("index")
    public String toIndex(){
        return "cxb/earnPoint/index";
    }
    
    /**
     * 获取赚取额度图数据和总览数据
     * @param request
     * @return
     */
    @RequestMapping("/getChart")
    @ResponseBody
    public ResultJson<Map<String, Object>> getChart(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return pointService.getEarnAmtChart(paramMap);
    }
    
    /**
     * 导出赚取方式数据
     * @param request
     * @return
     */
    @RequestMapping("/exportWayData")
    public void exportWayData(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = pointService.exportWayData(paramMap);
        String name ="积分赚取方式";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("赚取方式");
        titles.add("积分数");
        titles.add("赚取次数");
        titles.add("用户数");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("dt"));
            rowData.add(item.get("earnWay"));
            rowData.add(item.get("pointSum"));
            rowData.add(item.get("timeSum"));
            rowData.add(item.get("userSum"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }
    

    @RequestMapping("/exportSpeedData")
    public void exportSpeedData(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = pointService.exportSpeedData(paramMap);
        String name = "积分加速";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("加速级别");
        titles.add("超额积分数");
        titles.add("用户数");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("dt"));
            rowData.add(item.get("speedGrade"));
            rowData.add(item.get("pointSum"));
            rowData.add(item.get("userSum"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }

    /**
     * 获得赚取方式数据
     * @param request
     * @return
     */
    @RequestMapping("/getWayData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getWayData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return pointService.getWayData(paramMap);
    }

    /**
     * 获取详细数据总页数
     * @return
     */
    @RequestMapping("/getWayPageCnt")
    @ResponseBody
    public ResultJson<Integer> getWayPageCnt(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return pointService.getWayPageCnt(paramMap);
    }

    
    @RequestMapping("/getSpeedData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getSpeedData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return pointService.getSpeedData(paramMap);
    }

    @RequestMapping("/getSpeedPageCnt")
    @ResponseBody
    public ResultJson<Integer> getSpeedPageCnt(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return pointService.getSpeedPageCnt(paramMap);
    }
        

}
