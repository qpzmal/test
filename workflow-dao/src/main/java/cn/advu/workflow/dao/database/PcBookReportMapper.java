package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.PcBookReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BBD on 2017/5/16.
 */
public interface PcBookReportMapper extends ISqlMapper {


    List<PcBookReport> getPcBookDataByTimeAndBookIdBookName(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("bookId") String bookId, @Param("bookName") String bookName);

    Integer getPcBookDataTotalByTimeAndBookIdBookName(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("bookId") String bookId, @Param("bookName") String bookName);
}
