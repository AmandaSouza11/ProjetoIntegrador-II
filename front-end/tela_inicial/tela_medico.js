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
