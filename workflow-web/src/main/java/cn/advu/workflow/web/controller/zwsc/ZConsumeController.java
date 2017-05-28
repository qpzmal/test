package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.common.utils.ExportExcelUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.controller.BaseController;
import cn.advu.workflow.web.service.zwsc.ZConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 中文书城消费数据controller
 * Created by zhanglei on 2017/2/22.
 */
@Controller
@RequestMapping("/zwsc/consume")
public class ZConsumeController extends BaseController {

    @Autowired
    private ZConsumeService zConsumeService;



    @RequestMapping("/index")
    public String index(){
        return "/zwsc/consume/index";
    }

    @ResponseBody
    @RequestMapping("gettrendchart")
    public ResultJson<Map<String, Object>> getTrendChart(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        ResultJson<Map<String, Object>> trendEcharts = zConsumeService.getTrendEcharts(paramMap);
        return trendEcharts;
    }

    /**
     * 消费数据导出
     * @param request
     * @param response
     */
    @RequestMapping("exporttrend")
    public void exportTrend(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> paramMap = handleParams(request);
        List<Map<String, Object>> maps = zConsumeService.exportTrend(paramMap);
        String name ="消费数据";
        List<String> titles = new ArrayList<>();
        titles.add("日期");
        titles.add("消费金额");
        titles.add("铜币代金券");
        titles.add("充值用户");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:maps){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("timeCycle"));
            rowData.add(item.get("currencySum"));
            rowData.add(item.get("voucherSum"));
            rowData.add(item.get("manSum"));
            data.add(rowData);
        }
        ExportExcelUtil.export(response,name,titles,data);
    }


    @ResponseBody
    @RequestMapping("getwaypagedata")
    public ResultJson<List<Map<String, Object>>> getWayPageData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return zConsumeService.getWayPageData(paramMap);
    }

    @RequestMapping("/getwaytotalpages")
    @ResponseBody
    public ResultJson<Integer> getChargeWayTotalPages(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return zConsumeService.getWayTotalPages(paramMap);
    }

    @RequestMapping("/exportway")
    public void exportWay(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Map<String, Object> paramMap = handleParams(request);
            List<Map<String, Object>> wayData = zConsumeService.exportTypeData(paramMap, response);
            String name ="消费金额阶梯统计";
            List<String> title = new ArrayList<>();
            title.add("日期");
            title.add("200以内次数");
            title.add("200以内用户数");
            title.add("200-400次数");
            title.add("200-400用户数");
            title.add("400-600次数");
            title.add("400-600用户数");
            title.add("600以上次数");
            title.add("600以上用户数");


            List<List<Object>> data = new ArrayList<>();
            for(Map<String, Object> item:wayData){
                List<Object> rowData = new ArrayList<>();
                rowData.add(item.get("dt"));
                rowData.add(item.get("pvLt200"));
                rowData.add(item.get("uvLt200"));
                rowData.add(item.get("pvLt400"));
                rowData.add(item.get("uvLt400"));
                rowData.add(item.get("pvLt600"));
                rowData.add(item.get("uvLt600"));
                rowData.add(item.get("pvGt600"));
                rowData.add(item.get("uvGt600"));
                data.add(rowData);
            }
            ExportExcelUtil.export(response,name,title,data);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("/getgivepagedata")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getGivePageData(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return zConsumeService.getGivePageData(paramMap);

    }

    @RequestMapping("/getgivetotalpages")
    @ResponseBody
    public ResultJson<Integer> getGivTotalPages(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        return zConsumeService.getGivTotalPages(paramMap);
    }

    @RequestMapping("/exportgive")
    public void exportwGive(HttpServletRequest request,HttpServletResponse response) throws IOException {

        try {
            Map<String, Object> paramMap = handleParams(request);
            List<Map<String, Object>> wayData = zConsumeService.exportwGive(paramMap);
            String name ="消费类型统计";
            List<String> title = new ArrayList<>();
            title.add("日期");
            title.add("图书消费铜币");
            title.add("图书消费代金券");
            title.add("图书次数");
            title.add("图书用户数");
            title.add("VIP消费铜币");
            title.add("VIP消费代金券");
            title.add("VIP次数");
            title.add("VIP用户数");
            title.add("打赏消费铜币");
            title.add("打赏消费代金券");
            title.add("打赏次数");
            title.add("打赏用户数");

            List<List<Object>> data = new ArrayList<>();
            for(Map<String, Object> item:wayData){
                List<Object> rowData = new ArrayList<>();
                rowData.add(item.get("dt"));
                rowData.add(item.get("bookMoney"));
                rowData.add(item.get("bookCash"));
                rowData.add(item.get("bookPv"));
                rowData.add(item.get("bookUv"));
                rowData.add(item.get("vipMoney"));
                rowData.add(item.get("vipCash"));
                rowData.add(item.get("vipPv"));
                rowData.add(item.get("vipUv"));
                rowData.add(item.get("dashangMoney"));
                rowData.add(item.get("dashangCash"));
                rowData.add(item.get("dashangPv"));
                rowData.add(item.get("dashangUv"));
                data.add(rowData);
            }

            ExportExcelUtil.export(response,name,title,data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
