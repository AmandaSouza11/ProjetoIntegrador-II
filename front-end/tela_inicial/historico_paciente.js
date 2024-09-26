async function loadHistorico() {
    const email = localStorage.getItem('usuarioEmail');
    const response = await fetch(`http://localhost:8080/historico/paciente/${email}`);
    const historico = await response.json();

    const historicoDiv = document.getElementById('historico');
    historicoDiv.innerHTML = ''; // Limpa o conteúdo anterior

    historico.forEach(item => {
        const medicoInfo = document.createElement('div');
        medicoInfo.classList.add('medico-info');
        
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
            <p><strong>Data:</strong> ${item.data}</p>
            <p><strong>Horário:</strong> ${item.horario}</p>
        `;

        historicoDiv.appendChild(medicoInfo);
    });

    // Muda a visibilidade do conteúdo
    document.querySelectorAll('#content > div').forEach(div => div.classList.remove('active'));
    historicoDiv.classList.add('active');
}
