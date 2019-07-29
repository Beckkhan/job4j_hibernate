<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddCar</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script type="text/javascript">
        <%@include file="/scripts/addCar.js"%>
    </script>
    <style>
        <%@include file="/css/main.css"%>
    </style>
</head>
<body>
<h2>ADD CAR FOR SALE</h2>
<form method="post" action="${pageContext.servletContext.contextPath}/addCar">
    <label for="name">Car Name:</label>
    <input type="text" name="name" id="name"><br>

    <label for="body">Body Type:</label>
    <select id="body" name="body" style="width: 13em">
        <option>--Select Body Type--</option>
    </select><br>
    <label for="newBody">&nbsp;</label>
    <input type="text" name="newBody" id="newBody">
    <input type="button" value="Add New Body Type" onclick="addNewBodyType()"> <br>


    <label for="engine">Engine Type:</label>
    <select id="engine" name="engine" style="width: 13em">
        <option value="">--Select Engine Type--</option>
    </select><br>
    <label for="newEngine">&nbsp;</label>
    <input type="text" name="newEngine" id="newEngine">
    <input type="button" value="Add New Engine Type" onclick="addNewEngineType()"> <br>


    <label for="transmission">Transmission Type:</label>
    <select id="transmission" name="transmission" style="width: 13em">
        <option value="">--Select Transmission Type--</option>
    </select><br>
    <label for="newTransmission">&nbsp;</label>
    <input type="text" name="newTransmission" id="newTransmission">
    <input type="button" value="Add New Transmission Type" onclick="addNewTransmission()"> <br>

    <label for="location">Car Location:</label>
    <select id="location" name="location" style="width: 13em">
        <option value="">--Select Location--</option>
    </select><br>
    <label for="newLocation">&nbsp;</label>
    <input type="text" name="newLocation" id="newLocation">
    <input type="button" value="Add New Location" onclick="addNewLocation()"> <br>

    <label for="price">Price:</label>
    <input type="hidden" name="sold" value="false">
    <input type="text" name="price" id="price"><br>

    <input type="hidden" name="owner" id="owner" value="${sessionScope.login}"><br><br>

    <label>&nbsp;</label>
    <input type='submit' value='ADD' onclick="return validateCarInput()">
    <br>
    <br>
    <c:if test="${not empty error}">
        <c:out value='${error}'></c:out>
    </c:if>
    <br>
    <br>
</form>
<label>&nbsp;</label>
<form method='get' action='${pageContext.servletContext.contextPath}/'>
    <input type='submit' value='Return To Car List'>
</form>
</body>
</html>