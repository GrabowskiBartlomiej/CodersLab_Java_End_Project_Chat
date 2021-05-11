<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%@include file="/resources/web-fragments/head.jsp" %>
    <title>Login</title>
</head>
<body>
<div class="wrapper">

    <%@include file="/resources/web-fragments/navbar.jsp" %>

    <div class="center">
        <div class="container">
            <h1 id="title">Hello ${user.getUsername()}</h1>


        </div>
    </div>
</div>
</body>
</html>
