package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.util.DownloadTools;
import cn.advu.workflow.web.controller.BaseController;
import cn.advu.workflow.web.service.zwsc.ZConsumeDSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 中文书城打赏消费数据controller
 * @author Niu Qianghong
 *
 */
@Controller
@RequestMapping("/zwsc/consume/dashang")
public class ZConsumeOfDashangController extends BaseController{
    
    @Autowired
    private ZConsumeDSService dsService;

    @RequestMapping("/index")
    public String toIndex(){
        return "zwsc/consume/dashang/index";
    }
    
    /**
     * 获取消费图数据
     * @param request
     * @return
     */
    @RequestMapping("/getAmtChart")
    @ResponseBody
    public ResultJson<Map<String, Object>> getAmtChart(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return dsService.getAmtData(paramMap);
    }

    /**
     * 获取打赏对象数据
     * @param request
     * @return
     */
    @RequestMapping("/getTypeData")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getTypeData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return dsService.getTypeData(paramMap);
    }
    
    @RequestMapping("/exportTypeData")
    @ResponseBody
    public ResultJson<Object> exportTypeData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return dsService.exportTypeData(paramMap);
    }
    
    @RequestMapping("/downTypeExcel")
    public void downTypeExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DownloadTools.downExportFile("f:\\打赏对象数据.xls", request, response);
    }
}
