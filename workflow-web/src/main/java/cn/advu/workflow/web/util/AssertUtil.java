package cn.advu.workflow.web.util;

import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import org.apache.commons.lang.StringUtils;

/**
 * Created by wangry on 17/7/13.
 */
public class AssertUtil {


    public static <T> void assertNotNull(Object param) {
        assertNotNull(param, MessageConstants.PARAM_IS_ILLEGAL);
    }

    public static <T> void assertNotNull(Object param, String info) {
        if (param == null) {
            throw new ServiceException(info);
        }
    }

    public static <T> void assertNotNullOrEmpty(String param, String fieldName) {
        if (StringUtils.isEmpty(param)) {
            throw new ServiceException(fieldName + MessageConstants.IS_NULL_OR_EMPTY);
        }
    }


}
