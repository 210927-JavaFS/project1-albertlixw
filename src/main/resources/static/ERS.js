const URL = "http://localhost:8081/"

let buttonRow = document.getElementById("buttonRow");
let userButton = document.getElementById('userButton');
let homeButton = document.getElementById('homeButton');
let addHomeButton = document.getElementById("addHomeButton");
let loginButton = document.getElementById('loginButton');

userButton.onclick = getUsers;
homeButton.onclick = getHomes;
addHomeButton.onclick = addHome;
loginButton.onclick = loginToApp;

userButton.innerText = "Get All Users";
homeButton.innerText = "See Homes";

async function loginToApp(){
    let user = {
        username:document.getElementById("username").value,
        password:document.getElementById("password").value
    }
    let response = await fetch(URL+"login", {
        method: "POST",
        body:JSON.stringify(user),
        credentials:"include"//this will save the cookie when we receive it. 
    
    });
    if(response.status === 200){
        document.getElementsByClassName("formClass")[0].innerHTML = '';
        buttonRow.appendChild(userButton);
        buttonRow.appendChild(homeButton);
    }else{
        let para = document.createElement("p");
        para.setAttribute("style", "color:red")
        para.innerText = "LOGIN FAILED"
        document.getElementsByClassName("formClass")[0].appendChild(para);
    }

}

async function getUsers(){
    let response = await fetch(URL + "users", {credentials:"include"});

    if(response.status === 200){
        let data = await response.json(); 
        populateUsersTable(data);

    }else{
        console.log("The users are too busy saving the planet to respond. ")
    }
}

function populateUsersTable(data){
    let tbody = document.getElementById("userBody");
    tbody.innerHTML = "";
    // wipes out table before more data is displayed

    for(let user of data){
        let row = document.createElement("tr");
        
        for(let cell in user){
            let td = document.createElement("td");
            if(cell!="home"){
                td.innerText = user[cell];
            }else if(user[cell]){//if null: false. else true. 
                td.innerText = `${user[cell].name}: ${user[cell].streetNumber} ${user[cell].streetName} ${user[cell].city} ${user[cell].region} ${user[cell].zip} ${user[cell].country} `
            }
            row.appendChild(td);

        }
        tbody.appendChild(row);
    }
}

async function getHomes(){
    let response = await fetch(URL + "homes", {credentials:"include"});
    console.log('after fetch');

    if(response.status===200){
        console.log('about to start');
        let data = await response.json();
        console.log(data);

        populateHomeTable(data);

        //parsing json takes time, need await. 

    }else{
        console.log("homes not available");
    }
}

function populateHomeTable(data){
    let tbody = document.getElementById("homeBody");

    tbody.innerHTML= "";

    for(let home of data){
        let row = document.createElement("tr");
        for(let cell in home){
            let td = document.createElement("td");
            td.innerText = home[cell];
            row.appendChild(td);
        }
        tbody.appendChild(row);
    }
}


function getNewHome(){
    let newName = document.getElementById("homeName").value;
    let newStreetNum = document.getElementById("homeStreetNum").value;
    let newStreetName = document.getElementById("homeStreetName").value;
    let newCity = document.getElementById("homeCity").value;
    let newRegion = document.getElementById("homeRegion").value;
    let newZip = document.getElementById("homeZip").value;
    let newCountry = document.getElementById("homeCountry").value;

    let home = {
        name:newName, 
        streetNumber:newStreetNum,
        streetName: newStreetName,
        city: newCity,
        zip: newZip,
        region: newRegion,
        country: newCountry
    }

    return home;
}

async function addHome(){
    let home = await getNewHome();
    let response = await fetch(URL + "homes", {
        method: 'POST',
        body: JSON.stringify(home),
        credentials:"include"
    });

    if(response.status===201){
        console.log("Home created successfully. ");
    }else{
        console.log("Something went wrong creating your home. ");
    }

}