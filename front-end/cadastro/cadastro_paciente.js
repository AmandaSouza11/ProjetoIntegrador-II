async function cadastrarPaciente(event) {
    event.preventDefault();
    const nome = document.getElementById('nome').value;
    const dataNascimento = document.getElementById('data_nascimento').value;
    const cpf = document.getElementById('cpf').value;
    const telefone = document.getElementById('telefone').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;
    const cep = document.getElementById('cep').value;
    const bairro = document.getElementById('bairro').value;
    const rua = document.getElementById('rua').value;
    const numeroResidencial = document.getElementById('numero_residencial').value;
    const complemento = document.getElementById('complemento').value;
    const cidade = document.getElementById('cidade').value;

    const pacienteData = {
        nome,
        data_nascimento: dataNascimento,
        cpf,
        telefone,
        email,
        senha,
        cep,
        bairro,
        rua,
        numero_residencial: numeroResidencial,
        complemento,
        cidade
    };

    try {
        const response = await fetch('http://localhost:8080/paciente', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pacienteData)
        });

        if (response.ok) {
            alert('Cadastro realizado com sucesso!! Faça login para continuar');
            window.location.href = '../login/login.html';
        } else {
            const errorResponse = await response.json();
            alert('Erro ao cadastrar: ' + errorResponse.message);
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor.');
    }
}

function formatarData(event) {
    const input = event.target;
    const originalValue = input.value;
    let value = originalValue.replace(/\D/g, ''); 

    if (value.length > 8) {
        value = value.slice(0, 8);
    }

    let formattedValue = '';
    if (value.length >= 2) {
        formattedValue += value.slice(0, 2) + '/'; 
    }
    if (value.length >= 4) {
        formattedValue += value.slice(2, 4) + '/'; 
    }
    if (value.length >= 6) {
        formattedValue += value.slice(4); 
    }

    input.value = formattedValue; 

    const cursorPosition = formattedValue.length;
    input.setSelectionRange(cursorPosition, cursorPosition);
}

function formatarTelefone(event) {
    const input = event.target;
    let value = input.value.replace(/\D/g, ''); 

    if (value.length > 11) {
        value = value.slice(0, 11); 
    }

    let formattedValue = '';
    if (value.length > 0) {
        formattedValue += '(' + value.slice(0, 2) + ') '; 
    }
    if (value.length > 2) {
        formattedValue += value.slice(2, 7); 
    }
    if (value.length > 7) {
        formattedValue += '-' + value.slice(7, 11); 
    }

    input.value = formattedValue; 
}
