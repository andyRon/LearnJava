package com.andyron.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 字符编码过滤器
 */
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 过滤器放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
