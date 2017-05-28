package cn.advu.workflow.web.service.cxb;

import cn.advu.workflow.web.common.ResultJson;

import java.util.List;
import java.util.Map;

public interface CxbPointService {

    ResultJson<Map<String, Object>> getEarnAmtChart(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getWayData(Map<String, Object> paramMap);

    List<Map<String, Object>> exportWayData(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getSpeedData(Map<String, Object> paramMap);

    List<Map<String, Object>> exportSpeedData(Map<String, Object> paramMap);

    ResultJson<Map<String, Object>> getConsumeAmtChart(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getConsumeTypeData(Map<String, Object> paramMap);

    List<Map<String, Object>> exportConsumeTypeData(Map<String, Object> paramMap);

    ResultJson<Integer> getWayPageCnt(Map<String, Object> paramMap);

    ResultJson<Integer> getSpeedPageCnt(Map<String, Object> paramMap);

    ResultJson<Integer> getTypeDataCnt(Map<String, Object> paramMap);
}
