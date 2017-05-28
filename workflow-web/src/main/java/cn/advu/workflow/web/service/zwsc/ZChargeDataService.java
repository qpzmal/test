package cn.advu.workflow.web.service.zwsc;

import cn.advu.workflow.web.common.ResultJson;

import java.util.List;
import java.util.Map;

public interface ZChargeDataService {

    List<Map<String, Object>> exportTrendChart(Map<String, Object> paramMap);

    ResultJson<Map<String, Object>> getTrendEcharts(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getWayPageData(Map<String, Object> paramMap);

    ResultJson<Integer> getWayTotalPages(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getGivePageData(Map<String, Object> paramMap);

    ResultJson<Integer> getGiveTotalPages(Map<String, Object> paramMap);

    Integer getWaySumMoney(Map<String, Object> paramMap);

    List<Map<String, Object>> exportWayData(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getWayDetailData(Map<String, Object> paramMap);

    ResultJson<Object> exportDetailData(Map<String, Object> paramMap);

}
