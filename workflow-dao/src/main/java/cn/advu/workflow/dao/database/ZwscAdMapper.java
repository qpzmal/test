package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29.
 */
public interface ZwscAdMapper extends ISqlMapper {

    List<Map<String, Object>> getSdkDataByPage(@Param("adType") String adType,
                                               @Param("version") String version, @Param("startTime") String startTime,
                                               @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                               @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    List<Map<String, Object>> getAdType();

    Integer getSdkPageCnt(@Param("adType") String adType, @Param("version") String versions,
                          @Param("startTime") String startTime,
                          @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getEarnName();

    Integer getItemCnt(@Param("earnName") String earnName,
                       @Param("version") String version, @Param("startTime") String startTime,
                       @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getInstallDataByPage(@Param("earnName") String earnName,
                                                   @Param("version") String version, @Param("startTime") String startTime,
                                                   @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                                   @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    Integer getCollectDataPageCnt(@Param("version") String version, @Param("startTime") String startTime,
                                  @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getCollectDataByPage(@Param("version") String version, @Param("startTime") String startTime,
                                                   @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                                   @Param("startRow") int startRow, @Param("pageSize") int pageSize);
}
