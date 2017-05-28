package cn.advu.workflow.web.service.zwsc.impl;

import cn.advu.workflow.dao.database.ZwscBaseDataMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.util.echarts.Echarts;
import cn.advu.workflow.web.common.util.echarts.EchartsTools;
import cn.advu.workflow.web.common.util.echarts.Series;
import cn.advu.workflow.web.service.zwsc.ZChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ZChannelServiceImpl implements ZChannelService{

    private static Logger LOGGER = Logger.getLogger(ZChannelServiceImpl.class);

    @Autowired
    private ZwscBaseDataMapper baseMapper;
    
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
            detailData = baseMapper.getDetailByPage(version, startTime, endTime, cnids,0,0);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return detailData;
    }

    private List<Map<String, Object>> getRawUserData(Map<String, Object> paramMap){
        Integer timeCycle = (Integer) paramMap.get("timeCycle");
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        //timeCycle含义: 1-日, 2-周, 3-月
        List<Map<String, Object>> chargeData = null;
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
        return chargeData;
    }
    
    @Override
    public ResultJson<Echarts<BigDecimal>> getUserDataChart(Map<String, Object> paramMap) {
        List<Map<String, Object>> userData = getRawUserData(paramMap);
        List<String> legend = new ArrayList<>();
        legend.add("新增用户");
        legend.add("启动用户");
        legend.add("充值用户");
        legend.add("消费用户");
        List<String> xAxis = new ArrayList<>();
        List<Series<BigDecimal>> series = new ArrayList<>();
        Series<BigDecimal> s1 = new Series<>("新增用户");
        Series<BigDecimal> s2 = new Series<>("启动用户");
        Series<BigDecimal> s3 = new Series<>("充值用户");
        Series<BigDecimal> s4 = new Series<>("消费用户");
        series.add(s1);
        series.add(s2);
        series.add(s3);
        series.add(s4);
        for(Map<String, Object> item:userData){
            xAxis.add(item.get("timeCycle").toString());
            s1.add((BigDecimal) item.get("newUsers"));
            s2.add((BigDecimal) item.get("qdUsers"));
            s3.add((BigDecimal) item.get("chargeUsers"));
            s4.add((BigDecimal) item.get("consumeUsers"));
        }
        Echarts<BigDecimal> chart = EchartsTools.generateChart("渠道用户数据", legend, xAxis, "", series);
        ResultJson<Echarts<BigDecimal>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(chart);
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportUserData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        List<Map<String, Object>> detailData = baseMapper.getChannelUserDataByDay(version, startTime, endTime, cnids);
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
    public List<Map<String, Object>> exportSummaryPageData(Map<String, Object> paramMap) {
        List<Map<String, Object>> summaryData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            summaryData = baseMapper.getSummaryByPage(version, startTime, endTime, cnids,0,0);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return summaryData;
    }

    /**
     * 渠道详细分页总条数
     * @param mapData
     * @return
     */
    @Override
    public ResultJson<Integer> getChannelDetailPages(Map<String, Object> mapData) {
        String version = (String) mapData.get("version");
        String startTime = (String) mapData.get("startTime");
        String endTime = (String) mapData.get("endTime");
        String[] cnids = (String[]) mapData.get("cnids");
        int itemCnt = baseMapper.getChannelDetailPages(version, startTime, endTime, cnids);
        int pageCnt = itemCnt%WebConstants.COMMON_PAGESIZE == 0?
                itemCnt/WebConstants.COMMON_PAGESIZE:itemCnt/WebConstants.COMMON_PAGESIZE+1;
        ResultJson<Integer> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageCnt);
        return rj;
    }

    /**
     * 渠道详细数据
     * @param paramMap
     * @return
     */
    @Override
    public ResultJson<List<Map<String, Object>>> getChannelDetailPageData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer)paramMap.get("pageNo");
        int startRow = (pageNo - 1) * WebConstants.COMMON_PAGESIZE;
        List<Map<String, Object>> pageData =
                baseMapper.getChannelDetailPageData(version, startTime, endTime, cnids, startRow, WebConstants.COMMON_PAGESIZE);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageData);
        return rj;
    }

    /**
     * 渠道详细导出
     * @param paramMap
     * @return
     */
    @Override
    public List<Map<String, Object>> exportChannelDetail(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        List<Map<String, Object>> detailData = baseMapper.getChannelDetailPageData(version, startTime, endTime, cnids,0,0);
        return detailData;
    }
}
