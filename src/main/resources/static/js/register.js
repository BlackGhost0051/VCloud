console.log("Register")

let login_input = document.getElementById("login");
let pass1_input = document.getElementById("pass1");
let pass2_input = document.getElementById("pass2");

let register_button = document.getElementById("register_button");

register_button.addEventListener("click", function (){
    let login = login_input.value;
    let pass1 = pass1_input.value;
    let pass2 = pass2_input.value;

    console.log(login + " " + pass1 + " " + pass2);

    if(pass1 === pass2){

    } else {
        alert("Passwords are the difference! Try one more.");
    }
});