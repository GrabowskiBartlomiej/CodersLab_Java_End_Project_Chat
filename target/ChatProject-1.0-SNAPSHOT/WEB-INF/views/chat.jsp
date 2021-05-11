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

            <div class="chat_room">

                            <p class="room_name">${roomName}</p>

                            <a href="#" class="room_options">+</a>
                            <div class="about-dropdown">
                                <a href="#" onclick="addUsers()">Add user to the room</a>
                                <a href="#" onclick="addChannel()">Add channel</a>
                                <a href="#" onclick="changeLogo()">Change room's logo</a>
                                <a href="#" onclick="changeRoomName()">Change room's name</a>
                                <a href="#" onclick="leaveRoom()">Leave the room</a>
                            </div>

            </div>

            <div class="chat_channel">

                <p class="room_name"> ${channelName} </p>

                <a href="#" class="channel_options">V</a>

                <div class="about-dropdown-channel">
                    <a href="#" onclick="renameChannel()">Rename channel</a>
                    <a href="#" onclick="deleteChannel()">Delete channel</a>
                </div>


            </div>

            <div class="chat_room_list">
                <table>
                    <tbody>
                        <c:forEach items="${rooms}" var="room">
                                <tr><td><a href="/chat/${room.getId()}"><img class="immagine" src="${room.getLogo()}"/></a>
                                    <p class="get-name">${room.getName()}</p>
                                </td></tr>
                        </c:forEach>
                            <tr><td><a href="/chat/addRoom"><img class="immagine" src="https://cdn.iconscout.com/icon/premium/png-256-thumb/plus-166-825753.png" /></a> </td></tr>
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
                <a href="#" class="user_options" onclick="showUserOptions()"><img src="${user.getAvatar()}" style="border-radius: 40px;width: 20%"/><span>${user.getUsername()}</span></a>

                <div class="user-dropdown" style="display: none">
                    <a href="#" onclick="changeUserNickname()" >Change Nickname</a>
                    <a href="#" onclick="changeUserAvatar()">Change Avatar</a>
                    <a href="/logout" >Logout</a>
                </div>
            </div>





            <div class="chat_message_box">
                <table>
                    <tbody id = "chat_row">
                        <c:forEach items="${messages}" var="message">
                            <tr><td style="color: palegreen"> ${message.user.username}:<br>
                                    <span style="color: wheat">${message.content}</span> </td></tr>
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
                    <input type="text"  autocomplete="off" id="message" placeholder="Write on this channel..." name="message">
            </div>

        </div>

        <div class = "add-channel-box" style="display: none">
            <h2>Add Channel</h2>
            <form method="post" action="/addChannel/${roomId}">
                <div class="input-channel-name">
                    <input type="text" name="channelName" required="">
                    <label>Channel Name</label>
                </div>
                <input type="button" name="" value="cancel" onclick="cancel()">
                <input type="submit" name="" value="submit">
            </form>
        </div>

        <div class = "change-room-logo-box" style="display: none">
            <h2>Change Logo</h2>
            <form method="post" action="/changeLogo/${roomId}/${channelId}">
                <div class="input-channel-name">
                    <input type="text" name="logoUrl" required="">
                    <label>New Avatar URL</label>
                </div>
                <input type="button" name="" value="cancel" onclick="cancel()">
                <input type="submit" name="" value="submit">
            </form>
        </div>

        <div class = "add-users-box" style="display: none">
            <h2>Add Users</h2>
            <form method="post" action="/addUsersToTheChannel/${roomId}/${channelId}">
                <table>
                    <c:forEach items="${allUsers}" var="users">
                        <tr>
                            <td>
                                <input type="checkbox" id="_checkbox" name="usersToAdd" value="${users.getId()}"/>
                                <img src="${users.getAvatar()}" style="border-radius: 40px;width: 20%"/>
                                <div style="color: chartreuse; border:none; display:inline;padding-left:20px;position:absolute;padding-top:13px">
                                    <a href="#">${users.getUsername()}</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table><br>
                <input type="button" name="" value="cancel" onclick="cancel()">
                <input type="submit" name="" value="submit">
            </form>
        </div>


        <div class = "change-room-name-box" style="display: none">
            <h2>Change Room's Name</h2>
            <form method="post" action="/changeRoomName/${roomId}/${channelId}">
                <div class="input-channel-name">
                    <input type="text" name="name" required="">
                    <label>New Name</label>
                </div>
                <input type="button" name="" value="cancel" onclick="cancel()">
                <input type="submit" name="" value="submit">
            </form>
        </div>


        <div class = "leave-room-box" style="display: none">
            <h2>Are you sure you want to leave this room?</h2>
            <form method="post" action="/leaveRoom/${roomId}">
                <input type="button" name="" value="cancel" onclick="cancel()">
                <input type="submit" name="" value="delete">
            </form>
        </div>

        <div class = "change-user-name-box" style="display: none">
            <h2>Change User's Name</h2>
            <form method="post" action="/changeUserName/${roomId}/${channelId}">
                <div class="input-channel-name">
                    <input type="text" name="name" required="">
                    <label>New Name</label>
                </div>
                <input type="button" name="" value="cancel" onclick="cancel()">
                <input type="submit" name="" value="submit">
            </form>
        </div>


        <div class = "change-user-avatar-box" style="display: none">
            <h2>Change User's Avatar</h2>
            <form method="post" action="/changeUserAvatar/${roomId}/${channelId}">
                <div class="input-channel-name">
                    <input type="text" name="link" required="">
                    <label>New Avatar</label>
                </div>
                <input type="button" name="" value="cancel" onclick="cancel()">
                <input type="submit" name="" value="submit">
            </form>
        </div>

        <div class = "rename-channel-box" style="display: none">
            <h2>Rename channel</h2>
            <form method="post" action="/renameChannel/${roomId}/${channelId}">
                <div class="input-channel-name">
                    <input type="text" name="name" required="">
                    <label>New Name</label>
                </div>
                <input type="button" name="" value="cancel" onclick="cancel()">
                <input type="submit" name="" value="submit">
            </form>
        </div>

        <div class = "delete-channel-box" style="display: none">
            <h2>Delete this channel?</h2>
            <form method="post" action="/deleteChannel/${roomId}/${channelId}">
                <input type="button" name="" value="cancel" onclick="cancel()">
                <input type="submit" name="" value="confirm">
            </form>
        </div>


        <script src="/resources/js/app.js"></script>
    </body>
</html>
