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
        <div class="container">
            <h1 id="title">Register</h1>
            <p id="description">To sign-up for a free basic account please provide us with some basic information using the contact form below. Please use valid credentials.</p>
            <form class="form" id="form" method="post" onsubmit="return myFunction()" action="/register">


                <input type="text" class="username" name="username" placeholder="Username" required pattern="^[^<>%$].{3,30">
                <div>
                    <p class="username-help">Please enter your username.</p>
                </div>


                <input type="email" class="email" name="email" placeholder="Email" required>
                <div>
                    <p class="email-help">Please enter your current email address.</p>
                </div>


                <input type="password" id="password" class="password" name="password" placeholder="Password" required pattern="^(?=.*[a-z])(?=.*[A-Z]).{6,20}$">
                <div>
                    <p class="password-help">Please enter your password, at least one lowercase, one uppercase letter</p>
                    <p class="password-help">And between 6 and 20 characters</p>
                </div>

                <input type="password" id="re_password" class="re_password" name="re_password" placeholder="Repeat Password" required >
                <div>
                    <p class="re_password-help">Please repeat your password.</p>
                </div>

                <input type="submit" class="submit" value="Register">
            </form>
        </div>

    </div>
</div>

<script src="/resources/js/app.js"></script>
</body>
</html>