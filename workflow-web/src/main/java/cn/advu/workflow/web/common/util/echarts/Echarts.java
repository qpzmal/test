package cn.advu.workflow.web.common.util.echarts;

import java.util.List;

/**
 * echarts实体类, 目前只适用于折线图和柱状图
 * @author Niu Qianghong
 *
 * @param <T> 图表值的数据类型
 */
public class Echarts<T> {
    private Title title;//标题
    private Tooltip tooltip;//提示信息
    private Legend legend;//图例
    private XAxis xAxis;//X轴数据
    private YAxis yAxis;//Y轴数据
    private List<Series<T>> series;//表格数据
        
    public Echarts() {
        super();
    }
    
    public Echarts(Title title, Tooltip tooltip, Legend legend, XAxis xAxis, YAxis yAxis, List<Series<T>> series) {
        super();
        this.title = title;
        this.tooltip = tooltip;
        this.legend = legend;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.series = series;
    }
    public List<Series<T>> getSeries() {
        return series;
    }
    public void setSeries(List<Series<T>> series) {
        this.series = series;
    }
    public YAxis getyAxis() {
        return yAxis;
    }
    public void setyAxis(YAxis yAxis) {
        this.yAxis = yAxis;
    }
    public XAxis getxAxis() {
        return xAxis;
    }
    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }
    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }
    public Tooltip getTooltip() {
        return tooltip;
    }
    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }
    public Legend getLegend() {
        return legend;
    }
    public void setLegend(Legend legend) {
        this.legend = legend;
    }
}
