// Simulação de dados vindos de um banco de dados
const vendedorData = [
    { nome: 'Item 1', quantidade: 10, quantidadeAtual: 5 },
    { nome: 'Item 2', quantidade: 20, quantidadeAtual: 15 }
];

const compradorData = [
    { nome: 'Item A', quantidade: 3, descricao: 'Descrição A' },
    { nome: 'Item B', quantidade: 7, descricao: 'Descrição B' }
];

// Função para preencher a tabela de vendedor
function preencherTabelaVendedor() {
    const tbody = document.getElementById('vendedor-tbody');
    vendedorData.forEach(item => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${item.nome}</td>
            <td>${item.quantidade}</td>
            <td>${item.quantidadeAtual}</td>
        `;
        tbody.appendChild(tr);
    });
}

// Função para preencher a tabela de comprador
function preencherTabelaComprador() {
    const tbody = document.getElementById('comprador-tbody');
    compradorData.forEach(item => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${item.nome}</td>
            <td>${item.quantidade}</td>
            <td>${item.descricao}</td>
        `;
        tbody.appendChild(tr);
    });
}

// Chamar as funções para preencher as tabelas
preencherTabelaVendedor();
preencherTabelaComprador();
x