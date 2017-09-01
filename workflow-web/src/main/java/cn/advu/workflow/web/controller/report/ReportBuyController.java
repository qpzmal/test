package cn.advu.workflow.web.controller.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.advu.workflow.domain.fcf_vu.datareport.VuDataReport;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.datareport.DataReportService;
import cn.advu.workflow.web.third.echarts.NormalExtend;

import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;

/**
 * Created by weiqz on 2017/7/13.
 */
@Controller
@RequestMapping("/report/buy")
public class ReportBuyController {

    private static Logger LOGGER = LoggerFactory.getLogger(ReportBuyController.class);


    @Autowired
    private DataReportService dataReportService;

    /**
     * 资源类别
     *
     * @return
     */
    @RequestMapping("/resource")
    public String resource(){
        return "report/buy_resource";
    }
    /**
     * 厂商
     *
     * @return
     */
    @RequestMapping("/area")
    public String area(){
        return "report/buy_area";
    }




    @RequestMapping(value = "/resource/query", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryResource(
            String startDate, String endDate, String options, String orderKey,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
//        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(new DateTime().withDayOfYear(1).withHourOfDay(1).withMinuteOfHour(1).toString("yyyy-MM-dd")):startDate;
        endDate = StringUtils.isEmpty(endDate)?(new DateTime().toString("yyyy-MM-dd")):endDate;
        options = StringUtils.isEmpty(options)?"1":options;
        orderKey = StringUtils.isEmpty(orderKey)?"cpm_total":orderKey;

        LOGGER.info("orderKey:{},options:{}", orderKey, options);
        LOGGER.info("startDate:{},endDate:{}", startDate, endDate);
        //柱状图数据
        Map<String,List> list = dataReportService.queryBuyResourceByDate(startDate, endDate, options);
        //折线数据
        Map<String,List> lines = dataReportService.queryBuyResourceByDateLine(startDate, endDate, options);
//        Option option = this.createChart4Date(list, orderKey); // 柱状图+饼图
        Option option = this.createChart(list); // 柱状图
        Option option1 = this.createLine(lines);//折线图
        result.setData(option);
        result.setLine(option1);
        result.setCode(WebConstants.OPERATION_SUCCESS).setInfo("成功");
        LOGGER.info("result:{}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }



    @RequestMapping(value = "/area/query", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryArea(
            String lid, String startDate, String endDate, String options, String orderKey,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(new DateTime().withDayOfYear(1).withHourOfDay(1).withMinuteOfHour(1).toString("yyyy/MM/dd HH:mm:ss")):startDate;
        endDate = StringUtils.isEmpty(endDate)?(new DateTime().toString("yyyy/MM/dd HH:mm:ss")):endDate;
        options = StringUtils.isEmpty(options)?"1":options;
        orderKey = StringUtils.isEmpty(orderKey)?"cpm_total":orderKey;

        List<VuDataReport> list = dataReportService.queryBuyResourceByArea(startDate, endDate, orderKey);
        Option option = this.createChart4Area(list);
        Option option1 = this.createChart4Date(list);
        result.setData(option);
        result.setLine(option1);
        result.setCode(WebConstants.OPERATION_SUCCESS).setInfo("成功");
        LOGGER.info("result:{}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }


    private Option createChart4Date(List<VuDataReport> list) {
        return this.createChartLine(list, 2);
    }
    private Option createChart4Area(List<VuDataReport> list) {
        return this.createChartWithType(list, 2);
    }

    private Option createChartWithType(List<VuDataReport> list, int type) {
        //创建Option
        Option option = new Option();
//        option.title("销售汇总").tooltip(Trigger.axis).legend("金额（元）");
        option.title("广告投放CPM统计图").legend("金额（元）").tooltip().trigger();
        //横轴为值轴
        option.yAxis(new ValueAxis().boundaryGap(0d, 0.01));
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("金额（元）");
        bar.itemStyle().normal().color("rgb(34, 137, 196)").areaStyle().typeDefault().color("rgba(34, 137, 196,0.5)");
        //饼图数据
        Pie pie = new Pie("金额（元）");
        //循环数据
        for (VuDataReport vuDataReport : list) {
             if (2 == type) { //
                    //设置类目
                    category.data(vuDataReport.getName());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getCpmTotal());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getName(), vuDataReport.getCpmTotal()));
            }
        }
        //设置类目轴
        option.xAxis(category);
        List<Object> color = new ArrayList<Object>();
        color.add("#4169E1");
        color.add("#EE7600");
        option.setColor(color);
        //饼图的圆心和半径
        pie.center("80%","50%").radius(100);
         //设置柱状图参数
        ItemStyle itemStyle = new ItemStyle();
        NormalExtend normal = new NormalExtend();
        normal.setPosition("outer");
        normal.setShow(true);
        Label label = new Label();
        normal.setLabel(label);
        itemStyle.setNormal(normal);
        bar.setLabel(itemStyle);
        //饼状图
        ItemStyle itemStyle1 = new ItemStyle();
        NormalExtend normal1 = new NormalExtend();
        normal1.setShow(true);
        Label label1 = new Label();
        label1.setFormatter("{b}:{c} ({d}%)");
        normal1.setLabel(label1);
        itemStyle1.setNormal(normal1);
        pie.setLabel(itemStyle1);
        
        //设置数据
        option.series(bar, pie);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180).width("50%");
        //返回Option

        return option;
    }

    
    private Option createChartLine(List<VuDataReport> list, int type) {
        //创建Option
        Option option = new Option();
//        option.title("销售汇总").tooltip(Trigger.axis).legend("金额（元）");
        option.title("媒体采购成本统计图").legend("金额（元）").tooltip().trigger();
        //横轴为值轴
        option.yAxis(new ValueAxis().boundaryGap(0d, 0.01));
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Line line = new Line("金额（元）");
        line.itemStyle().normal().color("rgb(34, 137, 196)");
        //饼图数据
        Pie pie = new Pie("金额（元）");
        //循环数据
        for (VuDataReport vuDataReport : list) {
             if (2 == type) { //
                    //设置类目
                    category.data(vuDataReport.getName());
                    //类目对应的柱状图
                    line.data(vuDataReport.getCpmTotal());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getName(), vuDataReport.getCpmTotal()));
            }
        }
        //设置类目轴
        option.xAxis(category);
        List<Object> color = new ArrayList<Object>();
        color.add("#4169E1");
        color.add("#EE7600");
        option.setColor(color);
        //饼图的圆心和半径
        pie.center("80%","50%").radius(100);
        // 设置柱状图参数
//        ItemStyle itemStyle = new ItemStyle();
//        NormalExtend normal = new NormalExtend();
//        normal.setPosition("right");
//        normal.setShow(true);
//        itemStyle.setNormal(normal);
//        bar.setLabel(itemStyle);
      //饼状图
        ItemStyle itemStyle1 = new ItemStyle();
        NormalExtend normal1 = new NormalExtend();
        normal1.setShow(true);
        Label label1 = new Label();
        label1.setFormatter("{b}:{c} ({d}%)");
        normal1.setLabel(label1);
        itemStyle1.setNormal(normal1);
        pie.setLabel(itemStyle1);

        //设置数据
        option.series(line, pie);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180).width("50%");
        //返回Option

        return option;
    }

//    private Option createChart(List<VuDataReport> list, String orderKey) {
//
//        //创建Option对象
//        Option option = new Option();
//
//        //设置图表标题，并且居中显示
//        option.tooltip(Trigger.axis).title().text("采购汇总").x(X.left);
//
//        //设置y轴为值轴
//        option.yAxis(new ValueAxis());
//
//        //创建类目轴，并且不显示竖着的分割线，onZero=false
//        CategoryAxis categoryAxis = new CategoryAxis()
//                .splitLine(new SplitLine().show(false))
//                .axisLine(new AxisLine().onZero(false));
//
//        //不显示表格边框，就是围着图标的方框
//        option.grid().borderWidth(0);
//
//
//        Map<String, List<VuDataReport>> dataMap = new HashMap<>();
//        List<String> dateList = new ArrayList();
//        for (VuDataReport data : list) {
//            if (dataMap.containsKey(data.getName())) {
//                dataMap.get(data.getName()).add(data);
//            } else {
//                List<VuDataReport> dataList = new ArrayList();
//                dataList.add(data);
//                dataMap.put(data.getName(), dataList);
//            }
//        }
//
//        Iterator iterator = dataMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, List<VuDataReport>> entry = (Map.Entry<String, List<VuDataReport>>) iterator.next();
//
//            //设置图例,居中底部显示，显示边框
////            option.legend().data("人名").x(X.center).y(Y.bottom).borderWidth(1);
//            option.legend().data(entry.getKey());
//
//            //创建Line数据
//            Line line = new Line(entry.getKey()).smooth(true);
//
//
//            //根据获取的数据赋值
//            for (VuDataReport data : entry.getValue()) {
//
//                if (!dateList.contains(data.getOrderDate())) {
//                    dateList.add(data.getOrderDate());
//                    //增加类目，值为日期
//                    categoryAxis.data(data.getOrderDate());
//                }
//
//                //日期对应的数据
//                line.data(data.getCpmTotal());
//                if ("cpm_total".equals(orderKey)) {
//                    line.data(data.getCpmTotal());
//                } else if ("sale_amount".equals(orderKey)) { //
//                    line.data(data.getSaleAmount());
//                } else if ("buy_amount".equals(orderKey)) { //
//                    line.data(data.getBuyAmount());
//                }
//            }
//
//            //设置x轴为类目轴
//            option.xAxis(categoryAxis);
//
//            //设置数据
//            option.series(line);
//        }
//
//        return option;
//    }
    
    private Option createChart(Map<String,List> maps) {

    	List<VuDataReport> list = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> times = new ArrayList<>();
        list = maps.get("result");
        names = maps.get("names");
        times = maps.get("times");
    	
        //创建Option对象
        Option option = new Option();

        //设置图表标题，并且居中显示
        option.tooltip(Trigger.axis).title().text("媒体采购成本统计图").x(X.center);
        //设置y轴为值轴
        
        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        option.yAxis(valueAxis);  
        //创建类目轴，并且不显示竖着的分割线，onZero=false
        CategoryAxis category = new CategoryAxis()
                .splitLine(new SplitLine().show(false))
                .axisLine(new AxisLine().onZero(false));


        //柱状数据
//        Bar bar = new Bar("智能电视");
//        option.legend().data("智能电视").x(X.center).y(Y.bottom);
//        bar.stack("智能电视");  
//        bar.itemStyle().normal().color("rgb(255, 134, 26)").areaStyle().typeDefault().color("rgba(255, 134, 26,0.5)");  
//         
//        Bar bar1 = new Bar("应用");
//        option.legend().data("应用").x(X.center).y(Y.bottom);
//        bar1.stack("应用");  
//        bar1.itemStyle().normal().color("rgb(34, 137, 196)").areaStyle().typeDefault().color("rgba(34, 137, 196,0.5)");   
//         
//        Bar bar2 = new Bar("户外");
//        option.legend().data("户外").x(X.center).y(Y.bottom);
//        bar2.stack("户外");  
//        bar2.itemStyle().normal().color("rgb(215, 38, 59)").areaStyle().typeDefault().color("rgba(215, 38, 59,0.5)");  
//        
//        Bar bar3 = new Bar("内容商");
//        option.legend().data("内容商").x(X.center).y(Y.bottom);
//        bar3.stack("内容商");  
//        bar3.itemStyle().normal().color("rgb(62, 180, 220)").areaStyle().typeDefault().color("rgba(62, 180, 220,0.5)");  
//        
//        //循环数据
//        for (VuDataReport vuDataReport : list) {
//            //设置类目
//        	categoryAxis.data(vuDataReport.getOrderDate());
//            //类目对应的柱状图
//            bar.data(vuDataReport.getDs());
//            bar1.data(vuDataReport.getYy());
//            bar2.data(vuDataReport.getHw());
//            bar3.data(vuDataReport.getNr());
//        }
        //设置类目轴
//        option.xAxis(categoryAxis);
//        // 设置柱状图参数
//        ItemStyle itemStyle = new ItemStyle();
//        NormalExtend normal = new NormalExtend();
//        normal.setPosition("top");
//        normal.setShow(false);
//        itemStyle.setNormal(normal);
//        bar.setLabel(itemStyle);
//        bar1.setLabel(itemStyle);
//        bar2.setLabel(itemStyle);
//        bar3.setLabel(itemStyle);

       
        List<Bar> bars = new ArrayList<Bar>();
        for(String t:times){
        	category.data(t);
        }
        for(int i=0;i<names.size();i++){
        	Bar bar = new Bar(names.get(i));
        	option.legend().data(names.get(i)).x(X.center).y(Y.bottom);
            bar.stack(names.get(i));  
            bar.setName(names.get(i));
        	for(int j=0;j<times.size();j++){
        		
        		boolean flag = false;
        		for (VuDataReport vuDataReport : list) {
        			if(vuDataReport.getOrderDate().equals(times.get(j))&&vuDataReport.getType().equals(names.get(i))){
        				bar.data(vuDataReport.getBuyAmount());
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
        for(final Bar b:bars){
    	   b.setItemStyle(itemStyle);
    	   Series<Bar> s= new Series<Bar>() {
    		   @Override
    		public Bar type(SeriesType type) {
    			// TODO Auto-generated method stub
    			return b;
    		}
    		};
    		s.setType(SeriesType.bar);
    		s.setName(b.getName());
    		s.setData(b.data());
    		s.setItemStyle(itemStyle);
    		series.add(s);
       }
       option.series(series);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);

        return option;
    }

    private Option createLine(Map<String,List> maps) {

    	List<VuDataReport> list = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> times = new ArrayList<>();
        list = maps.get("result");
        names = maps.get("names");
        times = maps.get("times");
        //创建Option对象
        Option option = new Option();

        //设置图表标题，并且居中显示
        option.tooltip(Trigger.axis).title().text("广告投放CPM统计图").x(X.center);
        //设置y轴为值轴
        
        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        option.yAxis(valueAxis);  
        //创建类目轴，并且不显示竖着的分割线，onZero=false
        CategoryAxis category = new CategoryAxis()
                .splitLine(new SplitLine().show(true))
                .axisLine(new AxisLine().onZero(true));


//        //柱状数据
//        Line line = new Line("智能电视");
//        option.legend().data("智能电视").x(X.center).y(Y.bottom);
//        line.stack("智能电视");  
//        line.itemStyle().normal().color("rgb(255, 134, 26)");
//         
//        Line line1 = new Line("应用");
//        option.legend().data("应用").x(X.center).y(Y.bottom);
//        line1.stack("应用");  
//        line1.itemStyle().normal().color("rgb(34, 137, 196)");
//         
//        Line line2 = new Line("户外");
//        option.legend().data("户外").x(X.center).y(Y.bottom);
//        line2.stack("户外");  
//        line2.itemStyle().normal().color("rgb(215, 38, 59)");
//        
//        Line line3 = new Line("内容商");
//        option.legend().data("内容商").x(X.center).y(Y.bottom);
//        line3.stack("内容商");  
//        line3.itemStyle().normal().color("rgb(62, 180, 220)");
//        
//        //循环数据
//        for (VuDataReport vuDataReport : list) {
//            //设置类目
//        	categoryAxis.data(vuDataReport.getOrderDate());
//            //类目对应的柱状图
//        	line.data(vuDataReport.getDs());
//        	line1.data(vuDataReport.getYy());
//        	line2.data(vuDataReport.getHw());
//        	line3.data(vuDataReport.getNr());
//        }
//        //设置类目轴
//        option.xAxis(categoryAxis);
////        // 设置柱状图参数
////        ItemStyle itemStyle = new ItemStyle();
////        NormalExtend normal = new NormalExtend();
////        normal.setPosition("right");
////        normal.setShow(true);
////        itemStyle.setNormal(normal);
////        line.setLabel(itemStyle);
////        line1.setLabel(itemStyle);
////        line2.setLabel(itemStyle);
////        line3.setLabel(itemStyle);
//
//       
//        option.series(line2,line,line1,line3);
        
        List<Line> lines = new ArrayList<Line>();
        for(String t:times){
        	category.data(t);
        }
        for(int i=0;i<names.size();i++){
        	Line line = new Line(names.get(i));
        	option.legend().data(names.get(i)).x(X.center).y(Y.bottom);
            line.stack(names.get(i));  
            line.setName(names.get(i));
        	for(int j=0;j<times.size();j++){
        		
        		boolean flag = false;
        		for (VuDataReport vuDataReport : list) {
        			if(vuDataReport.getOrderDate().equals(times.get(j))&&vuDataReport.getType().equals(names.get(i))){
        				line.data(vuDataReport.getCpmTotal());
        				flag=true;
        			}
        		}
        		if(!flag){
        			line.data(0);
        		}
        	}
        	lines.add(line);
        }
        //设置类目轴
        option.xAxis(category);
        // 设置柱状图参数
        ItemStyle itemStyle = new ItemStyle();
        NormalExtend normal = new NormalExtend();
        Label label = new Label();
        normal.setLabel(label);
        normal.setPosition("outer");
        normal.setShow(false);
        itemStyle.setNormal(normal);
        
       List<Series> series = new ArrayList<Series>();
        for(final Line b:lines){
    	   b.setItemStyle(itemStyle);
    	   Series<Line> s= new Series<Line>() {
    		   @Override
    		public Line type(SeriesType type) {
    			// TODO Auto-generated method stub
    			return b;
    		}
    		};
    		s.setType(SeriesType.line);
    		s.setName(b.getName());
    		s.setData(b.data());
    		s.setItemStyle(itemStyle);
    		series.add(s);
       }
       option.series(series);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);

        return option;
    }
    
    private Option createChart(List<VuDataReport> list) {

        //创建Option对象
        Option option = new Option();

        //设置图表标题，并且居中显示
        option.tooltip(Trigger.axis).title().text("媒体采购成本统计图").x(X.center);
        //设置y轴为值轴
        
        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        option.yAxis(valueAxis);  
        //创建类目轴，并且不显示竖着的分割线，onZero=false
        CategoryAxis categoryAxis = new CategoryAxis()
                .splitLine(new SplitLine().show(false))
                .axisLine(new AxisLine().onZero(false));


        //柱状数据
        Bar bar = new Bar("智能电视");
        option.legend().data("智能电视").x(X.center).y(Y.bottom);
        bar.stack("智能电视");  
        bar.itemStyle().normal().color("rgb(255, 134, 26)").areaStyle().typeDefault().color("rgba(255, 134, 26,0.5)");  
         
        Bar bar1 = new Bar("应用");
        option.legend().data("应用").x(X.center).y(Y.bottom);
        bar1.stack("应用");  
        bar1.itemStyle().normal().color("rgb(34, 137, 196)").areaStyle().typeDefault().color("rgba(34, 137, 196,0.5)");   
         
        Bar bar2 = new Bar("户外");
        option.legend().data("户外").x(X.center).y(Y.bottom);
        bar2.stack("户外");  
        bar2.itemStyle().normal().color("rgb(215, 38, 59)").areaStyle().typeDefault().color("rgba(215, 38, 59,0.5)");  
        
        Bar bar3 = new Bar("内容商");
        option.legend().data("内容商").x(X.center).y(Y.bottom);
        bar3.stack("内容商");  
        bar3.itemStyle().normal().color("rgb(62, 180, 220)").areaStyle().typeDefault().color("rgba(62, 180, 220,0.5)");  
        
        //循环数据
        for (VuDataReport vuDataReport : list) {
            //设置类目
        	categoryAxis.data(vuDataReport.getOrderDate());
            //类目对应的柱状图
            bar.data(vuDataReport.getDs());
            bar1.data(vuDataReport.getYy());
            bar2.data(vuDataReport.getHw());
            bar3.data(vuDataReport.getNr());
        }
        //设置类目轴
        option.xAxis(categoryAxis);
        // 设置柱状图参数
        ItemStyle itemStyle = new ItemStyle();
        NormalExtend normal = new NormalExtend();
        normal.setPosition("top");
        normal.setShow(false);
        itemStyle.setNormal(normal);
        bar.setLabel(itemStyle);
        bar1.setLabel(itemStyle);
        bar2.setLabel(itemStyle);
        bar3.setLabel(itemStyle);

       
        option.series(bar2,bar,bar1,bar3);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);

        return option;
    }

    private Option createLine(List<VuDataReport> list) {

        //创建Option对象
        Option option = new Option();

        //设置图表标题，并且居中显示
        option.tooltip(Trigger.axis).title().text("广告投放CPM统计图").x(X.center);
        //设置y轴为值轴
        
        ValueAxis valueAxis = new ValueAxis();  
        valueAxis.splitLine().show(false);  
        valueAxis.axisLine().lineStyle().width(1);  
        valueAxis.axisLabel().textStyle().fontFamily("方正兰亭黑简体").color("rgb(130, 130, 130)");  
        option.yAxis(valueAxis);  
        //创建类目轴，并且不显示竖着的分割线，onZero=false
        CategoryAxis categoryAxis = new CategoryAxis()
                .splitLine(new SplitLine().show(true))
                .axisLine(new AxisLine().onZero(true));


        //柱状数据
        Line line = new Line("智能电视");
        option.legend().data("智能电视").x(X.center).y(Y.bottom);
        line.stack("智能电视");  
        line.itemStyle().normal().color("rgb(255, 134, 26)");
         
        Line line1 = new Line("应用");
        option.legend().data("应用").x(X.center).y(Y.bottom);
        line1.stack("应用");  
        line1.itemStyle().normal().color("rgb(34, 137, 196)");
         
        Line line2 = new Line("户外");
        option.legend().data("户外").x(X.center).y(Y.bottom);
        line2.stack("户外");  
        line2.itemStyle().normal().color("rgb(215, 38, 59)");
        
        Line line3 = new Line("内容商");
        option.legend().data("内容商").x(X.center).y(Y.bottom);
        line3.stack("内容商");  
        line3.itemStyle().normal().color("rgb(62, 180, 220)");
        
        //循环数据
        for (VuDataReport vuDataReport : list) {
            //设置类目
        	categoryAxis.data(vuDataReport.getOrderDate());
            //类目对应的柱状图
        	line.data(vuDataReport.getDs());
        	line1.data(vuDataReport.getYy());
        	line2.data(vuDataReport.getHw());
        	line3.data(vuDataReport.getNr());
        }
        //设置类目轴
        option.xAxis(categoryAxis);
//        // 设置柱状图参数
//        ItemStyle itemStyle = new ItemStyle();
//        NormalExtend normal = new NormalExtend();
//        normal.setPosition("right");
//        normal.setShow(true);
//        itemStyle.setNormal(normal);
//        line.setLabel(itemStyle);
//        line1.setLabel(itemStyle);
//        line2.setLabel(itemStyle);
//        line3.setLabel(itemStyle);

       
        option.series(line2,line,line1,line3);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().x(180);

        return option;
    }

}
