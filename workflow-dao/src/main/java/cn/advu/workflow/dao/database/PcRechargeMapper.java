package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.PcRecharge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BBD on 2017/5/16.
 */
public interface PcRechargeMapper extends ISqlMapper {


    List<PcRecharge> getPcRechargeByTimeAndCnidsAndType(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") Integer type, @Param("cnids") String[] cnids);

    Integer getPcRechargeTotalByTimeAndCnidsAndType(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") Integer type, @Param("cnids") String[] cnids);
}
