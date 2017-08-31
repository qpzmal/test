package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

import java.math.BigDecimal;

public class BasePerson extends AbstractEntity {

    private String uid;

    private String name;

    private Integer areaId;

    private String mobile;

    private String address;

    private String email;

    private Integer deptId;

    private Integer state;

    private Integer parentId;

    private BigDecimal pay2;

    private BigDecimal personPay;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getPay2() {
        return pay2;
    }

    public void setPay2(BigDecimal pay2) {
        this.pay2 = pay2;
    }

    public BigDecimal getPersonPay() {
        return personPay;
    }

    public void setPersonPay(BigDecimal personPay) {
        this.personPay = personPay;
    }

}