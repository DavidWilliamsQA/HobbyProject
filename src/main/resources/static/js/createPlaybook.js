function createPlaybook(){
    let params = `{ "name":"${document.getElementById("playbookName").value}"}`;
    let obj = JSON.parse(params)

    axios.post("http://localhost:8181/createPlaybooks", obj);
    console.log(params);
}


let butt1 = document.querySelector("#submitButton");
butt1.addEventListener("click", createPlaybook);