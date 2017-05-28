package cn.advu.workflow.web.service.zwsc;

import cn.advu.workflow.web.common.ResultJson;

import java.util.List;
import java.util.Map;

public interface ZVipService {

    ResultJson<Map<String, Object>> getAmtChart(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getOpenData(Map<String, Object> paramMap);

    List<Map<String, Object>> exportOpenData(Map<String, Object> paramMap);

    ResultJson<Integer> getOpenTotalPages(Map<String, Object> paramMap);

    ResultJson<Integer> getDonateTotalPages(Map<String, Object> paramMap);

    ResultJson<List<Map<String,Object>>> getDonateData(Map<String, Object> paramMap);

    List<Map<String, Object>> exportChart(Map<String, Object> paramMap);

    List<Map<String,Object>> exportDonate(Map<String, Object> paramMap);
}
