fetch('https://apis.scrimba.com/jsonplaceholder/posts')
    .then(response => response.json())
    .then(data => {
        console.log(data); // 🔍 Check structure
        let posts = data.slice(0, 5);
        for (let post of posts) {
            console.log(post)
        }
        //     document.getElementById("posts_container").innerHTML += `
        //         <h3>${post.title}</h3>
        //         <h4>${post.body}</h4>
        //     `
        // }
    })
