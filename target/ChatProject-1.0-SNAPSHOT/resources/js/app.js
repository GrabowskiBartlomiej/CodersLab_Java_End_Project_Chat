




$(".username").focus(function(){
    $(".username-help").slideDown(500);
}).blur(function(){
    $(".username-help").slideUp(500);
});

$(".email").focus(function(){
    $(".email-help").slideDown(500);
}).blur(function(){
    $(".email-help").slideUp(500);
});

$(".password").focus(function(){
    $(".password-help").slideDown(500);
}).blur(function(){
    $(".password-help").slideUp(500);
});

$(".re_password").focus(function(){
    $(".re_password-help").slideDown(500);
}).blur(function(){
    $(".re_password-help").slideUp(500);
});


function myFunction() {
    var pass1 = document.getElementById("password").value;
    var pass2 = document.getElementById("re_password").value;

    if (pass1 != pass2) {
        document.getElementById("password").style.background = "#E34234";
        document.getElementById("re_password").style.background = "#E34234";
        alert("Passwords Do not match");
    }
    else {
        document.getElementById("form").submit();
    }
}