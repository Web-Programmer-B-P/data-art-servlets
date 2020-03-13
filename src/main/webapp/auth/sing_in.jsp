<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 25%; margin-top: 10%">
    <h3 style="text-align: center">Авторизация</h3>
    <c:if test="${error != ''}">
    <p style="color: red">${error}<p>
    </c:if>
    <form action="${pageContext.request.contextPath}/sing-in" method="post">
        <div class="form-group">
            <label for="login">Логин:</label>
            <input type="text" name="login" class="form-control" placeholder="Введите логин" id="login">
        </div>
        <div class="form-group">
            <label for="pwd">Пароль:</label>
            <input type="password" name="pwd" class="form-control" placeholder="Введите пароль" id="pwd">
        </div>
        <button type="submit" class="btn btn-success btn-block">Авторизоватся</button>
    </form>
    <a href="${pageContext.request.contextPath}/sing-up" class="btn btn-primary btn-block" role="button">Зарегистрироваться</a>
</div>
</body>
</html>
