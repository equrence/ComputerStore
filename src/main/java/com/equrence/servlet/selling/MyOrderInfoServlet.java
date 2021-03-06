package com.equrence.servlet.selling;

import com.equrence.model.entity.Employee;
import com.equrence.model.service.OrderService;
import com.equrence.model.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/selling/my_order_info")
public class MyOrderInfoServlet extends HttpServlet {
    private OrderService service;

    @Override
    public void init() {
        service = new OrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.parseLong(req.getParameter("id"));

        HttpSession session = req.getSession();

        Employee employee = (Employee) session.getAttribute("employee");
        int employeeId = employee.getEmployeeId();

        Map<String, String> orderDetails = service.showOrderDetails(orderId, employeeId);

        req.setAttribute("orderDetails", orderDetails);

        req.getRequestDispatcher("/view/selling/my_order_info.jsp").forward(req, resp);
    }
}
