package com.equrence.model.dao;

import com.equrence.db_connection.JdbcManager;
import com.equrence.model.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private final JdbcManager jdbcManager = new JdbcManager();

    public void createOrder(Order order) {
        String sql1 = "INSERT INTO orders (product_id, employee_id, product_quantity, total_amount, order_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        String sql2 = "UPDATE products SET product_quantity = (product_quantity - ?) WHERE product_id = ?";

        int productQuantity = order.getProductQuantity();
        long productId = order.getProductId();

        try (Connection connection = jdbcManager.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement statement1 = connection.prepareStatement(sql1);
                PreparedStatement statement2 = connection.prepareStatement(sql2)) {

                statement1.setLong(1, productId);
                statement1.setInt(2, order.getEmployeeId());
                statement1.setInt(3, productQuantity);
                statement1.setBigDecimal(4, order.getTotalAmount());
                statement1.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

                statement2.setInt(1, productQuantity);
                statement2.setLong(2, productId);

                statement1.execute();
                statement2.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders() {
        String sql = "SELECT order_id, product_id, employee_id, product_quantity, total_amount, order_date FROM orders";
        List<Order> orders = new ArrayList<>();
        Order order;

        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                order = new Order();

                order.setOrderId(resultSet.getInt(1));
                order.setProductId(resultSet.getLong(2));
                order.setEmployeeId(resultSet.getInt(3));
                order.setProductQuantity(resultSet.getInt(4));
                order.setTotalAmount(resultSet.getBigDecimal(5));
                order.setOrderDate(resultSet.getTimestamp(6));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> getAllOrdersByEmployeeId(int id) {
        String sql = "SELECT order_id, product_id, employee_id, product_quantity, total_amount, order_date" +
                " FROM orders WHERE employee_id = ?";
        List<Order> orders = new ArrayList<>();
        Order order;

        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    order = new Order();

                    order.setOrderId(resultSet.getInt(1));
                    order.setProductId(resultSet.getLong(2));
                    order.setEmployeeId(id);
                    order.setProductQuantity(resultSet.getInt(4));
                    order.setTotalAmount(resultSet.getBigDecimal(5));
                    order.setOrderDate(resultSet.getTimestamp(6));

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrderById(long id) {
        String sql = "SELECT order_id, product_id, employee_id, product_quantity, total_amount, order_date FROM orders " +
                "WHERE order_id = ?";
        Order order = null;

        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    order = new Order();

                    order.setOrderId(id);
                    order.setProductId(resultSet.getLong(2));
                    order.setEmployeeId(resultSet.getInt(3));
                    order.setProductQuantity(resultSet.getInt(4));
                    order.setTotalAmount(resultSet.getBigDecimal(5));
                    order.setOrderDate(resultSet.getTimestamp(6));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public Order getOrderByIdAndEmployeeId(long orderId, int employeeId) {
        String sql = "SELECT order_id, product_id, employee_id, product_quantity, total_amount, order_date FROM orders " +
                "WHERE order_id = ? AND employee_id = ?";
        Order order = null;

        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, orderId);
            statement.setInt(2, employeeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    order = new Order();

                    order.setOrderId(orderId);
                    order.setProductId(resultSet.getLong(2));
                    order.setEmployeeId(employeeId);
                    order.setProductQuantity(resultSet.getInt(4));
                    order.setTotalAmount(resultSet.getBigDecimal(5));
                    order.setOrderDate(resultSet.getTimestamp(6));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }
}
