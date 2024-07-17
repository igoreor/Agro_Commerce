document.addEventListener("DOMContentLoaded", function() {
    const menuButton = document.querySelector(".menu-button");
    const nav = document.querySelector("nav ul");
    const btnFechar = document.querySelector('.btn-fechar');
    const inputBuscar = document.querySelector('.input-buscar input');

    menuButton.addEventListener("click", function() {
        nav.classList.toggle("open");
    });

    btnFechar.addEventListener('click', () => {
        inputBuscar.value = '';
    });
});