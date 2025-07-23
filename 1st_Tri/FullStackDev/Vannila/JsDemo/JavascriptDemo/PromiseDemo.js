new Promise((resolve, reject) =>{
    fetch('https://randomuser.me/api/')
        .then(response => response.json())
        .then(data => {
            resolve(data.results[0].name.first)})
            .catch(error => console.log(error+"Error"))             
        
})
.then(firstName => console.log(`${firstName}`))