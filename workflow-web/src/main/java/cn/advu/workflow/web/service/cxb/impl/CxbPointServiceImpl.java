package cn.advu.workflow.web.service.cxb.impl;

import cn.advu.workflow.dao.database.CxbIntegralMapper;
import cn.advu.workflow.dao.database.CxbIntegralVnMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.common.util.echarts.EchartsTools;
import cn.advu.workflow.web.common.util.echarts.Series;
import cn.advu.workflow.web.service.cxb.CxbPointService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CxbPointServiceImpl implements CxbPointService {
    
    @Autowired
    private CxbIntegralVnMapper pointVnMapper;
    
    @Autowired
    private CxbIntegralMapper pointMapper;


    private static Logger LOGGER = Logger.getLogger(CxbPointServiceImpl.class);
    /**
     * 获取赚取积分额度数据[图和导出使用]
     * @param paramMap
     * @return
     */
    private List<Map<String, Object>> getEarnAmtData(Map<String, Object> paramMap){
        Integer timeCycle = (Integer) paramMap.get("timeCycle");
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        //timeCycle含义: 1-日, 2-周, 3-月
        List<Map<String, Object>> earnData = null;
        //按照日周月统计数据
        if(timeCycle == 1){
            earnData = pointVnMapper.getEarnDataByDay(version, startTime, endTime, cnids);       
        } else if(timeCycle == 2){
            earnData = pointVnMapper.getEarnDataByWeek(version, startTime, endTime, cnids);
        } else if(timeCycle == 3){
            earnData = pointVnMapper.getEarnDataByMonth(version, startTime, endTime, cnids);
        } else{
            return null;
        }
        return earnData;
    }
    
    @Override
    public ResultJson<Map<String, Object>> getEarnAmtChart(Map<String, Object> paramMap) {
        List<Map<String, Object>> earnData = getEarnAmtData(paramMap);
        if(earnData.size() == 0){
            return new ResultJson<>("此条件下暂无数据!");
        }
        String title = "赚取积分额度图";
        List<String> legend = new ArrayList<>();
        legend.add("赚取积分");
        legend.add("赚取用户数");
        List<String> xAxis = new ArrayList<>();
        Series<BigDecimal> pointSeries = new Series<>("赚取积分");
        Series<BigDecimal> userSeries = new Series<>("赚取用户数");
        for(Map<String, Object> item:earnData){
            xAxis.add(item.get("timeCycle").toString());
            pointSeries.add((BigDecimal)item.get("totalIntegral"));
            userSeries.add((BigDecimal)item.get("totalUser"));
        }
        List<Series<BigDecimal>> series = new ArrayList<>();
        series.add(pointSeries);
        series.add(userSeries);
        Echarts<BigDecimal> chart = EchartsTools.generateChart(title, legend , xAxis , "", series);
        ResultJson<Map<String, Object>> rj = new ResultJson<>();
        
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Map<String, Object> summary = pointVnMapper.getEarnSummary(version, startTime, endTime, cnids);
        
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        Map<String, Object> data = new HashMap<>();
        data.put("chart", chart);
        data.put("summary", summary);
        rj.setData(data);
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getWayData(Map<String, Object> paramMap) {
        List<Map<String, Object>> wayData = getRawWayData(paramMap);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(wayData);
        return rj;
    }

    private List<Map<String, Object>> getRawWayData(Map<String, Object> paramMap) {
        List<Map<String, Object>> wayData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            Integer pageNo = (Integer) paramMap.get("pageNo");
            int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
            wayData = pointMapper.getEarnWayData(version, startTime, endTime, cnids,startRow, WebConstants.COMMON_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return wayData;
    }

    @Override
    public List<Map<String, Object>> exportWayData(Map<String, Object> paramMap) {
        List<Map<String, Object>> wayData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            wayData = pointMapper.getEarnWayData(version, startTime, endTime, cnids,0, WebConstants.NO_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return wayData;
    }

    private List<Map<String, Object>> getRawSpeedData(Map<String, Object> paramMap) {
        List<Map<String, Object>> wayData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            Integer pageNo = (Integer) paramMap.get("pageNo");
            int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
            wayData = pointMapper.getSpeedData(version, startTime, endTime, cnids,startRow, WebConstants.COMMON_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return wayData;
    }
    
    @Override
    public ResultJson<List<Map<String, Object>>> getSpeedData(Map<String, Object> paramMap) {
        List<Map<String, Object>> speedData = getRawSpeedData(paramMap);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(speedData);
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportSpeedData(Map<String, Object> paramMap) {
        List<Map<String, Object>> wayData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            wayData = pointMapper.getSpeedData(version, startTime, endTime, cnids,0, WebConstants.NO_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return wayData;
    }

    @Override
    public ResultJson<Map<String, Object>> getConsumeAmtChart(Map<String, Object> paramMap) {
        
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Map<String, Object> summary = pointVnMapper.getConsumeSummary(version, startTime, endTime, cnids);
        if(summary == null){
            return new ResultJson<>("此条件下暂无数据!");
        }
        
        List<Map<String, Object>> consumeData = getConsumeAmtData(paramMap);
        List<String> legend = new ArrayList<>();
        legend.add("消耗积分");
        legend.add("消耗用户数");
        List<String> xAxis = new ArrayList<>();
        List<Series<BigDecimal>> series = new ArrayList<>();
        Series<BigDecimal> pointSum = new Series<>("消耗积分");
        Series<BigDecimal> userSum = new Series<>("消耗用户数");
        for(Map<String, Object> item:consumeData){
            xAxis.add(item.get("timeCycle").toString());
            pointSum.add((BigDecimal) item.get("pointSum"));
            userSum.add((BigDecimal) item.get("userSum"));
        }
        series.add(pointSum);
        series.add(userSum);
        Echarts<BigDecimal> chart = EchartsTools.generateChart("消耗积分额度图", legend, xAxis, "", series);
        Map<String, Object> data = new HashMap<>();
        data.put("chart", chart);
        data.put("summary", summary);
        ResultJson<Map<String, Object>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(data);
        return rj;
    }
    
    /**
     * 获取消耗积分额度数据[图和导出使用]
     * @param paramMap
     * @return
     */
    private List<Map<String, Object>> getConsumeAmtData(Map<String, Object> paramMap){
        Integer timeCycle = (Integer) paramMap.get("timeCycle");
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        //timeCycle含义: 1-日, 2-周, 3-月
        List<Map<String, Object>> consumeData = null;
        //按照日周月统计数据
        if(timeCycle == 1){
            consumeData = pointVnMapper.getConsumeDataByDay(version, startTime, endTime, cnids);       
        } else if(timeCycle == 2){
            consumeData = pointVnMapper.getConsumeDataByWeek(version, startTime, endTime, cnids);
        } else if(timeCycle == 3){
            consumeData = pointVnMapper.getConsumeDataByMonth(version, startTime, endTime, cnids);
        } else{
            return null;
        }
        return consumeData;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getConsumeTypeData(Map<String, Object> paramMap) {
        List<Map<String, Object>> consumeTypeData = getRawConsumeTypeData(paramMap);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(consumeTypeData);
        return rj;
    }

    private List<Map<String, Object>> getRawConsumeTypeData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer) paramMap.get("pageNo");
        int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
        List<Map<String, Object>> consumeTypeData = pointMapper.getConsumeTypeData(version, startTime, endTime, cnids,startRow,WebConstants.COMMON_PAGESIZE);
        return consumeTypeData;
    }

    @Override
    public List<Map<String, Object>> exportConsumeTypeData(Map<String, Object> paramMap) {
        List<Map<String, Object>> consumeTypeData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            consumeTypeData = pointMapper.getConsumeTypeData(version, startTime, endTime, cnids,0,WebConstants.NO_PAGESIZE);

        } catch (Exception e) {
            LOGGER.error(e);
            return consumeTypeData;
        }
        return consumeTypeData;
    }

    @Override
    public ResultJson<Integer> getWayPageCnt(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        int itemCnt = pointMapper.getWayPageCnt(version, startTime, endTime, cnids);
        int pageCnt = itemCnt%WebConstants.COMMON_PAGESIZE == 0?
                itemCnt/WebConstants.COMMON_PAGESIZE:itemCnt/WebConstants.COMMON_PAGESIZE+1;
        ResultJson<Integer> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageCnt);
        return rj;
    }

    @Override
    public ResultJson<Integer> getSpeedPageCnt(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        int itemCnt = pointMapper.getSpeedPageCnt(version, startTime, endTime, cnids);
        int pageCnt = itemCnt%WebConstants.COMMON_PAGESIZE == 0?
                itemCnt/WebConstants.COMMON_PAGESIZE:itemCnt/WebConstants.COMMON_PAGESIZE+1;
        ResultJson<Integer> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageCnt);
        return rj;
    }

    @Override
    public ResultJson<Integer> getTypeDataCnt(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        int itemCnt = pointMapper.getTypeDataCnt(version, startTime, endTime, cnids);
        int pageCnt = itemCnt%WebConstants.COMMON_PAGESIZE == 0?
                itemCnt/WebConstants.COMMON_PAGESIZE:itemCnt/WebConstants.COMMON_PAGESIZE+1;
        ResultJson<Integer> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageCnt);
        return rj;
    }
}
