function entrar() {
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    const data = {
        email: email,
        password: senha
    };

    fetch('http://localhost:8090/auth/login', {
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


        localStorage.setItem('token', data.token);
        localStorage.setItem('userLogado', JSON.stringify(data.user));


        window.location.href = '../home/index.html';
    })
    .catch(error => {
        console.error('Erro:', error);
        document.getElementById('msgError').innerText = 'Email ou senha incorretos.';
        document.getElementById('msgError').style.display = 'block';
    });
}

function togglePasswordVisibility(passwordFieldId, toggleIcon) {
    const passwordField = document.getElementById(passwordFieldId);
    if (passwordField.type === 'password') {
        passwordField.type = 'text';
        toggleIcon.classList.remove('fa-eye');
        toggleIcon.classList.add('fa-eye-slash');
    } else {
        passwordField.type = 'password';
        toggleIcon.classList.remove('fa-eye-slash');
        toggleIcon.classList.add('fa-eye');
    }
}
