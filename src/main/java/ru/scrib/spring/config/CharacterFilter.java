package ru.scrib.spring.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component("myTestFilter")
public class CharacterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("ISO-8859-1");
        servletResponse.setContentType("text/html; charset=ISO-8859-1");
        servletResponse.setCharacterEncoding("ISO-8859-1");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
