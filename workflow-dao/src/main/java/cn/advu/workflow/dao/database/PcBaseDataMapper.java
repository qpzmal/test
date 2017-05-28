package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.PcBaseData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PcBaseDataMapper extends ISqlMapper {


    PcBaseData getZwscBaseDataByChannelAndDate(@Param("date") String date, @Param("date1") String date1, @Param("cnid") String cnid);


    List<PcBaseData> getPcBaseDataList(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    Integer getPcBaseDataCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    List<PcBaseData> getPcChannelMonthDatas(@Param("start") Integer startIndex, @Param("pageSize") Integer pageSize, @Param("startTime") String startTime, @Param("cnids") String[] cnids);

    Integer getPcChannelMonthDatasCount(@Param("startTime") String startTime, @Param("cnids") String[] cnids);

    List<PcBaseData> getPcChannelTotalDatas(@Param("start") Integer startIndex, @Param("pageSize") Integer pageSize, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    Integer getPcChannelTotalDatasCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("cnids") String[] cnids);
}
