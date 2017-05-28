package cn.advu.workflow.web.service.zwsc.impl;

import cn.advu.workflow.dao.database.ZwscVipConsumeMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.common.util.echarts.EchartsTools;
import cn.advu.workflow.web.common.util.echarts.Series;
import cn.advu.workflow.web.service.zwsc.ZVipService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZVipServiceImpl implements ZVipService{

    private static Logger LOGGER = Logger.getLogger(ZVipServiceImpl.class);
    @Autowired
    private ZwscVipConsumeMapper vipMapper;

    private Map<String, Object> getAmtData(Map<String, Object> paramMap){
        Map<String, Object> amtAndSummary = null;
        try {
            Integer timeCycle = (Integer)paramMap.get("timeCycle");
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            List<Map<String, Object>> amtData = null;
            Map<String, Object> summaryData = null;
            //按照日周月统计数据
            if(timeCycle == 1){
                amtData  = vipMapper.getAmtDataByDay(version, startTime, endTime, cnids);
            } else if(timeCycle == 2){
                amtData = vipMapper.getAmtDataByWeek(version, startTime, endTime, cnids);
            } else if(timeCycle == 3){
                amtData = vipMapper.getAmtDataByMonth(version, startTime, endTime, cnids);
            }
            summaryData = vipMapper.getSummaryData(version, startTime, endTime, cnids);
            amtAndSummary = new HashMap<>();
            amtAndSummary.put("amtData", amtData);
            amtAndSummary.put("summaryData", summaryData);
        } catch (Exception e) {
           LOGGER.error(e);
        }
        return amtAndSummary;
    }
    
    @Override
    public ResultJson<Map<String, Object>> getAmtChart(Map<String, Object> paramMap) {
        ResultJson<Map<String, Object>> rj = null;
        try {
            Map<String, Object> amtAndSummary = getAmtData(paramMap);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> amtData = (List<Map<String, Object>>) amtAndSummary.get("amtData");
            List<String> legend = new ArrayList<>();
            legend.add("消费总额");
            legend.add("开通次数");
            legend.add("开通用户数");
            List<String> xAxis = new ArrayList<>();
            Series<BigDecimal> s1 = new Series<>("消费总额");
            Series<BigDecimal> s2 = new Series<>("开通次数");
            Series<BigDecimal> s3 = new Series<>("开通用户数");
            for(Map<String, Object> item:amtData){
                xAxis.add(item.get("timeCycle").toString());
                s1.add((BigDecimal) item.get("moneySum"));
                s2.add((BigDecimal) item.get("pvSum"));
                s3.add((BigDecimal) item.get("uvSum"));
            }
            List<Series<BigDecimal>> series = new ArrayList<>();
            series.add(s1);
            series.add(s2);
            series.add(s3);
            Echarts<BigDecimal> chart = EchartsTools.generateChart("VIP消费图", legend, xAxis, "", series);
            Map<String, Object> data = new HashMap<>();
            data.put("chart", chart);
            data.put("summary", amtAndSummary.get("summaryData"));
            rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
            rj.setData(data);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return rj;
    }

    private List<Map<String, Object>> getRawOpenData(Map<String, Object> paramMap){
        List<Map<String, Object>> openData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            Integer pageNo = (Integer) paramMap.get("pageNo");
            int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
            openData = vipMapper.getOpenData(version, startTime, endTime, cnids, startRow, WebConstants.COMMON_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return openData;
    }
    
    @Override
    public ResultJson<List<Map<String, Object>>> getOpenData(Map<String, Object> paramMap) {
        ResultJson<List<Map<String, Object>>> rj = null;
        try {
            List<Map<String, Object>> openData = getRawOpenData(paramMap);
            if(openData.size() == 0){
                return new ResultJson<>("在此条件下暂无数据!");
            }
            rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
            rj.setData(openData);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public ResultJson<Integer> getOpenTotalPages(Map<String, Object> paramMap) {
        ResultJson<Integer> rj = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            int count = vipMapper.getOpenTotalPages(version, startTime, endTime, cnids);
            rj = new ResultJson<>();
            rj.setCode(WebConstants.OPERATION_SUCCESS);
            int totalPages = count % WebConstants.COMMON_PAGESIZE == 0 ?
                    count/WebConstants.COMMON_PAGESIZE : count/WebConstants.COMMON_PAGESIZE + 1;
            rj.setData(totalPages);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return rj;
    }


    @Override
    public ResultJson<List<Map<String, Object>>> getDonateData(Map<String, Object> paramMap) {
        ResultJson<List<Map<String, Object>>> rj = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            Integer pageNo = (Integer) paramMap.get("pageNo");
            int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
            List<Map<String, Object>> donateData = vipMapper.getDonataData(version, startTime, endTime, cnids,startRow, WebConstants.COMMON_PAGESIZE);
            if(donateData.size() == 0){
                return new ResultJson<>("在此条件下暂无数据!");
            }
            rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
            rj.setData(donateData);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public ResultJson<Integer> getDonateTotalPages(Map<String, Object> paramMap) {
        ResultJson<Integer> rj = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            int count = vipMapper.getDonateTotalPages(version, startTime, endTime, cnids);
            rj = new ResultJson<>();
            rj.setCode(WebConstants.OPERATION_SUCCESS);
            int totalPages = count % WebConstants.COMMON_PAGESIZE == 0 ?count/WebConstants.COMMON_PAGESIZE : count/WebConstants.COMMON_PAGESIZE + 1;
            rj.setData(totalPages);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportOpenData(Map<String, Object> paramMap) {
        List<Map<String, Object>> openData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            Integer pageNo = (Integer) paramMap.get("pageNo");
            openData = vipMapper.getOpenData(version, startTime, endTime, cnids, 0, 0);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return openData;
    }

    @Override
    public List<Map<String, Object>> exportChart(Map<String, Object> paramMap) {
        Map<String, Object> amtAndSummary = getAmtData(paramMap);
        return  (List<Map<String, Object>>) amtAndSummary.get("amtData");

    }

    @Override
    public List<Map<String, Object>> exportDonate(Map<String, Object> paramMap) {
        List<Map<String, Object>> donateData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            donateData = vipMapper.getDonataData(version, startTime, endTime, cnids,0, 0);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return donateData;
    }
}
