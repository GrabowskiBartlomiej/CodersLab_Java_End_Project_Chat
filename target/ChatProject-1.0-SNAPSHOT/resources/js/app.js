$(".username").focus(function () {
    $(".username-help").slideDown(500);
}).blur(function () {
    $(".username-help").slideUp(500);
});

$(".email").focus(function () {
    $(".email-help").slideDown(500);
}).blur(function () {
    $(".email-help").slideUp(500);
});

$(".password").focus(function () {
    $(".password-help").slideDown(500);
}).blur(function () {
    $(".password-help").slideUp(500);
});

$(".re_password").focus(function () {
    $(".re_password-help").slideDown(500);
}).blur(function () {
    $(".re_password-help").slideUp(500);
});


function myFunction() {
    var pass1 = document.getElementById("password").value;
    var pass2 = document.getElementById("re_password").value;

    if (pass1 != pass2) {
        document.getElementById("password").style.background = "#E34234";
        document.getElementById("re_password").style.background = "#E34234";
        alert("Passwords Do not match");
    } else {
        document.getElementById("form").submit();
    }
}




$(document).ready(function (){
    console.log("page loaded")
})

const connection = new WebSocket("ws://localhost:8080/chat");

const text = document.getElementById("message")

connection.onopen = (event) => {
    console.log("WebSocket is open now.");
};

connection.onclose = (event) => {
    console.log("WebSocket is closed now.");
};

connection.onerror = (event) => {
    console.error("WebSocket error observed:", event);
};





text.addEventListener("keyup", (event)=>{
    if(event.code === "Enter"){
        const username = "${user.getUsername()}";
        const message = text.value;
        const channel = "${channelId}"
        const data = channel + " ${user.getId()}" + " :" + username + ": " + message;

        console.log(data);


        connection.send(data);

        text.value = "";

    }
})



connection.onmessage = (event) =>{
    var channel = event.data.split(" ", 1);
    var message = event.data.replace(channel + " ", "");
    var userId = message.split(" ", 1);
    message = message.replace(userId + " :", "");

    var name = message.split(":", 1);

    message = message.replace(name + ":","");

    if(channel == "${channelId}"){
        const chat = document.querySelector("#chat_row");
        chat.innerHTML += `<tr><td style="color: palegreen">` + name + ":" + '<br><span style="color:wheat"> ' + message + '</span></td></tr>';



        console.log(message);
    }else {
        console.log("wrong channel")
        console.log(channel);
    }
}

const roomOptions = document.querySelector(".room_options");
const roomOptionsDropdown = document.querySelector(".about-dropdown")

roomOptions.addEventListener("mouseover", function (){
    console.log("najechalo sie")
    var style = window.getComputedStyle(roomOptionsDropdown);
    var display = style.getPropertyValue('display');

    if(display == 'none'){
        console.log("zmiana na block")
        roomOptionsDropdown.style.display = 'block';
        roomOptions.style.color = 'white';
    }else{
        console.log("zmiana na none")
        roomOptionsDropdown.style.display = 'none';
        roomOptions.style.color = '#898989';
    }
})



const channelOptions = document.querySelector(".channel_options");
const channelOptionsDropdown = document.querySelector(".about-dropdown-channel");

channelOptions.addEventListener("mouseover", function (){
    var style = window.getComputedStyle(channelOptionsDropdown);
    var display = style.getPropertyValue('display');

    if(display == 'none'){
        channelOptionsDropdown.style.display = 'block';
        channelOptions.style.color = 'white';
    }else{
        channelOptionsDropdown.style.display = 'none';
        channelOptions.style.color = '#898989';
    }
})


const addChannelBox = document.querySelector(".add-channel-box");
function addChannel() {

    var style = window.getComputedStyle(addChannelBox);
    var display = style.getPropertyValue('display');

    if(display == 'none'){
        addChannelBox.style.display = 'block';
    }
}

const changeLogoBox = document.querySelector(".change-room-logo-box");
function changeLogo(){
    var style = window.getComputedStyle(changeLogoBox);
    var display = style.getPropertyValue('display');
    if(display === 'none'){
        changeLogoBox.style.display = 'block';
    }
}

const addUsersBox = document.querySelector(".add-users-box");
function addUsers(){
    var style = window.getComputedStyle(addUsersBox);
    var display = style.getPropertyValue('display');
    if(display === 'none'){
        addUsersBox.style.display = 'block';
    }
}

const changeRoomsName = document.querySelector(".change-room-name-box")
function changeRoomName(){
    var style = window.getComputedStyle(changeRoomsName);
    var display = style.getPropertyValue('display');
    if(display === 'none'){
        changeRoomsName.style.display = 'block';
    }
}

const leaveRoomBox = document.querySelector(".leave-room-box")
function leaveRoom(){
    var style = window.getComputedStyle(leaveRoomBox);
    var display = style.getPropertyValue('display');
    if(display === 'none'){
        leaveRoomBox.style.display = 'block';
    }
}


document.addEventListener("keyup", (event) =>{
    if(event.code === 'Escape'){
        console.log("wciskam esc");
        addChannelBox.style.display = 'none';
        roomOptionsDropdown.style.display = 'none';
        changeLogoBox.style.display = 'none';
        addUsersBox.style.display = 'none';
        userOptions.style.display = 'none';
        userNick.style.display = 'none';
        userAvatar.style.display = 'none';
        nameChannel.style.display = 'none';
        deleteChannelBox.style.display = 'none';
        channelOptionsDropdown.style.display = 'none';
    }
})

const userOptions = document.querySelector(".user-dropdown");
function showUserOptions(){
    var style = window.getComputedStyle(userOptions);
    var display = style.getPropertyValue('display');
    if(display === 'none'){
        userOptions.style.display = 'block';
    }else {
        userOptions.style.display = 'none';
    }
}

const userNick = document.querySelector(".change-user-name-box")
function changeUserNickname(){
    var style = window.getComputedStyle(userNick);
    var display = style.getPropertyValue('display');
    if(display === 'none')
        userNick.style.display = 'block';
}

const userAvatar = document.querySelector(".change-user-avatar-box")
function changeUserAvatar(){
    var style = window.getComputedStyle(userAvatar);
    var display = style.getPropertyValue('display');
    if(display === 'none')
        userAvatar.style.display = 'block';
}

const nameChannel = document.querySelector(".rename-channel-box")
function renameChannel(){
    var style = window.getComputedStyle(nameChannel);
    var display = style.getPropertyValue('display');
    if(display === 'none')
        nameChannel.style.display = 'block';
}

const deleteChannelBox = document.querySelector(".delete-channel-box")
function deleteChannel(){
    var style = window.getComputedStyle(deleteChannelBox);
    var display = style.getPropertyValue('display');
    if(display === 'none')
        deleteChannelBox.style.display = 'block';
}

function cancel(){
    addChannelBox.style.display = 'none';
    roomOptionsDropdown.style.display = 'none';
    changeLogoBox.style.display = 'none';
    addUsersBox.style.display = 'none';
    userOptions.style.display = 'none';
    userNick.style.display = 'none';
    userAvatar.style.display = 'none';
    nameChannel.style.display = 'none';
    deleteChannelBox.style.display = 'none';
}
