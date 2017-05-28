package cn.advu.workflow.web.service.zwsc.impl;

import cn.advu.workflow.dao.database.ZwscChargeMapper;
import cn.advu.workflow.dao.database.ZwscChargeRangeMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.util.ExportTools;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.common.util.echarts.EchartsTools;
import cn.advu.workflow.web.common.util.echarts.Series;
import cn.advu.workflow.web.service.zwsc.ZChargeDataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZChargeDataServiceImpl implements ZChargeDataService{
    
    @Autowired
    private ZwscChargeMapper chargeMapper;
    
    @Autowired
    private ZwscChargeRangeMapper rangeMapper;

    private static Logger LOGGER = Logger.getLogger(ZChargeDataServiceImpl.class);
   
    @Override
    public List<Map<String, Object>> exportTrendChart(Map<String, Object> paramMap) {
        return getChargeTrendData(paramMap);
    }

    @Override
    public ResultJson<Map<String, Object>> getTrendEcharts(Map<String, Object> paramMap) {
        
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        //获取头部的总览数据
        Map<String, Object> summary = chargeMapper.getSummary(version, startTime, endTime, cnids);
        if(summary == null){
            return new ResultJson<>("在此条件下暂无数据!");
        }   
        
        List<Map<String, Object>> chargeData = getChargeTrendData(paramMap);

        if(chargeData == null){
            return new ResultJson<>();
        }
        
        //X轴数据
        List<String> xAxis = new ArrayList<>();
        List<BigDecimal> chargeAmt = new ArrayList<>();
        List<BigDecimal> chargeTime = new ArrayList<>();
        List<BigDecimal> chargeUsers = new ArrayList<>();
        
        for(Map<String, Object> item:chargeData){
            xAxis.add(item.get("timeCycle").toString());
            chargeAmt.add((BigDecimal)item.get("chargeAmt"));
            chargeTime.add((BigDecimal)item.get("chargeTime"));
            chargeUsers.add((BigDecimal)item.get("chargeUsers"));
        }
        //图数据
        Series<BigDecimal> series1 = new Series<>("充值总额(元)", WebConstants.LINE_CHART, chargeAmt);
        Series<BigDecimal> series2 = new Series<>("充值次数(次)", WebConstants.LINE_CHART, chargeTime);
        Series<BigDecimal> series3 = new Series<>("充值用户数(人)", WebConstants.LINE_CHART, chargeUsers);

        //图例
        List<String> legend = new ArrayList<>();
        legend.add("充值总额(元)");
        legend.add("充值次数(次)");
        legend.add("充值用户数(人)");
        
        List<Series<BigDecimal>> series = new ArrayList<>();
        series.add(series1);
        series.add(series2);
        series.add(series3);
        
        Echarts<BigDecimal> chart = EchartsTools.generateChart("充值趋势图", legend, xAxis, "", series);
        
        ResultJson<Map<String, Object>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        
        Map<String, Object> data = new HashMap<>();
        data.put("chart", chart);
        data.put("summary", summary);
        
        rj.setData(data);
        return rj;
    }

    /**
     * 获取充值趋势数据[趋势图和导出使用]
     * @param paramMap
     * @return
     */
    private List<Map<String, Object>> getChargeTrendData(Map<String, Object> paramMap){
        List<Map<String, Object>> chargeData = null;
        try {
            Integer timeCycle = (Integer) paramMap.get("timeCycle");
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            //timeCycle含义: 1-日, 2-周, 3-月
            chargeData = null;
            //按照日周月统计数据
            if(timeCycle == 1){
                chargeData = chargeMapper.getDataByDay(version, startTime, endTime, cnids);
            } else if(timeCycle == 2){
                chargeData = chargeMapper.getDataByWeek(version, startTime, endTime, cnids);
            } else if(timeCycle == 3){
                chargeData = chargeMapper.getDataByMonth(version, startTime, endTime, cnids);
            } else{
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return chargeData;
    }
    
    @Override
    public ResultJson<List<Map<String, Object>>> getWayPageData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer) paramMap.get("pageNo");
        int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
        List<Map<String, Object>> wayData = chargeMapper.getWayDataByPage(version, startTime, endTime, cnids, startRow, WebConstants.COMMON_PAGESIZE);
        
        if(wayData == null){
            return new ResultJson<List<Map<String, Object>>>("暂无数据!");
        }
        
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(wayData);
        
        return rj;
    }

    @Override
    public ResultJson<Integer> getWayTotalPages(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        int count = chargeMapper.getWayTotal(version, startTime, endTime, cnids);
        ResultJson<Integer> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        int totalPages = count % WebConstants.COMMON_PAGESIZE == 0 ?
                count/WebConstants.COMMON_PAGESIZE : count/WebConstants.COMMON_PAGESIZE + 1;
        rj.setData(totalPages);
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getGivePageData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer) paramMap.get("pageNo");
        int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
        List<Map<String, Object>> giveData = chargeMapper.getGiveData(version, startTime, endTime, cnids, startRow, WebConstants.COMMON_PAGESIZE);
        if(giveData == null){
            return new ResultJson<List<Map<String, Object>>>("暂无数据!");
        }
        
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(giveData);
        return rj;
    }
    
    @Override
    public ResultJson<Integer> getGiveTotalPages(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        int count = chargeMapper.getGiveTotal(version, startTime, endTime, cnids);
        ResultJson<Integer> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        int totalPages = count % WebConstants.COMMON_PAGESIZE == 0 ? 
                count/WebConstants.COMMON_PAGESIZE : count/WebConstants.COMMON_PAGESIZE + 1;
        rj.setData(totalPages);
        return rj;
    }

    @Override
    public Integer getWaySumMoney(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer wayCode = (Integer) paramMap.get("wayCode");
        return chargeMapper.getWaySumMoney(wayCode, version, startTime, endTime, cnids);
    }

    @Override
    public List<Map<String, Object>> exportWayData(Map<String, Object> paramMap) {
        List<Map<String, Object>> wayData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            wayData = chargeMapper.getWayDataByPage(version, startTime, endTime, cnids,0,0);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return wayData;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getWayDetailData(Map<String, Object> paramMap) {
        List<Map<String, Object>> wayDetail = getDetail(paramMap);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(wayDetail);
        return rj;
    }

    private List<Map<String, Object>> getDetail(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer wayCode = (Integer) paramMap.get("wayCode");
        List<Map<String, Object>> wayDetail = rangeMapper.getWayDetail(version, startTime, endTime, cnids, wayCode);
        return wayDetail;
    }

    @Override
    public ResultJson<Object> exportDetailData(Map<String, Object> paramMap) {
        List<Map<String, Object>> wayDetail = getDetail(paramMap);
        String chargeWay = (String) paramMap.get("chargeWay");
        List<String> titles = new ArrayList<>();
        titles.add("充值金额(元)");
        titles.add("充值次数");
        titles.add("次数占比");
        titles.add("充值用户数");
        titles.add("用户占比");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:wayDetail){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("grade"));
            rowData.add(item.get("pv"));
            rowData.add(item.get("pvRate")+"%");
            rowData.add(item.get("uv"));
            rowData.add(item.get("uvRate")+"%");
            data.add(rowData);
        }
        try {
            ExportTools.exportPlainExcel("f:\\" + chargeWay + "充值详细.xls", chargeWay, titles , data );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson<>();
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

}
