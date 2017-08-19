package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.datareport.BaseExecuteOrderReportVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseExecuteOrderMapper extends BaseDAO<BaseExecuteOrder> {

    // 以下为自定义SQL
    List<BaseExecuteOrder> queryAll(BaseExecuteOrder baseExecuteOrder);
    List<BaseExecuteOrder> queryAllForContract(BaseExecuteOrder baseExecuteOrder);
    List<BaseExecuteOrder> findAllUnFinished();
    List<Map> queryFinalReport(@Param("likeSearch") String likeSearch, @Param("mediaIdList") List<String> mediaIdList);
}