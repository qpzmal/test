package cn.advu.workflow.web.controller.report;

import cn.advu.workflow.domain.fcf_vu.datareport.VuDataReport;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.datareport.DataReportService;
import cn.advu.workflow.web.third.echarts.NormalExtend;
import cn.advu.workflow.web.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.style.ItemStyle;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiqz on 2017/7/13.
 */
@Controller
@RequestMapping("/report/area")
public class ReportAreaController {

    private static Logger LOGGER = LoggerFactory.getLogger(ReportSaleController.class);

    @Autowired
    private DataReportService dataReportService;

    /**
     * 损益分析
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/balance")
    public String balance(Model resultModel, String type){
        return "report/area_balance";
    }

    /**
     * 预算完成度分析
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/budget")
    public String budget(Model resultModel, String type){
        return "report/area_budget";
    }



    @RequestMapping(value = "/balance/query", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryBalance(
            String lid, String startDate, String type,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(String.valueOf(new DateTime().getYear())):startDate;
        type = StringUtils.isEmpty(type)?"1":type;



        Option option = new Option();

        result.setCode(WebConstants.OPERATION_SUCCESS).setInfo("成功");
        LOGGER.info("result:{}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }


    @RequestMapping(value = "/budget/query", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryBudget(
            String lid, String startDate, String type,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(String.valueOf(new DateTime().getYear())):startDate;
        type = StringUtils.isEmpty(type)?"1":type;


        String endDate = DateUtil.getToday();
        if ("1".equals(lid)) { // 全年
            startDate = DateUtil.getYearFirstDay();
            endDate = DateUtil.getYearLastDay();

        } else if ("2".equals(lid)) { // 半年
            if ("1".equals(type)) { // 上半年
                startDate = DateUtil.getYearFirstDay();
                endDate = DateUtil.getFirstHalfYearLastDay();

            } else {
                startDate = DateUtil.getSecondHalfYearFirstDay();
                endDate = DateUtil.getYearLastDay();

            }
        } else if ("3".equals(lid)) { // 季度
            if ("1".equals(type)) { // 一季度
                startDate = DateUtil.getYearFirstDay();
                endDate = DateUtil.getFirstSeasonLastDay();

            } else if ("2".equals(type)) {
                startDate = DateUtil.getSecSeasonFirstDay();
                endDate = DateUtil.getFirstHalfYearLastDay();

            } else if ("3".equals(type)) {
                startDate = DateUtil.getSecondHalfYearFirstDay();
                endDate = DateUtil.getThirdSeasonLastDay();

            } else if ("4".equals(type)) {
                startDate = DateUtil.getFourthSeasonFirstDay();
                endDate = DateUtil.getYearLastDay();

            }
        } else {
            LOGGER.error("lid 参数错误。");
        }
        LOGGER.info("startDate:{}, endDate:{}", startDate, endDate);

        Option option = new Option();
        List<VuDataReport> list = new ArrayList<>();

        list = dataReportService.queryAreaBudgetByDate(startDate, endDate, type);

        option = this.createChartWithType(list);
        result.setData(option);


        result.setCode(WebConstants.OPERATION_SUCCESS).setInfo("成功");
        LOGGER.info("result:{}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }








    /**
     * 跳转到报表模版页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel, String type){
//        ReportTypeEnum customTypeEnum = ValueEnumUtils.getEnum(ReportTypeEnum.class, type);
//        switch(customTypeEnum) {
//            case SALE_HISTORY:
//                resultModel.addAttribute("uri", "/report/sale/history");
//                break;
//            case SALE_FEATURE:
//                resultModel.addAttribute("uri", "/report/sale/feature");
//                break;
//            case BUY_RESOURCE:
//                resultModel.addAttribute("uri", "/report/buy/resource");
//                break;
//            case BUY_AREA:
//                resultModel.addAttribute("uri", "/report/buy/area");
//                break;
//            case AREA_BALANCE:
//                resultModel.addAttribute("uri", "/report/area/balance");
//                break;
//            case AREA_BUDGET:
//                resultModel.addAttribute("uri", "/report/area/budget");
//                break;
//            case CUSTOMER_PROFIT:
//                resultModel.addAttribute("uri", "/report/customer/profit");
//                break;
//            case CUSTOMER_PAY:
//                resultModel.addAttribute("uri", "/report/customer/pay");
//                break;
//            default:
//                break;
//        }

        return "report/area_index";
    }






    private Option createChartWithType(List<VuDataReport> list) {
        //创建Option
        Option option = new Option();
//        option.title("预算完成度").legend("金额（元）").tooltip().trigger();
        option.title("预算完成度").tooltip().trigger();
        //横轴为值轴
        option.xAxis(new ValueAxis().boundaryGap(0d, 0.01));
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("金额（元）");
        //循环数据
        for (VuDataReport vuDataReport : list) {
            //设置类目
            category.data(vuDataReport.getName());
            //类目对应的柱状图
            bar.data(vuDataReport.getYswc());
        }
        //设置类目轴
        option.yAxis(category);
        // 设置柱状图参数
        ItemStyle itemStyle = new ItemStyle();
        NormalExtend normal = new NormalExtend();
        normal.setPosition("right");
        normal.setShow(true);
        itemStyle.setNormal(normal);
        bar.setLabel(itemStyle);

        //设置数据
        option.series(bar);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);
        //返回Option

        return option;
    }
}
