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
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Series;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
        try {
			Date d = sdf.parse(startDate);
			startDate = new DateTime(d.getTime()).toString("yyyy-MM-dd");
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
        
        String endDate = new DateTime().toString("yyyy-MM-dd");
//        String endDate = new DateTime().toString("yyyy/MM/dd HH:mm:ss");
//        if ("1".equals(lid)) { // 全年
//            startDate = new DateTime().withDayOfYear(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString("yyyy/MM/dd HH:mm:ss");
//            endDate = new DateTime().dayOfYear().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString("yyyy/MM/dd HH:mm:ss");
//
//        } else if ("2".equals(lid)) { // 半年
//            if ("1".equals(type)) { // 上半年
//                startDate = new DateTime().withDayOfYear(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString("yyyy/MM/dd HH:mm:ss");
//                endDate = new DateTime().withMonthOfYear(6).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString("yyyy/MM/dd HH:mm:ss");
//
//            } else {
//                startDate = new DateTime().withMonthOfYear(7).dayOfMonth().withMinimumValue().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString("yyyy/MM/dd HH:mm:ss");
//                endDate = new DateTime().withMonthOfYear(12).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString("yyyy/MM/dd HH:mm:ss");
//
//            }
//        } else if ("3".equals(lid)) { // 季度
//            if ("1".equals(type)) { // 一季度
//                startDate = new DateTime().withDayOfYear(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString("yyyy/MM/dd HH:mm:ss");
//                endDate = new DateTime().withMonthOfYear(3).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString("yyyy/MM/dd HH:mm:ss");
//
//            } else if ("2".equals(type)) {
//                startDate = new DateTime().withMonthOfYear(4).dayOfMonth().withMinimumValue().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString("yyyy/MM/dd HH:mm:ss");
//                endDate = new DateTime().withMonthOfYear(6).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString("yyyy/MM/dd HH:mm:ss");
//
//            } else if ("3".equals(type)) {
//                startDate = new DateTime().withMonthOfYear(7).dayOfMonth().withMinimumValue().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString("yyyy/MM/dd HH:mm:ss");
//                endDate = new DateTime().withMonthOfYear(9).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString("yyyy/MM/dd HH:mm:ss");
//
//            } else if ("4".equals(type)) {
//                startDate = new DateTime().withMonthOfYear(10).dayOfMonth().withMinimumValue().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString("yyyy/MM/dd HH:mm:ss");
//                endDate = new DateTime().withMonthOfYear(12).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString("yyyy/MM/dd HH:mm:ss");
//
//            }
//        } else {
//            LOGGER.error("lid 参数错误。");
//        }
        LOGGER.info("startDate:{}, endDate:{}", startDate, endDate);

        Option option = new Option();
        
//        Map<String,List> maps = new HashMap<String,List>();
//        maps = dataReportService.queryAreaBudgetByDate(startDate, endDate, lid);
        List<VuDataReport> list = new ArrayList<>();
        list = dataReportService.queryAreaBudgetByDate1(startDate, endDate, lid);
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
        //设置工具箱,切换线性，柱状图  
        //option.toolbox().show(true).feature(new MagicType(Magic.line,Magic.bar),new MagicType(Magic.line, Magic.bar),Tool.saveAsImage).padding(10,50,5,5);  
        
        //纵轴为值轴
        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        valueAxis.axisLabel().formatter("{value} %");  
        option.yAxis(valueAxis);  
        
        
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("北京公司");
        option.legend().data("北京公司");
        bar.stack("北京");  
        bar.itemStyle().normal().color("rgb(255, 134, 26)").areaStyle().typeDefault().color("rgba(255, 134, 26,0.5)");  
         
        Bar bar1 = new Bar("广东公司");
        option.legend().data("广东公司");
        bar1.stack("广东");  
        bar1.itemStyle().normal().color("rgb(34, 137, 196)").areaStyle().typeDefault().color("rgba(34, 137, 196,0.5)");   
         
        Bar bar2 = new Bar("石家庄");
        option.legend().data("石家庄");
        bar2.stack("石家庄");  
        bar2.itemStyle().normal().color("rgb(215, 38, 59)").areaStyle().typeDefault().color("rgba(215, 38, 59,0.5)");  
        
        //循环数据
        for (VuDataReport vuDataReport : list) {
            //设置类目
            category.data(vuDataReport.getOrderNum());
            //类目对应的柱状图
            bar.data(vuDataReport.getBj());
            bar1.data(vuDataReport.getGd());
            bar2.data(vuDataReport.getSjz());
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

       
        option.series(bar,bar1,bar2);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);
        //返回Option

        return option;
    }
    
    private Option createChartWithType(Map<String,List> maps) {
      
    	List<VuDataReport> list = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> times = new ArrayList<>();
        list = maps.get("result");
        names = maps.get("names");
        times = maps.get("times");
    	
    	
    	
    	//创建Option
        Option option = new Option();
//        option.title("预算完成度").legend("金额（元）").tooltip().trigger();
        option.title("预算完成度").tooltip().trigger();
        //设置工具箱,切换线性，柱状图  
        //option.toolbox().show(true).feature(new MagicType(Magic.line,Magic.bar),new MagicType(Magic.line, Magic.bar),Tool.saveAsImage).padding(10,50,5,5);  
        
        //纵轴为值轴
        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        valueAxis.axisLabel().formatter("{value} %");  
        option.yAxis(valueAxis);  
        
        
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
//        Bar bar = new Bar("北京公司");
//        option.legend().data("北京公司");
//        bar.stack("北京");  
//        bar.itemStyle().normal().color("rgb(255, 134, 26)").areaStyle().typeDefault().color("rgba(255, 134, 26,0.5)");  
//         
//        Bar bar1 = new Bar("广东公司");
//        option.legend().data("广东公司");
//        bar1.stack("广东");  
//        bar1.itemStyle().normal().color("rgb(34, 137, 196)").areaStyle().typeDefault().color("rgba(34, 137, 196,0.5)");   
//         
//        Bar bar2 = new Bar("石家庄");
//        option.legend().data("石家庄");
//        bar2.stack("石家庄");  
//        bar2.itemStyle().normal().color("rgb(215, 38, 59)").areaStyle().typeDefault().color("rgba(215, 38, 59,0.5)");  
//        
        //循环数据
//        for (VuDataReport vuDataReport : list) {
//            //设置类目
//            category.data(vuDataReport.getOrderNum());
//            //类目对应的柱状图
//            bar.data(vuDataReport.getBj());
//            bar1.data(vuDataReport.getGd());
//            bar2.data(vuDataReport.getSjz());
//        }
        List<Bar> bars = new ArrayList<Bar>();
        for(String t:times){
        	category.data(t);
        }
        for(int i=0;i<names.size();i++){
        	Bar bar = new Bar(names.get(i));
        	option.legend().data(names.get(i));
            bar.stack(names.get(i));  
        	for(int j=0;j<times.size();j++){
        		
        		boolean flag = false;
        		for (VuDataReport vuDataReport : list) {
        			if(vuDataReport.getOrderNum().equals(times.get(j))&&vuDataReport.getName().equals(names.get(i))){
        				bar.data(vuDataReport.getYswc());
        				flag=true;
        			}
        		}
        		if(!flag){
        			bar.data(0);
        		}
        	}
        	bars.add(bar);
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
       List<Series> series = new ArrayList<Series>();
        for(Bar b:bars){
    	   b.setItemStyle(itemStyle);
    	   Series s= new Series<Bar>() {
    		};
    		s.setData(b.data());
    		s.setItemStyle(itemStyle);
    		series.add(s);
       }
       option.series(series);
       //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);
        //返回Option

        return option;
    }
}
