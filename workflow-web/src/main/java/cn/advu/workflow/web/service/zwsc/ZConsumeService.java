package cn.advu.workflow.web.service.zwsc;

import cn.advu.workflow.web.common.ResultJson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglei on 2017/2/22.
 */
public interface ZConsumeService {
    ResultJson<Map<String, Object>> getTrendEcharts(Map<String, Object> paramMap);

    ResultJson<List<Map<String, Object>>> getWayPageData(Map<String, Object> paramMap);

    ResultJson<Integer> getWayTotalPages(Map<String, Object> paramMap);

    List<Map<String, Object>> exportTypeData(Map<String, Object> paramMap, HttpServletResponse response) throws IOException;

    ResultJson<List<Map<String,Object>>> getGivePageData(Map<String, Object> paramMap);

    ResultJson<Integer> getGivTotalPages(Map<String, Object> paramMap);

    List<Map<String, Object>> exportwGive(Map<String, Object> paramMap) ;

    List<Map<String, Object>> exportTrend(Map<String, Object> paramMap);
}
