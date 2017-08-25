package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

public class SysInfo extends AbstractEntity {

    private String name;

    private String logo;

    private Byte emailSwitch;

    private Byte smsSwitch;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Byte getEmailSwitch() {
        return emailSwitch;
    }

    public void setEmailSwitch(Byte emailSwitch) {
        this.emailSwitch = emailSwitch;
    }

    public Byte getSmsSwitch() {
        return smsSwitch;
    }

    public void setSmsSwitch(Byte smsSwitch) {
        this.smsSwitch = smsSwitch;
    }

}