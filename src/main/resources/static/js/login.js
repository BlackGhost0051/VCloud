console.log("Login")


let login_input = document.getElementById("login");
let pass_input = document.getElementById("pass");

let login_button = document.getElementById("login_button");


login_button.addEventListener("click", function (){
    let login = login_input.value;
    let password = pass_input.value;

    fetch("/login?login=" + login + "&password=" + password,{
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
