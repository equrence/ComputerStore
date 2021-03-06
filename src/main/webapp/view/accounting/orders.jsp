<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Продажи</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <c:import url="/resource/top_bar.jsp" />
        <div class="center">

            <h1>Все заказы</h1>

            <form action="${pageContext.request.contextPath}/accounting/order_info">
                <label><input type="number" name="id" placeholder="Поиск заказа по ID" required></label>
                <input type="submit" value="Поиск">
            </form>
            <table class="tableMain">
                <tr>
                    <th>Номер заказа</th>
                    <th>Артикул товара</th>
                    <th>Количество товаров</th>
                    <th>Сумма (руб.)</th>
                    <th>Дата</th>
                    <th>Действия</th>
                </tr>
                <c:forEach items="${requestScope.orders}" var="order">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.productId}</td>
                        <td>${order.productQuantity}</td>
                        <td>${order.totalAmount}</td>
                        <td>${order.orderDate}</td>
                        <td><a href="${pageContext.request.contextPath}/accounting/order_info?id=${order.orderId}">Подробнее</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
