package cn.advu.workflow.web.service.zwsc.impl;

import cn.advu.workflow.dao.database.ZwscDashangMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.util.ExportTools;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.common.util.echarts.EchartsTools;
import cn.advu.workflow.web.common.util.echarts.Series;
import cn.advu.workflow.web.service.zwsc.ZConsumeDSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZConsumeDSServiceImpl implements ZConsumeDSService{

    @Autowired
    private ZwscDashangMapper dsMapper;
    
    private List<Map<String, Object>> getRawAmtData(Map<String, Object> paramMap){
        Integer timeCycle = (Integer)paramMap.get("timeCycle");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        List<Map<String, Object>> amtData = null;
        //按照日周月统计数据
        if(timeCycle == 1){
            amtData  = dsMapper.getAmtDataByDay(startTime, endTime);       
        } else if(timeCycle == 2){
            amtData = dsMapper.getAmtDataByWeek(startTime, endTime);
        } else if(timeCycle == 3){
            amtData = dsMapper.getAmtDataByMonth(startTime, endTime);
        }
        return amtData;
    }
    
    @Override
    public ResultJson<Map<String, Object>> getAmtData(Map<String, Object> paramMap) {
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        Map<String, Object> summary = dsMapper.getSummaryData(startTime, endTime);
        
        List<Map<String, Object>> amtData = getRawAmtData(paramMap);
        List<String> legend = new ArrayList<>();
        legend.add("打赏金额");
        legend.add("打赏次数");
        legend.add("打赏用户数");
        List<String> xAxis = new ArrayList<>();
        List<Series<BigDecimal>> series = new ArrayList<>();
        Series<BigDecimal> s1 = new Series<>("打赏金额");
        Series<BigDecimal> s2 = new Series<>("打赏次数");
        Series<BigDecimal> s3 = new Series<>("打赏用户数");
        for(Map<String, Object> item:amtData){
            xAxis.add(item.get("timeCycle").toString());
            s1.add((BigDecimal) item.get("moneySum"));
            s2.add((BigDecimal) item.get("timeSum"));
            s3.add((BigDecimal) item.get("userSum"));
        }
        series.add(s1);
        series.add(s2);
        series.add(s3);
        Echarts<BigDecimal> chart = EchartsTools.generateChart("打赏消费", legend, xAxis, "", series);
        Map<String, Object> data = new HashMap<>();
        data.put("chart", chart);
        data.put("summary", summary);
        ResultJson<Map<String, Object>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(data);
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getTypeData(Map<String, Object> paramMap) {
        List<Map<String, Object>> typeData = getRawTypeData(paramMap);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(typeData);
        return rj;
    }

    private List<Map<String, Object>> getRawTypeData(Map<String, Object> paramMap) {
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        List<Map<String, Object>> typeData = dsMapper.getTypeData(startTime, endTime);
        for(Map<String, Object> item:typeData){
            String type = (String) item.get("dsType");
            if("1".equals(type)){
                item.put("dsType", "图书");
            } else if("2".equals(type)){
                item.put("dsType", "书单");
            } else if("3".equals(type)){
                item.put("dsType", "直播间");
            }
        }
        return typeData;
    }

    @Override
    public ResultJson<Object> exportTypeData(Map<String, Object> paramMap) {
        List<Map<String, Object>> typeData = getRawTypeData(paramMap);
        List<String> titles = new ArrayList<>();
        titles.add("打赏对象");
        titles.add("打赏金额");
        titles.add("打赏次数");
        titles.add("打赏用户数");
        List<List<Object>> data = new ArrayList<>();
        for(Map<String, Object> item:typeData){
            List<Object> rowData = new ArrayList<>();
            rowData.add(item.get("dsType"));
            rowData.add(item.get("moneySum"));
            rowData.add(item.get("timeSum"));
            rowData.add(item.get("userSum"));
            data.add(rowData);
        }
        try {
            ExportTools.exportPlainExcel("f:\\打赏对象数据.xls", "sheet1", titles, data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson<>();
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

}
