async function fetchProducts() {
    try {
        const response = await fetch('http://localhost:8090/products/');
        if (!response.ok) {
            throw new Error(`Erro: ${response.status} ${response.statusText}`);
        }
        const products = await response.json();
        const container = document.getElementById('products-container');
        container.innerHTML = '';
        products.forEach(product => {
            const card = document.createElement('div');
            card.className = 'card';
            card.innerHTML = `
                <img src="${product.image}" alt="${product.name}">
                <div class="info">
                    <h1>${product.name}</h1>
                    <h2>${product.description}</h2>
                    <span>R$ ${product.value}</span>
                    <button onclick="redirectToReservation(${product.id})">Faça sua reserva</button>
                </div>
            `;
            container.appendChild(card);
        });
    } catch (error) {
        const container = document.getElementById('products-container');
        container.innerHTML = '<div class="error-message">Não foi possível carregar os produtos. Tente novamente mais tarde.</div>';
        console.error('Erro ao buscar produtos:', error);
    }
}

function redirectToReservation(productId) {
    window.location.href = `../reserva/reserva.html?productId=${productId}`;
}

window.onload = fetchProducts;
