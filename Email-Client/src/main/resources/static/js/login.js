var link = "http://localhost:8089"
var loginButton; 
var username; 
var password; 
var userID; 


$(document).ready(function() {
    var containerLogin = $("#containerLogin"); 

    containerLogin.on("click", ".login", function() {
        username = user.username; 
        id = user.id; 
        localStorage.setItem("user", $(this).data("index"));
		window.location.assign("accounts.html");
    });

    loginButton = $("#loginButton");
    username = $("#username"); 
    password = $("#password");

    login();
})

function login() {
    loginButton.click(function() {
        var data = {
            "username" : username.val(),
            "password" : password.val()
        }

        $.ajax({
            url : link + "/api/users/login",
            type: "POST", 
            contentType: "application/json", 
            data: JSON.stringify(data),
            success: function() {
                localStorage.setItem("user", $(this).data("index"));
                location.assign("accounts.html");
            },
             error: function() {
                alert("Ne postoji korisnik sa datim kredencijalima!")
            }
        })
    })
}