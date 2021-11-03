const URL = "http://localhost:8081/"

// let buttonRow = document.getElementById("buttonRow");
let root = document.getElementById('root');
let allUsersButtonForm = document.getElementById("allUsersButtonForm");
let managerButtonsForm = document.getElementById("managerButtonsForm");

let userButton = document.getElementById('userButton');
let reimbButton = document.getElementById('reimbButton');
let addReimbButton = document.getElementById("addReimbButton");
let loginButton = document.getElementById('loginButton');
let getReimbIdButton = document.getElementById('getReimbIdButton');
let getReimbStatusButton = document.getElementById('getReimbStatusButton');

let getReimbOfAuthorButton = document.getElementById('getReimbOfAuthorButton');

let denyButton = document.getElementById('denyButton');
let approveButton = document.getElementById('approveButton');

userButton.innerText = "Get All Users";
reimbButton.innerText = "Show All Reimbursement Requests";
getReimbIdButton.innerText = "Find this ticket!";
getReimbStatusButton.innerText = "Find tickets of this status!";
approveButton.innerText = "Approve this ticket";
denyButton.innerText = "Deny this ticket";


function clearDiv(divId){
    document.getElementById(divId).innerText = '';
}

async function getUserByUsername(username){
    let response = await fetch(URL + "users/user/" + username, {credentials:"include"});
    if(response.status === 200){
        let data = await response.json(); 

        //saves user to sessionStorage
        sessionStorage.setItem('user', JSON.stringify(data));

    }else{
        console.log("Failed to find your user. ")
    }
}

async function loginToApp(){
    let user = {
        username:document.getElementById("username").value,
        password:document.getElementById("password").value
    }
    //saves userDTO to sessionStorage
    // sessionStorage.setItem('user', JSON.stringify(user));

    let response = await fetch(URL+"login", {
        method: "POST",
        body:JSON.stringify(user),
        credentials:"include"//this will save the cookie when we receive it. 
    });
    if(response.status === 200){
        document.getElementsByClassName("formClass")[0].innerHTML = '';
        await getUserByUsername(user.username);
        await setEnvironment();
    }else{
        let para = document.createElement("p");
        para.setAttribute("style", "color:red")
        para.innerText = "LOGIN FAILED"
        document.getElementsByClassName("formClass")[0].appendChild(para);
    }
}

async function setEnvironment(){
    clearDiv("root");
    let userParsed = JSON.parse(sessionStorage.getItem('user'));
    // console.log(typeof (userParsed.role.roleId));
    let roleId = userParsed.role.roleId;
    // document.getElementsByClassName("formClass")[0].innerHTML = "";
    if(roleId >= 1){
        root.appendChild(allUsersButtonForm);
    }
    if(roleId >= 2){
        // document.getElementsByClassName("formClass")[0].innerHTML = "";
        root.appendChild(managerButtonsForm);

    }else{
        clearDiv("managerButtonsForm");
    }
}

async function getUsers(){

    let userRoleId = JSON.parse(sessionStorage.getItem('user')).role.roleId;

    if(userRoleId>=2){
        let response = await fetch(URL + "users", {credentials:"include"});

        if(response.status === 200){
            let data = await response.json(); 
            populateUsersTable(data);
            // console.log(data);

        }
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
            if(cell!="role"){
                td.innerText = user[cell];
            }else if(user[cell]){//if null: false. else true. 
                td.innerText = `${user[cell].role}  `
            }
            row.appendChild(td);

        }
        tbody.appendChild(row);
    }
}

async function getAllReimbs(){

    let userRoleId = JSON.parse(sessionStorage.getItem('user')).role.roleId;

    if(userRoleId>=2){
        let response = await fetch(URL + "reimbs", {credentials:"include"});

        if(response.status===200){
            // console.log('about to start');
            let data = await response.json();
            // console.log(data);
    
            populateReimbTable(data);
    
            //parsing json takes time, need await. 
    
        }
    }
    else{
        console.log("Reimbursements not available");
    }
}

function populateReimbTable(data){
    let tbody = document.getElementById("reimbBody");

    tbody.innerHTML = "";
    // wipes out table before more data is displayed

    for(let reimb of data){
        let row = document.createElement("tr");//creates empty row
        for(let cell in reimb){             //create each cell
            let td = document.createElement("td"); 
            td.innerText = reimb[cell];
            row.appendChild(td);            //appends datacell to row
            if((cell=="resolver" ||cell=="author")&&reimb[cell]){
                td.innerText = reimb[cell].userId;
            }else if(cell=="status"&&reimb[cell]){//if null: false. else true. 
                td.innerText = `${reimb[cell].status}  `
            }else if(cell=="type"&&reimb[cell]){//if null: false. else true. 
                td.innerText = `${reimb[cell].type}  `
            }else if((cell=="submitted"||cell=="resolved")&&reimb[cell]){//if null: false. else true. 
                
                td.innerText = `${convertTimestamp(reimb[cell])}  `
            }else if(cell=="receipt"&&reimb[cell]){//if null: false. else true. 
                td.innerText = `${reimb[cell]}  `
            }else if(reimb[cell]){
                td.innerText = `${reimb[cell]}  `
            }
        }
        tbody.appendChild(row);     //appends the row after previous row in table
    }
}

function convertTimestamp(unix_timestamp){
    let date = new Date(unix_timestamp);

    return date;
}


async function getReimbByAuthor(){

    let author = JSON.parse(sessionStorage.user);
    let authorUsername = author.username;
    let response = await fetch(URL + "reimbs/reimb/author/" + authorUsername, {credentials:"include"});

    if(response.status===200){
        let data = await response.json();
        // console.log(data);
        populateReimbTable(data);
        // return data;
    }else{
        console.log("Reimbursements not available");
    }
}


async function getReimbByStatus(){

    let userRoleId = JSON.parse(sessionStorage.getItem('user')).role.roleId;

    if(userRoleId>=2){
        // let statusId = document.getElementById('findReimbStatus').value;
        let statusId = document.querySelector('input[name="statusRadio"]:checked').value;

        let response = await fetch(URL + "reimbs/reimb/"+statusId, {credentials:"include"});

        if(response.status===200){
            let data = await response.json();
            // console.log(data);
            populateReimbTable(data);
            // return data;
        }
    }else{
        console.log("Reimbursements not available");
    }
}

async function getReimbById(){

    let userRoleId = JSON.parse(sessionStorage.getItem('user')).role.roleId;

    if(userRoleId>=2){
        let reimbId = document.getElementById('reimbId').value;
        let response = await fetch(URL + "reimbs/" + reimbId, {credentials:"include"});

        if(response.status===200){
            let data = await response.json();
            // console.log(data);
            printOneReimb(data);
            // return data;
        }
    }else{
        console.log("Reimbursements not available");
    }
}

function printOneReimb(data){
    let tbody = document.getElementById("reimbBody");
        
    tbody.innerHTML = "";
        // wipes out table before more data is displayed    
        let row = document.createElement("tr");

        for(let cell in data){
            let td = document.createElement("td");
            td.innerText = data[cell];
            row.appendChild(td);
            if((cell=="resolver" ||cell=="author")&&data[cell]){
                td.innerText = data[cell].userId;
            }else if(cell=="status"&&data[cell]){//if null: false. else true. 
                td.innerText = `${data[cell].status}  `
            }else if(cell=="type"&&data[cell]){//if null: false. else true. 
                td.innerText = `${data[cell].type}  `
            }else if((cell=="submitted"||cell=="resolved")&&data[cell]){//if null: false. else true. 
                td.innerText = `${convertTimestamp(data[cell])}  `
            }else if(cell=="receipt"&&data[cell]){//if null: false. else true. 
                td.innerText = `${data[cell]}  `
            }else if(data[cell]){
                td.innerText = `${data[cell]}  `
            }
        }
        //parsing json takes time, need await. 
        tbody.appendChild(row);
}

function getNewReimb(){
    let newAmount = document.getElementById("amount").value;
    let newDescription = document.getElementById("description").value;
    let newAuthor = JSON.parse(sessionStorage.user);
    let newTypeId = document.getElementById("typeId").value;

    let reimb = {
        amount:newAmount, 
        description:newDescription,
        author: newAuthor,
        type: {
            typeId: newTypeId
            // type: "Other"
        }
    }

    return reimb;
}

// call_locker = () => { addReimb(); return false; }

async function addReimb(){
    let reimb = getNewReimb();
    let response = await fetch(URL + "reimbs", {
        method: 'POST',
        body: JSON.stringify(reimb),
        credentials:"include"
    });

    if(response.status===201){
        console.log("reimbursement created successfully. ");
    }else{
        console.log("Something went wrong creating your reimbursement. ");
    }
    
}



async function resolve(newStatusId){
    let newReimbId = document.getElementById('reimbId').value;
    // let newStatusId = document.getElementById('reimbStatus').value;
    let newResolver = JSON.parse(sessionStorage.user);
    
    let reimb = {
        reimbId: newReimbId,
        resolver: newResolver,
        status:{
            statusId: newStatusId
        }
    }

    let response = await fetch(URL + "reimbs/reimb", {
        method: "PUT",
        body:JSON.stringify(reimb),
        credentials:"include"
    });
    if(response.status===200){
        console.log("reimbursement status updated successfully. ");
    }else{
        console.log("Something went wrong updating your reimbursement. ");
    }
}

function approveReimb(){
    resolve(2);
}

function denyReimb(){
    resolve(3);
}