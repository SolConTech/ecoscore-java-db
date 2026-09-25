# 🌱 EcoScore by SolCon

> Sistema de gamificação sustentável desenvolvido para o Challenge FIAP 2026 em parceria com a SoulUp by Prospera.

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)](https://www.oracle.com/java/)
[![Oracle](https://img.shields.io/badge/Database-Oracle-red?logo=oracle)](https://www.oracle.com/database/)
[![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-CI%2FCD-2088FF?logo=githubactions)](https://github.com/features/actions)

---

## 📌 Índice

- [Sobre o projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias e ferramentas](#-tecnologias-e-ferramentas)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Pré-requisitos](#-pré-requisitos)
- [Importação](#-importação)
- [Execução](#-execução)
- [Configuração](#-configuração)
- [Integrantes](#-integrantes)
- [Links](#-links)

---

## 🌱 Sobre o projeto

O **EcoScore** é uma proposta de gamificação sustentável para a plataforma **SoulUp**.

A solução busca incentivar a realização contínua de ações sustentáveis por meio de pontos, missões, quizzes, posts, selos e ranking.

O sistema registra as ações dos usuários, calcula sua pontuação e utiliza interações da comunidade para promover engajamento e reconhecimento.

O projeto foi desenvolvido pela **SolCon** para o **Challenge FIAP 2026**, em parceria com a SoulUp by Prospera.

---

## 🎯 Funcionalidades

- 👤 Cadastro e gerenciamento de usuários
- ♻️ Registro de ações sustentáveis
- 🎯 Registro de missões
- 🏆 Conquistas e selos
- 🧠 Registro de quizzes
- 📝 Publicação de posts
- 👍 Votos positivos e negativos
- 📊 Ranking de usuários
- ⚠️ Registro de penalidades
- 💰 Sistema de pontos e SoulCoins
- 🖥️ Interface gráfica com Java Swing
- 🗄️ Persistência dos dados em Oracle Database

---

## 🛠️ Tecnologias e ferramentas

### Desenvolvimento

| Tecnologia/Ferramenta | Utilização |
|---|---|
| Java 21 | Linguagem principal |
| IntelliJ IDEA | IDE de desenvolvimento |
| Oracle Database | Banco de dados |
| Oracle SQL Developer | Administração e execução de scripts SQL |
| Oracle Data Modeler | Modelagem do banco de dados |
| Git | Controle de versão |
| GitHub | Repositório e colaboração |
| GitHub Actions | Automação do repositório |
| Java Swing | Interface gráfica |
| JDBC | Comunicação entre Java e Oracle |

### IDE

**IntelliJ IDEA**

Licença utilizada: **Ultimate**

---

## 📁 Estrutura do projeto

```text
src/
└── br.com.fiap/
    ├── bean/
    │   └── Classes de domínio
    │
    ├── controller/
    │   └── Controllers da aplicação
    │
    ├── dao/
    │   └── Acesso e persistência no banco
    │
    ├── model/
    │   └── DTOs e modelos auxiliares
    │
    └── main/
        └── Interface e inicialização da aplicação