# Projeto-API-springboot
API REST desenvolvida em Java com Spring Boot para gerenciamento de usuários, salas, laboratórios e reservas. Utiliza Spring Data JPA, SQL Server, Lombok e MapStruct, com validações e tratamento de erros.

Projeto da API SPRING BOOT
2º semestre
O objetivo desse documento é explicar o escopo do Projeto da API no Spring Boot a ser
desenvolvido agora no 2º semestre.
A nota desse trabalho será utilizada na disciplina Sistemas de Computação.
A plataforma de programação que deverá ser utilizada para o desenvolvimento desse projeto é
o Spring Boot (Java).
Qual o tema do Projeto?
O tema do projeto é sistema de RESERVA DE LABORATÓRIO e SALAS DE AULA.
Quantos alunos terão em cada equipe de projeto?
Cada equipe de projeto terá no máximo dois integrantes (dupla de alunos).
Qual o escopo do sistema RESERVA DE LABORATÓRIO e SALAS DE AULA?
O sistema de Reserva de Laboratórios e Salas de Aula deverá ter as seguintes
funcionalidades:
→ Cadastro de usuário do sistema (login/senha)
Esta funcionalidade refere-se ao cadastro do usuário que usará o sistema.
O cadastro de usuário deve ter: CPF, nome completo, data de aniversário, celular e e-
mail.
→ Login de Acesso ao sistema
Tela de acesso ao sistema solicitando o login e senha.
→ Cadastro de Laboratórios
Esta funcionalidade refere-se ao cadastro dos recursos que poderão ser reservados.
Entende-se que laboratório é um recurso que pode ser reservado.
O cadastro de laboratório deve ter: código, nome, capacidade e localização.
→ Cadastro de Salas
Esta funcionalidade refere-se ao cadastro dos recursos que poderão ser reservados.
Entende-se que sala é um recurso que pode ser reservado.
O cadastro de sala deve ter: código, nome, capacidade e localização.
→ Cadastro de Status
Esta funcionalidade refere-se ao cadastro do status dos recursos que poderão ser
reservados.
O cadastro de status deve ter: código e nome.
Entende-se status da reserva como:
Livre
Ocupado
Bloqueado
Reservado
→ Reserva de Laboratórios e Salas (recurso = laboratório e sala)
Esta funcionalidade refere-se ao cadastro de reserva dos recursos.
Para fazer uma reserva é necessário os seguintes dados: data inicial e final da reserva,
hora inicial e final da reserva, usuário que está fazendo a reserva, código do recurso que
está sendo reservado e status da reserva.
Sobre o status do recurso, entende-se que: Livre (recurso ainda não está reservado),
Ocupado (o recurso está sendo utilizando na data e hora reservada), Bloqueado (recurso
está em manutenção, por exemplo. Nesse caso o recurso não poderá ser reservado) e
Reservado (o recurso está reservado, porém ainda não ocupado).
→ Consultas das reservas por recurso, data, horário e usuário
Esta funcionalidade refere-se a uma tela de consulta onde será possível verificar a
reserva do recurso com os seguintes filtros: código e nome do recurso, data ou período
da reserva, hora da reserva, usuário que fez a reserva e status da reserva.
Além disso, deverá ser possível fazer a combinação dos filtros acima na pesquisa de
uma reserva.
Outras consultas necessárias além da reserva:
- consulta dos dados os usuários cadastrados por e-mail e data de aniversário
- consulta dos status cadastrados
- consulta dos recursos (sala e laboratório) por nome, capacidade e localização
Qual será a data de entrega do projeto?
A entrega TOTAL do projeto deverá ser feita ATÉ dia 25/09/26 às 18h.
A entrega será feita EXCLUSIVAMENTE via classroom em uma atividade que será aberta na
disciplina Sistema de Computação.
Não será aceito entrega via e-mail e nem depois do prazo previamente marcado acima.
A nota desse projeto será a média do 3º bimestre.
Caso ocorra da dupla não fazer a entrega do projeto até dia 25/09/26 às 18h seus integrantes
poderão ficar com média zero no 3º bimestre.
OBSERVAÇÃO: compactar os arquivos do projeto nomeando-o com os RAs da dupla.
Segue exemplo: 24123-24321.zip ou 24123-24321.rar
Por favor, somente um integrante da dupla fará a entrega na atividade no classroom.
Entregas onde o arquivo compactado não estiver com a nomenclatura correta solicitada
perderá 2,0 pontos na nota do projeto.
O que será avaliado nesse projeto?
Serão avaliados os seguintes pontos:
 Modelagem e criação do BD a ser utilizado no projeto. Preferencialmente, utilizar o SQL
SERVER.
 A API deverá conter TODOS os endpoints necessários para atender as funcionalidades
do sistema RESERVA DE LABORATÓRIO e SALAS DE AULA.
 Utilização das bibliotecas do Spring Boot: Spring Web, Spring Data JPA, Project
Lombok, MapStruct, Spring Test, etc.
 Validação de campos de cada cadastro.
 Tratativa de erros com mensagens personalizadas.
 Como nesse projeto NÃO terá FrontEnd os endpoints serão testados via Insomia ou
Swagger.
OBSERVAÇÕES IMPORTANTES
1) Se a dupla consultar livros, Internet, vídeos e IA para pesquisar e estudar sobre a
codificação do projeto será necessário colocar na BIBLIOGRAFIA de desenvolvimento
do projeto.
2) Caso ocorra da dupla, por qualquer razão, decidir não concluir o projeto juntos, cada
integrante fará o projeto sozinho.
Bom trabalho a todos!
