<h1 align="center">Mobile-SENAC</h1>
<h3 align="justify">Sistema desenvolvido para reservar estação de trabalho.</h3>
<h3 align="justify">Projeto acadêmico da disciplina de mobile da Faculdade Senac de Pernambuco.</h3>
<h2 align="center">Lógica de funcionamento</h2>
<p align=justify">Sistema desenvolvido para efetuar as reservas das estações de trabalho. Serão cadastrados três tipos de usuários. Administrador, gestor e colaborador, cada um deles com suas funcionalidades dentro do sistema. O usuário colaborador poderá reservar, atualizar, visualizar e cancelar suas solicitações de reserva no sistema. Já o gestor terá as mesmas funcionalidades do colaborador, incluindo também reservar mais de uma estação no mesmo dia e visualizar, alterar e excluir todas as reservas feitas por outros usuários.  O usuário administrador terá todas as permissões possíveis dentro do sistema, além de todas as permissões já citadas, ele poderá também excluir, atualizar, cadastrar e visualizar usuários e estações.</p>
<h2 align="center">Equipe</h2>
                                                     <ul>
                                                     <li> 👨‍💻 Guilherme Régis - Desenvolvedor -> https://github.com/gprcarvalho </li>
                                                     <li> 👨‍💻 Mateus Rodrigues - Desenvolvedor -> https://github.com/mateusrdn </li> 
                                                     <li> 👨‍💻 Artur Bernardino - Desenvolvedor -> https://github.com/Arturlima77 </li>
                                                     <li> 👨‍💻 Gabriel Ribeiro - Scrum Master -> https://github.com/Gabriel5291 </li>
                                                     <li> 👨‍💻 Gustavo César - Tester </li>
                                                     <li> 👩‍💻 Maria Gabriella - Product Owner -> https://github.com/MGabriellaS </li>
                                                     </ul>       
<h2 align="center">Tecnologias, Linguagens e Frameworks</h2>
<ul>
                  <li>Java</li>
                  <li>Spring Boot</li>
                  <li>PostgreSQL</li>
                  <li>Insonmia</li>
                  <li>Git/Github
                  </ul>
                  <h2 align="center">Preparação do Ambiente</h2>
                                    <ul>
                                    <li>Baixar e fazer instalação do  Java  17 - https://www.azul.com/downloads/?package=jdk</li>
                                    <li>Baixar o Spring Tools 4 - https://spring.io/tools</li>
                                    <li>Fazer a instalação do lombok - https://www.youtube.com/watch?v=W0ywxkvc4_M</li>
                                    <li>Baixar e fazer instalação do PostgreSQL - https://www.postgresql.org/download/</li>
                                    <li>Baixa e instalar o Insonmia - https://insomnia.rest/download</li>
                                    </ul>
                                    <h2 align="center">Executando a API</h2>
                                                      <ol>
                                    <li>Abrir o Spring Tools 4</li>
                                    <li>Importar o projeto Maven</li>
                                    <li>Criar o database no PostgreSQL</li>
                                    <li>Inserir no arquivo de config Application.properties o user e a senha do database criado</li>
                                    <li>Abrir o Insonmia e executar as requisições com as rotas descritas no tópico abaixo</li>
                                    <li>Utilizar a porta 8080 do localhost</li>
                                                      <li>API Documentada com swagger http://localhost:8080/swagger-ui.html</li>
                                    </ol>
                                                      <h2 align="center">Funcionalidades e Rotas da API</h2>
                                                                        
                                                                        
                      | Funcionalidades User |          | Rotas API |          | Métodos de requisição HTTP |
                                    
                      | -------------------- |          | --------- |          | ------------------------- |
                      | Visualizar todos os users |     | /api/users |         | GET |
                      | Buscar usuário por ID |         | /api/user{id} |      | GET |
                      | Cadastrar Usuário |             | /api/user |          | POST |
                      | Atualizar Usuário |             | /api/user{id} |      | PUT |
                      | Deletar Usuário |               | /api |               | DELETE |
                                                      
                      | Funcionalidades Login |         | Rotas API |          | Métodos de requisição HTTP |
                                           
                      | --------------------- |         | --------- |          | -------------------------- |
                      | Realizar Login |                | /api/login |         | POST |
                                                      
                      | Funcionalidades Station |       | Rotas API |          | Método de requisição HTTP |
                                           
                      | ----------------------- |       | --------- |          | ------------------------- |
                        Visualizar todas as             
                       workstations disponíveis |      | /api/stations |      | GET |
                      | Buscar workstation por ID |     | /api/station/{id} |  | GET |
                      | Cadastrar workstation |         | /api/station |       | POST |
                      | Atualizar workstation |         | /api/station{id} |   | PUT |
                      | Deletar workstation |           | /api/station/{id} |  | DELETE |

                      | Funcionalidades Reservations |  | Rotas API |                       | Método de requisição HTTP |
                                                 
                      | ---------------------------- |  | --------- |                       | ------------------------- |
                      | Visualizar reservas efetuadas | | /api/reservations |               | GET |
                      | Buscar reservas por ID |        | /api/reservation/{id} |           | GET |
                      | Fazer uma reserva |             | /api/reservation/me |             | POST |
                      | Atualizar reserva |             | /api/reservation/{id} |           | PUT |
                      | Cancelar uma reserva |          | /api/reservation/canceled/{id} |  | PUT |
                                       
               
