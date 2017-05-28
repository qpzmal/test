package cn.advu.workflow.web.common.util.echarts;

import cn.advu.workflow.web.common.WebConstants;

import java.util.ArrayList;
import java.util.List;

public class Series<T> {
    private String name;
    private String type;
    private List<T> data = new ArrayList<>();
    public Series(){
        
    }
    public Series(String name, String type, List<T> data) {
        super();
        this.name = name;
        this.type = type;
        this.data = data;
    }
    public Series(String name, String type) {
        super();
        this.name = name;
        this.type = type;
    }
    public Series(String name){
        this.name = name;
        this.type = WebConstants.LINE_CHART;
    }
    public String getName() {
        return name;
    }
    public void add(T t){
        data.add(t);
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
}
