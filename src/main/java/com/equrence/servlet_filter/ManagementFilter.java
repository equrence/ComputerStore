package com.equrence.servlet_filter;

import com.equrence.model.entity.Employee;
import com.equrence.model.entity.enumeration.EmployeePosition;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ManagementFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        EmployeePosition position = employee.getEmployeePosition();

        switch (position) {
            case MANAGER:
            case DIRECTOR:
            case ADMINISTRATOR:
                chain.doFilter(req, resp);
                break;
            default:
                req.getRequestDispatcher("/view/error/error403.jsp").forward(req, resp);
                break;
        }
    }
}
