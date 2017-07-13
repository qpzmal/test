package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseCustomMapper extends BaseDAO<BaseCustom>{

    // 以下为自定义SQL
    List<BaseCustom> queryAll(@Param("status") Integer status);

    List<BaseCustom> queryParentCustom();

    BaseCustom queryByIdAndName(@Param("id") Integer id, @Param("name") String name);

    List<BaseCustom> query4AChildCustom(@Param("id") Integer id);

    List<BaseCustom> queryAllChildCustom(@Param("id") Integer id);


}