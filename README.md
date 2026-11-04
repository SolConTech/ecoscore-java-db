# 🌱 EcoScore by SolCon

> Sistema de gamificação sustentável desenvolvido para o Challenge FIAP 2026 em parceria com a SoulUp by Prospera.

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x%2F4.x-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![Oracle Database](https://img.shields.io/badge/Database-Oracle-red?logo=oracle)](https://www.oracle.com/database/)
[![Docker](https://img.shields.io/badge/Docker-Container-blue?logo=docker)](https://www.docker.com/)
[![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-CI%2FCD-2088FF?logo=githubactions)](https://github.com/features/actions)
[![Deploy Render](https://img.shields.io/badge/Deploy-Render-46E3B7?logo=render)](http://ecoscore-java-db.onrender.com)

---

## 📌 Índice

- [🌱 Sobre o projeto](#-sobre-o-projeto)
- [🎯 Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias e ferramentas](#️-tecnologias-e-ferramentas)
- [📁 Estrutura do projeto](#-estrutura-do-projeto)
- [🌐 Endpoints da API REST](#-endpoints-da-api-rest)
- [⚙️ Configuração de Ambiente](#️-configuração-de-ambiente)
- [💻 Pré-requisitos](#-pré-requisitos)
- [📥 Importação e Instalação](#-importação-e-instalação)
- [🚀 Execução](#-execução)
- [📊 Modelagem e Documentação](#-modelagem-e-documentação)
- [👥 Integrantes](#-integrantes)
- [🔗 Links](#-links)

---

## 🌱 Sobre o projeto

O **EcoScore** é uma plataforma de gamificação socioambiental desenvolvida pela **SolCon** para o **Challenge FIAP 2026**, em parceria com a **SoulUp by Prospera**.

A solução canaliza hábitos cotidianos em resultados ecológicos mensuráveis. Por meio de algoritmos de pontuação baseados em impacto real (economia de água, neutralização de carbono, descarte de recicláveis e preservação da natureza), o sistema incentiva a comunidade a maximizar seu benefício ambiental coletivo através de missões ecológicas, quizzes educativos, publicações comunitárias e rankings dinâmicos de engajamento.

O ecossistema disponibiliza tanto uma **API RESTful** robusta (construída com Spring Boot e conectada a banco de dados Oracle) quanto uma **interface gráfica desktop (Java Swing)** para gerenciamento ágil de ações.

---

## 🎯 Funcionalidades

- 👤 **Gestão de Usuários:** Cadastro, autenticação e acompanhamento de estatísticas e reputação.
- ♻️ **Registro e Cálculo de Ações Sustentáveis:** Conversão quantitativa de atitudes ecológicas em pontos (`CalculadoraPontos`):
  - *Água:* Cálculo por litros preservados.
  - *Carbono:* Cálculo por emissão evitada (kg CO₂).
  - *Reciclagem:* Cálculo por massa reaproveitada (kg).
  - *Natureza:* Pontuação escalonada por nível de dificuldade em reflorestamento e plantio.
- 🎯 **Missões Coletivas e Desafios:** Metas estruturadas para guiar a comunidade a práticas de alto valor sustentável.
- 🧠 **Quizzes Educativos:** Disseminação de conhecimento para reduzir desperdício e aprimorar a conscientização ecológica.
- 📝 **Feed Comunitário e Interações:** Publicação de posts sobre ações sustentáveis com sistema de votos positivos e negativos.
- 📊 **Ranking Global e Pontuação:** Classificação transparente do progresso comunitário e acúmulo de *SoulCoins*.
- 🖥️ **Interface Gráfica Desktop:** Módulos visuais em Java Swing para interação local.
- 🌐 **API RESTful Completa:** Endpoints documentados com validações de entrada e suporte a CORS.
- 🗄️ **Persistência Relacional:** Modelagem e integridade transacional com Oracle Database.

---

## 🛠️ Tecnologias e ferramentas

### Desenvolvimento e Infraestrutura

| Tecnologia / Ferramenta | Utilização |
|---|---|
| **Java 21** | Linguagem principal do projeto (LTS) |
| **Spring Boot** | Framework para construção da API RESTful (Web MVC, Validação) |
| **Jakarta Bean Validation** | Validações declarativas de integridade de dados (`@NotNull`, `@Positive`, etc.) |
| **Oracle Database** | Banco de dados relacional corporativo |
| **Oracle JDBC (ojdbc11/ojdbc17)** | Driver de conectividade e persistência de dados |
| **Dotenv Java (`dotenv-java`)** | Gerenciamento seguro de variáveis de ambiente (`.env`) |
| **Java Swing** | Interface gráfica desktop integrada |
| **Maven & Maven Wrapper (`mvnw`)** | Gerenciamento de dependências, compilação e empacotamento |
| **Docker** | Containerização da aplicação com imagem base `eclipse-temurin:21-jdk-alpine` |
| **Render** | Plataforma de hospedagem e deploy contínuo em nuvem |
| **GitHub Actions** | Automação e esteira de CI/CD para compilação e checagem de dependências |
| **Git & GitHub** | Controle de versionamento e repositório de código |
| **Insomnia / OpenAPI** | Testes e especificação dos contratos de endpoints HTTP |
| **Oracle SQL Developer & Data Modeler** | Modelagem relacional e administração de scripts DDL/DML |

---

## 📁 Estrutura do projeto

O repositório é organizado no formato modular, separando a aplicação principal, scripts de banco de dados e documentações:

```text
ecoscore-java/
├── .github/
│   └── workflows/
│       └── ci.yml                   # Pipeline de automação CI/CD
├── database/
│   └── modelagem-database-ecoscore.ddl # Scripts DDL de criação do banco Oracle
├── docs/
│   ├── database/                    # Documentações e relatórios de banco de dados
│   ├── java/                        # Diagramas de classe (Draw.io) e especificações
│   ├── modelo-negocios/             # Documentação de negócios e escopo da solução
│   └── endpoints-table.md           # Tabela detalhada de endpoints e status HTTP
├── insomnia/
│   └── ecoscore-endpoints.yaml      # Coleção de requisições prontas para Insomnia
├── ecoscore/                        # Módulo principal da aplicação Java
│   ├── dockerfile                   # Configuração de build e container Docker
│   ├── pom.xml                      # Configuração de dependências Maven
│   ├── mvnw / mvnw.cmd              # Wrapper do Maven
│   └── src/
│       └── main/
│           ├── java/br/com/fiap/
│           │   ├── bo/              # Business Objects (Regras de negócio e validações)
│           │   ├── controller/      # Controllers e fluxos de controle
│           │   ├── dao/             # Data Access Objects e ConnectionFactory (JDBC)
│           │   ├── resource/        # Controladores REST API Spring Boot e Filtro CORS
│           │   ├── to/              # Transfer Objects (DTOs com validações) e Calculadora
│           │   ├── view/            # GUI Desktop (Swing) e executável RunApplication
│           │   └── EcoscoreApplication.java # Classe principal Spring Boot
│           └── resources/
│               └── application.properties # Configurações da aplicação Spring
└── README.md                        # Guia principal do projeto
```

---

## 🌐 Endpoints da API REST

A API REST disponibiliza operações completas de CRUD sobre as entidades da plataforma.

A URL base em produção é: `http://ecoscore-java-db.onrender.com`

| Recurso | Método | Rota | Descrição |
|---|---|---|---|
| **Ação** | `GET` | `/acao` | Lista todas as ações sustentáveis |
| **Ação** | `GET` | `/acao/{id}` | Busca uma ação específica por ID |
| **Ação** | `POST` | `/acao` | Cadastra uma nova ação sustentável |
| **Ação** | `PUT` | `/acao/{id}` | Atualiza uma ação existente |
| **Ação** | `DELETE` | `/acao/{id}` | Remove uma ação por ID |
| **Missão** | `GET` | `/missao` | Lista todas as missões ecológicas |
| **Missão** | `GET` | `/missao/{id}` | Consulta detalhes de uma missão por ID |
| **Missão** | `POST` | `/missao` | Cadastra uma nova missão |
| **Missão** | `PUT` | `/missao/{id}` | Atualiza uma missão por ID |
| **Missão** | `DELETE` | `/missao/{id}` | Exclui uma missão por ID |
| **Quiz** | `GET` | `/quiz` | Lista registros de quizzes |
| **Quiz** | `GET` | `/quiz/{id}` | Consulta dados de quiz por ID |
| **Quiz** | `POST` | `/quiz` | Registra a conclusão de um novo quiz |
| **Quiz** | `PUT` | `/quiz/{id}` | Atualiza registro de quiz por ID |
| **Quiz** | `DELETE` | `/quiz/{id}` | Remove registro de quiz por ID |
| **Post** | `GET` | `/post` | Lista publicações do feed comunitário |
| **Post** | `GET` | `/post/{id}` | Consulta uma publicação específica |
| **Post** | `POST` | `/post` | Cria uma nova publicação no feed |
| **Post** | `PUT` | `/post/{id}` | Atualiza conteúdo ou contadores de um post |
| **Post** | `DELETE` | `/post/{id}` | Remove um post por ID |
| **Ranking** | `GET` | `/ranking` | Consulta o ranking geral de pontuação |
| **Ranking** | `GET` | `/ranking/{id}` | Consulta pontuação de ranking por ID |
| **Ranking** | `POST` | `/ranking` | Registra nova pontuação no ranking |
| **Ranking** | `PUT` | `/ranking/{id}` | Atualiza registro de ranking |
| **Ranking** | `DELETE` | `/ranking/{id}` | Exclui entrada do ranking |
| **Usuário** | `GET` | `/usuario` | Lista todos os usuários cadastrados |
| **Usuário** | `GET` | `/usuario/{id}` | Busca perfil e dados de usuário por ID |
| **Usuário** | `POST` | `/usuario` | Cadastra um novo usuário no sistema |
| **Usuário** | `PUT` | `/usuario/{id}` | Atualiza informações cadastrais de usuário |
| **Usuário** | `DELETE` | `/usuario/{id}` | Remove a conta de usuário por ID |

> 📖 Para conferir a lista completa com os códigos HTTP retornados (ex: `200`, `201`, `204`, `400`, `404`, `500`), consulte a [Tabela de Endpoints](ecoscore/docs/endpoints-table.md).

---

## ⚙️ Configuração de Ambiente

A conexão com o banco de dados Oracle é configurada via variáveis de ambiente. Você pode defini-las no sistema operacional ou criar um arquivo `.env` dentro da pasta `ecoscore/`:

```env
DB_URL=jdbc:oracle:thin:@oracle.com.br:1521:ORCL
DB_USER=seu_usuario_oracle
DB_PASSWORD=sua_senha_oracle
```

---

## 💻 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado em sua máquina:

- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) ou superior
- [Apache Maven](https://maven.apache.org/) (opcional, caso utilize o `./mvnw` embutido)
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/) (opcional, para execução em container)
- Acesso a uma instância do **Oracle Database** com o schema criado via `database/modelagem-database-ecoscore.ddl`

---

## 📥 Importação e Instalação

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/murilo-a-souza/ecoscore-java.git
   cd ecoscore-java
   ```

2. **Acesse o diretório da aplicação:**
   ```bash
   cd ecoscore
   ```

3. **Instale as dependências e compile o projeto:**
   ```bash
   ./mvnw clean install
   ```

---

## 🚀 Execução

### 1. Executando a API REST (Spring Boot)

Para iniciar o servidor HTTP e disponibilizar os endpoints REST:

```bash
cd ecoscore
./mvnw spring-boot:run
```

A aplicação inicializará por padrão na porta `8080`: `http://localhost:8080`

### 2. Executando a Interface Desktop (Java Swing)

Para abrir a interface gráfica desktop integrada:

```bash
cd ecoscore
java -cp target/ecoscore-sp4-2026-10.jar br.com.fiap.view.RunApplication
```
*(Ou execute a classe `br.com.fiap.view.RunApplication` diretamente pela sua IDE favorita)*.

### 3. Executando via Docker

Para construir a imagem e executar o container da aplicação:

```bash
cd ecoscore
docker build -t ecoscore-app .
docker run -p 8080:8080 --env-file .env ecoscore-app
```

---

## 📊 Modelagem e Documentação

O repositório conta com artefatos de documentação técnica e de negócio:

- 📑 **Tabela de Endpoints:** [`ecoscore/docs/endpoints-table.md`](ecoscore/docs/endpoints-table.md)
- 📮 **Coleção Insomnia (OpenAPI/YAML):** [`insomnia/ecoscore-endpoints.yaml`](insomnia/ecoscore-endpoints.yaml)
- 🗄️ **Scripts DDL de Banco de Dados:** [`database/modelagem-database-ecoscore.ddl`](database/modelagem-database-ecoscore.ddl)
- 📐 **Diagrama de Classes (Draw.io):** [`docs/java/class-diagram.drawio`](docs/java/class-diagram.drawio)
- 📄 **Documentações Técnicas em PDF:**
  - `docs/java/documentacao-java-sp3.pdf`
  - `docs/database/documentacao-database-sp1-2.pdf`
  - `docs/modelo-negocios/documentacao-solcon-sp4.pdf`

---

## 👥 Integrantes

**SolCon — Equipe de Desenvolvimento (FIAP 1TDSPH):**

- **Carlos Henrique Franco de Melo** — RM 569868
- **Henrique Bonachela de Carvalho Carabante** — RM 573620
- **Murilo Almeida Rodrigues de Souza** — RM 573977

---

## 🔗 Links

- 🌐 **Deploy da API em Produção (Render):** [http://ecoscore-java-db.onrender.com](http://ecoscore-java-db.onrender.com)
- 📦 **Repositório GitHub:** [https://github.com/murilo-a-souza/ecoscore-java](https://github.com/murilo-a-souza/ecoscore-java)
- 🎓 **Instituição:** [FIAP](https://www.fiap.com.br/)
- 🤝 **Parceiro do Challenge:** SoulUp by Prospera
