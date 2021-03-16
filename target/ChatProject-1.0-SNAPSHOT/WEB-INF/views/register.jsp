<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%@include file="/resources/web-fragments/head.jsp"%>
    <title>Register</title>

</head>
<body>
<div class="wrapper">

    <%@include file="/resources/web-fragments/navbar.jsp"%>

    <div class="center">
        <h1 id="title">Register</h1>
        <p id="description">To sign-up for a free basic account please provide us with some basic information using the contact form below. Please use valid credentials.</p>
        <form class="form" method="post" action="/register">


                <input type="text" class="username" name="username" placeholder="Username">
                <div>
                    <p class="username-help">Please enter your username.</p>
                </div>


                <input type="email" class="email" name="email" placeholder="Email">
                <div>
                    <p class="email-help">Please enter your current email address.</p>
                </div>


                <input type="password" class="password" name="password" placeholder="Password">
                <div>
                    <p class="password-help">Please enter your password.</p>
                </div>

                <input type="password" class="re_password" name="re_password" placeholder="Repeat Password">
                <div>
                    <p class="re_password-help">Please repeat your password.</p>
                </div>

            <input type="submit" class="submit" value="Register">
        </form>

    </div>
</div>

<script src="/resources/js/app.js"></script>
</body>
</html>