# Tabelas de Endpoints
Diferentes tabelas, a geral são todas as requisições juntas, as outras estão separadas por classe.
- [1. Tabela geral](#1-geral)
- [2. Tabela de Ação](#2-ação)
- [3. Tabela de Missão](#3-missão)
- [4. Tabela de Post](#4-post)
- [5. Tabela de Quiz](#5-quiz)
- [6. Tabela de Ranking](#6-ranking)
- [7. Tabela de Usuário](#7-usuário)

## 1. Geral

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllAcao | GET | `https://ecoscore-java-db.onrender.com/acao` | 200, 500 | Lista todas as ações cadastradas. |
| findByIdAcao | GET | `https://ecoscore-java-db.onrender.com/acao/:id` | 200, 404, 500 | Busca uma ação específica pelo ID. |
| saveAcao | POST | `https://ecoscore-java-db.onrender.com/acao` | 201, 400, 500 | Cadastra uma nova ação. |
| updateAcao | PUT | `https://ecoscore-java-db.onrender.com/acao/:id` | 200, 400, 404, 500 | Atualiza os dados de uma ação pelo ID. |
| deleteAcao | DELETE | `https://ecoscore-java-db.onrender.com/acao/:id` | 204, 404, 500 | Remove uma ação pelo ID. |
| findAllMissao | GET | `https://ecoscore-java-db.onrender.com/missao` | 200, 500 | Lista todas as missões disponíveis. |
| findByIdMissao | GET | `https://ecoscore-java-db.onrender.com/missao/:id` | 200, 404, 500 | Busca uma missão específica pelo ID. |
| saveMissao | POST | `https://ecoscore-java-db.onrender.com/missao` | 201, 400, 500 | Cadastra uma nova missão. |
| updateMissao | PUT | `https://ecoscore-java-db.onrender.com/missao/:id` | 200, 400, 404, 500 | Atualiza uma missão existente pelo ID. |
| deleteMissao | DELETE | `https://ecoscore-java-db.onrender.com/missao/:id` | 204, 404, 500 | Remove uma missão pelo ID. |
| findAllQuiz | GET | `https://ecoscore-java-db.onrender.com/quiz` | 200, 500 | Lista todos os quizzes registrados. |
| findByIdQuiz | GET | `https://ecoscore-java-db.onrender.com/quiz/:id` | 200, 404, 500 | Busca os dados de um quiz pelo ID. |
| saveQuiz | POST | `https://ecoscore-java-db.onrender.com/quiz` | 201, 400, 500 | Registra a realização de um novo quiz. |
| updateQuiz | PUT | `https://ecoscore-java-db.onrender.com/quiz/:id` | 200, 400, 404, 500 | Atualiza um registro de quiz pelo ID. |
| deleteQuiz | DELETE | `https://ecoscore-java-db.onrender.com/quiz/:id` | 204, 404, 500 | Exclui um registro de quiz pelo ID. |
| findAllPost | GET | `https://ecoscore-java-db.onrender.com/post` | 200, 500 | Lista todos os posts da comunidade. |
| findByIdPost | GET | `https://ecoscore-java-db.onrender.com/post/:id` | 200, 404, 500 | Busca um post específico pelo ID. |
| savePost | POST | `https://ecoscore-java-db.onrender.com/post` | 201, 400, 500 | Cria uma nova publicação. |
| updatePost | PUT | `https://ecoscore-java-db.onrender.com/post/:id` | 200, 400, 404, 500 | Atualiza o conteúdo/votos de um post pelo ID. |
| deletePost | DELETE | `https://ecoscore-java-db.onrender.com/post/:id` | 204, 404, 500 | Apaga uma publicação pelo ID. |
| findAllRanking | GET | `https://ecoscore-java-db.onrender.com/ranking` | 200, 500 | Lista as posições do ranking geral. |
| findByIdRanking | GET | `https://ecoscore-java-db.onrender.com/ranking/:id` | 200, 404, 500 | Consulta a pontuação do ranking pelo ID. |
| saveRanking | POST | `https://ecoscore-java-db.onrender.com/ranking` | 201, 400, 500 | Registra uma nova pontuação no ranking. |
| updateRanking | PUT | `https://ecoscore-java-db.onrender.com/ranking/:id` | 200, 400, 404, 500 | Atualiza os votos/pontos do ranking pelo ID. |
| deleteRanking | DELETE | `https://ecoscore-java-db.onrender.com/ranking/:id` | 204, 404, 500 | Exclui um registro do ranking pelo ID. |
| findAllUsuario | GET | `https://ecoscore-java-db.onrender.com/usuario` | 200, 500 | Lista todos os usuários cadastrados. |
| findByIdUsuario | GET | `https://ecoscore-java-db.onrender.com/usuario/:id` | 200, 404, 500 | Busca as informações de um usuário pelo ID. |
| saveUsuario | POST | `https://ecoscore-java-db.onrender.com/usuario` | 201, 400, 500 | Cadastra um novo usuário no sistema. |
| updateUsuario | PUT | `https://ecoscore-java-db.onrender.com/usuario/:id` | 200, 400, 404, 500 | Atualiza o perfil e estatísticas do usuário pelo ID. |
| deleteUsuario | DELETE | `https://ecoscore-java-db.onrender.com/usuario/:id` | 204, 404, 500 | Remove a conta de um usuário pelo ID. |

## 2. Ação

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllAcao | GET | `https://ecoscore-java-db.onrender.com/acao` | 200, 500 | Lista todas as ações cadastradas. |
| findByIdAcao | GET | `https://ecoscore-java-db.onrender.com/acao/:id` | 200, 404, 500 | Busca uma ação específica pelo ID. |
| saveAcao | POST | `https://ecoscore-java-db.onrender.com/acao` | 201, 400, 500 | Cadastra uma nova ação. |
| updateAcao | PUT | `https://ecoscore-java-db.onrender.com/acao/:id` | 200, 400, 404, 500 | Atualiza os dados de uma ação pelo ID. |
| deleteAcao | DELETE | `https://ecoscore-java-db.onrender.com/acao/:id` | 204, 404, 500 | Remove uma ação pelo ID. |

## 3. Missão

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllMissao | GET | `https://ecoscore-java-db.onrender.com/missao` | 200, 500 | Lista todas as missões disponíveis. |
| findByIdMissao | GET | `https://ecoscore-java-db.onrender.com/missao/:id` | 200, 404, 500 | Busca uma missão específica pelo ID. |
| saveMissao | POST | `https://ecoscore-java-db.onrender.com/missao` | 201, 400, 500 | Cadastra uma nova missão. |
| updateMissao | PUT | `https://ecoscore-java-db.onrender.com/missao/:id` | 200, 400, 404, 500 | Atualiza uma missão existente pelo ID. |
| deleteMissao | DELETE | `https://ecoscore-java-db.onrender.com/missao/:id` | 204, 404, 500 | Remove uma missão pelo ID. |

## 4. Post

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllPost | GET | `https://ecoscore-java-db.onrender.com/post` | 200, 500 | Lista todos os posts da comunidade. |
| findByIdPost | GET | `https://ecoscore-java-db.onrender.com/post/:id` | 200, 404, 500 | Busca um post específico pelo ID. |
| savePost | POST | `https://ecoscore-java-db.onrender.com/post` | 201, 400, 500 | Cria uma nova publicação. |
| updatePost | PUT | `https://ecoscore-java-db.onrender.com/post/:id` | 200, 400, 404, 500 | Atualiza o conteúdo/votos de um post pelo ID. |
| deletePost | DELETE | `https://ecoscore-java-db.onrender.com/post/:id` | 204, 404, 500 | Apaga uma publicação pelo ID. |

## 5. Quiz

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllQuiz | GET | `https://ecoscore-java-db.onrender.com/quiz` | 200, 500 | Lista todos os quizzes registrados. |
| findByIdQuiz | GET | `https://ecoscore-java-db.onrender.com/quiz/:id` | 200, 404, 500 | Busca os dados de um quiz pelo ID. |
| saveQuiz | POST | `https://ecoscore-java-db.onrender.com/quiz` | 201, 400, 500 | Registra a realização de um novo quiz. |
| updateQuiz | PUT | `https://ecoscore-java-db.onrender.com/quiz/:id` | 200, 400, 404, 500 | Atualiza um registro de quiz pelo ID. |
| deleteQuiz | DELETE | `https://ecoscore-java-db.onrender.com/quiz/:id` | 204, 404, 500 | Exclui um registro de quiz pelo ID. |

## 6. Ranking

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllRanking | GET | `https://ecoscore-java-db.onrender.com/ranking` | 200, 500 | Lista as posições do ranking geral. |
| findByIdRanking | GET | `https://ecoscore-java-db.onrender.com/ranking/:id` | 200, 404, 500 | Consulta a pontuação do ranking pelo ID. |
| saveRanking | POST | `https://ecoscore-java-db.onrender.com/ranking` | 201, 400, 500 | Registra uma nova pontuação no ranking. |
| updateRanking | PUT | `https://ecoscore-java-db.onrender.com/ranking/:id` | 200, 400, 404, 500 | Atualiza os votos/pontos do ranking pelo ID. |
| deleteRanking | DELETE | `https://ecoscore-java-db.onrender.com/ranking/:id` | 204, 404, 500 | Exclui um registro do ranking pelo ID. |

## 7. Usuário

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllUsuario | GET | `https://ecoscore-java-db.onrender.com/usuario` | 200, 500 | Lista todos os usuários cadastrados. |
| findByIdUsuario | GET | `https://ecoscore-java-db.onrender.com/usuario/:id` | 200, 404, 500 | Busca as informações de um usuário pelo ID. |
| saveUsuario | POST | `https://ecoscore-java-db.onrender.com/usuario` | 201, 400, 500 | Cadastra um novo usuário no sistema. |
| updateUsuario | PUT | `https://ecoscore-java-db.onrender.com/usuario/:id` | 200, 400, 404, 500 | Atualiza o perfil e estatísticas do usuário pelo ID. |
| deleteUsuario | DELETE | `https://ecoscore-java-db.onrender.com/usuario/:id` | 204, 404, 500 | Remove a conta de um usuário pelo ID. |
