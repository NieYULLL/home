package com.rookie.practice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * @author: niezhiqiang
 * @date: 2020/11/16 10:58
 * @description:
 */
@Configuration
public class MyFilter extends ZuulFilter {

    private static final Logger log = Logger.getLogger(MyFilter.class.getName());
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info("send:"+request.getMethod()+"request to "+request.getRequestURI());
        String token = request.getParameter("token");
        if (StringUtils.isNotBlank(token)){
            currentContext.setSendZuulResponse(true);
        }else {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(403);
        }

        return null;
    }
}
