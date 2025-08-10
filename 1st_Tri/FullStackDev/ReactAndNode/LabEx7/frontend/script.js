const menuContainer = document.getElementById("menuContainer");
const filterBtn = document.getElementById("filterBtn");
const itemInput = document.getElementById("itemInput");
const priceInput = document.getElementById("priceInput");
const categorySelect = document.getElementById("categorySelect");

async function fetchData(url) {
    const res = await fetch(url);
    const data = await res.json();
    displayData(data);
}

function displayData(data) {
    menuContainer.innerHTML = "";
    if (data.length === 0) {
        menuContainer.innerHTML = `<p class="text-gray-500">No items found</p>`;
        return;
    }
    data.forEach(item => {
        const card = document.createElement("div");
        card.className = "bg-white border rounded-lg shadow p-4";
        card.innerHTML = `
            <h2 class="text-xl font-semibold">${item.item}</h2>
            <p class="text-gray-600">${item.name}</p>
            <p class="text-green-600 font-bold">₹${item.price}</p>
            <span class="text-sm text-gray-500">${item.category}</span>
        `;
        menuContainer.appendChild(card);
    });
}

// Initial load
fetchData("http://localhost:3000/api");

// Filter button click
filterBtn.addEventListener("click", () => {
    let url = "http://localhost:3000/api/filter?";
    if (itemInput.value) url += `item=${encodeURIComponent(itemInput.value)}&`;
    if (priceInput.value) url += `price=${priceInput.value}&`;
    if (categorySelect.value) url += `category=${categorySelect.value}&`;
    fetchData(url);
});
