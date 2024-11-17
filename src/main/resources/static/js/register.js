console.log("Register")

let login_input = document.getElementById("login");
let email_input = document.getElementById("email");
let pass1_input = document.getElementById("pass1");
let pass2_input = document.getElementById("pass2");

let register_button = document.getElementById("register_button");

register_button.addEventListener("click", function (){
    let login = login_input.value;
    let email = email_input.value;
    let pass1 = pass1_input.value;
    let pass2 = pass2_input.value;


    if(!isEmailValid(email)){
        alert("Write current email form!");
        return;
    }

    if(pass1 === pass2){
    } else {
        alert("Passwords are the difference! Try one more.");
        return;
    }

    fetch("/register?email=" + email + "&login=" + login + "&password=" + pass1,{
        method: "POST",
    }).then(responce => {
        if(!responce.ok){
            throw new Error("Network response was not ok " + response.statusText)
        }
        return responce.text();
    }).then(data => {
        alert(data);
    }).catch(error => {
        alert(error.message);
    });

});

function isEmailValid(email){
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}