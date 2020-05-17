function addPlayToPlaybook (){
    console.log("the function is called")
    let e = document.getElementById("playbooks");
    let strUser = e.options[e.selectedIndex].value;
    console.log(strUser)

    let id = 1;

    axios({
        method: 'PUT',
        url: "http://localhost:8181/addPlaysToPlaybook/" + strUser,
        data: JSON.stringify(id),
        headers:{'Content-Type': 'application/json; charset=utf-8'}
    })
        .then( (response) => {
            console.log(response.status);
        })
        .catch((error) => {
            console.log(error)
        });

}

let butt1 = document.querySelector("#butt1");
butt1.addEventListener("click", addPlayToPlaybook);