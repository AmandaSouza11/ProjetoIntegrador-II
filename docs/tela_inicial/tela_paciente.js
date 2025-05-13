function loadProfile() {
    document.querySelector('#profile').classList.add('active');
    document.querySelector('#agendamento').classList.remove('active');
    document.querySelector('#historico').classList.remove('active');
}

function loadAgendamento() {
    document.querySelector('#agendamento').classList.add('active');
    document.querySelector('#profile').classList.remove('active');
    document.querySelector('#historico').classList.remove('active');
}

function loadHistorico() {
    document.querySelector('#historico').classList.add('active');
    document.querySelector('#profile').classList.remove('active');
    document.querySelector('#agendamento').classList.remove('active');
}

function logout() {
    localStorage.removeItem('usuarioEmail');
    window.location.href = "../login/login.html";
}
