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
| findAllAcao | GET | `http://ecoscore-java-db.onrender.com/acao` | 200, 500 | Lista todas as ações cadastradas. |
| findByIdAcao | GET | `http://ecoscore-java-db.onrender.com/acao/:id` | 200, 404, 500 | Busca uma ação específica pelo ID. |
| saveAcao | POST | `http://ecoscore-java-db.onrender.com/acao` | 201, 400, 500 | Cadastra uma nova ação. |
| updateAcao | PUT | `http://ecoscore-java-db.onrender.com/acao/:id` | 200, 400, 404, 500 | Atualiza os dados de uma ação pelo ID. |
| deleteAcao | DELETE | `http://ecoscore-java-db.onrender.com/acao/:id` | 204, 404, 500 | Remove uma ação pelo ID. |
| findAllMissao | GET | `http://ecoscore-java-db.onrender.com/missao` | 200, 500 | Lista todas as missões disponíveis. |
| findByIdMissao | GET | `http://ecoscore-java-db.onrender.com/missao/:id` | 200, 404, 500 | Busca uma missão específica pelo ID. |
| saveMissao | POST | `http://ecoscore-java-db.onrender.com/missao` | 201, 400, 500 | Cadastra uma nova missão. |
| updateMissao | PUT | `http://ecoscore-java-db.onrender.com/missao/:id` | 200, 400, 404, 500 | Atualiza uma missão existente pelo ID. |
| deleteMissao | DELETE | `http://ecoscore-java-db.onrender.com/missao/:id` | 204, 404, 500 | Remove uma missão pelo ID. |
| findAllQuiz | GET | `http://ecoscore-java-db.onrender.com/quiz` | 200, 500 | Lista todos os quizzes registrados. |
| findByIdQuiz | GET | `http://ecoscore-java-db.onrender.com/quiz/:id` | 200, 404, 500 | Busca os dados de um quiz pelo ID. |
| saveQuiz | POST | `http://ecoscore-java-db.onrender.com/quiz` | 201, 400, 500 | Registra a realização de um novo quiz. |
| updateQuiz | PUT | `http://ecoscore-java-db.onrender.com/quiz/:id` | 200, 400, 404, 500 | Atualiza um registro de quiz pelo ID. |
| deleteQuiz | DELETE | `http://ecoscore-java-db.onrender.com/quiz/:id` | 204, 404, 500 | Exclui um registro de quiz pelo ID. |
| findAllPost | GET | `http://ecoscore-java-db.onrender.com/post` | 200, 500 | Lista todos os posts da comunidade. |
| findByIdPost | GET | `http://ecoscore-java-db.onrender.com/post/:id` | 200, 404, 500 | Busca um post específico pelo ID. |
| savePost | POST | `http://ecoscore-java-db.onrender.com/post` | 201, 400, 500 | Cria uma nova publicação. |
| updatePost | PUT | `http://ecoscore-java-db.onrender.com/post/:id` | 200, 400, 404, 500 | Atualiza o conteúdo/votos de um post pelo ID. |
| deletePost | DELETE | `http://ecoscore-java-db.onrender.com/post/:id` | 204, 404, 500 | Apaga uma publicação pelo ID. |
| findAllRanking | GET | `http://ecoscore-java-db.onrender.com/ranking` | 200, 500 | Lista as posições do ranking geral. |
| findByIdRanking | GET | `http://ecoscore-java-db.onrender.com/ranking/:id` | 200, 404, 500 | Consulta a pontuação do ranking pelo ID. |
| saveRanking | POST | `http://ecoscore-java-db.onrender.com/ranking` | 201, 400, 500 | Registra uma nova pontuação no ranking. |
| updateRanking | PUT | `http://ecoscore-java-db.onrender.com/ranking/:id` | 200, 400, 404, 500 | Atualiza os votos/pontos do ranking pelo ID. |
| deleteRanking | DELETE | `http://ecoscore-java-db.onrender.com/ranking/:id` | 204, 404, 500 | Exclui um registro do ranking pelo ID. |
| findAllUsuario | GET | `http://ecoscore-java-db.onrender.com/usuario` | 200, 500 | Lista todos os usuários cadastrados. |
| findByIdUsuario | GET | `http://ecoscore-java-db.onrender.com/usuario/:id` | 200, 404, 500 | Busca as informações de um usuário pelo ID. |
| saveUsuario | POST | `http://ecoscore-java-db.onrender.com/usuario` | 201, 400, 500 | Cadastra um novo usuário no sistema. |
| updateUsuario | PUT | `http://ecoscore-java-db.onrender.com/usuario/:id` | 200, 400, 404, 500 | Atualiza o perfil e estatísticas do usuário pelo ID. |
| deleteUsuario | DELETE | `http://ecoscore-java-db.onrender.com/usuario/:id` | 204, 404, 500 | Remove a conta de um usuário pelo ID. |

## 2. Ação

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllAcao | GET | `http://ecoscore-java-db.onrender.com/acao` | 200, 500 | Lista todas as ações cadastradas. |
| findByIdAcao | GET | `http://ecoscore-java-db.onrender.com/acao/:id` | 200, 404, 500 | Busca uma ação específica pelo ID. |
| saveAcao | POST | `http://ecoscore-java-db.onrender.com/acao` | 201, 400, 500 | Cadastra uma nova ação. |
| updateAcao | PUT | `http://ecoscore-java-db.onrender.com/acao/:id` | 200, 400, 404, 500 | Atualiza os dados de uma ação pelo ID. |
| deleteAcao | DELETE | `http://ecoscore-java-db.onrender.com/acao/:id` | 204, 404, 500 | Remove uma ação pelo ID. |

## 3. Missão

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllMissao | GET | `http://ecoscore-java-db.onrender.com/missao` | 200, 500 | Lista todas as missões disponíveis. |
| findByIdMissao | GET | `http://ecoscore-java-db.onrender.com/missao/:id` | 200, 404, 500 | Busca uma missão específica pelo ID. |
| saveMissao | POST | `http://ecoscore-java-db.onrender.com/missao` | 201, 400, 500 | Cadastra uma nova missão. |
| updateMissao | PUT | `http://ecoscore-java-db.onrender.com/missao/:id` | 200, 400, 404, 500 | Atualiza uma missão existente pelo ID. |
| deleteMissao | DELETE | `http://ecoscore-java-db.onrender.com/missao/:id` | 204, 404, 500 | Remove uma missão pelo ID. |

## 4. Post

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllPost | GET | `http://ecoscore-java-db.onrender.com/post` | 200, 500 | Lista todos os posts da comunidade. |
| findByIdPost | GET | `http://ecoscore-java-db.onrender.com/post/:id` | 200, 404, 500 | Busca um post específico pelo ID. |
| savePost | POST | `http://ecoscore-java-db.onrender.com/post` | 201, 400, 500 | Cria uma nova publicação. |
| updatePost | PUT | `http://ecoscore-java-db.onrender.com/post/:id` | 200, 400, 404, 500 | Atualiza o conteúdo/votos de um post pelo ID. |
| deletePost | DELETE | `http://ecoscore-java-db.onrender.com/post/:id` | 204, 404, 500 | Apaga uma publicação pelo ID. |

## 5. Quiz

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllQuiz | GET | `http://ecoscore-java-db.onrender.com/quiz` | 200, 500 | Lista todos os quizzes registrados. |
| findByIdQuiz | GET | `http://ecoscore-java-db.onrender.com/quiz/:id` | 200, 404, 500 | Busca os dados de um quiz pelo ID. |
| saveQuiz | POST | `http://ecoscore-java-db.onrender.com/quiz` | 201, 400, 500 | Registra a realização de um novo quiz. |
| updateQuiz | PUT | `http://ecoscore-java-db.onrender.com/quiz/:id` | 200, 400, 404, 500 | Atualiza um registro de quiz pelo ID. |
| deleteQuiz | DELETE | `http://ecoscore-java-db.onrender.com/quiz/:id` | 204, 404, 500 | Exclui um registro de quiz pelo ID. |

## 6. Ranking

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllRanking | GET | `http://ecoscore-java-db.onrender.com/ranking` | 200, 500 | Lista as posições do ranking geral. |
| findByIdRanking | GET | `http://ecoscore-java-db.onrender.com/ranking/:id` | 200, 404, 500 | Consulta a pontuação do ranking pelo ID. |
| saveRanking | POST | `http://ecoscore-java-db.onrender.com/ranking` | 201, 400, 500 | Registra uma nova pontuação no ranking. |
| updateRanking | PUT | `http://ecoscore-java-db.onrender.com/ranking/:id` | 200, 400, 404, 500 | Atualiza os votos/pontos do ranking pelo ID. |
| deleteRanking | DELETE | `http://ecoscore-java-db.onrender.com/ranking/:id` | 204, 404, 500 | Exclui um registro do ranking pelo ID. |

## 7. Usuário

| **Nome** | **Método** | **Endereço** | **Status** | **Explicação** |
| --- | --- | --- | --- | --- |
| findAllUsuario | GET | `http://ecoscore-java-db.onrender.com/usuario` | 200, 500 | Lista todos os usuários cadastrados. |
| findByIdUsuario | GET | `http://ecoscore-java-db.onrender.com/usuario/:id` | 200, 404, 500 | Busca as informações de um usuário pelo ID. |
| saveUsuario | POST | `http://ecoscore-java-db.onrender.com/usuario` | 201, 400, 500 | Cadastra um novo usuário no sistema. |
| updateUsuario | PUT | `http://ecoscore-java-db.onrender.com/usuario/:id` | 200, 400, 404, 500 | Atualiza o perfil e estatísticas do usuário pelo ID. |
| deleteUsuario | DELETE | `http://ecoscore-java-db.onrender.com/usuario/:id` | 204, 404, 500 | Remove a conta de um usuário pelo ID. |
