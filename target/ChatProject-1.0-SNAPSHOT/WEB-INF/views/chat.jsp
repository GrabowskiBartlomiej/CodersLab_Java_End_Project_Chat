<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="/resources/web-fragments/head.jsp"%>

    <title>Wolfpack</title>
</head>
    <body>
        <div class="chat_container">




            <div class="chat_logo">
                <a href="/">
                    <img style="width: 100%" src="/resources/images/logo.png">
                </a>
            </div>





            <div class="chat_room">${roomName}</div>




            <div class="chat_channel"> ${channelName}</div>





            <div class="chat_room_list">
                <table>
                    <tbody>
                        <c:forEach items="${rooms}" var="room">
                            <tr><td><a href="/chat/${room.getId()}/1"><img style="width: 100%; border-radius: 40px;height: 100%" src="${room.logo}"/></a></td></tr>
                        </c:forEach>
                            <tr><td><a href="/chat/addRoom"><img style="width: 100%;height: 100%;border-radius: 40px" src="https://cdn.iconscout.com/icon/premium/png-256-thumb/plus-166-825753.png" /></a> </td></tr>
                    </tbody>
                </table>
            </div>




            <div class="chat_users_list">
                <table>
                    <p id="online">Online</p>
                    <tbody>
                    <c:forEach items="${usersOnline}" var="users">
                        <tr><td><a href="#" style="text-decoration: none;"><img src="${users.getAvatar()}" style="border-radius: 40px;width: 20%"/><div style="color: chartreuse; border:none; display:inline;padding-left:20px;position:absolute;padding-top:13px"> ${users.getUsername()}</div></a></td></tr>
                    </c:forEach>
                    </tbody>
                </table>
                <p id="offline">--Offline--</p>
                <table style="opacity: 40%">
                    <tbody>
                    <c:forEach items="${usersOffline}" var="users">
                        <tr><td><a href="#"><img src="${users.getAvatar()}" style="border-radius: 40px;width: 20%"/><div style="color: gray; border:none; display:inline;padding-left:20px;position:absolute;padding-top:13px"> ${users.getUsername()}</div></a></td></tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>





            <div class="chat_user">
                ${user.getUsername()}
            </div>

            <div class="chat_message_box">
                <table>
                    <tbody>
                        <c:forEach items="${messages}" var="message">
                            <tr><td style="color: wheat">${message.content}</td></tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

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
                <form action="/type?roomId=${roomId}&channelId=${channelId}&userId=${user.getId()}" method="post">
                    <input type="text" id="message" placeholder="Write on this channel..." name="message">
                    <button type="submit" style="display: none"/>
                </form>
            </div>




        </div>
</body>
</html>
