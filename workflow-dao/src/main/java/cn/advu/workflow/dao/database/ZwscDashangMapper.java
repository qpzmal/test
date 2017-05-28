package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZwscDashangMapper extends ISqlMapper {

    List<Map<String, Object>> getAmtDataByDay(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> getAmtDataByWeek(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> getAmtDataByMonth(@Param("startTime") String startTime, @Param("endTime") String endTime);

    Map<String, Object> getSummaryData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> getTypeData(@Param("startTime") String startTime, @Param("endTime") String endTime);

}
