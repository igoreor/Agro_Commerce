// scripts.js
document.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:3000/configuracoes')
        .then(response => response.json())
        .then(data => {
            document.getElementById('nome').value = data.nome;
            document.getElementById('email').value = data.email;
            document.getElementById('telefone').value = data.telefone;
            document.getElementById('estado').value = data.estado;
            document.getElementById('cidade').value = data.cidade;
            document.getElementById('bairro').value = data.bairro;
            document.getElementById('rua').value = data.rua;
            document.getElementById('numero').value = data.nume     ro;
        })
        .catch(error => console.error('Erro ao buscar dados:', error));
});
