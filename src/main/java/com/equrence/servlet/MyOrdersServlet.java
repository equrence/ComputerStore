package com.equrence.servlet;

import com.equrence.model.entity.Employee;
import com.equrence.model.entity.Order;
import com.equrence.model.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/my_orders")
public class MyOrdersServlet extends HttpServlet {
    private OrderService service;

    @Override
    public void init() {
        service = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        int id = employee.getEmployeeId();

        List<Order> orders = service.getAllOrdersByEmployeeId(id);

        req.setAttribute("orders", orders);
        req.getRequestDispatcher(req.getContextPath() + "view/general/my_orders.jsp").forward(req, resp);
    }
}
