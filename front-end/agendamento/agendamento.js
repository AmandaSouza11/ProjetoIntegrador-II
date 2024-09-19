const especialidadeSelect = document.getElementById('especialidade');
const medicosDiv = document.getElementById('medicos');
const calendarioDiv = document.getElementById('calendario');
const dataInput = document.getElementById('data');
const horarioSelect = document.getElementById('horario');
const horariosDiv = document.getElementById('horarios');

especialidadeSelect.addEventListener('change', function() {
    const especialidade = this.value;
    medicosDiv.innerHTML = ''; 

    if (especialidade) {
        fetch(`http://localhost:8080/medico/${especialidade}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao buscar médicos');
                }
                return response.json();
            })
            .then(medicos => {
                if (medicos.length === 0) {
                    medicosDiv.innerHTML = '<p>Nenhum médico encontrado.</p>';
                } else {
                    medicos.forEach(medico => {
                        const medicoInfo = document.createElement('div');
                        medicoInfo.className = 'medico-info';
                        medicoInfo.innerHTML = `
                            <h3>${medico.nome}</h3>
                            <p>Telefone: ${medico.telefone}</p>
                            <p>Email: ${medico.email}</p>
                            <p>Endereço: ${medico.rua}, ${medico.numero_residencial}, ${medico.complemento}, ${medico.bairro} - ${medico.cidade}, CEP: ${medico.cep}</p>
                            <p>Especialidade: ${medico.especialidade}</p>
                        `;
                        const button = document.createElement('button');
                        button.textContent = 'Agendar';
                        button.addEventListener('click', () => {
                            calendarioDiv.style.display = 'block';
                            horarioSelect.style.display = 'none'; 
                            dataInput.value = ''; 
                            horarioSelect.innerHTML = ''; 
                            horariosDiv.style.display = 'none'; 
                        });
                        medicoInfo.appendChild(button);
                        medicosDiv.appendChild(medicoInfo);
                    });
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                medicosDiv.innerHTML = '<p>Ocorreu um erro ao buscar médicos.</p>';
            });
    }
});

dataInput.addEventListener('change', function() {
    horarioSelect.innerHTML = '';
    const horariosDisponiveis = ['09:00', '10:00', '14:00']; 

    if (this.value) {
        horariosDisponiveis.forEach(horario => {
            const option = document.createElement('option');
            option.value = horario;
            option.textContent = horario;
            horarioSelect.appendChild(option);
        });
        horariosDiv.style.display = 'block'; 
        horarioSelect.style.display = 'block'; 
    } else {
        horariosDiv.style.display = 'none'; 
    }
});
