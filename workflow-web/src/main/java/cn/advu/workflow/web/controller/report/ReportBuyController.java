package cn.advu.workflow.web.controller.report;

import cn.advu.workflow.domain.fcf_vu.datareport.VuDataReport;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.datareport.DataReportService;
import cn.advu.workflow.web.third.echarts.NormalExtend;
import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
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
import java.util.List;

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
        startDate = StringUtils.isEmpty(startDate)?(new DateTime().withDayOfYear(1).withHourOfDay(1).withMinuteOfHour(1).toString("yyyy/MM/dd HH:mm:ss")):startDate;
        endDate = StringUtils.isEmpty(endDate)?(new DateTime().toString("yyyy/MM/dd HH:mm:ss")):endDate;
        options = StringUtils.isEmpty(options)?"1":options;
        orderKey = StringUtils.isEmpty(orderKey)?"cpm_total":orderKey;

        LOGGER.info("orderKey:{},options:{}", orderKey, options);
        LOGGER.info("startDate:{},endDate:{}", startDate, endDate);

        List<VuDataReport> list = dataReportService.queryBuyResourceByDate(startDate, endDate, options, orderKey);
        Option option = this.createChart4Date(list, orderKey);

        result.setData(option);
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
        Option option = this.createChart4Area(list, orderKey);

        result.setData(option);
        result.setCode(WebConstants.OPERATION_SUCCESS).setInfo("成功");
        LOGGER.info("result:{}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }


    private Option createChart4Date(List<VuDataReport> list, String orderKey) {
        return this.createChartWithType(list, orderKey, 1);
    }
    private Option createChart4Area(List<VuDataReport> list, String orderKey) {
        return this.createChartWithType(list, orderKey, 2);
    }

    private Option createChartWithType(List<VuDataReport> list, String orderKey, int type) {
        //创建Option
        Option option = new Option();
//        option.title("销售汇总").tooltip(Trigger.axis).legend("金额（元）");
        option.title("采购汇总").legend("金额（元）").tooltip().trigger();
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
            if (1 == type) { // orderDate
                if ("cpm_total".equals(orderKey)) {
                    //设置类目
                    category.data(vuDataReport.getOrderDate());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getCpmTotal());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getOrderDate(), vuDataReport.getCpmTotal()));
                } else if ("sale_amount".equals(orderKey)) { //
                    //设置类目
                    category.data(vuDataReport.getOrderDate());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getSaleAmount());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getOrderDate(), vuDataReport.getSaleAmount()));
                } else if ("buy_amount".equals(orderKey)) { //
                    //设置类目
                    category.data(vuDataReport.getOrderDate());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getBuyAmount());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getOrderDate(), vuDataReport.getBuyAmount()));
                }
            } else if (2 == type) { //
                if ("cpm_total".equals(orderKey)) {
                    //设置类目
                    category.data(vuDataReport.getName());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getCpmTotal());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getName(), vuDataReport.getCpmTotal()));
                } else if ("sale_amount".equals(orderKey)) { //
                    //设置类目
                    category.data(vuDataReport.getName());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getSaleAmount());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getName(), vuDataReport.getSaleAmount()));
                } else if ("buy_amount".equals(orderKey)) { //
                    //设置类目
                    category.data(vuDataReport.getName());
                    //类目对应的柱状图
                    bar.data(vuDataReport.getBuyAmount());
                    //饼图数据
                    pie.data(new PieData(vuDataReport.getName(), vuDataReport.getBuyAmount()));
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


}
