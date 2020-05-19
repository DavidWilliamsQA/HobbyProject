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
           button.id = playbookName;
           button.textContent = playbookName;

           div2.className = "tabcontent";
           div2.id = opt.id;

           div2.appendChild(h3);
           h3.textContent = playbookName;
           for(let plays of opt.plays){
               const p = document.createElement("p");
               const img = document.createElement("img");
               const deleteButton = document.createElement("button");

               deleteButton.textContent = "Delete";
               deleteButton.className = "btn btn-primary"
               p.textContent = plays.description;
               img.src = "images/Play" + plays.id + ".png";

               div2.appendChild(p);
               div2.appendChild(img);
               div2.appendChild(deleteButton);

               deleteButton.onclick = function deletePlay(evt) {
                   let playbookSelected = opt.id;
                   axios({
                       method: 'PUT',
                       url: "http://localhost:8181/deletePlayFromPlaybook/" + playbookSelected,
                       data: JSON.stringify(plays.id),
                       headers:{'Content-Type': 'application/json; charset=utf-8'}
                   })
                       .then( (response) => {
                           console.log(response.status);
                       })
                       .catch((error) => {
                           console.log(error)
                       });

               }
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

