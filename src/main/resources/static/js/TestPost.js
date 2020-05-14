// const REQ = new XMLHttpRequest();

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

// function getTodos() {
//
//     REQ.onload = () => {
//         let data = `[{"id": "${document.getElementById("Playbook1").value}", "plays" }]`;
//         let obj2 = JSON.parse(data);
//         if (REQ.status === 200) {
//             //console.log(REQ);
//             console.log(REQ.response);
//             console.log(REQ.response.title);
//            // document.querySelector("#resp").outerHTML = REQ.response[0].description;
//            // document.createElement("option")
//         } else {
//             console.log(`Handle Error!`);
//         }
//     };
//
//     REQ.open("PUT", "http://localhost:8181/addPlaysToPlaybook/");
//     REQ.setRequestHeader("Content-Type", "Application/json");
//     REQ.setRequestHeader("Access-Control-Allow-Origin", "*");
//     REQ.responseType = "json";
//     REQ.send();
// }

let butt1 = document.querySelector("#butt1");
butt1.addEventListener("click", getTodos);