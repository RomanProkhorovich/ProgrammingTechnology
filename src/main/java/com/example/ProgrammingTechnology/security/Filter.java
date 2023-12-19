package com.example.ProgrammingTechnology.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Filter implements jakarta.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String origin = ((HttpServletRequest) servletRequest).getHeader("Origin");
        String refer = ((HttpServletRequest) servletRequest).getHeader("Referer");

        String currentOrigin = origin == null ? (refer == null ? "null" : refer) : origin;

        response.setHeader("Access-Control-Allow-Origin", currentOrigin.equals("http://localhost:63342") ? currentOrigin : "null");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method," +
                " Access-Control-Request-Headers");
        response.setHeader("Access-Control-Expose-Headers", "");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
