document.getElementById('cadastro').onclick = function() {
    window.location.href = '../cadastro/cadastro_paciente.html'; 
};

document.getElementById('login').onclick = function() {
    const form = document.getElementById('login-form');
    form.style.display = form.style.display === 'none' ? 'block' : 'none';
};

document.getElementById('entrar').onclick = function() {
    window.location.href = '../tela_inicial/tela_inicial.html'; 
};
