# Agenda de Contatos (Java Swing + MVC)

Um sistema desktop robusto para gerenciamento de contatos, desenvolvido em **Java** utilizando a arquitetura **MVC (Model-View-Controller)**. O projeto foca na segregação de dados entre múltiplos usuários e persistência leve utilizando arquivos **JSON**.

## Sobre o Projeto

Este aplicativo permite que múltiplos usuários criem contas e gerenciem suas próprias agendas telefônicas de forma privada. A principal característica técnica é a implementação do padrão MVC e o uso da biblioteca **Google Gson** para persistência de dados, eliminando a necessidade de um banco de dados SQL pesado para operações locais.

### Principais Características:
* **Controle de Acesso:** Sistema de Login e Registro.
* **Segregação de Dados:** Um usuário só visualiza os contatos que ele mesmo criou.
* **Persistência JSON:** Dados salvos automaticamente em arquivos locais.
* **Atualização em Tempo Real:** A interface reflete adições e remoções instantaneamente sem reiniciar.

---

## Funcionalidades

* **Autenticação:**
    * Login seguro.
    * Cadastro de novos usuários (com verificação de duplicidade).
    * Alteração de senha via Perfil.
* **Gerenciamento de Contatos (CRUD):**
    * **C**riar novos contatos (Nome, Email, Telefone).
    * **R**ecuperar/Listar contatos (Filtragem automática por usuário logado).
    * **U**pdate (Editar) dados de contatos existentes.
    * **D**elete (Remover) contatos da lista.
* **Sistema de IDs:** Geração automática e incremental de IDs para os contatos.

---

## Tecnologias Utilizadas

* **Linguagem:** Java JDK 8+
* **Interface Gráfica:** Java Swing (JFrame, JPanel, JTable).
* **Persistência:** JSON (Arquivos de texto).
* **Bibliotecas Externas:**
    * Google Gson (Serialização/Desserialização de Objetos).

---

## Estrutura do Projeto (MVC)

O código foi organizado seguindo estritamente o padrão MVC para facilitar a manutenção e escalabilidade:

```text
src/
├── model/           # Definição dos objetos (POJO)
│   ├── Usuario.java
│   └── Contato.java
│
├── view/            # Interface do Usuário (GUI)
│   ├── TelaLogin.java
│   ├── TelaRegistro.java
│   ├── TelaPrincipal.java
│   ├── TelaCadastroContato.java
│   └── TelaPerfil.java
│
├── controller/      # Lógica de Negócios e Regras
│   ├── UsuarioController.java (Auth e gestão de contas)
│   └── ContatoController.java (Filtragem e CRUD)
│
└── util/            # Utilitários
    └── JsonUtil.java (Gerenciador genérico de leitura/escrita JSON)

data/                # Pasta gerada automaticamente
    ├── usuarios.json
    └── contatos.json
