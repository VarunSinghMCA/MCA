new Promise((resolve,reject) =>{
    fetch("https://dog.ceo/api/breeds/image/random")
    .then(response => response.json())
    .then(data => resolve(data))
    .catch(error =>reject(error))
})
.then(result => console.log(result))

