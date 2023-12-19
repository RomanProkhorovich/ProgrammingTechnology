package com.example.ProgrammingTechnology.security;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class Filter implements jakarta.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        jakarta.servlet.Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //httpServletResponse.addHeader("Access-Control-Allow-Origin","http://localhost:63342");
        //httpServletResponse.addHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS");
        //httpServletResponse.addHeader("Access-Control-Allow-Headers","Content-Type");
        //filterChain.doFilter(servletRequest, servletResponse);
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Access-Control-Allow-Origin
        String origin = ((HttpServletRequest) servletRequest).getHeader("Origin");
        String refer = ((HttpServletRequest) servletRequest).getHeader("Referer");

        String currentOrigin = ObjectUtils.firstNonNull(origin, refer);

        response.setHeader("Access-Control-Allow-Origin", StringUtils.isNotBlank(currentOrigin)
                ? (allowedOrigins.contains(currentOrigin) ? currentOrigin : "null")
                : "null");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method," +
                " Access-Control-Request-Headers");
        response.setHeader("Access-Control-Expose-Headers", "");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        jakarta.servlet.Filter.super.destroy();
    }
}
