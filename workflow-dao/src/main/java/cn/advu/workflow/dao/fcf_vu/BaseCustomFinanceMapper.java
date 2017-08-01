package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BaseCustomFinanceMapper extends BaseDAO<BaseCustomFinance> {

    List<BaseCustomFinance> queryByCustom(@Param("customId") Integer customId);

    BaseCustomFinance queryByCustomAndDate(
            @Param("id")Integer id,
            @Param("customId") Integer customId,
            @Param("startDate") Date starDate,
            @Param("endDate") Date endDate);

    BaseCustomFinance queryByIdAndName(@Param("id")Integer id, @Param("name")String name);
}
