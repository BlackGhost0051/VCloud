/*
document.addEventListener("DOMContentLoaded", function() {
    var sendPostButton = document.getElementById("sendPost");
    sendPostButton.addEventListener("click", function(event) {
        event.preventDefault();

        var loginInput = document.getElementById("login").value;
        var passwordInput = document.getElementById("pass").value;

        var formData = new URLSearchParams();
        formData.append("login", loginInput);
        formData.append("password", passwordInput);

        fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData
        })
        .then(response => response.text())
        .then(token => {
            fetch("/protected", {
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + token
                }
            })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('Access denied');
                }
            })
            .then(data => {
                console.log(data);
            })
            .catch(error => console.error("Error:", error));
        })
        .catch(error => console.error("Error:", error));
    });
});*/


/*
document.addEventListener("DOMContentLoaded", function() {
    var sendPostButton = document.getElementById("sendPost");
    sendPostButton.addEventListener("click", function(event) {
        event.preventDefault();

        var loginInput = document.getElementById("login").value;
        var passwordInput = document.getElementById("pass").value;

        var formData = new URLSearchParams();
        formData.append("login", loginInput);
        formData.append("password", passwordInput);

        fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData
        })
        .then(response => response.text())
        .then(data => {
            console.log(data);
        })
        .catch(error => console.error("Error:", error));
    });
});
*/


document.addEventListener("DOMContentLoaded", function() {
    var sendPostButton = document.getElementById("sendPost");
    sendPostButton.addEventListener("click", function(event) {
        event.preventDefault();

        var loginInput = document.getElementById("login").value;
        var passwordInput = document.getElementById("pass").value;

        var formData = new URLSearchParams();
        formData.append("login", loginInput);
        formData.append("password", passwordInput);

        fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData
        })
        .then(response => {
            if (response.ok) {
                window.location.href = '/send';
            } else {
                throw new Error('Login failed');
            }
        })
        .catch(error => console.error("Error:", error));
    });
});
