function loadProfile() {
    const email = localStorage.getItem('usuarioEmail');
    const endpoint = `http://localhost:8080/paciente/perfil/${email}`;

    fetch(endpoint)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar perfil do usuário');
            }
            return response.json();
        })
        .then(data => {
            displayProfile(data);
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}

function displayProfile(data) {
    document.querySelector('#nomeUsuario').textContent = data.nome;
    document.querySelector('#dataNascimento').value = formatDate(data.data_nascimento);
    document.querySelector('#cpf').value = formatCPF(data.cpf);
    document.querySelector('#telefone').value = data.telefone;
    document.querySelector('#email').value = data.email;
    document.querySelector('#cep').value = data.cep;
    document.querySelector('#bairro').value = data.bairro;
    document.querySelector('#rua').value = data.rua;
    document.querySelector('#numeroResidencial').value = data.numero_residencial;
    document.querySelector('#complemento').value = data.complemento;
    document.querySelector('#cidade').value = data.cidade;

    document.getElementById('profile').classList.add('active');
    document.getElementById('agendamento').classList.remove('active');
    document.getElementById('historico').classList.remove('active');
}

function formatDate(dateString) {
    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return new Date(dateString).toLocaleDateString('pt-BR', options);
}

function formatCPF(cpf) {
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
}

document.addEventListener('DOMContentLoaded', loadProfile);
