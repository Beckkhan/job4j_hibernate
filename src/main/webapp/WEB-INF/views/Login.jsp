<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <%@include file="/css/main.css"%>
    </style>
    <script >
        function validate() {
            var login = document.getElementById("login").value;
            var pass = document.getElementById("pass").value;
            if (login === "") {
                alert("Enter login name");
                return false;
            } else if (pass === "") {
                alert("Enter password");
                return false;
            }
            return true;
        }
    </script>
</head>

<body>
<h2>USER LOGIN</h2>
<form method = 'post' action='${pageContext.servletContext.contextPath}/login'>
    <label for="login"> Login:</label>
    <input type='text' name='login' id="login"><br>

    <label for="pass">Password:</label>
    <input type='password' name='password' id="pass"><br>

    <label>&nbsp;</label>
    <input type='submit' value='LOGIN' onclick="return validate();">
</form>
<c:if test="${not empty error}">
    <div style="background-color: red">
        <c:out value='${error}'></c:out>
    </div>
</c:if>
<br>
<br>
<label>&nbsp;</label>
<form method="get" action="${pageContext.servletContext.contextPath}/addUser">
    <input type='submit' value='REGISTER NEW USER'>
</form>
</body>
</html>