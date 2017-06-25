package cn.advu.workflow.domain.base;


import java.util.Date;

/**
 * 实体
 *
 */
public interface IEntity extends IDomain {
    /**
     * 获取ID
     * @return
     */
    public Integer getId();
    /**
     * 设置ID
     * @param id
     */
    public void setId(Integer id);
    /**
    /**
     * 获取创建时间
     * @return
     */
    public Date getCreateTime();

    /**
     * 获取修改时间
     * @return
     */
    public Date getUpdateTime();

}
