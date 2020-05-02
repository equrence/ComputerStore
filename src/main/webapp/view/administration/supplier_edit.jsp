<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Добавить поставщика</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div>
            Вы вошли как: <a href="${pageContext.request.contextPath}/my_info">${sessionScope.employee.employeeName}</a>  (${sessionScope.employee.employeePosition})
            <a class="right" href="${pageContext.request.contextPath}/logout">Выйти</a>
        </div>
        <br/>
        <div class="center">
            <a href="${pageContext.request.contextPath}/administration">Работники</a>
            <a href="${pageContext.request.contextPath}/products">Товары</a>
            <a href="${pageContext.request.contextPath}/administration/suppliers">Поставщики</a>
            <a href="${pageContext.request.contextPath}/administration/deliveries">Поставки</a>
            <a href="${pageContext.request.contextPath}/administration/orders">Все заказы</a>
        </div>
        <div class="center">
            <h1>Изменить информацию о поставщике</h1>

            <form action="${pageContext.request.contextPath}/administration/supplier_edit" method="post">
                <table class="tableInfo">
                    <tr>
                        <th><label for="id">ID</label></th>
                        <td><input type="number" id="id" name="id" value="${requestScope.supplier.supplierId}" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="name">Наименование</label></th>
                        <td><input type="text" id="name" name="name" min="1" value="${requestScope.supplier.supplierName}" required></td>
                    </tr>
                    <tr>
                        <th><label for="representative">Представитель</label></th>
                        <td><input type="text" id="representative" name="representative" value="${requestScope.supplier.supplierRepresentative}" required></td>
                    </tr>
                    <tr>
                        <th><label for="address">Адрес</label></th>
                        <td><input type="text" id="address" name="address" value="${requestScope.supplier.supplierAddress}" required></td>
                    </tr>
                    <tr>
                        <th><label for="phone_number">Номер телефона</label></th>
                        <td><input type="text" id="phone_number" name="phone_number" value="${requestScope.supplier.phoneNumber}" required></td>
                    </tr>
                    <tr class="noBorder">
                        <td class="noBorder" colspan="2"><input type="submit" value="Сохранить"></td>
                    </tr>
                </table>
            </form>

            <p><a href="${pageContext.request.contextPath}/administration/suppliers">Отмена</a></p>
        </div>
    </body>
</html>
