const searchbox = document.getElementById("searchbox");
const categoryFilter = document.getElementById("categoryFilter");
const priceSort = document.getElementById("priceSort");
const productList = document.getElementById("productList");

let products = [];

// Debounce function
function debounce(func, delay) {
  let timeoutId;
  return function (...args) {
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => func.apply(this, args), delay);
  };
}

// Fetch products
async function fetchProducts() {
  try {
    const response = await fetch("https://dummyjson.com/products");
    const data = await response.json();
    products = data.products;
    populateCategoryFilter(products);
    displayProducts(products);
  } catch (err) {
    console.error("Error fetching products:", err);
    productList.innerHTML = `<p class="text-red-500 col-span-full">Failed to load products.</p>`;
  }
}
// Populate category filter
function populateCategoryFilter(products) {
  let categories = [];

  // Collect unique categories manually
  products.forEach(product => {
    if (!categories.includes(product.category)) {
      categories.push(product.category);
    }
  });

  // Add each category to the dropdown
  categories.forEach(category => {
    let option = document.createElement("option");
    option.value = category;
    option.textContent = category;
    categoryFilter.appendChild(option);
  });
}


// Display products
function displayProducts(productArray) {
  productList.innerHTML = "";

  if (productArray.length === 0) {
    productList.innerHTML = `<p class="text-center col-span-full">No products found.</p>`;
    return;
  }

  productArray.forEach(product => {
    const card = document.createElement("div");
    card.className = "bg-white p-4 rounded shadow hover:shadow-md transition";

    const tags = product.tags
      .map(tag => `<span class="bg-gray-200 text-xs font-semibold text-gray-800 px-2 py-1 mr-2 rounded">${tag}</span>`)
      .join("");

    card.innerHTML = `
      <img src="${product.thumbnail}" alt="${product.title}" class="h-40 w-full object-cover rounded mb-3" />
      <h2 class="text-lg font-semibold mb-1">${product.title}</h2>
      <p class="text-gray-600 text-sm mb-1">${product.category}</p>
      <p class="text-green-600 font-bold mb-2">$${product.price}</p>
      <div class="flex flex-wrap gap-1">${tags}</div>
    `;

    productList.appendChild(card);
  });
}

// Filter and Sort
function filterProducts() {
  const searchTerm = searchbox.value.toLowerCase();
  const selectedCategory = categoryFilter.value;
  const selectedSort = priceSort.value;

  let filtered = products.filter(product => {
    const matchesTitle = product.title.toLowerCase().includes(searchTerm);
    const matchesCategory = selectedCategory ? product.category === selectedCategory : true;
    return matchesTitle && matchesCategory;
  });

  if (selectedSort === "lowToHigh") {
    filtered.sort((a, b) => a.price - b.price);
  } else if (selectedSort === "highToLow") {
    filtered.sort((a, b) => b.price - a.price);
  }

  displayProducts(filtered);
}

// Event Listeners
searchbox.addEventListener("input", debounce(filterProducts, 500));
categoryFilter.addEventListener("change", filterProducts);
priceSort.addEventListener("change", filterProducts);

// Fetch products initially
fetchProducts();
