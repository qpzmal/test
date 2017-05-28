package cn.advu.workflow.web.service.cxb;

import cn.advu.workflow.web.common.ResultJson;

import java.util.List;
import java.util.Map;

public interface CFreeDataService {

    ResultJson<List<Map<String, Object>>> getSdkData(Map<String, Object> paramMap);

    ResultJson<Integer> getSdkPageCnt(Map<String, Object> paramMap);

    List<Map<String, Object>> exportSdkData(Map<String, Object> paramMap);

    ResultJson<Integer> getInstallDataPageCnt(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getInstallDataByPage(Map<String, Object> paramMap);

    List<Map<String, Object>> exportInstallData(Map<String, Object> paramMap);

    ResultJson<List<Map<String,Object>>> getAdType();

    ResultJson<List<Map<String,Object>>> getEarnName();

    ResultJson<Integer> getCollectDataPageCnt(Map<String, Object> paramMap);

    ResultJson<List<Map<String,Object>>> getCollectDataByPage(Map<String, Object> paramMap);

    List<Map<String,Object>> exportCollectData(Map<String, Object> paramMap);
}
