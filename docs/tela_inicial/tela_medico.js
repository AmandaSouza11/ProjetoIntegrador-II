function loadProfile() {
    document.querySelector('#profile').classList.add('active');
    document.querySelector('#historico').classList.remove('active');
}

function loadHistorico() {
    document.querySelector('#historico').classList.add('active');
    document.querySelector('#profile').classList.remove('active');
}

function logout() {
    localStorage.removeItem('usuarioEmail');
    window.location.href = "../login/login.html";
}

window.onload = function() {
    console.log('Página carregada'); 
    const email = localStorage.getItem('usuarioEmail');
    console.log('Email do usuário:', email); 

    if (email) {
        console.log(`Iniciando a requisição para http://localhost:8080/medico/consulta/${email}`);
        fetch(`https://projetointegrador-ii-production.up.railway.app/medico/consulta/${email}`)
            .then(response => {
                console.log('Resposta da API:', response);
                if (!response.ok) {
                    throw new Error('Erro na rede: ' + response.status);
                }
                return response.json();
            })
            .then(numeroConsultas => {
                console.log('Número de consultas:', numeroConsultas); 
                showPopup(numeroConsultas);
            })
            .catch(error => {
                console.error('Erro ao buscar consultas:', error);
                showPopup(0); 
            });
    } else {
        showPopup(0); 
    }
};

function showPopup(numeroConsultas) {
    const message = `Você tem ${numeroConsultas} consulta(s) agendada(s) para hoje. Para saber mais, acesse o histórico de consultas.`;
    document.getElementById('popup-message').innerText = message; 
    document.getElementById('popup').classList.remove('hidden'); 
}

function closePopup() {
    console.log('closePopup chamado');
    const popup = document.getElementById('popup');
    if (popup) {
        popup.classList.remove('hidden'); 
        popup.style.display = 'none'; 
    }
}
