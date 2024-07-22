async function fetchProductDetails(productId) {
    try {
        const response = await fetch(http://localhost:8090/products/${productId});
        if (!response.ok) {
            throw new Error(Erro: ${response.status} ${response.statusText});
        }
        const product = await response.json();
        document.getElementById('product-name').innerText = product.name;
        document.getElementById('product-description').innerText = product.description;
        document.getElementById('product-value').innerText = Preço unitário: R$ ${product.value};
        document.getElementById('total-value').innerText = Valor total: R$ ${product.value};

        document.getElementById('quantity').addEventListener('input', () => {
            const quantity = document.getElementById('quantity').value;
            const totalValue = quantity * product.value;
            document.getElementById('total-value').innerText = Valor total: R$ ${totalValue};
        });

    } catch (error) {
        console.error('Erro ao buscar detalhes do produto:', error);
    }
}

async function makeReservation() {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('productId');
    const quantity = document.getElementById('quantity').value;

    const reservation = {
        productId: productId,
        quantity: quantity
    };

    try {
        const response = await fetch('http://localhost:8090/reservations/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reservation)
        });

        if (response.ok) {
            alert('Reserva confirmada!');
            window.location.href = 'index.html';
        } else {
            alert('Erro ao realizar a reserva. Tente novamente.');
        }
    } catch (error) {
        console.error('Erro ao fazer a reserva:', error);
    }
}

window.onload = () => {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('productId');
    fetchProductDetails(productId);
};