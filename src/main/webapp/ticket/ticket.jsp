<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница пользователя</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 5%">
    <h3 style="text-align: center">Список тикетов</h3>
    <table class="table" style="margin-top: 3%">
        <thead class="thead-dark">
        <tr>
            <th>#</th>
            <th>Название тикета</th>
            <th>Описание</th>
            <th>Статус</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ticket" items="${tickets}">
            <tr>
                <td>${ticket.id}</td>
                <td>${ticket.name}</td>
                <td>${ticket.description}</td>
                <td>${ticket.status}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/edit-ticket" method="get">
                        <div class="form-group">
                            <input type="hidden"  name="ticketId" value="${ticket.id}" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success btn-sm">Редактировать</button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/delete-ticket" method="post">
                        <div class="form-group">
                            <input type="hidden"  name="ticketId" value="${ticket.id}" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-danger btn-sm">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/sing-out" style="float: right; margin-left: 10px;"
       class="btn btn-success btn-sm" role="button">Выйти</a>
    <a href="${pageContext.request.contextPath}/create-ticket" style="float: right; margin-left: 10px;"
       class="btn btn-success btn-sm" role="button">Создать тикет</a>
</div>
</body>
</html>
