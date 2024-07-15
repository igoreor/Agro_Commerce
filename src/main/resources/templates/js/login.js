function entrar() {
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    const data = {
        email: email,
        senha: senha
    };

    fetch('http://localhost:8090/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Email ou senha incorretos.');
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('msgError').style.display = 'none';


        window.location.href = '../home/home.html';
    })
    .catch(error => {
        console.error('Erro:', error);
        document.getElementById('msgError').innerText = 'Email ou senha incorretos.';
        document.getElementById('msgError').style.display = 'block';
    });

}
