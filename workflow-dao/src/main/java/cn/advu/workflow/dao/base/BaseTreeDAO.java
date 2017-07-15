package cn.advu.workflow.dao.base;

import cn.advu.workflow.domain.base.IEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mapper接口
 *
 * @author wangry
 */
public interface BaseTreeDAO<T extends IEntity> extends BaseDAO {

    void updateChildLevel(@Param("level") Integer level, @Param("parentId") Integer parentId);

    List<T> queryChildList(@Param("parentId") Integer parentId);
}


