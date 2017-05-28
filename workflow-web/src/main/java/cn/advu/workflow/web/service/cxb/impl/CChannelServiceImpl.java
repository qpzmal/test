package cn.advu.workflow.web.service.cxb.impl;

import cn.advu.workflow.dao.database.CxbBaseDataMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.common.util.echarts.EchartsTools;
import cn.advu.workflow.web.common.util.echarts.Series;
import cn.advu.workflow.web.service.cxb.CChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CChannelServiceImpl implements CChannelService{
    
    @Autowired
    private CxbBaseDataMapper baseMapper;

    private static Logger LOGGER = Logger.getLogger(CChannelServiceImpl.class);
    
    @Override
    public ResultJson<Integer> getDetailTotalPages(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        int itemCnt = baseMapper.getItemCnt(version, startTime, endTime, cnids);
        int pageCnt = itemCnt%WebConstants.COMMON_PAGESIZE == 0?
                itemCnt/WebConstants.COMMON_PAGESIZE:itemCnt/WebConstants.COMMON_PAGESIZE+1;
        ResultJson<Integer> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageCnt);
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getDetailPageData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer)paramMap.get("pageNo");
        int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
        List<Map<String, Object>> pageData =
                baseMapper.getDetailByPage(version, startTime, endTime, cnids, startRow, WebConstants.COMMON_PAGESIZE);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageData);
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportDetail(Map<String, Object> paramMap) {
        List<Map<String, Object>> detailData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            detailData = baseMapper.getDetailByPage(version, startTime, endTime, cnids,0,WebConstants.NO_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
            return detailData;
        }
        return detailData;
    }

    private List<Map<String, Object>> getRawUserData(Map<String, Object> paramMap){
        List<Map<String, Object>> chargeData = null;
        try {
            Integer timeCycle = (Integer) paramMap.get("timeCycle");
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            //timeCycle含义: 1-日, 2-周, 3-月
            //按照日周月统计数据
            if(timeCycle == 1){
                chargeData = baseMapper.getChannelUserDataByDay(version, startTime, endTime, cnids);
            } else if(timeCycle == 2){
                chargeData = baseMapper.getChannelUserDataByWeek(version, startTime, endTime, cnids);
            } else if(timeCycle == 3){
                chargeData = baseMapper.getChannelUserDataByMonth(version, startTime, endTime, cnids);
            } else{
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return chargeData;
    }
    
    @Override
    public ResultJson<Echarts<BigDecimal>> getUserDataChart(Map<String, Object> paramMap) {
        List<Map<String, Object>> userData = getRawUserData(paramMap);
        List<String> legend = new ArrayList<>();
        legend.add("新增用户");
        legend.add("启动用户");
        legend.add("赚取积分额度");
        legend.add("消费积分额度");
        List<String> xAxis = new ArrayList<>();
        List<Series<BigDecimal>> series = new ArrayList<>();
        Series<BigDecimal> s1 = new Series<>("新增用户");
        Series<BigDecimal> s2 = new Series<>("启动用户");
        Series<BigDecimal> s3 = new Series<>("赚取积分额度");
        Series<BigDecimal> s4 = new Series<>("消费积分额度");
        series.add(s1);
        series.add(s2);
        series.add(s3);
        series.add(s4);
        for(Map<String, Object> item:userData){
            xAxis.add(item.get("timeCycle").toString());
            s1.add((BigDecimal) item.get("newUsers"));
            s2.add((BigDecimal) item.get("qdUsers"));
            s3.add((BigDecimal) item.get("earnIntegral"));
            s4.add((BigDecimal) item.get("consumeIntegral"));
        }
        Echarts<BigDecimal> chart = EchartsTools.generateChart("用户及积分", legend, xAxis, "", series);
        ResultJson<Echarts<BigDecimal>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(chart);
        return rj;
    }
    
    @Override
    public List<Map<String, Object>> exportUserData(Map<String, Object> paramMap) {
        List<Map<String, Object>> detailData = null;
        try {
            detailData = getRawUserData(paramMap);
        } catch (Exception e) {
            LOGGER.error(e);
            return detailData;
        }
        return detailData;
    }

    @Override
    public ResultJson<Integer> getSummaryPageCnt(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        int itemCnt = baseMapper.getSummaryItemCnt(version, startTime, endTime, cnids);
        int pageCnt = itemCnt%WebConstants.COMMON_PAGESIZE == 0?
                itemCnt/WebConstants.COMMON_PAGESIZE:itemCnt/WebConstants.COMMON_PAGESIZE+1;
        ResultJson<Integer> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageCnt);
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getSummaryPageData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer) paramMap.get("pageNo");
        int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
        List<Map<String, Object>> summaryData =
                baseMapper.getSummaryByPage(version, startTime, endTime, cnids, startRow, WebConstants.COMMON_PAGESIZE);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(summaryData);
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportSummary(Map<String, Object> paramMap) {
        List<Map<String, Object>> detailData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            detailData = baseMapper.getSummaryByPage(version, startTime, endTime, cnids,0,WebConstants.NO_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
            return detailData;
        }
        return detailData;
    }
}
