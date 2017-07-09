package cn.advu.workflow.web.manager;

import java.util.Comparator;

/**
 * Created by wangry on 17/7/4.
 */
public class LevelComparetor implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
}
