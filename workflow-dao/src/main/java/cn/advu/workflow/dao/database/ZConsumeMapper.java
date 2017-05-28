package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/22.
 */
public interface ZConsumeMapper extends ISqlMapper {

    Map<String, Object> getSummary(@Param("version") String version, @Param("startTime") String startTime, @Param("endTime")
            String endTime, @Param("cnids") String... cnids);

    List<Map<String, Object>> getDataByDay(@Param("version") String version, @Param("startTime") String startTime, @Param("endTime")
            String endTime, @Param("cnids") String... cnids);

    List<Map<String, Object>> getDataByWeek(@Param("version") String version, @Param("startTime") String startTime, @Param("endTime")
            String endTime, @Param("cnids") String... cnids);

    List<Map<String, Object>> getDataByMonth(@Param("version") String version, @Param("startTime") String startTime, @Param("endTime")
            String endTime, @Param("cnids") String... cnids);

    List<Map<String, Object>> getWayDataByPage(@Param("version") String version, @Param("startTime") String startTime,
                                               @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                               @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    int getWayTotal(@Param("version") String version, @Param("startTime") String startTime,
                    @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getAllWayData(@Param("version") String version, @Param("startTime") String startTime,
                                            @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getGivePageData(@Param("version") String version, @Param("startTime") String startTime,
                                              @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                              @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    int getGivTotalPages(@Param("version") String version, @Param("startTime") String startTime,
                         @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getAllGiveData(@Param("version") String version, @Param("startTime") String startTime,
                                             @Param("endTime") String endTime, @Param("cnids") String[] cnids);
}
