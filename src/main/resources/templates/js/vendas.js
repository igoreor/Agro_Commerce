function previewImage(event) {
    const reader = new FileReader();
    reader.onload = function() {
        const output = document.getElementById('product-image-preview');
        output.src = reader.result;
    }
    if (event.target.files[0]) {
        reader.readAsDataURL(event.target.files[0]);
    }
}

document.getElementById('value').addEventListener('input', function (e) {
    const value = e.target.value;
    if (!/^\d*\.?\d*$/.test(value)) {
        e.target.value = value.slice(0, -1);
    }
});

function submitForm() {
    const form = document.getElementById('reservation-form');
    const formData = new FormData();

    formData.append('type', form.type.value);
    formData.append('name', form.name.value);
    formData.append('description', form.description.value);
    formData.append('value', form.value.value);
    formData.append('image', document.getElementById('image-upload').files[0]);

    const token = localStorage.getItem("token");

    fetch('http://localhost:8090/products/', {
        method: 'POST',
        headers: {
            Authorization: `Bearer ${token}`,
        },
        body: formData
    })
    .then(response => {
        if (response.ok) {
            alert('Produto cadastrado com sucesso!');
            response.json().then(data => {
                document.getElementById('product-image-preview').src = data.imageURL; // Atualize a pré-visualização da imagem com a URL da imagem retornada
            });
        } else {
            alert('Falha ao cadastrar produto.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Um erro ocorreu durante o cadastro do produto');
    });
}
