package com.garyhu.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : garyhu
 * @decripetion : 自定义一个zuul过滤器，继承ZuulFilter
 * @since : 2018/11/18
 **/
public class PreRequestLogFilter extends ZuulFilter {

    private static final Logger LOGGER  = LoggerFactory.getLogger(PreRequestLogFilter.class);

    /**
     * 返回过滤器的类型，有pre、route、post、error等几种
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 返回一个int值来指定过滤器执行的顺序，不同的过滤器允许返回相同的值
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 返回一个Boolean值来判断该过滤器是否要执行，true表示要执行，false表示不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑，本例让它答应请求的HTTP的方法及请求地址
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOGGER.info(String.format("send %s request to %s",request.getMethod(),request.getRequestURL().toString()));
        return null;
    }
}
