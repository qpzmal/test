package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

import java.math.BigDecimal;

public class BaseArea extends AbstractEntity {

    private String name;

    private Integer parentId;

    private String code;

    private BigDecimal roomPay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public BigDecimal getRoomPay() {
        return roomPay;
    }

    public void setRoomPay(BigDecimal roomPay) {
        this.roomPay = roomPay;
    }

}