function fetchData() {
    fetch(
        'https://apis.scrimba.com/jsonplaceholder/posts',
        {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: null // No body needed for GET request
        }
    ).then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    }).then(data => {
        const topFivePosts = data.slice(0, 5);
        const postElement = document.getElementById('data');
        postElement.innerHTML = ""; // Clear previous content
        topFivePosts.forEach(post => {
            postElement.innerHTML += `
                <div class="mb-6 p-4 rounded-lg bg-blue-50 shadow">
                    <h2 class="text-xl font-semibold text-blue-800 mb-2">${post.title}</h2>
                    <p class="text-gray-700">${post.body}</p>
                </div>
            `;
        });
        console.log('Top 5 posts:', topFivePosts);

    }).catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
}

const btn = document.getElementById('btn');
btn.addEventListener('click', fetchData);








