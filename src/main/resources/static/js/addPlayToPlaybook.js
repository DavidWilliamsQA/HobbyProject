function addPlayToPlaybook (buttId){

let e;
    if (buttId == "1" || buttId == "5" ||buttId == "9" ||buttId == "13" ){
        e = document.getElementById("playbooks");
    }
    else if (buttId == "2" || buttId == "6" ||buttId == "10" ||buttId == "14" ){
        e = document.getElementById("playbooks2");
    }
    else if (buttId == "3" || buttId == "7" ||buttId == "11" ||buttId == "15" ){
        e = document.getElementById("playbooks3");
    }
    else if (buttId == "4" || buttId == "8" ||buttId == "12" ||buttId == "16" ){
        e = document.getElementById("playbooks4");
    }

    console.log("the button pressed is" + buttId);

    let playbookSelected = e.options[e.selectedIndex].value;
    console.log("the playbook selected has the playbook id of " + playbookSelected)

    axios({
        method: 'PUT',
        url: "http://localhost:8181/addPlaysToPlaybook/" + playbookSelected,
        data: JSON.stringify(buttId),
        headers:{'Content-Type': 'application/json; charset=utf-8'}
    })
        .then( (response) => {
            console.log(response.status);
        })
        .catch((error) => {
            console.log(error)
        });

}

