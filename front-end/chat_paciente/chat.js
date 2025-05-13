let emailMedicoSelecionado = "";
const emailPaciente = localStorage.getItem('usuarioEmail');
let nomePaciente = "";
let mapaMedicos = {}; 

function carregarMedicos() {
    fetch("https://projetointegrador-ii-production.up.railway.app/medico/listar")
        .then(res => res.json())
        .then(data => {
            let medicosDiv = document.getElementById("medicos");
            medicosDiv.innerHTML = "";
            data.forEach(m => {
                mapaMedicos[m.email] = m.nome;
                let div = document.createElement("div");
                div.className = "medico";
                div.textContent = m.nome;
                div.onclick = () => abrirChat(m.email);
                medicosDiv.appendChild(div);
            });
        });

    fetch(`https://projetointegrador-ii-production.up.railway.app/paciente/perfil/${emailPaciente}`)
        .then(res => res.json())
        .then(data => {
            nomePaciente = data.nome;
        });
}

function abrirChat(emailMedico) {
    emailMedicoSelecionado = emailMedico;
    fetch(`https://projetointegrador-ii-production.up.railway.app/api/chat/mensagens?emailMedico=${emailMedico}&emailPaciente=${emailPaciente}`)
        .then(res => res.json())
        .then(data => {
            let mensagensDiv = document.getElementById("mensagens");
            mensagensDiv.innerHTML = "";
            document.getElementById("entradaMensagem").style.display = "flex";

            if (data.length === 0) {
                let div = document.createElement("div");
                div.className = "mensagem-inicial";
                div.textContent = "Inicie uma conversa";
                mensagensDiv.appendChild(div);
            } else {
                data.forEach(m => {
                    let div = document.createElement("div");
                    div.className = "mensagem";
                    let autor;
                    if (m.remetente === emailPaciente) {
                        autor = nomePaciente || emailPaciente;
                    } else if (m.remetente === emailMedicoSelecionado) {
                        autor = mapaMedicos[emailMedicoSelecionado] || emailMedicoSelecionado;
                    } else {
                        autor = "Desconhecido";
                    }
                    div.textContent = `${autor}: ${m.conteudo}`;
                    mensagensDiv.appendChild(div);
                });
            }
        });
}

function enviarMensagem() {
    let input = document.getElementById("mensagemInput");
    let conteudo = input.value;

    fetch("https://projetointegrador-ii-production.up.railway.app/api/chat/enviar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            emailMedico: emailMedicoSelecionado,
            emailPaciente: emailPaciente,
            conteudo: conteudo,
            remetente: emailPaciente 
        })
    }).then(() => abrirChat(emailMedicoSelecionado));

    input.value = "";
}

carregarMedicos();
