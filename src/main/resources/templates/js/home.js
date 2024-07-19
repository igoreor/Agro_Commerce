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

    const cookies = document.cookie.split(';').reduce((acc, cookie) => {
        const [name, value] = cookie.split('=').map(c => c.trim());
        acc[name] = value;
        return acc;
    }, {});

    if (!cookies.token) {
        alert("Você precisa estar logado para acessar essa página");
        window.location.href = "../login/login.html";
    } else {
        const userLogado = JSON.parse(decodeURIComponent(cookies.userLogado));
        const logado = document.querySelector("#logado");
        logado.innerHTML = `Olá ${userLogado.userName}`;
    }

    document.querySelector('#sair').addEventListener('click', () => {
        document.cookie = 'token=; Max-Age=-99999999;';
        document.cookie = 'userLogado=; Max-Age=-99999999;';
        window.location.href = '../login/login.html';
    });
});
