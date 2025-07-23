fetch("https://apis.scrimba.com/jsonplaceholder/todos",
    {
        method:"POST",
        body:JSON.stringify(
        {
            title:"Buy Milk",
            completed:true
        }),
        headers:{
            "Content-Type":"application/json"
        }
    })
    .then(response => response.json())
    .then(data => console.log(data))