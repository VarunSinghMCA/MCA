function getRandomUser(printUser,errorUser){
   fetch('https://randomuser.me/api/')
   .then(response => response.json())
   .then(data => printUser(data.results[0].name.first))
   .catch(error => errorUser(error))
}

function printUser(user) {
 console.log(`Welcome ${user}`);
 
 
}


function errorUser(error) {
    console.log("Error:" +error)

}
getRandomUser(printUser, errorUser)