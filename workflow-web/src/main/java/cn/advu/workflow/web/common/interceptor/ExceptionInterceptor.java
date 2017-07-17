package cn.advu.workflow.web.common.interceptor;

import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by wangry on 17/7/13.
 */
public class ExceptionInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object retVal = null;
        try{
            retVal = joinPoint.proceed(joinPoint.getArgs());
        } catch(Exception e){
            retVal = printTrace(joinPoint, e);
        }
        return retVal;
    }

    private ResultJson<?> printTrace(ProceedingJoinPoint joinPoint, Exception e) throws Throwable{
        if (joinPoint.getArgs() != null) {
            logger.error("arguments:{}", joinPoint.getArgs());
        }

        String info = null;
        if (e instanceof ServiceException){
            ServiceException se = (ServiceException) e;
            info = se.getInfo();
        } else {
            logger.error("系统异常:", e);
            throw e;
        }

        ResultJson<?> result = new ResultJson<>();
        result.setCode(WebConstants.OPERATION_FAILURE);
        result.setInfo(info);
        return result;
    }
}
