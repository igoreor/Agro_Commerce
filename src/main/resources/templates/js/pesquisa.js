document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const query = urlParams.get('query');

    const searchResultsContainer = document.getElementById('search-results');
    const searchForm = document.getElementById('search-form');
    const searchInput = document.getElementById('search-input');
    const clearButton = document.querySelector('.btn-fechar');
    const lupaBuscar = document.querySelector('.lupa-buscar');

    const performSearch = (query) => {
        fetch(`http://localhost:8090/products/name/${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                searchResultsContainer.innerHTML = '';
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
                searchResultsContainer.innerHTML = '<p>Nenhum produto encontrado.</p>';
                console.error('Erro:', error);
            });
    };

    if (query) {
        performSearch(query);
    }

    searchForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const query = searchInput.value.trim();
        if (query) {
            window.location.href = `../pesquisa/pesquisa.html?query=${encodeURIComponent(query)}`;
        }
    });

    lupaBuscar.addEventListener('click', function() {
        const query = searchInput.value.trim();
        if (query) {
            window.location.href = `../pesquisa/pesquisa.html?query=${encodeURIComponent(query)}`;
        }
    });

    clearButton.addEventListener('click', function() {
        searchInput.value = '';
    });

});
