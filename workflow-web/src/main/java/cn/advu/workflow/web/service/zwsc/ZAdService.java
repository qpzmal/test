package cn.advu.workflow.web.service.zwsc;

import cn.advu.workflow.web.common.ResultJson;

import java.util.List;
import java.util.Map;

/**
 * 中文书城广告接口
 * Created by zhanglei on 2017/3/29.
 */
public interface ZAdService {

    ResultJson<List<Map<String,Object>>> getSdkData(Map<String, Object> paramMap);

    ResultJson<List<Map<String,Object>>> getAdType();

    ResultJson<Integer> getSdkPageCnt(Map<String, Object> paramMap);

    List<Map<String,Object>> exportSdkData(Map<String, Object> paramMap);

    ResultJson<List<Map<String,Object>>> getEarnName();

    ResultJson<Integer> getInstallDataPageCnt(Map<String, Object> paramMap);

    ResultJson<List<Map<String,Object>>> getInstallDataByPage(Map<String, Object> paramMap);

    List<Map<String,Object>> exportInstallData(Map<String, Object> paramMap);

    ResultJson<Integer> getCollectDataPageCnt(Map<String, Object> paramMap);

    ResultJson<List<Map<String,Object>>> getCollectDataByPage(Map<String, Object> paramMap);

    List<Map<String,Object>> exportCollectData(Map<String, Object> paramMap);
}
