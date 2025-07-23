async function getRandomName() {
   const response = await fetch('https://randomuser.me/api/')
   const data = await response.json()
   const firstName = data.results[0].name.first
   console.log(firstName);
   
    
}
getRandomName()