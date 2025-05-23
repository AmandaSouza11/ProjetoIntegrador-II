const especialidadeSelect = document.getElementById('especialidade');
const medicosDiv = document.getElementById('medicos');
const calendarioDiv = document.getElementById('calendario');
const dataInput = document.getElementById('data');
const horarioSelect = document.getElementById('horario');
const horariosDiv = document.getElementById('horarios');
const agendarButton = document.getElementById('agendarButton');

especialidadeSelect.addEventListener('change', function() {
    const especialidade = this.value;
    medicosDiv.innerHTML = ''; 

    if (especialidade) {
        fetch(`https://projetointegrador-ii-production.up.railway.app/medico/${especialidade}`)
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
                    medicosDiv.classList.remove('hidden'); 
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
                            agendarButton.dataset.medicoEmail = medico.email; 
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
    } else {
        medicosDiv.classList.add('hidden');
    }
});

dataInput.addEventListener('change', function() {
    horarioSelect.innerHTML = ''; 
    horariosDiv.style.display = 'none'; 

    if (this.value) {
        const data = this.value;
        const horarioInput = '9:00'; 
        const medicoEmail = agendarButton.dataset.medicoEmail; 

        fetch(`https://projetointegrador-ii-production.up.railway.app/agendamento/disponiveis?data=${encodeURIComponent(data)}&horario=${encodeURIComponent(horarioInput)}&medioEmail=${encodeURIComponent(medicoEmail)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar horários disponíveis');
            }
            return response.json();
        })
        .then(horarios => {
            horarios.forEach(horario => {
                const option = document.createElement('option');
                option.value = horario;
                option.textContent = horario;
                horarioSelect.appendChild(option);
            });

            horariosDiv.style.display = 'block'; 
            horarioSelect.style.display = 'block'; 
            agendarButton.classList.remove('hidden'); 
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao buscar horários disponíveis.');
        });
    } else {
        horariosDiv.style.display = 'none'; 
        agendarButton.classList.add('hidden'); 
    }
});

agendarButton.addEventListener('click', () => {
    const medicoEmail = agendarButton.dataset.medicoEmail;
    const pacienteEmail = localStorage.getItem('usuarioEmail'); 
    const data = dataInput.value;
    const horario = horarioSelect.value;

    const body = {
        medico_email: medicoEmail,
        paciente_email: pacienteEmail,
        data: data,
        horario: horario
    };

    fetch('https://projetointegrador-ii-production.up.railway.app/agendamento', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao agendar consulta');
        }
        return response.json();
    })
    .then(data => {
        alert('Consulta agendada com sucesso!');
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Ocorreu um erro ao agendar a consulta.');
    });
});
