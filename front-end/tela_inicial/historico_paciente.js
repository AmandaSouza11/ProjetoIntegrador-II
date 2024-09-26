async function loadHistorico() {
    const email = localStorage.getItem('usuarioEmail');
    const response = await fetch(`http://localhost:8080/historico/paciente/${email}`);
    const historico = await response.json();

    const historicoDiv = document.getElementById('historico');
    historicoDiv.innerHTML = ''; 

    const container = document.createElement('div');
    container.classList.add('consulta-container'); 

    // Função para formatar a data no formato dd/mm/aaaa
    function formatarData(data) {
        const partes = data.split("-");
        return `${partes[2]}/${partes[1]}/${partes[0]}`;
    }

    historico.forEach(item => {
        const medicoInfo = document.createElement('div');
        medicoInfo.classList.add('medico-info');

        const statusClass = item.status.toUpperCase() === 'AGENDADO' ? 'status-agendado' :
                            item.status.toUpperCase() === 'REALIZADA' ? 'status-realizada' :
                            item.status.toUpperCase() === 'CANCELADA' ? 'status-cancelada' : '';

        medicoInfo.innerHTML = `
            <h3>${item.nomeMedico}</h3>
            <p><strong>CRM:</strong> ${item.crm}</p>
            <p><strong>Especialidade:</strong> ${item.especialidade}</p>
            <p><strong>CEP:</strong> ${item.cep}</p>
            <p><strong>Bairro:</strong> ${item.bairro}</p>
            <p><strong>Rua:</strong> ${item.rua}</p>
            <p><strong>Número Residencial:</strong> ${item.numeroResidencial}</p>
            <p><strong>Complemento:</strong> ${item.complemento}</p>
            <p><strong>Cidade:</strong> ${item.cidade}</p>
            <p><strong>Data:</strong> ${formatarData(item.data)}</p> <!-- Formatação da data -->
            <p><strong>Horário:</strong> ${item.horario}</p>
            <p><strong>Status:</strong> <span class="${statusClass}">${item.status}</span></p>
        `;

        if (item.status && item.status.toUpperCase() === 'AGENDADO') {
            const cancelarButton = document.createElement('button');
            cancelarButton.className = 'cancelarButton'; 
            cancelarButton.innerText = 'Cancelar consulta';
            cancelarButton.onclick = async () => {
                await cancelarConsulta(item.idAgendamento);
            };
            medicoInfo.appendChild(cancelarButton);
        }

        container.appendChild(medicoInfo);
    });

    historicoDiv.appendChild(container);

    document.querySelectorAll('#content > div').forEach(div => div.classList.remove('active'));
    historicoDiv.classList.add('active');
}
