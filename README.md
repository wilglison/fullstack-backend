
# Tarefa Backend
---
## Slides de Aula - 1º Encontro (v3)
- [x] Revisar o material para ter uma visão geral do conteúdo.
### Slides 14 e 15: 
- [x] Implementação de um Servlet de Calculadora
    - [x] Criar um Novo Servlet:
        - [x] Crie um servlet chamado CalculatorServlet que manipule requisições GET.
        - [x] O servlet deve realizar a operação matemática com base nos parâmetros recebidos na requisição.
    - [x] Configurar o Servlet:
        - [x] Adicione as configurações do servlet no arquivo web.xml.
    - [x] Implementar a Lógica do Servlet:
        - [x] No método Get, leia os parâmetros num1, num2, e operacao da requisição e responda com o resultado da operação.
        - [x] operacao pode ser um valor entre: soma, subtracao, multiplicacao, divisao
        - [x] Realize validação dos parâmetros (num1 e num2 devem ser valores numéricos).
        - [x] Dica: utilize o método request.getParameter() para pegar um parâmetro da requisição.
    - [x] Testar o Servlet:
        - [x] Use o Postman ou um navegador para enviar requisições GET ao servlet.
        - [x] Verifique se as respostas são geradas corretamente.


### Slides 27 a 31: 
- [x] Gerenciamento de Tarefas com Manipulação de Dados em Memória
>Você precisa criar uma aplicação web simples para gerenciar uma lista de tarefas. Esta aplicação permitirá que os usuários adicionem, visualizem, atualizem e excluam tarefas. O gerenciamento das tarefas será feito em memória e não persistirá após o desligamento do servidor.
- [x] Criar um servlet chamado TaskServlet que suporte os seguintes métodos HTTP:
    - [x] GET: Para listar todas as tarefas.
    - [x] POST: Para adicionar uma nova tarefa.
    - [x] PUT: Para atualizar uma tarefa existente.
    - [x] DELETE: Para remover uma tarefa.
- [x] Armazenamento em Memória:
    - [x] Utilize uma estrutura de dados adequada (por exemplo, uma List String>) para armazenar as tarefas em memória.
- [x] Métodos HTTP e suas Funcionalidades:
    - [x] GET /tasks:
        - [x] Retorne todas as tarefas na lista.
        - [x] Se não houver tarefas, retorne uma mensagem indicando que não há tarefas disponíveis.
    - [x] POST /tasks:
        - [x] Adicione uma nova tarefa à lista.
        - [x] A tarefa deve ser enviada como um parâmetro chamado task.
    - [x] PUT /tasks:
        - [x] Atualize uma tarefa existente na lista.
        - [x] A requisição deve conter os parâmetros index (índice da tarefa na lista, começando em 1) e task (novo valor da tarefa).
        - [x] Se o índice estiver fora do intervalo ou não for válido, retorne uma mensagem de erro apropriada.
    - [x] DELETE /tasks:
        - [x] Remova uma tarefa da lista.
        - [x] A requisição deve conter o parâmetro index (índice da tarefa na lista, começando em 1).
        - [x] Se o índice estiver fora do intervalo ou não for válido, retorne uma mensagem de erro apropriada.
    - [x]Dicas
        - ○ Certifique-se de que os parâmetros recebidos nas requisições são válidos antes de realizar qualquer operação.
        - ○ Utilize try-catch para tratar exceções e retornar mensagens de erro apropriadas.
        - ○ Teste exaustivamente cada método HTTP para garantir que todas as funcionalidades estão implementadas corretamente.
        
### Slides 40 a 43: 
- [x] Implementação de IoC e Injeção de Dependências na Aplicação de Gerenciamento de Tarefas
>Você precisa refatorar a aplicação de gerenciamento de tarefas para utilizar IoC e Injeção de Dependências. A aplicação deve ser capaz de gerenciar tarefas
(adicionar, listar, atualizar e remover) e utilizar serviços de notificação (por e-mail e SMS) para notificar o usuário sobre a adição de novas tarefas.

- [x] Criar uma Interface de Serviço de Notificação:
    - [x] Crie uma interface NotificacaoService com um método enviarNotificacao.
- [x] Implementar Serviços de Notificação:
    - [x] Crie duas classes que implementam a interface NotificacaoService:
        - [x] EmailNotificacaoService e SMSNotificacaoService.
    - [x] Cada classe deve implementar o método enviarNotificacao para simular o envio de uma notificação.
- [x] Refatorar o serviço de gerenciamento de Tarefas:
    - [x] Crie uma classe TaskService que use o serviço de notificação.
    - [x] Use injeção de dependências (por construtor) para injetar a dependência do serviço de notificação no cliente de tarefas.
    - [x] Adicione os métodos adicionar, atualizar, remover e listar que notifique o usuário no final da execução.
        - [x] Utilize partes do código da classe TaskServlet original para construção de tais métodos.
- [x] Atualizar o Servlet de Gerenciamento de Tarefas:
    - [x] Atualize o servlet TaskServlet para utilizar a classe TarefaService ao invés de gerenciar diretamente as tarefas.
    - [x] Use injeção de dependências para configurar o serviço de notificação utilizado pelo TarefaService.

---

## ORM e Hibernate:
### Slides 17 a 23
- [x] Completar Funcionalidades CRUD para Produtos.

- Objetivo
    - [x] Implementar as funcionalidades restantes para o CRUD (Create, Read, Update, Delete) de produtos utilizando Spring Boot e JPA.
- Descrição
    - [x] Completar a aplicação para suportar todas as operações CRUD para a entidade Produto.
    - [x] Adicionar as funcionalidades de atualização (Update) e exclusão (Delete) de produtos. 
    - [x] Criar as páginas HTML necessárias para suportar essas operações.
- Atualizar Produto:
    - [x] Criar um endpoint GET para exibir o formulário de edição de um produto.
    - [x] Criar um endpoint POST para processar a atualização de um produto.
- Deletar Produto
    - [x] Criar um endpoint POST para processar a exclusão de um produto.


- Testar a Aplicação
    - [ ] Execute a aplicação e verifique se todas as operações CRUD estão funcionando corretamente.
    - [ ] Verifique a adição, edição e exclusão de produtos e veja se as mensagens de sucesso são exibidas corretamente.

---

## Spring Data e Single Page Application (v2): 
### Slide 22
- [x] Crie os endpoints para CRUD das entidades Categoria e Tag.
    - [x] Criar CategoriaRepository e TagRepository aos moldes do ProdutoRepository.
    - [x] Criar CategoriaService e TagService com base no ProdutoService.
    - [x] Criar CategoriaController e TagController com base no ProdutoController.
- [ ] Desafio: Implementar o frontend simples de uma SPA para consumir os endpoints para CRUD de produtos.
    - [ ] A utilização de framework Javascript é opcional. Alguns exemplos de frameworks que podem ser utilizados: React, Vue ou Angular.

---
## Principios S.O.L.I.D.: 
### Slide 20
- [ ] Garantir os 5 princípios S.O.L.I.D. na arquitetura do projeto final.
- Leitura Complementar:
    - https://www.freecodecamp.org/portuguese/news/os-principios-solid-da-programacao-orientada-a-objetos-explicados-em-bom-portugues/
    - https://www.baeldung.com/solid-principles

---
## Testes de Unidade e Integração: 
### Slide 17 
- [ ] Completar Testes Unitários e de Integração
- Objetivo
    - [ ] Implementar os testes para as funcionalidades restantes para manipulação de produtos.
    - [ ] Criar testes unitários para os métodos do serviço e de integração para os métodos do controlador.
- Descrição
    - [ ] Criar testes unitários para os métodos do serviço.
    - [ ] Utilize mocks para os testes unitários conforme necessário.
    - [ ] Criar testes de integração para os métodos do controlador.

---
## Spring Security: 
### Slide 14
- [ ] Utilizar Spring Security no Projeto Final
- Objetivo
    - [ ] Configurar autenticação e autorização da aplicação do projeto final utilizando Spring Security.
- Descrição
    - [ ] Criar classe de configuração do spring security.
    - [ ] Modelar os usuários do sistema.
    - [ ] Configurar as roles (papeis) para diferentes tipos de usuários.