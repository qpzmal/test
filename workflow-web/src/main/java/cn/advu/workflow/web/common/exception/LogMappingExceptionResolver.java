package cn.advu.workflow.web.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weiqz on 2017/6/27.
 */
public class LogMappingExceptionResolver extends SimpleMappingExceptionResolver {
    private final static Logger LOGGER = LoggerFactory.getLogger(LogMappingExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception exception) {
//        Map<String, Exception> model = new HashMap<>();
//        model.put("ex", ex);
//        ModelAndView modelAndView = new ModelAndView("../../exception/errorPage",model);

		/*错误日志输出到控制台*/
        LOGGER.debug( "" + request.getRequestURI());
        LOGGER.error( "ExceptionResolver: " + exception.getMessage());
        LOGGER.error( "ExceptionResolver: ", exception);

        return super.doResolveException(request, response, handler, exception);
    }
}