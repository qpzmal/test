package cn.advu.workflow.web.controller.report;

import cn.advu.workflow.domain.fcf_vu.datareport.VuDataReport;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.datareport.DataReportService;
import cn.advu.workflow.web.third.echarts.NormalExtend;

import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/report/customer")
public class ReportCustomerController {

    private static Logger LOGGER = LoggerFactory.getLogger(ReportCustomerController.class);

    @Autowired
    private DataReportService dataReportService;

    /**
     * 毛利/净利贡献率
     *
     * @return
     */
    @RequestMapping("/profit")
    public String profit(){
        return "report/customer_profit";
    }
    /**
     * 回款汇总
     *
     * @return
     */
    @RequestMapping("/pay")
    public String pay(){
        return "report/customer_index";
    }




    @RequestMapping(value = "/profit/query", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryProfit(
            String lid, String startDate, String endDate, String options, String customerType, String profitType,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(new DateTime().withDayOfYear(1).withHourOfDay(1).withMinuteOfHour(1).toString("yyyy/MM/dd HH:mm:ss")):startDate;
        endDate = StringUtils.isEmpty(endDate)?(new DateTime().toString("yyyy/MM/dd HH:mm:ss")):endDate;
        options = StringUtils.isEmpty(options)?"1":options;
        customerType = StringUtils.isEmpty(customerType)?"1":customerType;
        profitType = StringUtils.isEmpty(profitType)?"1":profitType;

        List<VuDataReport> list = new ArrayList<>();
        List<VuDataReport> list1 = new ArrayList<>();
        Option option = new Option();
        Option option1 = new Option();
        list = dataReportService.queryCustomerByProfit(startDate, endDate, customerType, options);
        list1 = dataReportService.queryCustomerByMargin(startDate, endDate, customerType, options);
        //毛利率
        if("1".equals(customerType)){
        	option = this.createChart4Profit(list);
            option1 = this.createChart4Profit1(list1);
        }else{
        	option = this.createChart4Margin(list);
            option1 = this.createChart4Margin1(list1);
        }
       
        result.setData(option);
        result.setLine(option1);
        result.setCode(WebConstants.OPERATION_SUCCESS).setInfo("成功");
        LOGGER.info("result:{}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }




    @RequestMapping(value = "/pay/query", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryPay(
            String lid, String startDate, String endDate, String options,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(new DateTime().withDayOfYear(1).withHourOfDay(1).withMinuteOfHour(1).toString("yyyy/MM/dd HH:mm:ss")):startDate;
        endDate = StringUtils.isEmpty(endDate)?(new DateTime().toString("yyyy/MM/dd HH:mm:ss")):endDate;
        options = StringUtils.isEmpty(options)?"1":options;

        List<VuDataReport> list = new ArrayList<>();
        Option option = new Option();

        result.setData(option);
        result.setCode(WebConstants.OPERATION_SUCCESS).setInfo("成功");
        LOGGER.info("result:{}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }


    private Option createChart4Date(List<VuDataReport> list, String profitType, String options) {
        return this.createChartWithType(list, profitType, options);
    }


    private Option createChartWithType(List<VuDataReport> list, String profitType, String options) {
        //创建Option
        Option option = new Option();
//        option.title("销售汇总").tooltip(Trigger.axis).legend("金额（元）");
        option.title("客户汇总").legend("金额（元）").tooltip().trigger();
        //横轴为值轴
        option.xAxis(new ValueAxis().boundaryGap(0d, 0.01));
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("金额（元）");
        //饼图数据
        Pie pie = new Pie("金额（元）");
        //循环数据
        for (VuDataReport vuDataReport : list) {
            if ("0".equals(options)) {
                if ("1".equals(profitType)) { // 毛利
                    //设置类目
                    category.data(vuDataReport.getName());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getmPay());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getName(), vuDataReport.getmPay()));
                } else if ("2".equals(profitType)) { // 净利
                    //设置类目
                    category.data(vuDataReport.getName());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getjPay());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getName(), vuDataReport.getjPay()));
                }

            } else {
                if ("1".equals(profitType)) { // 毛利
                    //设置类目
                    category.data(vuDataReport.getOrderDate());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getmPay());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getOrderDate(), vuDataReport.getmPay()));
                } else if ("2".equals(profitType)) { // 净利
                    //设置类目
                    category.data(vuDataReport.getOrderDate());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getjPay());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getOrderDate(), vuDataReport.getjPay()));
                }
            }
        }
        //设置类目轴
        option.yAxis(category);
        //饼图的圆心和半径
        pie.center("80%","50%").radius(100);
        // 设置柱状图参数
        ItemStyle itemStyle = new ItemStyle();
        NormalExtend normal = new NormalExtend();
        normal.setPosition("right");
        normal.setShow(true);
        itemStyle.setNormal(normal);
        bar.setLabel(itemStyle);

        //设置数据
        option.series(bar, pie);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180).width("50%");
        //返回Option

        return option;
    }
    
    private Option createChart4Profit(List<VuDataReport> list) {
        //创建Option
        Option option = new Option();
//        option.title("销售汇总").tooltip(Trigger.axis).legend("金额（元）");
        option.tooltip(Trigger.axis).title().text("毛利率统计").x(X.left);

        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        option.yAxis(valueAxis);  
        
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("4A1");
        option.legend().data("4A1");
        bar.stack("4A1");  
        bar.itemStyle().normal().color("rgb(255, 134, 26)").areaStyle().typeDefault().color("rgba(255, 134, 26,0.5)");  
         
        Bar bar1 = new Bar("4A2");
        option.legend().data("4A2");
        bar1.stack("4A2");  
        bar1.itemStyle().normal().color("rgb(34, 137, 196)").areaStyle().typeDefault().color("rgba(34, 137, 196,0.5)");   
         
        Bar bar2 = new Bar("4A3");
        option.legend().data("4A3");
        bar2.stack("4A3");  
        bar2.itemStyle().normal().color("rgb(215, 38, 59)").areaStyle().typeDefault().color("rgba(215, 38, 59,0.5)");  
        
        Bar bar3 = new Bar("4A4");
        option.legend().data("4A4");
        bar3.stack("4A4");  
        bar3.itemStyle().normal().color("rgb(83, 212, 255)").areaStyle().typeDefault().color("rgba(83, 212, 255,0.5)");  
        //循环数据
        for (VuDataReport vuDataReport : list) {
            //设置类目
            category.data(vuDataReport.getStartDate());
            //类目对应的柱状图
            bar.data(vuDataReport.getA1());
            bar1.data(vuDataReport.getA2());
            bar2.data(vuDataReport.getA3());
            bar3.data(vuDataReport.getA4());
        }
        //设置类目轴
        option.xAxis(category);
        // 设置柱状图参数
        ItemStyle itemStyle = new ItemStyle();
        NormalExtend normal = new NormalExtend();
        Label label = new Label();
        normal.setLabel(label);
        normal.setPosition("outer");
        normal.setShow(true);
        itemStyle.setNormal(normal);
        bar.setLabel(itemStyle);
        bar1.setLabel(itemStyle);
        bar2.setLabel(itemStyle);
        bar3.setLabel(itemStyle);

       
        option.series(bar,bar1,bar2,bar3);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);
        //返回Option

        return option;
    }
    
    private Option createChart4Profit1(List<VuDataReport> list) {
        //创建Option
        Option option = new Option();
//        option.title("销售汇总").tooltip(Trigger.axis).legend("金额（元）");
        option.tooltip(Trigger.axis).title().text("净利率统计").x(X.left);

        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        option.yAxis(valueAxis);  
        
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("4A1");
        option.legend().data("4A1");
        bar.stack("4A1");  
        bar.itemStyle().normal().color("rgb(255, 134, 26)").areaStyle().typeDefault().color("rgba(255, 134, 26,0.5)");  
         
        Bar bar1 = new Bar("4A2");
        option.legend().data("4A2");
        bar1.stack("4A2");  
        bar1.itemStyle().normal().color("rgb(34, 137, 196)").areaStyle().typeDefault().color("rgba(34, 137, 196,0.5)");   
         
        Bar bar2 = new Bar("4A3");
        option.legend().data("4A3");
        bar2.stack("4A3");  
        bar2.itemStyle().normal().color("rgb(215, 38, 59)").areaStyle().typeDefault().color("rgba(215, 38, 59,0.5)");  
        
        Bar bar3 = new Bar("4A4");
        option.legend().data("4A4");
        bar3.stack("4A4");  
        bar3.itemStyle().normal().color("rgb(83, 212, 255)").areaStyle().typeDefault().color("rgba(83, 212, 255,0.5)");  
        //循环数据
        for (VuDataReport vuDataReport : list) {
            //设置类目
            category.data(vuDataReport.getStartDate());
            //类目对应的柱状图
            bar.data(vuDataReport.getA1());
            bar1.data(vuDataReport.getA2());
            bar2.data(vuDataReport.getA3());
            bar3.data(vuDataReport.getA4());
        }
        //设置类目轴
        option.xAxis(category);
        // 设置柱状图参数
        ItemStyle itemStyle = new ItemStyle();
        NormalExtend normal = new NormalExtend();
        Label label = new Label();
        normal.setLabel(label);
        normal.setPosition("outer");
        normal.setShow(true);
        itemStyle.setNormal(normal);
        bar.setLabel(itemStyle);
        bar1.setLabel(itemStyle);
        bar2.setLabel(itemStyle);
        bar3.setLabel(itemStyle);

       
        option.series(bar,bar1,bar2,bar3);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);
        //返回Option

        return option;
    }
    
    private Option createChart4Margin(List<VuDataReport> list) {
    	  //创建Option
        Option option = new Option();
//        option.title("销售汇总").tooltip(Trigger.axis).legend("金额（元）");
        option.tooltip(Trigger.axis).title().text("毛利率统计").x(X.left);

        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        option.yAxis(valueAxis);  
        
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("直客1");
        option.legend().data("直客1");
        bar.stack("直客1");  
        bar.itemStyle().normal().color("rgb(255, 134, 26)").areaStyle().typeDefault().color("rgba(255, 134, 26,0.5)");  
         
        Bar bar1 = new Bar("直客2");
        option.legend().data("直客2");
        bar1.stack("直客2");  
        bar1.itemStyle().normal().color("rgb(34, 137, 196)").areaStyle().typeDefault().color("rgba(34, 137, 196,0.5)");   
         
        Bar bar2 = new Bar("直客3");
        option.legend().data("直客3");
        bar2.stack("直客3");  
        bar2.itemStyle().normal().color("rgb(215, 38, 59)").areaStyle().typeDefault().color("rgba(215, 38, 59,0.5)");  
        
        Bar bar3 = new Bar("直客4");
        option.legend().data("直客4");
        bar3.stack("直客4");  
        bar3.itemStyle().normal().color("rgb(83, 212, 255)").areaStyle().typeDefault().color("rgba(83, 212, 255,0.5)");  
        //循环数据
        for (VuDataReport vuDataReport : list) {
            //设置类目
            category.data(vuDataReport.getStartDate());
            //类目对应的柱状图
            bar.data(vuDataReport.getZ1());
            bar1.data(vuDataReport.getZ2());
            bar2.data(vuDataReport.getZ3());
            bar3.data(vuDataReport.getZ4());
        }
        //设置类目轴
        option.xAxis(category);
        // 设置柱状图参数
        ItemStyle itemStyle = new ItemStyle();
        NormalExtend normal = new NormalExtend();
        Label label = new Label();
        normal.setLabel(label);
        normal.setPosition("outer");
        normal.setShow(true);
        itemStyle.setNormal(normal);
        bar.setLabel(itemStyle);
        bar1.setLabel(itemStyle);
        bar2.setLabel(itemStyle);
        bar3.setLabel(itemStyle);

       
        option.series(bar,bar1,bar2,bar3);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);
        //返回Option

        return option;
    }
    
    private Option createChart4Margin1(List<VuDataReport> list) {
        //创建Option
        Option option = new Option();
//        option.title("销售汇总").tooltip(Trigger.axis).legend("金额（元）");
        option.tooltip(Trigger.axis).title().text("净利率统计").x(X.left);

        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        option.yAxis(valueAxis);  
        
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("直客1");
        option.legend().data("直客1");
        bar.stack("直客1");  
        bar.itemStyle().normal().color("rgb(255, 134, 26)").areaStyle().typeDefault().color("rgba(255, 134, 26,0.5)");  
         
        Bar bar1 = new Bar("直客2");
        option.legend().data("直客2");
        bar1.stack("直客2");  
        bar1.itemStyle().normal().color("rgb(34, 137, 196)").areaStyle().typeDefault().color("rgba(34, 137, 196,0.5)");   
         
        Bar bar2 = new Bar("直客3");
        option.legend().data("直客3");
        bar2.stack("直客3");  
        bar2.itemStyle().normal().color("rgb(215, 38, 59)").areaStyle().typeDefault().color("rgba(215, 38, 59,0.5)");  
        
        Bar bar3 = new Bar("直客4");
        option.legend().data("直客4");
        bar3.stack("直客4");  
        bar3.itemStyle().normal().color("rgb(83, 212, 255)").areaStyle().typeDefault().color("rgba(83, 212, 255,0.5)");  
        //循环数据
        for (VuDataReport vuDataReport : list) {
            //设置类目
            category.data(vuDataReport.getStartDate());
            //类目对应的柱状图
            bar.data(vuDataReport.getZ1());
            bar1.data(vuDataReport.getZ2());
            bar2.data(vuDataReport.getZ3());
            bar3.data(vuDataReport.getZ4());
        }
        //设置类目轴
        option.xAxis(category);
        // 设置柱状图参数
        ItemStyle itemStyle = new ItemStyle();
        NormalExtend normal = new NormalExtend();
        Label label = new Label();
        normal.setLabel(label);
        normal.setPosition("outer");
        normal.setShow(true);
        itemStyle.setNormal(normal);
        bar.setLabel(itemStyle);
        bar1.setLabel(itemStyle);
        bar2.setLabel(itemStyle);
        bar3.setLabel(itemStyle);

       
        option.series(bar,bar1,bar2,bar3);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);
        //返回Option

        return option;
    }
}
