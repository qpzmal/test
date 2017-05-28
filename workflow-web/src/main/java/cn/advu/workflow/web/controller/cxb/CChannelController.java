package cn.advu.workflow.web.controller.cxb;

import cn.advu.workflow.common.utils.ExportExcelUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.controller.BaseController;
import cn.advu.workflow.web.service.cxb.CChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创新版渠道controller
 * @author Niu Qianghong
 *
 */
@Controller
@RequestMapping("/cxb/channel")
public class CChannelController extends BaseController{
    
    @Autowired
    private CChannelService channelService;
    
    @RequestMapping("/index")
    public String toIndex(){
        return "cxb/channel/index";
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
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = channelService.exportUserData(paramMap);
        String name = "用户积分数据";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("启动用户");
        titles.add("新增用户");
        titles.add("赚取积分额度");
        titles.add("消费积分额度");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("timeCycle"));
            rowData.add(item.get("qdUsers"));
            rowData.add(item.get("newUsers"));
            rowData.add(item.get("earnIntegral"));
            rowData.add(item.get("consumeIntegral"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
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
     * 获取详细数据
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
     * 导出详细数据
     * @param request
     * @return
     */
    @RequestMapping("/exportSummary")
    public void exportSummary(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = channelService.exportSummary(paramMap);
        String name = "数据总览";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("累计用户");
        titles.add("启动用户");
        titles.add("新增用户");
        titles.add("启动次数");
        titles.add("赠送积分额度");
        titles.add("消费积分额度");
        titles.add("阅读图书");
        titles.add("阅读章节");
        titles.add("人均阅读时长");
        titles.add("渠道留存");
        titles.add("新增留存");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("dt"));
            rowData.add(item.get("accumUsers"));
            rowData.add(item.get("qdUsers"));
            rowData.add(item.get("newUsers"));
            rowData.add(item.get("qdTimes"));
            rowData.add(item.get("earnIntegral"));
            rowData.add(item.get("consumeIntegral"));
            rowData.add(item.get("bookNum"));
            rowData.add(item.get("chapterNum"));
            rowData.add(item.get("readTime"));
            rowData.add(item.get("qdRemain"));
            rowData.add(item.get("addRemain"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }

    @RequestMapping("/detail")
    public String toDetail(HttpServletRequest request, Model model){
        model.addAttribute("dt", request.getParameter("dt"));
        return "cxb/channel/detail";
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
     *导出渠道详细
     * @return
     */
    @RequestMapping("/exportDetailData")
    public void exportDetailData(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = channelService.exportDetail(paramMap);
        String name = "渠道详细";
        List<String> titles = new ArrayList<>();
        titles.add("渠道");
        titles.add("累计用户");
        titles.add("启动用户");
        titles.add("新增用户");
        titles.add("启动次数");
        titles.add("赠送积分额度");
        titles.add("消费积分额度");
        titles.add("阅读图书");
        titles.add("阅读章节");
        titles.add("人均阅读时长");
        titles.add("渠道留存");
        titles.add("新增留存");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("cnid"));
            rowData.add(item.get("accumUsers"));
            rowData.add(item.get("qdUsers"));
            rowData.add(item.get("newUsers"));
            rowData.add(item.get("qdTimes"));
            rowData.add(item.get("earnIntegral"));
            rowData.add(item.get("consumeIntegral"));
            rowData.add(item.get("bookNum"));
            rowData.add(item.get("chapterNum"));
            rowData.add(item.get("readTime"));
            rowData.add(item.get("qdRemain"));
            rowData.add(item.get("addRemain"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }



    
}
