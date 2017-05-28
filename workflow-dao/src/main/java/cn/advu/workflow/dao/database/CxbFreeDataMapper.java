package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CxbFreeDataMapper extends ISqlMapper {

    List<Map<String, Object>> getSdkDataByPage(@Param("adType") String adType,
                                               @Param("version") String version, @Param("startTime") String startTime,
                                               @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                               @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    Integer getSdkPageCnt(@Param("adType") String adType, @Param("version") String versions,
                          @Param("startTime") String startTime,
                          @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getSdkData(@Param("version") String version, @Param("startTime") String startTime,
                                         @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getAdType();

    Integer getCollectDataPageCnt(@Param("version") String version, @Param("startTime") String startTime,
                                  @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getCollectDataByPage(@Param("version") String version, @Param("startTime") String startTime,
                                                   @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                                   @Param("startRow") int startRow, @Param("pageSize") int pageSize);


}
