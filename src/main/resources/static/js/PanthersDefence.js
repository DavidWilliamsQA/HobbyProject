
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

axios.get("http://localhost:8181/getAllPlaybooks").then(
    res => {
        const select = this.document.getElementById("playbooks2");
        for (let opt of res.data) {
            const option = document.createElement("option");
            option.value = opt.id;
            option.textContent = opt.name;
            select.appendChild(option);
        }
    }
);

axios.get("http://localhost:8181/getAllPlaybooks").then(
    res => {
        const select = this.document.getElementById("playbooks3");
        for (let opt of res.data) {
            const option = document.createElement("option");
            option.value = opt.id;
            option.textContent = opt.name;
            select.appendChild(option);
        }
    }
);
axios.get("http://localhost:8181/getAllPlaybooks").then(
    res => {
        const select = this.document.getElementById("playbooks4");
        for (let opt of res.data) {
            const option = document.createElement("option");
            option.value = opt.id;
            option.textContent = opt.name;
            select.appendChild(option);
        }
    }
);


let butt1 = document.querySelector("#butt1");
butt1.addEventListener("click", getTodos);