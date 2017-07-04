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
    Integer getId();
    /**
     * 设置ID
     * @param id
     */
    void setId(Integer id);
    /**
    /**
     * 获取创建时间
     * @return
     */
    Date getCreateTime();
    void setCreateTime(Date date);

    /**
     * 获取修改时间
     * @return
     */
    Date getUpdateTime();
    void setUpdateTime(Date date);


    String getCreatorId();
    void setCreatorId(String creatorId);

    String getUpdaterId();
    void setUpdaterId(String updaterId);

    Boolean getDelFlag();
    void setDelFlag(Boolean delFlag);

    Boolean getItemStatus();
    void setItemStatus(Boolean itemStatus);

    String getRemark();
    void setRemark(String remark);
}
