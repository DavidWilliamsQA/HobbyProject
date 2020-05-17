axios.get("http://localhost:8181/getAllPlaybooks").then(
    res => {
        const div = this.document.getElementById("tab");
        const first = this.document.getElementById("firstdiv");
        console.log(res.data);
       for(let opt of res.data){
           const button = document.createElement("button");
           const div2 = document.createElement("div");
           const h3 = document.createElement("h3");

           let playbookName = opt.name;

           button.className = "tablinks";
           button.textContent = playbookName;

           div2.className = "tabcontent";
           div2.id = opt.id;

           div2.appendChild(h3);
           h3.textContent = playbookName;
           for(let plays of opt.plays){
               const p = document.createElement("p");
               const img = document.createElement("img");
                // IF YOU WANT TO IMPLEMENT THE DELETE THEN YOU SHOULD
               //ADD THE DELETE BUTTONS HERE AS WELL SO THAT THEY CAN
               //APPEAR WITH EACH PICTURE AND YOU CAN STORE THE CORRESPONDING
               //BUTTON VALUES IN IT THAT WILL CORRESPOND TO THE PLAY THAT
               //IS TO BE DELETED
               p.textContent = plays.description;
               img.src = "images/Play" + plays.id + ".png";
               div2.appendChild(p);
               div2.appendChild(img);
           }

           div.appendChild(button);

           first.appendChild(div2);
           button.onclick = function openCity(evt){
               var i, tabcontent, tablinks;
               tabcontent = document.getElementsByClassName("tabcontent");
               for (i = 0; i < tabcontent.length; i++) {
                   tabcontent[i].style.display = "none";
               }
               tablinks = document.getElementsByClassName("tablinks");
               for (i = 0; i < tablinks.length; i++) {
                   tablinks[i].className = tablinks[i].className.replace(" active", "");
               }
               document.getElementById(opt.id).style.display = "block";
               evt.currentTarget.className += " active";
           };
        }
    }
    );

function createPlaybook(){
    let params = `{ "name":"${document.getElementById("playbookName").value}"}`;
    let obj = JSON.parse(params)

    axios.post("http://localhost:8181/createPlaybooks", obj);
    console.log(params);
}


let butt1 = document.querySelector("#submitButton");
butt1.addEventListener("click", createPlaybook);