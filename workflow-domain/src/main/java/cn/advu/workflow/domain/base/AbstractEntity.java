package cn.advu.workflow.domain.base;


import java.util.Date;

/**
 * 抽象实体
 */
public class AbstractEntity implements IEntity {
    private static final String EMPTY = "";

    private Integer id;
    private Date createTime; // 建立时间
    private Date updateTime; // 更新时间
    private String creatorId; // 建立者ID
    private String updaterId; // 更新者ID
    private String delFlag; // 删除标记 ，0正常，1删除
    private String itemStatus; // 状态 ，0正常；1停用。由于前端选中为true，不选中为false，与DB设计相反，所以取反
    private String remark; // 备注

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getCreatorId() {
        return creatorId;
    }

    @Override
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? EMPTY : creatorId.trim();
    }

    @Override
    public String getUpdaterId() {
        return updaterId;
    }

    @Override
    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId == null ? EMPTY : updaterId.trim();
    }

    @Override
    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? EMPTY : delFlag.trim();
    }

    @Override
    public String getItemStatus() {
        return itemStatus;
    }

    @Override
    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus == null ? EMPTY : itemStatus;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark == null ? EMPTY : remark.trim();
    }
}
