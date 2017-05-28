package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZwscChargeRangeMapper extends ISqlMapper {

    List<Map<String, Object>> getWayDetail(@Param("version") String version, @Param("startTime") String startTime,
                                           @Param("endTime") String endTime, @Param("cnids") String[] cnids,
                                           @Param("wayCode") Integer wayCode);

}
