package cn.advu.workflow.web.common.util.echarts;

import java.util.ArrayList;
import java.util.List;

/**
 * echarts工具类
 * @author Niu Qianghong
 *
 */
public class EchartsTools {
    /**
     * 生成图表
     * @param title 标题
     * @param legend 图例
     * @param xAxis X轴数据
     * @param yAxis Y轴数据
     * @param series 表数据
     * @return
     */
    public static <T>Echarts<T> generateChart(String title, List<String> legend,
                                              List<String> xAxis, String yAxis, List<Series<T>> series) {
        /*if(legend.size() != series.size()){
            throw new EchartsException("The number of legends and series should be equal.");
        }*/
        Title t = new Title(title, "");
        Tooltip tt = new Tooltip();
        Legend l = new Legend(legend);
        XAxis x = new XAxis(xAxis);
        YAxis y = new YAxis(yAxis);
        Echarts<T> chart = new Echarts<>(t, tt, l, x, y, series);
        return chart;
    } 
    
    /**
     * 生成表格, 适用于只有一个图例, 一个series的情况
     * @param title
     * @param legend
     * @param xAxis
     * @param yAxis
     * @param series
     * @return
     */
    public static <T>Echarts<T> generateChart(String title, String legend,
                                              List<String> xAxis, String yAxis, Series<T> series) {

        Title t = new Title(title, "");
        Tooltip tt = new Tooltip();
        List<String> legends = new ArrayList<>();
        legends.add(legend);
        Legend l = new Legend(legends);
        XAxis x = new XAxis(xAxis);
        YAxis y = new YAxis(yAxis);
        List<Series<T>> s = new ArrayList<>();
        s.add(series);
        Echarts<T> chart = new Echarts<>(t, tt, l, x, y, s);
        return chart;
    } 
}
