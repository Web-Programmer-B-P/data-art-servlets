<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать тикет</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 45%; margin-top: 10%">
    <h3 style="text-align: center">Создать новый тикет</h3>
    <c:if test="${error != ''}">
    <p style="color: red">${error}<p>
    </c:if>
    <form action="${pageContext.request.contextPath}/create-ticket" method="post">
        <div class="form-group">
            <label for="name">Наименование тикета:</label>
            <input type="text" name="name" class="form-control" placeholder="Введите наименование тикета" id="name">
        </div>
        <div class="form-group">
            <label for="desc">Описание:</label>
            <textarea type="text" rows="5"  name="desc" class="form-control" placeholder="Введите описание тикета" id="desc"></textarea>
        </div>
        <button type="submit" class="btn btn-success btn-block">Создать</button>
    </form>
</div>
</body>
</html>
