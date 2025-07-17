const coffeeList = document.getElementById('coffeeList');
const coffeeData = [];

async function fetchCoffeeData() {
    try {
        coffeeData.length = 0;
        const response = await fetch('https://api.sampleapis.com/coffee/hot');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const coffee = await response.json();
        coffeeData.push(...coffee);
        console.log('Coffee data fetched successfully>>>>>>>\n', coffeeData);
        displayCoffeeData(coffeeData);

    } catch (error) {
        console.error('Error fetching coffee data:', error);
    }
    
}
function displayCoffeeData(coffees) {
    coffeeList.innerHTML = '';
    coffees.forEach(coffee => {

        const coffeeItem = document.createElement('div');
        coffeeItem.className = 'coffee-item p-4 bg-white rounded shadow mb-4';

        coffeeItem.innerHTML = `
            <div class="mb-2 h-auto w-full overflow-hidden relative">
                <img src="${coffee.image}" alt="${coffee.title}" class="w-full h-full object-cover rounded">
            </div>    
            <h2 class="text-xl font-bold">${coffee.title}</h2>
            <p>${coffee.description}</p>
            <p class=""><b>Ingredients:</b></p>
            <p class="text-gray-600">
                ${
                    coffee.ingredients.map(ingredient => `${ingredient}`).join(', ')
                }
            </p>
            <button class="w-full mt-2 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700">Order Now</button>
        `;
        coffeeList.appendChild(coffeeItem);
    });
}

fetchCoffeeData();

document.getElementById('search-button').addEventListener('click', () => {
    const searchInput = document.getElementById('search-input').value.toLowerCase();
    const filteredCoffees = coffeeData.filter(coffee => 
        coffee.title.toLowerCase().includes(searchInput) || 
        coffee.description.toLowerCase().includes(searchInput)
    );
    displayCoffeeData(filteredCoffees);
});

const userLocation = document.getElementById('location');

function geoLocation() {
    if (navigator.geolocation) {
        try {
            navigator.geolocation.getCurrentPosition(position => {
                const lat = position.coords.latitude;
                const lon = position.coords.longitude;
                if (lat && lon){
                    userLocation.textContent = `Your Location: Latitude ${lat}, Longitude ${lon}`;
                }
            }, error => {
                console.error('Error getting location:', error);
                userLocation.textContent = 'Unable to retrieve your location.';
            });
        } catch (error) {
            console.error('Geolocation error:', error);
            userLocation.textContent = 'An error occurred while retrieving your location.';
        }
    }
    else {
        userLocation.textContent = 'Geolocation is not supported by this browser.';
    }
}

geoLocation();

// form submit / setting data to loaclhost 
const feedbackForm = document.getElementById('feedbackForm');

feedbackForm.addEventListener('submit', (e) => {
    e.preventDefault();
    
    const name = document.getElementById('name').value;
    const coffee = document.getElementById('coffee').value;

    if (name && coffee){
        localStorage.setItem('name', JSON.stringify(name));
        localStorage.setItem('coffee', JSON.stringify(coffee));
    }
});


function isPrevUser(){
    const name = localStorage.getItem('name');

    const coffee = localStorage.getItem('coffee');
    const header = document.getElementById('header');
    console.log(name , "\n", coffee);

    const pName = name ? JSON.parse(name) : '';

    if (name && coffee){
        header.innerHTML = `
            <h1 class="text-3xl font-bold mb-4 text-center">Welcome Back ${pName} to Coffee Hub</h1>
            <h2 class="text-2xl font-bold mb-4 text-blue-700">Your favourite Coffee is - ${coffee}</h2>
            <p class="text-gray-600 mb-4">Explore our popular hot coffee drinks.</p>
        `
    } else {
        header.innerHTML =
        `
            <h1 class="text-3xl font-bold mb-4 text-center">Welcome to Coffee Hub</h1>
            <h2 class="text-2xl font-bold mb-4 text-blue-700">Coffee Hub</h2>
            <p class="text-gray-600 mb-4">Explore our popular hot coffee drinks.</p>
        `
    }
}

isPrevUser();
