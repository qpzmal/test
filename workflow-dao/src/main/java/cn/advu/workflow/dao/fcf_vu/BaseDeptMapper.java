package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.base.BaseTreeDAO;
import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.domain.fcf_vu.DeptVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDeptMapper extends BaseTreeDAO<BaseDept> {
    // 以下为自定义SQL
    List<BaseDept> queryAll(@Param("status") Integer status);
    // 以下为自定义SQL
    List<BaseDept> queryByArea(@Param("areaId") Integer areaId);
    // 以下为自定义SQL
    List<BaseDept> queryChildDept(@Param("areaId") Integer areaId, @Param("parentId") Integer parentId);

    List<DeptVO> queryByAreaAndDept(@Param("areaId") Integer areaId, @Param("parentId") Integer parentId);

}