package com.clinicbooking.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();

        if (path.contains("/assets/") ||path.contains("/css/") || path.contains("/js/") || path.contains("/images/")) {
            filterChain.doFilter(request,response);
            return;
        }

        if (path.endsWith("/login") || path.endsWith("/register") || path.endsWith("/logout")) {
            filterChain.doFilter(request,response);
            return;
        }

        if (path.equals(request.getContextPath() + "/") || path.endsWith("index.jsp")) {
            filterChain.doFilter(request,response);
            return;
        }

        HttpSession session = request.getSession(false);
        boolean logged = session != null && session.getAttribute("user") != null;

        if (!logged) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        filterChain.doFilter(request,response);
    }
}
