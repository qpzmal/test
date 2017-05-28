package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZwscChapterConsumeMapper extends ISqlMapper {

    List<Map<String, Object>> getTypeDataByPage(@Param("version") String version, @Param("startTime") String startTime,
                                                @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                                @Param("startRow") int startRow, @Param("pageSize") int pagesize);

    int getTypePages(@Param("version") String version, @Param("startTime") String startTime,
                     @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getAmtDataByDay(@Param("version") String version, @Param("startTime") String startTime,
                                              @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getAmtDataByWeek(@Param("version") String version, @Param("startTime") String startTime,
                                               @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getAmtDataByMonth(@Param("version") String version, @Param("startTime") String startTime,
                                                @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    Map<String, Object> getSummaryData(@Param("version") String version, @Param("startTime") String startTime,
                                       @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getTypeData(@Param("version") String version, @Param("startTime") String startTime,
                                          @Param("endTime") String endTime, @Param("cnids") String[] cnids);

}
