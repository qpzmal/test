package cn.advu.workflow.web.service;

import java.util.Comparator;

/**
 * Created by wangry on 17/7/4.
 */
public class DeptLevelComparetor implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {

        return o2.compareTo(o1);
    }
}
