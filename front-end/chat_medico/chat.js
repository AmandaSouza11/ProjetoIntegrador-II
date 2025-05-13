let emailPacienteSelecionado = "";
const emailMedico = localStorage.getItem('usuarioEmail');
let nomeMedico = "";
let mapaPacientes = {}; 

function carregarPacientes() {
    fetch("https://projetointegrador-ii-production.up.railway.app/paciente/listar")
        .then(res => res.json())
        .then(data => {
            let pacientesDiv = document.getElementById("pacientes");
            pacientesDiv.innerHTML = "";
            data.forEach(p => {
                mapaPacientes[p.email] = p.nome;
                let div = document.createElement("div");
                div.className = "paciente";
                div.textContent = p.nome;
                div.onclick = () => abrirChat(p.email);
                pacientesDiv.appendChild(div);
            });
        });

    fetch(`https://projetointegrador-ii-production.up.railway.app/medico/perfil/${emailMedico}`)
        .then(res => res.json())
        .then(data => {
            nomeMedico = data.nome;
        });
}

function abrirChat(emailPaciente) {
    emailPacienteSelecionado = emailPaciente;
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
                    if (m.remetente === emailMedico) {
                        autor = nomeMedico || emailMedico;
                    } else if (m.remetente === emailPacienteSelecionado) {
                        autor = mapaPacientes[emailPacienteSelecionado] || emailPacienteSelecionado;
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
            emailMedico: emailMedico,
            emailPaciente: emailPacienteSelecionado,
            conteudo: conteudo,
            remetente: emailMedico 
        })
    }).then(() => abrirChat(emailPacienteSelecionado));

    input.value = "";
}

carregarPacientes();
