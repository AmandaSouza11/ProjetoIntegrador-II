document.getElementById('cadastro').onclick = function() {
    window.location.href = '../cadastro/cadastro_paciente.html'; 
};

document.getElementById('login').onclick = function() {
    const form = document.getElementById('login-form');
    form.style.display = form.style.display === 'none' ? 'block' : 'none';
};

const emailInput = document.querySelector(".email");
const senhaInput = document.querySelector(".senha");

function logar() {
    const email = emailInput.value;
    const senha = senhaInput.value;

    fetch("http://localhost:8080/login", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            email: email,
            senha: senha
        })
    })
    .then(response => response.text()) 
    .then(role => {
        if (role === 'PACIENTE') {
            window.location.href = "../tela_inicial/tela_paciente.html";
        } else if (role === 'MEDICO') {
            window.location.href = "../tela_inicial/tela_medico.html";
        } else {
            alert("Credenciais inválidas. Por favor, tente novamente.");
        }
    })
    .catch(error => {
        console.error('Erro ao realizar login:', error);
        alert("Ocorreu um erro ao tentar realizar o login. Por favor, tente novamente.");
    });
}

document.getElementById("login-form").addEventListener("submit", function(event) {
    event.preventDefault();
    logar();
});
