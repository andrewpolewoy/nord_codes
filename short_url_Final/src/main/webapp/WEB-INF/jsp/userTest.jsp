<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Log in with your account</title>
  <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
  <table>
    <thead>
    <th>ID</th>
    <th>UserName</th>
    <th>Password</th>
    <th>Roles</th>
    </thead>
    <c:forEach items="${allUsers}" var="user">
      <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.password}</td>
        <td>
          <c:forEach items="${user.roles}" var="role">${role.name}; </c:forEach>
        </td>
        <td>
          <form action="${pageContext.request.contextPath}/admin" method="post">
            <input type="hidden" name="userId" value="${user.id}"/>
            <input type="hidden" name="action" value="delete"/>
            <button type="submit">Delete</button>
          </form>

        </td>

      </tr>
    </c:forEach>
  </table>

 function handleFormSubmit(event) {
                        event.preventDefault();
                        var url = 'http://localhost:8080/user';

                        const data = new FormData(event.target);

                        const formJSON = Object.fromEntries(data.entries());
                        console.log(formJSON);
                        $.ajax({
                            url: url,
                            type: 'post',
                            data: formJSON,
                            statusCode: {
                                201: function() {
                                    alert('Запись добавлена');
                                },
                            }
                        });
                    }

                document.querySelector('.form').addEventListener('submit', handleFormSubmit);
  <a href="/">Главная</a>
</div>
</body>
</html>