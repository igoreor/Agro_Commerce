function validarNumeros(input) {
  input.value = input.value.replace(/[^0-9]/g, '');
}

document.getElementById('telefone').addEventListener('input', function() {
  validarNumeros(this);
});

document.getElementById('numero').addEventListener('input', function() {
  validarNumeros(this);
});

function cadastrarVenda() {
  const nomeCompleto = document.getElementById('nomeCompleto').value;
  const telefone = document.getElementById('telefone').value;
  const estado = document.getElementById('estado').value;
  const cidade = document.getElementById('cidade').value;
  const bairro = document.getElementById('bairro').value;
  const rua = document.getElementById('rua').value;
  const numero = document.getElementById('numero').value;

  if (!nomeCompleto || !telefone || !estado || !cidade || !bairro || !rua || !numero) {
    document.getElementById('msgError').innerText = 'Todos os campos são obrigatórios.';
    document.getElementById('msgError').style.display = 'block';
    document.getElementById('msgSuccess').style.display = 'none';
    return;
  }

  const data = {
    nomeCompleto: nomeCompleto,
    telefone: telefone,
    endereco: {
      estado: estado,
      cidade: cidade,
      bairro: bairro,
      rua: rua,
      numero: numero
    }
  };

  const token = localStorage.getItem('authToken'); // Obtém o token armazenado

  fetch('http://localhost:8090/agro-commerce/venda', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` // Inclui o token no cabeçalho de autorização
    },
    body: JSON.stringify(data)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Erro ao cadastrar venda.');
    }
    return response.json();
  })
  .then(data => {
    document.getElementById('msgSuccess').innerText = 'Informações cadastradas com sucesso!';
    document.getElementById('msgSuccess').style.display = 'block';
    document.getElementById('msgError').style.display = 'none';
  })
  .catch(error => {
    console.error('Erro:', error);
    document.getElementById('msgError').innerText = 'Erro ao cadastrar venda.';
    document.getElementById('msgError').style.display = 'block';
    document.getElementById('msgSuccess').style.display = 'none';
  });
}
