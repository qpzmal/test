package cn.advu.workflow.web.common.util.echarts;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TestJackson {
    public static void main(String[] args) throws Exception {
        testChart00();
    }
    
    public static void testChart00() throws Exception {
        List<String> data = new ArrayList<>();
        data.add("最高温度");        
        data.add("最低温度"); 
        List<String> xdata = new ArrayList<>();
        xdata.add("周一");
        xdata.add("周二");
        xdata.add("周三");
        List<Series<Integer>> series = new ArrayList<>();
        List<Integer> result1 = new ArrayList<>();
        result1.add(11);
        result1.add(11);
        result1.add(15);
        List<Integer> result2 = new ArrayList<>();
        result2.add(1);
        result2.add(-2);
        result2.add(2);
        Series<Integer> s1 = new Series<>("最高温度", "line", result1);
        Series<Integer> s2 = new Series<>("最低温度", "line", result2);
        series.add(s1);
        series.add(s2);
        Echarts<Integer> chart = null;
        chart = EchartsTools.generateChart("温度图", data, xdata, "℃", series);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(chart);
        System.out.println(json);
    }
    
    public static void testChart01() throws Exception {
        Echarts<Integer> chart = new Echarts<>();
        chart.setTitle(new Title("父标题", "子标题"));
        chart.setTooltip(new Tooltip("item"));
        Legend legend = new Legend();
        List<String> data = new ArrayList<>();
        data.add("最高温度");        
        data.add("最低温度");        
        legend.setData(data);
        chart.setLegend(legend);
        
        List<String> xdata = new ArrayList<>();
        xdata.add("周一");
        xdata.add("周二");
        xdata.add("周三");
        XAxis xAxis = new XAxis(xdata);
        chart.setxAxis(xAxis);
        YAxis yAxis = new YAxis("℃");
        chart.setyAxis(yAxis);
        
        List<Series<Integer>> series = new ArrayList<>();
        List<Integer> result1 = new ArrayList<>();
        result1.add(11);
        result1.add(11);
        result1.add(15);
        List<Integer> result2 = new ArrayList<>();
        result2.add(1);
        result2.add(-2);
        result2.add(2);
        Series<Integer> s1 = new Series<>("最高温度", "line", result1);
        Series<Integer> s2 = new Series<>("最低温度", "line", result2);
        series.add(s1);
        series.add(s2);
        chart.setSeries(series);
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(chart);
        System.out.println(json);
    }
}
