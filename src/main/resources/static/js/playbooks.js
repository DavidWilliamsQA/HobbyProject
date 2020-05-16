axios.get("http://localhost:8181/getAllPlaybooks").then(
    res => {
        const select = this.document.getElementById("playbook-list");
        for (let opt of res.data) {
            const list = document.createElement("li");
            const aTag = document.createElement("a");
            aTag.textContent = opt.name;
            aTag.href = "#";
            // list.className = "nav-item";
            list.appendChild(aTag);
            select.appendChild(list)
        }
    }
    );

function createPlaybook(){
    let params = `{ "name":"${document.getElementById("playbookName").value}"}`;
    let obj = JSON.parse(params)
// let params = {
//     "name":"test-playbook2"
// }
    axios.post("http://localhost:8181/createPlaybooks", obj);
    console.log(params);
}

let butt1 = document.querySelector("#submitButton");
butt1.addEventListener("click", createPlaybook);