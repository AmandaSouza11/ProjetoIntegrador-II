# <h1> 'Agenda+' Solução Integrada para Agendamento e Gestão de Consultas 

Este projeto é um sistema de agendamento desenvolvido em Java e JavaScript, destinado a profissionais autônomos da área da saúde. O objetivo é facilitar o gerenciamento de consultas e otimizar o agendamento entre profissionais e pacientes.

### Requisitos:
    SQLite ou MySQL (um dos dois bancos de dados deve ser instalado)

### Estrutura do Projeto:
    A classe principal do projeto é ProjetoIntegradorApplication. Para iniciar a aplicação, você deve executar esta classe.

### Como rodar o projeto localmente:

    Clone o repositório:
        git clone <URL-do-repositório>

    Instale a extensão Live Server no Visual Studio Code:
    -Abra o Visual Studio Code.
    -Vá até a aba de extensões (ícone de quadrados).
    -Pesquise por "Live Server" e instale a extensão.

### Configure o banco de dados:

Escolha entre SQLite ou MySQL e siga as instruções apropriadas abaixo.

**Configuração do Banco de Dados**

1. Usando SQLite

No arquivo application.properties, configure as seguintes propriedades:

    spring.datasource.url=jdbc:sqlite:SQLite/banco_projeto.db
    spring.datasource.driver-class-name=org.sqlite.JDBC
    spring.datasource.username=
    spring.datasource.password=
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect

No terminal, entre na pasta SQLite e execute o seguinte comando para criar o banco de dados e as tabelas:
    
      cd SQLite
     ./sqlite3 banco_projeto.db < Script--SQLite.sql


2. Usando MySQL

Baixe e instale o MySQL em seu computador, seguindo as instruções da documentação oficial.

No arquivo application.properties, configure as seguintes propriedades:

    spring.application.name=nome do seu banco
    spring.datasource.url=jdbc:mysql://localhost/projeto_integrador_ii
    spring.datasource.username=root
    spring.datasource.password=1243
    server.error.include-stacktrace=never

Copie o script SQL do arquivo Script--MySQL.sql e execute-o no MySQL que foi instalado para criar as tabelas necessárias.

### Iniciando a Aplicação
Com o banco de dados configurado, volte para a raiz do projeto e execute a classe ProjetoIntegradorApplication em sua IDE (recomendado usar o Visual Studio Code ou outra IDE de sua preferência).

**Acessando a Aplicação**

Após iniciar a aplicação, utilize o Live Server para abrir as páginas HTML no seu navegador. Isso permitirá visualizar a interface do sistema de agendamento.