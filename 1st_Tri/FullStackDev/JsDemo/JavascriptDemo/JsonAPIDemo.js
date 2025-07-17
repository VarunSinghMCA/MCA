let postsArray = []
function getPosts() {
//const container = document.getElementById("posts_container");
let html=""
        for(let post of postsArray ){
                       
           html +=     `
        <h2 class="mx-2 font-mono text-3xl font-bold">${post.title}</h2>
        <br />
        <h3 class=" mx-3 font-serif text-2xl">${post.body} </h3>
        <br /> 
        <hr />  <br />      
        `
        }
        document.getElementById("posts_container").innerHTML = html
}
fetch("https://apis.scrimba.com/jsonplaceholder/posts")
    .then(response => response.json())
    .then(data => {
        postsArray= data.slice(0,5)
         getPosts()     
        
    })
    

    document.getElementById("blog").addEventListener("submit", function(e){
        e.preventDefault();
        const title=document.getElementById("title").value
        const body=document.getElementById("description").value
        const data = {
            title:title,
            body:body
        }
        console.log(data)
        fetch("https://apis.scrimba.com/jsonplaceholder/posts",{
            method:"POST",
            body:JSON.stringify(data),
            headers:{
                "Content-Type":"application/json"
            }
        }
    )
    .then(response => response.json())
    .then(post => {
        postsArray.unshift(post)
        getPosts()
    

    }) 
})

