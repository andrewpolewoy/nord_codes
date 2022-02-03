<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> User account </title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">

    <sec:authentication var="user" property="principal" />

</head>

<body>

    <div>
        <a>
            <sec:authentication property="principal.username" />
        </a>
        <a hidden id="principalId">
            <sec:authentication property="principal.id" />
        </a>
        <a hidden id="principalRole">
            <sec:authentication property="principal.roles" />
        </a>
    </div>

    <div class="container">
      <table class="table" id="user-table">
        <thead>
        <tr>
            <th>Date</th>
            <th>Short reference</th>
            <th>Original reference</th>
        </tr>
        </thead>
        <tbody></tbody>
      </table>
      <a href="/">Главная</a>
    </div>




        <form class="form">

            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInput" name="originalUrl" required>
                <label for="floatingInput">Оригинальная ссылка</label>
            </div>

            <button class="w-100 btn btn-sm btn-outline-dark" type="submit">Генерировать короткую ссылку</button>
            <button class="w-100 btn btn-sm btn-outline-secondary" onClick="window.location.reload();">Обновить страницу</button>

        </form>





<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>


                    $(document).ready(function() {
                        var principalId = $('#principalId').text();
                        $.ajax({
                            url: 'http://localhost:8080/user/' + principalId,
                            type: 'get',
                            success: function(response) {
                                var len = response.length;
                                for (var i = 0; i < len; i++) {

                                    var id = response[i].id;
                                    var createdAt = response[i].createdAt;
                                    var hash = response[i].hash;
                                    var originalUrl = response[i].originalUrl;
                                    console.log(createdAt);
                                    var tr_str = "<tr>" +
                                        "<td class='id' hidden align='center'>" + id + "</td>" +
                                        "<td align='center'>" + createdAt + "</td>" +
                                        "<td align='center'>" +
                                        "<a class='nav-link' href='" + originalUrl + "'>" + hash + "</a>"
                                        "</td>" +
                                        "<td align='center'>" + originalUrl + "</td>" +
                                        "<td>" +
                                        "<button id = 'delete' type = 'button' " +
                                        "class = 'btn btn-outline-secondary btn-sm'>Удалить</button>" +
                                        "</td>" +
                                        "</tr>";

                                    $("#user-table tbody").append(tr_str);
                                }
                            }
                        });
                    });
                    function handleFormSubmit(event) {
                       event.preventDefault();
                       var principalId = $('#principalId').text();
                       var url = 'http://localhost:8080/user/' + principalId,

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

                     $('table').on('click', '#delete', function() {
                         var rowEl = $(this).closest('tr');
                         var id = rowEl.find('.id').text();
                         var url = 'http://localhost:8080/user/' + id;
                         $.ajax({
                             url: url,
                             type: 'delete',
                             statusCode: {
                                 200: function() {
                                     alert("Успешно удалено");
                                     window.location.reload();
                                 }
                             }
                         });
                     });
    </script>


</body>
</html>