console.log("Login")


let login_input = document.getElementById("login");
let pass_input = document.getElementById("pass");

let login_button = document.getElementById("login_button");


login_button.addEventListener("click", function (){
    let login = login_input.value;
    let password = pass_input.value;

    console.log(login + " " + password);
});
