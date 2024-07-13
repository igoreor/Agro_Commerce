function cadastrar() {
    const nome = document.getElementById('nome').value;
    const usuario = document.getElementById('usuario').value;
    const senha = document.getElementById('senha').value;
    const confirmSenha = document.getElementById('confirmSenha').value;
  
    if (senha !== confirmSenha) {
      document.getElementById('msgError').innerText = 'As senhas não coincidem.';
      document.getElementById('msgError').style.display = 'block';
      document.getElementById('msgSuccess').style.display = 'none';
      return;
    }
  
    const data = {
      nome: nome,
      usuario: usuario,
      senha: senha
    };
  
    fetch('http://localhost:8080/users', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Erro ao cadastrar usuário.');
      }
      return response.json();
    })
    .then(data => {
      document.getElementById('msgSuccess').innerText = data;
      document.getElementById('msgSuccess').style.display = 'block';
      document.getElementById('msgError').style.display = 'none';
      limparCampos();
    })
    .catch(error => {
      console.error('Erro:', error);
      document.getElementById('msgError').innerText = 'Erro ao cadastrar usuário.';
      document.getElementById('msgError').style.display = 'block';
      document.getElementById('msgSuccess').style.display = 'none';
    });
  }
  
  function limparCampos() {
    document.getElementById('nome').value = '';
    document.getElementById('usuario').value = '';
    document.getElementById('senha').value = '';
    document.getElementById('confirmSenha').value = '';
  }
  