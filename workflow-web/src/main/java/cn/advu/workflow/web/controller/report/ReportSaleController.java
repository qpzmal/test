package cn.advu.workflow.web.controller.report;

import cn.advu.workflow.domain.fcf_vu.datareport.VuDataReport;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.datareport.DataReportService;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/report/sale")
public class ReportSaleController {

    private static Logger LOGGER = LoggerFactory.getLogger(ReportSaleController.class);


    @Autowired
    private DataReportService dataReportService;

    /**
     * 历史订单
     *
     * @return
     */
    @RequestMapping("/history")
    public String history(){
        return "report/sale_history";
    }

    /**
     * 未来订单
     *
     * @return
     */
    @RequestMapping("/feature")
    public String feature(){
        return "report/sale_feature";
    }



    @RequestMapping("/history/query")
    @ResponseBody
    public ResultJson<VuDataReport> queryHistory(
            String lid, String startDate, String endDate, String options,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(new DateTime().withDayOfYear(1).withHourOfDay(1).withMinuteOfHour(1).toString("yyyy/MM/dd HH:mm:ss")):startDate;
        endDate = StringUtils.isEmpty(endDate)?(new DateTime().toString("yyyy/MM/dd HH:mm:ss")):endDate;
        options = StringUtils.isEmpty(options)?"1":options;

        List<VuDataReport> list = new ArrayList<>();
        if ("1".equals(lid)) {
            list = dataReportService.querySaleHistoryByDate(startDate, endDate, options);
        } else if ("2".equals(lid)) {
            list = dataReportService.querySaleHistoryByArea();
        } else if ("3".equals(lid)) {
            list = dataReportService.querySaleHistoryBySaler(startDate, endDate, options);
        } else if ("4".equals(lid)) {
            list = dataReportService.querySaleHistoryByCustomType(startDate, endDate, options);
        } else {
            LOGGER.error("lid 参数错误。");
        }
        result.setData(list);

        return result;
    }



    @RequestMapping("/feature/query")
    @ResponseBody
    public ResultJson<Object> queryFeature(
            String lid, String startDate, String endDate, String options,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(new DateTime().withDayOfYear(1).withHourOfDay(1).withMinuteOfHour(1).toString("yyyy/MM/dd HH:mm:ss")):startDate;
        endDate = StringUtils.isEmpty(endDate)?(new DateTime().toString("yyyy/MM/dd HH:mm:ss")):endDate;
        options = StringUtils.isEmpty(options)?"1":options;


        List<VuDataReport> list = new ArrayList<>();
        list = dataReportService.querySaleFeature(startDate, endDate, options);
        result.setData(list);

        return result;
    }

}
