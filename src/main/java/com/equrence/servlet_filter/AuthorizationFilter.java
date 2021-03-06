package com.equrence.servlet_filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String loginURI = req.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("employee") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean isStaticResource = req.getRequestURI().startsWith("/css");

        if (loggedIn || loginRequest || isStaticResource) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(loginURI);
        }
    }
}
