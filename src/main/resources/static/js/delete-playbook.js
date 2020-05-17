axios.get("http://localhost:8181/getAllPlaybooks").then(
    res => {
        const select = this.document.getElementById("playbooks");
        for (let opt of res.data) {
            const option = document.createElement("option");
            option.value = opt.id;
            option.textContent = opt.name;
            select.appendChild(option);
        }
    }
);

function deletePlaybook() {
    let e = document.getElementById("playbooks");
    let playbook = e.options[e.selectedIndex].value;
    let res = axios.delete("http://localhost:8181/deletePlaybook/" + playbook);
}

let deleteButton = document.querySelector("#deletePlaybook");
deleteButton.addEventListener("click", deletePlaybook);