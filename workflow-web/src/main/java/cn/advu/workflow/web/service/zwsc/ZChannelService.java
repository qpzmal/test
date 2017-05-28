package cn.advu.workflow.web.service.zwsc;

import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.util.echarts.Echarts;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ZChannelService {

    ResultJson<Integer> getDetailTotalPages(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getDetailPageData(Map<String, Object> paramMap);

    List<Map<String, Object>> exportDetail(Map<String, Object> paramMap);

    ResultJson<Echarts<BigDecimal>> getUserDataChart(Map<String, Object> paramMap);

    List<Map<String, Object>> exportUserData(Map<String, Object> paramMap);

    ResultJson<Integer> getSummaryPageCnt(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getSummaryPageData(Map<String, Object> paramMap);

    List<Map<String,Object>> exportSummaryPageData(Map<String, Object> paramMap);

    ResultJson<List<Map<String,Object>>> getChannelDetailPageData(Map<String, Object> paramMap);

    ResultJson<Integer> getChannelDetailPages(Map<String, Object> mapData);

    List<Map<String,Object>> exportChannelDetail(Map<String, Object> paramMap);
}
