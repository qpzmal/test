package cn.advu.workflow.web.service.zwsc;

import cn.advu.workflow.web.common.ResultJson;

import java.util.List;
import java.util.Map;

public interface ZConsumeBookService {

    ResultJson<List<Map<String, Object>>> getTypeDataByPage(Map<String, Object> paramMap, Integer pageNo);

    ResultJson<Integer> getTypeTotalPages(Map<String, Object> paramMap);

    ResultJson<Map<String, Object>> getAmtChartData(Map<String, Object> paramMap);

    List<Map<String, Object>> exportTypeData(Map<String, Object> paramMap);

    List<Map<String,Object>> exportChart(Map<String, Object> paramMap);
}
