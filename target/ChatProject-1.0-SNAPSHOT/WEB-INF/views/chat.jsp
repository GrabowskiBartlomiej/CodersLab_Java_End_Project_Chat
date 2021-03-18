<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="/resources/web-fragments/head.jsp"%>

    <title>Wolfpack</title>
</head>
    <body>
        <div class="chat_container">

            <div><img href="/" class="chat_logo" src="/resources/images/logo.png"/></div>

            <div class="chat_room">${roomName}</div>

            <div class="chat_channel"> ${channelName}</div>

            <div class="chat_room_list">
                <table>
                    <tbody>
                        <c:forEach items="${rooms}" var="room">
                            <tr><td><a href="/chat/${room.getId()}">${room.getName()}</a></td></tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="chat_users_list">users list</div>

            <div class="chat_user">
                ${user.getUsername()}
            </div>

            <div class="chat_message_box">Welcome back ${user.getUsername()}!</div>

            <div class="chat_channels_list">
                <table>
                    <tbody>
                    <c:forEach items="${channels}" var="channel">
                        <tr><td><a class="data" href="/chat/${roomId}/${channel.getId()}"># ${channel.getName()}</a></td></tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="chat_type_box">
                <form>
                    <input type="text" id="message" placeholder="Write on this channel...">
                    <button type="submit" style="display: none"/>
                </form>
            </div>
        </div>
</body>
</html>
