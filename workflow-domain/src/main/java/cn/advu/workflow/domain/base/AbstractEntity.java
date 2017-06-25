package cn.advu.workflow.domain.base;


import java.util.Date;

/**
 * 抽象实体
 */
public class AbstractEntity implements IEntity {

    private Integer id;
    private Date createTime;
    private Date updateTime;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Date getCreateTime() {
        return this.createTime;
    }

    @Override
    public Date getUpdateTime() {
        return this.updateTime;
    }
}
