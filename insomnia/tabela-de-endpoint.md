# Tabelas de Endpoints
Diferentes tabelas, a geral são todas as requisições juntas, as outras estão separadas por classe.
- [[#1. Geral]]
- [[#2. Ação]]
- [[#3. Missão]]
- [[#4. Post]]
- [[#5. Quiz]]
- [[#6. Ranking]]
- [[#7. Usuário]]
## 1. Geral
| Nome                | Método | Endereço                            | Explicação                                           |
| ------------------- | ------ | ----------------------------------- | ---------------------------------------------------- |
| **findAllAcao**     | GET    | `http://localhost:8080/acao`        | Lista todas as ações cadastradas.                    |
| **findByIdAcao**    | GET    | `http://localhost:8080/acao/:id`    | Busca uma ação específica pelo ID.                   |
| **saveAcao**        | POST   | `http://localhost:8080/acao`        | Cadastra uma nova ação.                              |
| **updateAcao**      | PUT    | `http://localhost:8080/acao/:id`    | Atualiza os dados de uma ação pelo ID.               |
| **deleteAcao**      | DELETE | `http://localhost:8080/acao/:id`    | Remove uma ação pelo ID.                             |
| **findAllMissao**   | GET    | `http://localhost:8080/missao`      | Lista todas as missões disponíveis.                  |
| **findByIdMissao**  | GET    | `http://localhost:8080/missao/:id`  | Busca uma missão específica pelo ID.                 |
| **saveMissao**      | POST   | `http://localhost:8080/missao`      | Cadastra uma nova missão.                            |
| **updateMissao**    | PUT    | `http://localhost:8080/missao/:id`  | Atualiza uma missão existente pelo ID.               |
| **deleteMissao**    | DELETE | `http://localhost:8080/missao/:id`  | Remove uma missão pelo ID.                           |
| **findAllQuiz**     | GET    | `http://localhost:8080/quiz`        | Lista todos os quizzes registrados.                  |
| **findByIdQuiz**    | GET    | `http://localhost:8080/quiz/:id`    | Busca os dados de um quiz pelo ID.                   |
| **saveQuiz**        | POST   | `http://localhost:8080/quiz`        | Registra a realização de um novo quiz.               |
| **updateQuiz**      | PUT    | `http://localhost:8080/quiz/:id`    | Atualiza um registro de quiz pelo ID.                |
| **deleteQuiz**      | DELETE | `http://localhost:8080/quiz/:id`    | Exclui um registro de quiz pelo ID.                  |
| **findAllPost**     | GET    | `http://localhost:8080/post`        | Lista todos os posts da comunidade.                  |
| **findByIdPost**    | GET    | `http://localhost:8080/post/:id`    | Busca um post específico pelo ID.                    |
| **savePost**        | POST   | `http://localhost:8080/post`        | Cria uma nova publicação.                            |
| **updatePost**      | PUT    | `http://localhost:8080/post/:id`    | Atualiza o conteúdo/votos de um post pelo ID.        |
| **deletePost**      | DELETE | `http://localhost:8080/post/:id`    | Apaga uma publicação pelo ID.                        |
| **findAllRanking**  | GET    | `http://localhost:8080/ranking`     | Lista as posições do ranking geral.                  |
| **findByIdRanking** | GET    | `http://localhost:8080/ranking/:id` | Consulta a pontuação do ranking pelo ID.             |
| **saveRanking**     | POST   | `http://localhost:8080/ranking`     | Registra uma nova pontuação no ranking.              |
| **updateRanking**   | PUT    | `http://localhost:8080/ranking/:id` | Atualiza os votos/pontos do ranking pelo ID.         |
| **deleteRanking**   | DELETE | `http://localhost:8080/ranking/:id` | Exclui um registro do ranking pelo ID.               |
| **findAllUsuario**  | GET    | `http://localhost:8080/usuario`     | Lista todos os usuários cadastrados.                 |
| **findByIdUsuario** | GET    | `http://localhost:8080/usuario/:id` | Busca as informações de um usuário pelo ID.          |
| **saveUsuario**     | POST   | `http://localhost:8080/usuario`     | Cadastra um novo usuário no sistema.                 |
| **updateUsuario**   | PUT    | `http://localhost:8080/usuario/:id` | Atualiza o perfil e estatísticas do usuário pelo ID. |
| **deleteUsuario**   | DELETE | `http://localhost:8080/usuario/:id` | Remove a conta de um usuário pelo ID.                |

## 2. Ação
| Nome             | Método | Endereço                         | Explicação                                      |
| ---------------- | ------ | -------------------------------- | ----------------------------------------------- |
| **findAllAcao**  | GET    | `http://localhost:8080/acao`     | Lista todas as ações cadastradas.      |
| **findByIdAcao** | GET    | `http://localhost:8080/acao/:id` | Busca uma ação específica pelo ID.     |
| **saveAcao**     | POST   | `http://localhost:8080/acao`     | Cadastra uma nova ação.                |
| **updateAcao**   | PUT    | `http://localhost:8080/acao/:id` | Atualiza os dados de uma ação pelo ID. |
| **deleteAcao**   | DELETE | `http://localhost:8080/acao/:id` | Remove uma ação pelo ID.               |

## 3. Missão
| Nome               | Método | Endereço                           | Explicação                                      |
| ------------------ | ------ | ---------------------------------- | ----------------------------------------------- |
| **findAllMissao**  | GET    | `http://localhost:8080/missao`     | Lista todas as missões disponíveis.    |
| **findByIdMissao** | GET    | `http://localhost:8080/missao/:id` | Busca uma missão específica pelo ID.   |
| **saveMissao**     | POST   | `http://localhost:8080/missao`     | Cadastra uma nova missão.              |
| **updateMissao**   | PUT    | `http://localhost:8080/missao/:id` | Atualiza uma missão existente pelo ID. |
| **deleteMissao**   | DELETE | `http://localhost:8080/missao/:id` | Remove uma missão pelo ID.             |
## 4. Post
| Nome | Método | Endereço | Explicação |
| --- | --- | --- | --- |
| **findAllPost** | GET | `http://localhost:8080/post` | Lista todos os posts da comunidade. |
| **findByIdPost** | GET | `http://localhost:8080/post/:id` | Busca um post específico pelo ID. |
| **savePost** | POST | `http://localhost:8080/post` | Cria uma nova publicação. |
| **updatePost** | PUT | `http://localhost:8080/post/:id` | Atualiza o conteúdo/votos de um post pelo ID. |
| **deletePost** | DELETE | `http://localhost:8080/post/:id` | Apaga uma publicação pelo ID. |

## 5. Quiz
| Nome | Método | Endereço | Explicação |
| --- | --- | --- | --- |
| **findAllQuiz** | GET | `http://localhost:8080/quiz` | Lista todos os quizzes registrados. |
| **findByIdQuiz** | GET | `http://localhost:8080/quiz/:id` | Busca os dados de um quiz pelo ID. |
| **saveQuiz** | POST | `http://localhost:8080/quiz` | Registra a realização de um novo quiz. |
| **updateQuiz** | PUT | `http://localhost:8080/quiz/:id` | Atualiza um registro de quiz pelo ID. |
| **deleteQuiz** | DELETE | `http://localhost:8080/quiz/:id` | Exclui um registro de quiz pelo ID. |

## 6. Ranking
| Nome | Método | Endereço | Explicação |
| --- | --- | --- | --- |
| **findAllRanking** | GET | `http://localhost:8080/ranking` | Lista as posições do ranking geral. |
| **findByIdRanking** | GET | `http://localhost:8080/ranking/:id` | Consulta a pontuação do ranking pelo ID. |
| **saveRanking** | POST | `http://localhost:8080/ranking` | Registra uma nova pontuação no ranking. |
| **updateRanking** | PUT | `http://localhost:8080/ranking/:id` | Atualiza os votos/pontos do ranking pelo ID. |
| **deleteRanking** | DELETE | `http://localhost:8080/ranking/:id` | Exclui um registro do ranking pelo ID. |

## 7. Usuário
| Nome | Método | Endereço | Explicação |
| --- | --- | --- | --- |
| **findAllUsuario** | GET | `http://localhost:8080/usuario` | Lista todos os usuários cadastrados. |
| **findByIdUsuario** | GET | `http://localhost:8080/usuario/:id` | Busca as informações de um usuário pelo ID. |
| **saveUsuario** | POST | `http://localhost:8080/usuario` | Cadastra um novo usuário no sistema. |
| **updateUsuario** | PUT | `http://localhost:8080/usuario/:id` | Atualiza o perfil e estatísticas do usuário pelo ID. |
| **deleteUsuario** | DELETE | `http://localhost:8080/usuario/:id` | Remove a conta de um usuário pelo ID. |