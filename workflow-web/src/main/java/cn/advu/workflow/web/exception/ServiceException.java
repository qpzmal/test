package cn.advu.workflow.web.exception;

/**
 * Created by wangry on 17/7/13.
 */
public class ServiceException extends RuntimeException {

    private String info;

    public ServiceException(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

}
