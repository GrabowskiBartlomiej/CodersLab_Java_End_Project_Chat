<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <%@include file="/resources/web-fragments/head.jsp"%>

        <title>Home Page</title>
    </head>
    <body>
        <div class="wrapper">

            <%@include file="/resources/web-fragments/navbar.jsp"%>

            <div class="center">
                <h1>Welcome To Wolfpack</h1>
                <div class="buttons">
                    <a href="#"><button class="off">Download<br><h4>(Not Available Yet)</h4></button></a>
                    <a href="register"><button class="btn">Register<h4>For Free!</h4></button></a>
                </div>
            </div>
        </div>


    </body>
</html>
