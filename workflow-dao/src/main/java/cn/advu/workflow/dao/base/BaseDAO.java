package cn.advu.workflow.dao.base;

import cn.advu.workflow.domain.base.IEntity;

/**
 * mapper接口
 *
 * @author hekai
 */
public interface BaseDAO<T extends IEntity> extends ISqlMapper {

    int insert(T record);

    int insertSelective(T record);

    int deleteByPrimaryKey(Integer id);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}


