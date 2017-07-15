package cn.advu.workflow.web.manager;

import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.util.AssertUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangry on 17/7/15.
 */
@Component
public class DateManager {

    /**
     * 校验日期
     *
     * @param startDate
     * @param endDate
     */
    public void validateStartEndDate(Date startDate, Date endDate) {
        AssertUtil.assertNotNull(startDate, MessageConstants.START_DATE_IS_NULL);
        AssertUtil.assertNotNull(endDate, MessageConstants.END_DATE_IS_NULL);
        if (startDate.after(endDate)) {
            throw new ServiceException(MessageConstants.START_DATE_AFTER_END_DATE);
        }
    }
}
