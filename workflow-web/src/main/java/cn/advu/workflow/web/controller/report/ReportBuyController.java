package cn.advu.workflow.web.controller.report;

import cn.advu.workflow.domain.fcf_vu.datareport.VuDataReport;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weiqz on 2017/7/13.
 */
@Controller
@RequestMapping("/report/buy")
public class ReportBuyController {

    /**
     * 资源类别
     *
     * @return
     */
    @RequestMapping("/resource")
    public String resource(){
        return "report/buy_index";
    }
    /**
     * 厂商
     *
     * @return
     */
    @RequestMapping("/area")
    public String area(){
        return "report/buy_index";
    }




    @RequestMapping("/resource/query")
    @ResponseBody
    public ResultJson<VuDataReport> queryResource(
            String lid, String startDate, String endDate, String options,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(new DateTime().withDayOfYear(1).withHourOfDay(1).withMinuteOfHour(1).toString("yyyy/MM/dd HH:mm:ss")):startDate;
        endDate = StringUtils.isEmpty(endDate)?(new DateTime().toString("yyyy/MM/dd HH:mm:ss")):endDate;
        options = StringUtils.isEmpty(options)?"1":options;

        return result;
    }



    @RequestMapping("/area/query")
    @ResponseBody
    public ResultJson<VuDataReport> queryArea(
            String lid, String startDate, String endDate, String options,
            HttpServletRequest request, HttpServletResponse response) {
        ResultJson result = new ResultJson<>(WebConstants.OPERATION_FAILURE);
        lid = StringUtils.isEmpty(lid)?"1":lid;
        startDate = StringUtils.isEmpty(startDate)?(new DateTime().withDayOfYear(1).withHourOfDay(1).withMinuteOfHour(1).toString("yyyy/MM/dd HH:mm:ss")):startDate;
        endDate = StringUtils.isEmpty(endDate)?(new DateTime().toString("yyyy/MM/dd HH:mm:ss")):endDate;
        options = StringUtils.isEmpty(options)?"1":options;

        return result;
    }
}
