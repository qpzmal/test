package cn.advu.workflow.web.service.zwsc;

import cn.advu.workflow.web.common.ResultJson;

import java.util.List;
import java.util.Map;

public interface ZConsumeDSService {

    ResultJson<Map<String, Object>> getAmtData(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getTypeData(Map<String, Object> paramMap);

    ResultJson<Object> exportTypeData(Map<String, Object> paramMap);

}
