package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CxbAzAdMapper extends ISqlMapper {

    Integer getItemCnt(@Param("earnName") String earnName,
                       @Param("version") String version, @Param("startTime") String startTime,
                       @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getInstallDataByPage(@Param("earnName") String earnName,
                                                   @Param("version") String version, @Param("startTime") String startTime,
                                                   @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                                   @Param("startRow") int startRow, @Param("pageSize") int pageSize);

    List<Map<String, Object>> getInstallData(@Param("version") String version, @Param("startTime") String startTime,
                                             @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<Map<String, Object>> getEarnName();
}
