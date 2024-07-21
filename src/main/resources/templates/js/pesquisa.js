document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const query = urlParams.get('query');

    const searchResultsContainer = document.getElementById('search-results');

    if (query) {
        fetch(`http://localhost:8090/products/name/${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                if (data.length > 0) {
                    data.forEach(product => {
                        const productElement = document.createElement('div');
                        productElement.className = 'product-item';
                        productElement.innerHTML = `
                            <h3>${product.name}</h3>
                            <p>${product.description}</p>
                            <p>Preço: R$ ${product.value}</p>
                        `;
                        searchResultsContainer.appendChild(productElement);
                    });
                } else {
                    searchResultsContainer.innerHTML = '<p>Nenhum produto encontrado.</p>';
                }
            })
            .catch(error => {
                searchResultsContainer.innerHTML = '<p>Ocorreu um erro ao buscar os produtos.</p>';
                console.error('Erro:', error);
            });
    }
});
