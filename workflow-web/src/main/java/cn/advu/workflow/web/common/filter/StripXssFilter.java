package cn.advu.workflow.web.common.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Date: 13-7-18
 * To change this template use File | Settings | File Templates.
 */
public class StripXssFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(StripXssFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        LOGGER.info("StripXssFilter init()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new StripXssHttpWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        LOGGER.info("StripXssFilter destroy()");
    }
}
