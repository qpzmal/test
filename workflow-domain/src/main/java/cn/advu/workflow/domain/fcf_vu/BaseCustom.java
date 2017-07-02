package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

public class BaseCustom extends AbstractEntity {

    private String name;

    private String customName;

    private Byte customIndustryId;

    private Byte customType;

    private String contacts;

    private String mobile;

    private String address;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName == null ? null : customName.trim();
    }

    public Byte getCustomIndustryId() {
        return customIndustryId;
    }

    public void setCustomIndustryId(Byte customIndustryId) {
        this.customIndustryId = customIndustryId;
    }

    public Byte getCustomType() {
        return customType;
    }

    public void setCustomType(Byte customType) {
        this.customType = customType;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
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
}