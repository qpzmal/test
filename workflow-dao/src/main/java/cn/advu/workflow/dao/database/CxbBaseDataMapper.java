package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CxbBaseDataMapper extends ISqlMapper {

    /**
     * 获取创新版所有版本
     *
     * @return
     */
    public List<String> getVersions();

    public int getItemCnt(@Param("version") String version, @Param("startTime") String startTime,
                          @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    public List<Map<String, Object>> getDetailByPage(@Param("version") String version, @Param("startTime") String startTime,
                                                     @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                                     @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    public List<Map<String, Object>> getChannelUserDataByDay(@Param("version") String version, @Param("startTime") String startTime,
                                                             @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    public List<Map<String, Object>> getChannelUserDataByWeek(@Param("version") String version, @Param("startTime") String startTime,
                                                              @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    public List<Map<String, Object>> getChannelUserDataByMonth(@Param("version") String version, @Param("startTime") String startTime,
                                                               @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    public int getSummaryItemCnt(@Param("version") String version, @Param("startTime") String startTime,
                                 @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    public List<Map<String, Object>> getSummaryByPage(@Param("version") String version, @Param("startTime") String startTime,
                                                      @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                                      @Param("startRow") int startRow, @Param("pageSize") int pageSize);

}
