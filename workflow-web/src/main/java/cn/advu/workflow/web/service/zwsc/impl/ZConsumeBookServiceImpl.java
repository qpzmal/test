package cn.advu.workflow.web.service.zwsc.impl;

import cn.advu.workflow.dao.database.ZwscChapterConsumeMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.common.util.echarts.EchartsTools;
import cn.advu.workflow.web.common.util.echarts.Series;
import cn.advu.workflow.web.service.zwsc.ZConsumeBookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZConsumeBookServiceImpl implements ZConsumeBookService{

    private static Logger LOGGER = Logger.getLogger(ZConsumeBookServiceImpl.class);

    @Autowired
    private ZwscChapterConsumeMapper chapterConsumeMapper;

    @Override
    public ResultJson<List<Map<String, Object>>> getTypeDataByPage(Map<String, Object> paramMap, Integer pageNo) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
        
        List<Map<String, Object>> consumeData = chapterConsumeMapper.getTypeDataByPage(version,
                startTime, endTime, cnids, startRow, WebConstants.COMMON_PAGESIZE);
        
        if(consumeData == null){
            return new ResultJson<List<Map<String, Object>>>("暂无数据!");
        } else if(consumeData.size() == 0){
            return new ResultJson<List<Map<String, Object>>>("暂无数据!");
        }
        
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(consumeData);
        return rj;
    }

    @Override
    public ResultJson<Integer> getTypeTotalPages(Map<String, Object> paramMap) {
        ResultJson<Integer> rj = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            int itemCount = chapterConsumeMapper.getTypePages(version, startTime, endTime, cnids);
            int pageCount = itemCount % WebConstants.COMMON_PAGESIZE == 0?itemCount/WebConstants.COMMON_PAGESIZE:itemCount/WebConstants.COMMON_PAGESIZE + 1;
            rj = new ResultJson<>();
            rj.setCode(WebConstants.OPERATION_SUCCESS);
            rj.setData(pageCount);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public ResultJson<Map<String, Object>> getAmtChartData(Map<String, Object> paramMap) {
        
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        
        //查询基础数据
        Map<String, Object> summary =
                chapterConsumeMapper.getSummaryData(version, startTime, endTime, cnids);
        
        if(summary == null || summary.size() == 0){
            return new ResultJson<>("在此条件下暂无数据!");
        }
        
        List<Map<String, Object>> amtData = getAmtData(paramMap);
        if(amtData == null){
            return new ResultJson<>();
        }
        List<String> legend = new ArrayList<>();
        legend.add("铜币");
        legend.add("代金券");
        legend.add("购买次数");
        legend.add("购买用户数");
        List<String> xAxis = new ArrayList<>();
        Series<BigDecimal> series1 = new Series<>("铜币", WebConstants.LINE_CHART);
        Series<BigDecimal> series2 = new Series<>("代金券", WebConstants.LINE_CHART);
        Series<BigDecimal> series3 = new Series<>("购买次数", WebConstants.LINE_CHART);
        Series<BigDecimal> series4 = new Series<>("购买用户数", WebConstants.LINE_CHART);
        for(Map<String, Object> item:amtData){
            xAxis.add(item.get("timeCycle").toString());
            series1.add((BigDecimal)item.get("totalMoney"));
            series2.add((BigDecimal)item.get("totalCash"));
            series3.add((BigDecimal)item.get("totalPV"));
            series4.add((BigDecimal)item.get("totalUV"));
        }
        List<Series<BigDecimal>> series = new ArrayList<>();
        series.add(series1);
        series.add(series2);
        series.add(series3);
        series.add(series4);
        Echarts<BigDecimal> chart = EchartsTools.generateChart("消费额度图", legend, xAxis, "", series);
               
        Map<String, Object> data = new HashMap<>();
        data.put("chart", chart);
        data.put("summary", summary);
        
        ResultJson<Map<String, Object>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(data);
        return rj;
    }
    
    private List<Map<String, Object>> getAmtData(Map<String, Object> paramMap){
        List<Map<String, Object>> amtData = null;
        try {
            Integer timeCycle = (Integer)paramMap.get("timeCycle");
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            //按照日周月统计数据
            if(timeCycle == 1){
                amtData  = chapterConsumeMapper.getAmtDataByDay(version, startTime, endTime, cnids);
            } else if(timeCycle == 2){
                amtData = chapterConsumeMapper.getAmtDataByWeek(version, startTime, endTime, cnids);
            } else if(timeCycle == 3){
                amtData = chapterConsumeMapper.getAmtDataByMonth(version, startTime, endTime, cnids);
            } else{
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return amtData;
    }

    @Override
    public List<Map<String, Object>> exportTypeData(Map<String, Object> paramMap) {
        List<Map<String, Object>> consumeData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            consumeData = chapterConsumeMapper.getTypeDataByPage(version,
                    startTime, endTime, cnids, 0, 0);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return consumeData;
    }

    @Override
    public List<Map<String, Object>> exportChart(Map<String, Object> paramMap) {
        return getAmtData(paramMap);
    }
}
