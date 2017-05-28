package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CxbIntegralMapper extends ISqlMapper {

    List<Map<String, Object>> getConsumeWayData(@Param("version") String version, @Param("startTime") String startTime,
                                                @Param("endTime") String endTime, @Param("cnids") String[] cnids);


    List<Map<String, Object>> getEarnWayData(@Param("version") String version, @Param("startTime") String startTime,
                                             @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                             @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    List<Map<String, Object>> getSpeedData(@Param("version") String version, @Param("startTime") String startTime,
                                           @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                           @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    List<Map<String, Object>> getConsumeTypeData(@Param("version") String version, @Param("startTime") String startTime,
                                                 @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                                 @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    int getWayPageCnt(@Param("version") String version, @Param("startTime") String startTime,
                      @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    int getSpeedPageCnt(@Param("version") String version, @Param("startTime") String startTime,
                        @Param("endTime") String endTime, @Param("cnids") String[] cnids);


    int getTypeDataCnt(@Param("version") String version, @Param("startTime") String startTime,
                       @Param("endTime") String endTime, @Param("cnids") String[] cnids);
}
