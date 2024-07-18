document.addEventListener("DOMContentLoaded", function() {
    const menuButton = document.querySelector(".menu-button");
    const nav = document.querySelector("nav ul");
    const btnFechar = document.querySelector('.btn-fechar');
    const inputBuscar = document.querySelector('.input-buscar input');
    const advertiseButton = document.querySelector('.advertise-button'); 

    menuButton.addEventListener("click", function() {
        nav.classList.toggle("open");
    });

    btnFechar.addEventListener('click', () => {
        inputBuscar.value = '';
    });

    advertiseButton.addEventListener('click', function() {
        window.location.href = '../vendas/vendas.html'; 
    });
});
