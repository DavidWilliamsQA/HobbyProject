axios.get("http://localhost:8181/getAllPlaybooks").then(
    res => {
        const select = this.document.getElementById("playbook-list");
        const section = this.document.getElementById("playbookListSection");
        // section.className = "gallery";
        for (let opt of res.data) {
            const list = document.createElement("li");
            const aTag = document.createElement("a");

            const divTag1 = document.createElement("div");
            const divTag2 = document.createElement("div");
            const divTag3 = document.createElement("div");
            const pTag = document.createElement("p");

            aTag.textContent = opt.name;
            aTag.href = "#";

            // list.className = "nav-item";

            divTag3.appendChild(pTag);
            divTag2.appendChild(divTag3);
            divTag1.appendChild(divTag2);
            list.appendChild(aTag);
            select.appendChild(list);
            section.appendChild(select);
            section.appendChild(divTag1);
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