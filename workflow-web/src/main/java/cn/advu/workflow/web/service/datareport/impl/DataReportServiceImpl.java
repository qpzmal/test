package cn.advu.workflow.web.service.datareport.impl;

import cn.advu.workflow.dao.fcf_vu.datareport.VuDataReportMapper;
import cn.advu.workflow.domain.fcf_vu.datareport.VuDataReport;
import cn.advu.workflow.web.service.datareport.DataReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<VuDataReport> querySaleHistoryBySalerTOP3(String startDate, String endDate) {
        List<VuDataReport> result = new ArrayList<>();
       return  dataReportMapper.querySaleHistoryBySalerTOP3(startDate, endDate);


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
    public Map<String,List> queryBuyResourceByDate(String startDate, String endDate, String type) {
    	
    	 List<VuDataReport> result = new ArrayList<>();
         List<String> names = new ArrayList<>();
         List<String> times = new ArrayList<>();
         Map<String,List> maps = new HashMap<String,List>();
        if ("1".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateMonth(startDate, endDate);
            names = dataReportMapper.queryBuyResourceNamesMonth(startDate, endDate);
        	times = dataReportMapper.queryBuyResourceTimesMonth(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else if ("2".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateQuarter(startDate, endDate);
            names = dataReportMapper.queryBuyResourceNamesQuarter(startDate, endDate);
        	times = dataReportMapper.queryBuyResourceTimesQuarter(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else if ("3".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateHalfYear(startDate, endDate);
            names = dataReportMapper.queryBuyResourceNamesHalfYear(startDate, endDate);
        	times = dataReportMapper.queryBuyResourceTimesHalfYear(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else if ("4".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateWholeYear(startDate, endDate);
            names = dataReportMapper.queryBuyResourceNamesWholeYear(startDate, endDate);
        	times = dataReportMapper.queryBuyResourceTimesWholeYear(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else {
            LOGGER.warn("input parameter error:type-{}", type);
        }

        return maps;
    }

    @Override
    public Map<String,List> queryBuyResourceByDateLine(String startDate, String endDate, String type) {

    	List<VuDataReport> result = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> times = new ArrayList<>();
        Map<String,List> maps = new HashMap<String,List>();
        if ("1".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateLineMonth(startDate, endDate);
            names = dataReportMapper.queryBuyResourceNamesLineMonth(startDate, endDate);
        	times = dataReportMapper.queryBuyResourceTimesLineMonth(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else if ("2".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateLineQuarter(startDate, endDate);
            names = dataReportMapper.queryBuyResourceNamesLineQuarter(startDate, endDate);
        	times = dataReportMapper.queryBuyResourceTimesLineQuarter(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else if ("3".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateLineHalfYear(startDate, endDate);
            names = dataReportMapper.queryBuyResourceNamesLineHalfYear(startDate, endDate);
        	times = dataReportMapper.queryBuyResourceTimesLineHalfYear(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else if ("4".equals(type)) {
            result = dataReportMapper.queryBuyResourceByDateLineWholeYear(startDate, endDate);
            names = dataReportMapper.queryBuyResourceNamesLineWholeYear(startDate, endDate);
        	times = dataReportMapper.queryBuyResourceTimesLineWholeYear(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else {
            LOGGER.warn("input parameter error:type-{}", type);
        }

        return maps;
    }

    @Override
    public List<VuDataReport> queryBuyResourceByArea(String startDate, String endDate, String orderKey) {
        List<VuDataReport> result = dataReportMapper.queryBuyArea(startDate, endDate, orderKey);
        return result;
    }

    @Override
    public Map<String,List> queryAreaBudgetByDate(String startDate, String endDate, String type) {
        List<VuDataReport> result = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> times = new ArrayList<>();
        Map<String,List> maps = new HashMap<String,List>();
        if ("1".equals(type)) {
            //result = dataReportMapper.queryAreaBudgetByDateWholeYear(startDate, endDate);
        	result = dataReportMapper.queryAreaBudgetByDateWholeYear(startDate, endDate);
        	names = dataReportMapper.queryAreaBudgetNamesWholeYear(startDate, endDate);
        	times = dataReportMapper.queryAreaBudgetTimesWholeYear(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else if ("2".equals(type)) {
            result = dataReportMapper.queryAreaBudgetByDateHalfYear(startDate, endDate);
            names = dataReportMapper.queryAreaBudgetNamesHalfYear(startDate, endDate);
        	times = dataReportMapper.queryAreaBudgetTimesHalfYear(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        } else if("3".equals(type)) {
            result = dataReportMapper.queryAreaBudgetByDateQuarterYear(startDate, endDate);
            names = dataReportMapper.queryAreaBudgetNamesQuarterYear(startDate, endDate);
        	times = dataReportMapper.queryAreaBudgetTimesQuarterYear(startDate, endDate);
        	maps.put("result", result);
        	maps.put("names", names);
        	maps.put("times", times);

        }

        return maps;
    }

    @Override
    public List<VuDataReport> queryAreaBudgetByDate1(String startDate, String endDate, String type) {
        List<VuDataReport> result = new ArrayList<>();

        if ("1".equals(type)) {
            //result = dataReportMapper.queryAreaBudgetByDateWholeYear(startDate, endDate);
        	result = dataReportMapper.queryAreaBudgetByDateWholeYear1(startDate, endDate);

        } else if ("2".equals(type)) {
            result = dataReportMapper.queryAreaBudgetByDateHalfYear(startDate, endDate);

        } else if("3".equals(type)) {
            result = dataReportMapper.queryAreaBudgetByDateQuarterYear(startDate, endDate);

        }

        return result;
    }
    
    @Override
    public Map<String,List> queryCustomerByProfit(String startDate, String endDate, String customerType, String options) {
    	List<VuDataReport> result = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> times = new ArrayList<>();
        Map<String,List> maps = new HashMap<String,List>();
        	if("1".equals(options)){
        		result = dataReportMapper.queryCustomerByProfitDateMonth(startDate, endDate, Integer.valueOf(customerType));
        		names = dataReportMapper.queryCustomerNamesMonth(startDate, endDate, Integer.valueOf(customerType));
            	times = dataReportMapper.queryCustomerTimesMonth(startDate, endDate, Integer.valueOf(customerType));
            	maps.put("result", result);
            	maps.put("names", names);
            	maps.put("times", times);
        	}else if("2".equals(options)){
        		result = dataReportMapper.queryCustomerByProfitDateQuarter(startDate, endDate, Integer.valueOf(customerType));
        		names = dataReportMapper.queryCustomerNamesQuarter(startDate, endDate, Integer.valueOf(customerType));
            	times = dataReportMapper.queryCustomerTimesQuarter(startDate, endDate, Integer.valueOf(customerType));
            	maps.put("result", result);
            	maps.put("names", names);
            	maps.put("times", times);
        	}else if("3".equals(options)){
        		 result = dataReportMapper.queryCustomerByProfitDateHalfYear(startDate, endDate, Integer.valueOf(customerType));
        		 names = dataReportMapper.queryCustomerNamesHalfYear(startDate, endDate, Integer.valueOf(customerType));
             	times = dataReportMapper.queryCustomerTimesHalfYear(startDate, endDate, Integer.valueOf(customerType));
             	maps.put("result", result);
             	maps.put("names", names);
             	maps.put("times", times);
        	}else if("4".equals(options)){
        		result = dataReportMapper.queryCustomerByProfitDateWholeYear(startDate, endDate, Integer.valueOf(customerType));
        		names = dataReportMapper.queryCustomerNamesWholeYear(startDate, endDate, Integer.valueOf(customerType));
            	times = dataReportMapper.queryCustomerTimesWholeYear(startDate, endDate, Integer.valueOf(customerType));
            	maps.put("result", result);
            	maps.put("names", names);
            	maps.put("times", times);
        	}

        return maps;
    }
    
    
    @Override
    public List<VuDataReport> queryCustomerByPay(String startDate, String endDate) {
        List<VuDataReport> result = new ArrayList<>();
        result = dataReportMapper.queryCustomerByPay(startDate, endDate);

        return result;
    }
}
