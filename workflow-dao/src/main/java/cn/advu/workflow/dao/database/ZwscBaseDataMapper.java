package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.ZwscBaseData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZwscBaseDataMapper extends ISqlMapper {

    /**
     * 获取中文书城所有版本
     *
     * @return
     */
    List<String> getVersions();

    int getItemCnt(@Param("version") String version, @Param("startTime") String startTime,
                   @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getDetailByPage(@Param("version") String version, @Param("startTime") String startTime,
                                              @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                              @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    List<Map<String, Object>> getChannelUserDataByMonth(@Param("version") String version, @Param("startTime") String startTime,
                                                        @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getChannelUserDataByWeek(@Param("version") String version, @Param("startTime") String startTime,
                                                       @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getChannelUserDataByDay(@Param("version") String version, @Param("startTime") String startTime,
                                                      @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    int getSummaryItemCnt(@Param("version") String version, @Param("startTime") String startTime,
                          @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getSummaryByPage(@Param("version") String version, @Param("startTime") String startTime,
                                               @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                               @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    ZwscBaseData getZwscBaseDataByChannelAndDate(@Param("date") String date, @Param("date1") String date1, @Param("cnid") String cnid);


    List<Map<String, Object>> getChannelDetailPageData(@Param("version") String version, @Param("startTime") String startTime,
                                                       @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                                       @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    int getChannelDetailPages(@Param("version") String version, @Param("startTime") String startTime,
                              @Param("endTime") String endTime, @Param("cnids") String[] cnids);
}
