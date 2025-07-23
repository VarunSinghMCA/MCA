
document.getElementById("get_activity").addEventListener("click", function(){
fetch("https://apis.scrimba.com/bored/api/activity")
    .then(response => response.json())
    .then(data => {
        console.log(data)
        document.getElementById("activity_container").textContent= data.activity
          
    })

    })