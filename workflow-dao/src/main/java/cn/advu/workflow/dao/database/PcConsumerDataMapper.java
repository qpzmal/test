package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.PcConsumer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BBD on 2017/5/16.
 */
public interface PcConsumerDataMapper extends ISqlMapper {


    List<PcConsumer> getPcConsumerDataByTimeAndCnids(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("cnids") String[] cnids);

    Integer getPcConsumerDataTotalByTimeAndCnids(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("cnids") String[] cnids);
}
