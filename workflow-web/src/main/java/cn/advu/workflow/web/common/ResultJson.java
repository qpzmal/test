package cn.advu.workflow.web.common;

import cn.advu.workflow.web.common.constant.WebConstants;

/**
 * ajax请求返回的实体类
 * @author Niu Qianghong
 *
 */
public class ResultJson<T> {
    private int code = WebConstants.OPERATION_FAILURE;
    private String info = "请联系技术人员!";
    private T data;
    
    public ResultJson(){}
    
    public ResultJson(int code, String info) {
        super();
        this.code = code;
        this.info = info;
    }
    
    public ResultJson(String info) {
        super();
        this.info = info;
    }
    
    public ResultJson(int code){
        this.code = code;
    }
    
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
}
