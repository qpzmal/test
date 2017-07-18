package cn.advu.workflow.web.util;

import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangry on 17/7/13.
 */
public class StringListUtil {


    public static List<String> toList(String idStr) {

        List<String> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(idStr)) {
            list = Arrays.asList(idStr.split(","));
        }
        return list;
    }


}
