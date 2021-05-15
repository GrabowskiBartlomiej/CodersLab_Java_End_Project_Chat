<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%@include file="/resources/web-fragments/head.jsp" %>
    <title>Add Room</title>
</head>
<body>
<div class="wrapper">
    <div class="center">
        <div class="container">
            <h1 id="title">Add the new room</h1>
            <p id="description">If you don't paste any direct link to the image the default one will be set.</p>

            <form class="form" id="form" method="post" action="/chat/addRoom">
                <input type="text" name="roomName" placeholder="Insert rooms name" required>
                <input type="text" name="roomLogo" placeholder="Paste URL for rooms logo (not required)">
                <input type="submit" class="submit" value="Create">
            </form>
        </div>
    </div>

</div>

</body>
</html>
