package cn.advu.workflow.web.common;

import cn.advu.workflow.web.common.constant.WebConstants;

/**
 * ajax请求返回的实体类
 * @author Niu Qianghong
 *
 */
public class ResultJson<T> {
    private int code = WebConstants.OPERATION_SUCCESS;
    private String info = "请联系技术人员!";
    private T data;
    private T line;
    private T guangdong;
    private T sjz;

    public ResultJson(){}



    public T getLine() {
        return line;
    }



    public T getGuangdong() {
        return guangdong;
    }



    public T getSjz() {
        return sjz;
    }



    public void setLine(T line) {
        this.line = line;
    }



    public void setGuangdong(T guangdong) {
        this.guangdong = guangdong;
    }



    public void setSjz(T sjz) {
        this.sjz = sjz;
    }



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
    public ResultJson setData(T data) {
        this.data = data;
        return this;
    }
    public int getCode() {
        return code;
    }
    public ResultJson setCode(int code) {
        this.code = code;
        return this;
    }
    public String getInfo() {
        return info;
    }
    public ResultJson setInfo(String info) {
        this.info = info;
        return this;
    }
}
