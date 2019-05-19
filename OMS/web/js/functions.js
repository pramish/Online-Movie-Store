//Data

var Movies = [  
    {Id:4312, Title:"Boats", Genre:"Action",Description:"A movie about red space boats colluding to take over the Ucomm System", Stock:2, Price:18.99, ActiveStatus:true},
    {Id:10483, Title:"Cars", Genre:"Action", Description:"A movie about Cars and racing and modifications things", Stock:8, Price:19.99, ActiveStatus:true},
    {Id:1832, Title:"Cats", Genre:"Action", Description:"A movie about Cats and their outer worldly experiences with potatoes", Stock:0, Price:17.99, ActiveStatus:false},
    {Id:5932, Title:"Dogs", Genre:"Adventure", Description:"A movie about Dogs. Everyone loves dogs!", Stock:31, Price:8.99, ActiveStatus:true},
    {Id:4913, Title:"Trains", Genre:"Adventure", Description:"A very slow movie about Trains.", Stock:4, Price:19.99, ActiveStatus:true}
];

var Orders = [  
    {Id:123463, Title:"Boats", Description:"A movie about red space boats colluding to take over the Ucomm System", Stock:1, Price:18.99, Total:18.99, TimeStamp:"09/04/2019", ActiveStatus:true},
    {Id:123462, Title:"Cars", Description:"A movie about Cars and racing and modifications things", Stock:1, Price:19.99, Total:19.99, TimeStamp:"08/04/2019", ActiveStatus:true},
    {Id:123461, Title:"Cats", Description:"A movie about Cats and their outer worldly experiences with potatoes", Stock:2, Price:17.99, Total:35.98, TimeStamp:"07/04/2019", ActiveStatus:false},
    {Id:123460, Title:"Dogs", Description:"A movie about Dogs. Everyone loves dogs!", Stock:1, Price:8.99, Total:8.99, TimeStamp:"06/04/2019", ActiveStatus:false},
    {Id:123459, Title:"Trains", Description:"A very slow movie about Trains.", Stock:3, Price:19.99, Total:59.97, TimeStamp:"05/04/2019", ActiveStatus:false},
    {Id:123458, Title:"Cats", Description:"A movie about Cats and their outer worldly experiences with potatoes", Stock:1, Price:17.99, Total:17.99, TimeStamp:"04/04/2019", ActiveStatus:false},
    {Id:123457, Title:"Dogs", Description:"A movie about Dogs. Everyone loves dogs!", Stock:1, Price:8.99, Total:8.99, TimeStamp:"03/04/2019", ActiveStatus:false},
    {Id:123456, Title:"Trains", Description:"A very slow movie about Trains.", Stock:1, Price:19.99, Total:19.99, TimeStamp:"02/04/2019", ActiveStatus:false,}
];


// Cookie Functions
function SetCookie(cname, cvalue) {
    var d = new Date();
    d.setTime(d.getTime() + (100*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function GetCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
          c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
          return c.substring(name.length, c.length);
        }
    }
    return "";
}

// Security Functions
function IsLoggedIn(){
    return GetCookie("loggedIn") === "YES";
}

function LogIn(){
    SetCookie("loggedIn", "YES");
    window.location.href = "/";
}

function LogOut(){
    SetCookie("loggedIn", "");
    window.location.href = "/";
}

function IsStaff(){
    return GetCookie("loggedIn") === "YES";
}

