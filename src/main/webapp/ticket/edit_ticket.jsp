<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать тикет</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 45%; margin-top: 10%">
    <h3 style="text-align: center">Редактировать</h3>
    <c:if test="${error != ''}">
    <p style="color: red">${error}<p>
    </c:if>
    <form action="${pageContext.request.contextPath}/edit-ticket" method="post">
        <div class="form-group">
            <label for="name">Наименование тикета:</label>
            <input type="text" name="name" class="form-control" placeholder="Введите наименование тикета" id="name"
                   value="${ticket.name}">
        </div>
        <div class="form-group">
            <label for="desc">Описание:</label>
            <textarea type="text" rows="5" name="desc" class="form-control" placeholder="Введите описание тикета"
                      id="desc">${ticket.description}</textarea>
        </div>
        <div class="form-group">
            <input type="hidden" name="action" class="form-control" value="save">
        </div>
        <div class="form-group">
            <input type="hidden" name="id" class="form-control" value="${ticket.id}">
        </div>
        <button type="submit" class="btn btn-success btn-block">Сохранить</button>
    </form>
</div>
</body>
</html>
