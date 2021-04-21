<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%@include file="/resources/web-fragments/head.jsp" %>
    <title>Success!</title>
</head>
<body>
<div class="wrapper">
    <div class="center">
        <div class="container">
            <h1 id="title">Welcome ${username}!</h1>
            <p id="message">We herebly welcome you young wolf in the Wolfpack. We hope you will find here your fellow
                gaming wolfs to create your own wolfpack with!</p>
            <p id="go_to">Click on the image below to log in.</p>
            <a href="/login"><img class="wolf" src="resources/images/wolf.jpg"></a>
        </div>
    </div>
</div>
</body>
</html>
