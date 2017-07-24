package cn.advu.workflow.web.service.datareport.impl;

import cn.advu.workflow.dao.fcf_vu.datareport.VuDataReportMapper;
import cn.advu.workflow.domain.fcf_vu.datareport.VuDataReport;
import cn.advu.workflow.web.service.datareport.DataReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataReportServiceImpl implements DataReportService {

    private static Logger LOGGER = LoggerFactory.getLogger(DataReportServiceImpl.class);

    @Autowired
    private VuDataReportMapper dataReportMapper;


    @Override
    public List<VuDataReport> querySaleHistoryByDate(String startDate, String endDate, String type) {
        List<VuDataReport> result = new ArrayList<>();
        if ("1".equals(type)) {
            result = dataReportMapper.querySaleHistoryByDateMonth(startDate, endDate);

        } else if ("2".equals(type)) {
            result = dataReportMapper.querySaleHistoryByDateQuarter(startDate, endDate);

        } else if ("3".equals(type)) {
            result = dataReportMapper.querySaleHistoryByDateHalfYear(startDate, endDate);

        } else if ("4".equals(type)) {
            result = dataReportMapper.querySaleHistoryByDateWholeYear(startDate, endDate);

        } else {
            result = dataReportMapper.querySaleHistoryByDate(startDate, endDate);
        }

        return result;
    }

    @Override
    public List<VuDataReport> querySaleHistoryByArea() {
        List<VuDataReport> result = new ArrayList<>();
        result = dataReportMapper.querySaleHistoryByArea();
        return result;
    }

    @Override
    public List<VuDataReport> querySaleHistoryBySaler(String startDate, String endDate, String type) {
        List<VuDataReport> result = new ArrayList<>();
        if ("1".equals(type)) {
            result = dataReportMapper.querySaleHistoryBySalerMonth(startDate, endDate);

        } else if ("2".equals(type)) {
            result = dataReportMapper.querySaleHistoryBySalerQuarter(startDate, endDate);

        } else if ("4".equals(type)) {
            result = dataReportMapper.querySaleHistoryBySalerWholeYear(startDate, endDate);

        } else {
            result = dataReportMapper.querySaleHistoryBySaler(startDate, endDate);
        }


        return result;
    }

    @Override
    public List<VuDataReport> querySaleHistoryByCustomType(String startDate, String endDate, String customType) {
        List<VuDataReport> result = new ArrayList<>();
        result = dataReportMapper.querySaleHistoryByCustomType(startDate, endDate, customType);

        return result;
    }

    @Override
    public List<VuDataReport> querySaleFeature(String startDate, String endDate, String queryType) {
        List<VuDataReport> result = new ArrayList<>();
        result = dataReportMapper.querySaleFeature(startDate, endDate, queryType);

        return result;
    }

    @Override
    public List<VuDataReport> queryAreaBudgetByDate(String startDate, String endDate, String type) {
        List<VuDataReport> result = new ArrayList<>();
        result = dataReportMapper.querySaleFeature(startDate, endDate, type);

        return result;
    }

    @Override
    public List<VuDataReport> queryCustomerByProfit(String startDate, String endDate) {
        List<VuDataReport> result = new ArrayList<>();
        result = dataReportMapper.queryCustomerByProfit(startDate, endDate);

        return result;
    }

    @Override
    public List<VuDataReport> queryCustomerByPay(String startDate, String endDate) {
        List<VuDataReport> result = new ArrayList<>();
        result = dataReportMapper.queryCustomerByPay(startDate, endDate);

        return result;
    }
}
