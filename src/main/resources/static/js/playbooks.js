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



let butt1 = document.querySelector("#butt1");
butt1.addEventListener("click", getTodos);