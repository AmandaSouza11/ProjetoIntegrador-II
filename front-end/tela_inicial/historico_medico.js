async function loadHistorico() {
    const email = localStorage.getItem('usuarioEmail');
    const response = await fetch(`http://localhost:8080/historico/medico/${email}`);
    
    if (!response.ok) {
        console.error('Erro ao buscar histórico:', response.status);
        return;
    }

    const historico = await response.json();

    const historicoDiv = document.getElementById('historico');
    historicoDiv.innerHTML = ''; 

    const container = document.createElement('div');
    container.classList.add('consulta-container');

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
            <h3>${item.nomePaciente}</h3>
            <p><strong>Telefone:</strong> ${item.telefone}</p>
            <p><strong>Data:</strong> ${formatarData(item.data)}</p>
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

async function cancelarConsulta(idAgendamento) {
    try {
        const response = await fetch(`http://localhost:8080/cancelar/${idAgendamento}`, {
            method: 'POST' 
        });

        if (response.ok) {
            alert("Consulta Cancelada");
            loadHistorico();
        } else {
            console.error("Erro ao cancelar a consulta:", response.statusText);
            alert("Falha ao cancelar a consulta.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
        alert("Ocorreu um erro ao tentar cancelar a consulta.");
    }
}