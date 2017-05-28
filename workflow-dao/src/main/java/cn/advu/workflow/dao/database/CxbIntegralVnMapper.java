package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CxbIntegralVnMapper extends ISqlMapper {

    List<Map<String, Object>> getEarnDataByDay(@Param("version") String version, @Param("startTime") String startTime,
                                               @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getEarnDataByWeek(@Param("version") String version, @Param("startTime") String startTime,
                                                @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getEarnDataByMonth(@Param("version") String version, @Param("startTime") String startTime,
                                                 @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    Map<String, Object> getEarnSummary(@Param("version") String version, @Param("startTime") String startTime,
                                       @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getConsumeDataByDay(@Param("version") String version, @Param("startTime") String startTime,
                                                  @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getConsumeDataByWeek(@Param("version") String version, @Param("startTime") String startTime,
                                                   @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getConsumeDataByMonth(@Param("version") String version, @Param("startTime") String startTime,
                                                    @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    Map<String, Object> getConsumeSummary(@Param("version") String version, @Param("startTime") String startTime,
                                          @Param("endTime") String endTime, @Param("cnids") String[] cnids);
}
