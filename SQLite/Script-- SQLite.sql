-- SQLite
CREATE TABLE paciente (
id INTEGER PRIMARY KEY AUTOINCREMENT, 
nome TEXT, 
data_nascimento TEXT, 
cpf TEXT, 
telefone TEXT, 
email TEXT, 
senha TEXT, 
cep TEXT, 
bairro TEXT, 
rua TEXT, 
numero_residencial TEXT, 
omplemento TEXT, 
cidade TEXT, 
role TEXT, 
UNIQUE(cpf) 
);

CREATE TABLE medico (
id INTEGER PRIMARY KEY AUTOINCREMENT, 
nome TEXT, 
cpf TEXT, 
telefone TEXT, 
email TEXT, 
senha TEXT, 
ra TEXT, 
crm TEXT, 
crn TEXT, 
cep TEXT, 
bairro TEXT, 
rua TEXT, 
numero_residencial TEXT, 
complemento TEXT, 
cidade TEXT, 
especialidade TEXT, 
role TEXT, 
UNIQUE(cpf), UNIQUE(ra), UNIQUE(crm), UNIQUE(crn) );

CREATE TABLE agendamento (
id INTEGER PRIMARY KEY AUTOINCREMENT, 
medico_email TEXT, 
paciente_email TEXT, 
data TEXT, 
horario TEXT, 
status TEXT );