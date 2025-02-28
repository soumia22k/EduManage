package com.example.edumanage.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic (if needed)
    }

    @Override
    public void destroy() {
        // Cleanup logic (if needed)
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String action = req.getServletPath();
        if(  "/".equals(action) || "/login".equals(action) || "/log_in.jsp".equals(action)|| action.startsWith("/resources/") ){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{

            Object isLoggedObj = req.getSession().getAttribute("isLoggedIn");
            if (isLoggedObj != null && (Boolean) isLoggedObj) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }


            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}