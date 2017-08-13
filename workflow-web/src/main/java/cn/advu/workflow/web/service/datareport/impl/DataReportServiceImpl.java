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
        if ("0".equals(type)) {
            result = dataReportMapper.querySaleHistoryBySaler(startDate, endDate);
        } else if ("1".equals(type)) {
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
    public List<VuDataReport> queryBuyResourceByDate(String startDate, String endDate, String type, String orderKey) {

        List<VuDataReport> result = new ArrayList<>();
        if ("1".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateMonth(startDate, endDate, orderKey);

        } else if ("2".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateQuarter(startDate, endDate, orderKey);

        } else if ("3".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateHalfYear(startDate, endDate, orderKey);

        } else if ("4".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateWholeYear(startDate, endDate, orderKey);

        } else {
            LOGGER.warn("input parameter error:type-{}", type);
        }

        return result;
    }


    @Override
    public List<VuDataReport> queryBuyResourceByArea(String startDate, String endDate, String orderKey) {
        List<VuDataReport> result = dataReportMapper.queryBuyArea(startDate, endDate, orderKey);
        return result;
    }

    @Override
    public List<VuDataReport> queryAreaBudgetByDate(String startDate, String endDate, String type) {
        List<VuDataReport> result = new ArrayList<>();
        if ("1".equals(type)) {
            result = dataReportMapper.queryAreaBudgetByDateWholeYear(startDate, endDate);

        } else if ("2".equals(type)) {
            result = dataReportMapper.queryAreaBudgetByDateHalfYear(startDate, endDate);

        } else {
            result = dataReportMapper.queryAreaBudgetByDateWholeYear(startDate, endDate);

        }

        return result;
    }

    @Override
    public List<VuDataReport> queryCustomerByProfit(String startDate, String endDate, String customerType, String options) {
        List<VuDataReport> result = new ArrayList<>();
        if ("0".equals(options)) {
            result = dataReportMapper.queryCustomerByProfitNoDate(startDate, endDate, customerType);

        } else if ("1".equals(options)) {
            result = dataReportMapper.queryCustomerByProfitDateMonth(startDate, endDate, customerType);

        } else if ("2".equals(options)) {
            result = dataReportMapper.queryCustomerByProfitDateQuarter(startDate, endDate, customerType);

        } else if ("3".equals(options)) {
            result = dataReportMapper.queryCustomerByProfitDateHalfYear(startDate, endDate, customerType);

        } else if ("4".equals(options)) {
            result = dataReportMapper.queryCustomerByProfitDateWholeYear(startDate, endDate, customerType);

        }

        return result;
    }

    @Override
    public List<VuDataReport> queryCustomerByPay(String startDate, String endDate) {
        List<VuDataReport> result = new ArrayList<>();
        result = dataReportMapper.queryCustomerByPay(startDate, endDate);

        return result;
    }
}
