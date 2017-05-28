package cn.advu.workflow.web.service.zwsc.impl;

import cn.advu.workflow.dao.database.ZConsumeMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.common.util.echarts.EchartsTools;
import cn.advu.workflow.web.common.util.echarts.Series;
import cn.advu.workflow.web.service.zwsc.ZConsumeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglei on 2017/2/22.
 */
@Service
public class ZConsumeServiceImpl implements ZConsumeService {

    @Autowired
    private ZConsumeMapper zconsumeMapper;

    private static Logger LOGGER = Logger.getLogger(ZConsumeServiceImpl.class);

    @Override
    public ResultJson<Map<String, Object>> getTrendEcharts(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        //获取头部的总览数据
        Map<String, Object> summary = zconsumeMapper.getSummary(version, startTime, endTime, cnids);

        if(summary == null){
            return new ResultJson<>("在此条件下暂无数据!");
        }

        List<Map<String, Object>> chargeData = getChargeTrendData(paramMap);

        if(chargeData == null){
            return new ResultJson<>();
        }
        //X轴数据
        List<String> xAxis = new ArrayList<>();
        List<BigDecimal> currencySum = new ArrayList<>();
        List<BigDecimal> voucherSum = new ArrayList<>();
        List<BigDecimal> manSum = new ArrayList<>();
        for(Map<String, Object> item:chargeData){
            xAxis.add(item.get("timeCycle").toString());
            currencySum.add((BigDecimal)item.get("currencySum"));
            voucherSum.add((BigDecimal)item.get("voucherSum"));
            manSum.add((BigDecimal)item.get("manSum"));
        }
        //图数据
        Series<BigDecimal> series1 = new Series<>("消费金额(元)", WebConstants.LINE_CHART, currencySum);
        Series<BigDecimal> series2 = new Series<>("铜币代金券(元)", WebConstants.LINE_CHART, voucherSum);
        Series<BigDecimal> series3 = new Series<>("充值用户数(人)", WebConstants.LINE_CHART, manSum);

        //图例
        List<String> legend = new ArrayList<>();
        legend.add("消费金额(元)");
        legend.add("铜币代金券(元)");
        legend.add("充值用户数(人)");

        List<Series<BigDecimal>> series = new ArrayList<>();
        series.add(series1);
        series.add(series2);
        series.add(series3);

        Echarts<BigDecimal> chart = EchartsTools.generateChart("消费趋势图", legend, xAxis, "", series);

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
                chargeData = zconsumeMapper.getDataByDay(version, startTime, endTime, cnids);
            } else if(timeCycle == 2){
                chargeData = zconsumeMapper.getDataByWeek(version, startTime, endTime, cnids);
            } else if(timeCycle == 3){
                chargeData = zconsumeMapper.getDataByMonth(version, startTime, endTime, cnids);
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
        List<Map<String, Object>> wayData = zconsumeMapper.getWayDataByPage(version, startTime, endTime, cnids, startRow, WebConstants.COMMON_PAGESIZE);

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
        int count = zconsumeMapper.getWayTotal(version, startTime, endTime, cnids);
        ResultJson<Integer> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        int totalPages = count % WebConstants.COMMON_PAGESIZE == 0 ?
                count/WebConstants.COMMON_PAGESIZE : count/WebConstants.COMMON_PAGESIZE + 1;
        rj.setData(totalPages);
        return rj;
    }


    @Override
    public List<Map<String, Object>> exportTypeData(Map<String, Object> paramMap, HttpServletResponse response) throws IOException {

        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        List<Map<String, Object>> wayData = zconsumeMapper.getAllWayData(version, startTime, endTime, cnids);
        return wayData;
    }


    @Override
    public ResultJson<List<Map<String, Object>>> getGivePageData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer) paramMap.get("pageNo");
        int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
        List<Map<String, Object>> wayData = zconsumeMapper.getGivePageData(version, startTime, endTime, cnids, startRow, WebConstants.COMMON_PAGESIZE);

        if(wayData == null){
            return new ResultJson<List<Map<String, Object>>>("暂无数据!");
        }

        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(wayData);

        return rj;
    }

    @Override
    public ResultJson<Integer> getGivTotalPages(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        int count = zconsumeMapper.getGivTotalPages(version, startTime, endTime, cnids);
        ResultJson<Integer> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        int totalPages = count % WebConstants.COMMON_PAGESIZE == 0 ?
                count/WebConstants.COMMON_PAGESIZE : count/WebConstants.COMMON_PAGESIZE + 1;
        rj.setData(totalPages);
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportwGive(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        List<Map<String, Object>> wayData = zconsumeMapper.getAllGiveData(version, startTime, endTime, cnids);
        return wayData;
    }

    @Override
    public List<Map<String, Object>> exportTrend(Map<String, Object> paramMap) {
        return getChargeTrendData(paramMap);
    }
}
