// Fetch posts from the API and display them
async function fetchPosts() {
    try {
        const response = await fetch('https://apis.scrimba.com/jsonplaceholder/posts');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const posts = await response.json();
        const postsContainer = document.getElementById('posts_container_fetch_api');
        postsContainer.innerHTML = ''; // Clear previous posts
        posts.forEach(post => {
            const postElement = document.createElement('div');
            postElement.className = 'border p-4 mb-4 bg-white rounded shadow';
            postElement.innerHTML = `
                <h2 class="text-xl font-bold">${post.title}</h2>
                <p>${post.body}</p>
            `;
            postsContainer.appendChild(postElement);
        });
    } catch (error) {
        console.error('Error fetching posts:', error);
    }
}
fetchPosts();
