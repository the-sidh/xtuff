# xtuff

Caro professor da FIAP ON responsável pela correção das atividades 1 e 12 da fase 3, por gentileza leia o presente documento para maiores informações acerca das decisões tomadas em desenvolvimento.

Esperamos que as informações abaixo auxiliem na tarefa de avaliar ambas as tarefas.

É de nosso entendimento que a correção de tantas atividades de diferentes grupos, cada uma com soluções completamente distintas das demais seja uma tarefa complexa. Esperamos assim facilitar a tarefa e poder evidenciar melhor o trabalho que realizamos.

## Sobre a escolha do MySQL

Optamos por usar MySQL, e não Oracle. Essa escolha se deu pela familiaridade que integrantes do grupo tem com o MySQL, já instalado e rodando bem em seu ambiente de desenvolvimento.

Enxergamos, inclusive, futuramente, a possibilidade de seguir com algum banco NoSQL, como MongoDB. 

Sabemos que boa parte da avaliação nesse momento é sobre Frameworks e ORM, especialmente Hibernate e JPA. Por esta razão, essas entregas ainda utilizam MySQL.

Com o entendimento de que para sua avaliação é possível que não disponha de MySQL instalado em seu ambiente, deployamos no Heroku. Iremos detalhar mais adiante como acessar o ambiente e testar nossa aplicação

## Do objetivo do nosso projeto

Aproveito o presente documento para tentar explicar alguns aspectos de negócio do nosso projeto que falhamos em evidenciar em atividades anteriores.

Nosso projeto não é sobre reservar itens para posterior retirada em lojas físicas. Isso é sim, uma das possibilidades que entregamos, mas nem de longe é o principal objetivo.

Da forma com que enxergamos, o Xtuff é um aplicativo que permite ao pequeno e médio comerciante, dono de uma loja física, facilmente colocar seus produtos a venda em ambientes de comércio eletrônico.

Para nosso MVP, estamos apenas apresentando a solução e subindo os dados de produto no nosso ambiente, onde um consumidor pode reservar o produto e retirar na loja. Mas nossa solução vai muito além disso. Nosso objetivo é integrar com outros MarketPlaces, como Magazine Luiza, Mercado Livre, Rappi, entre outros. O Comerciante poderá, a partir do nosso App, em poucos minutos, com uma facilidade sem igual, colocar seu produto disponível em diversas plataformas de vendas.

## Sobre o escopo do MVP

O que propomos a apresentar como MVP são aplicativos iOS e Android que possibilitam que o comerciante tire uma foto do produto, com o preço evidente, revise alguma informação adicional, clique em enviar e pronto! Este produto está disponível online.

Essa facilidade será alcançada integrando OCR com nosso serviço de backend apresentado no presente repositório.

## A Atividade Fase 3 Capítulo 1

Para esta atividade foi pedido que a integração do código presente na atividade anterior com o Spring.

Na nossa entrega anterior já havíamos utilizado Spring Boot, Hibernate, MySQL e Thymeleaf.

Para melhorar, e não apenas repetir a mesma entrega, fizemos duas alterações:

  - Uso de repositórios JPA ao invés de DAO 
  
  - Deploy no Heroku

Para acessar nossa aplicação no Heroku, por favor acesse [https://radiant-ravine-66287.herokuapp.com/web/]

Você pode registrar um novo usuário e a seguir criar sua loja, ou já entrar com um usuário que cadastrei previamente:

**email**: sid@equals.com

**senha**: 123

Cumprimos esta atividade, assim como a anterior, apenas pela exigência da entrega. Dento do nosso projeto, esse dashboard não faz sentido. Queremos que 100% do acesso à plataforma venha do nosso app.

Entendemos a importância de conseguirem avaliar os grupos por alguns parâmetro mínimos e também reconhecemos a importância, didaticamente falando, de ensinar Spring, Hibernate e outras tecnologias amplamente usadas no mercado. 

Dito isso, aviso que não gastamos muito tempo arrumando detalhes de front end. Você encontrará uma interface simplista e rústica. É do nosso entendimento que o que está sendo avaliado é o uso do Spring, e esperamos não ser prejudicados por não ter investido nesse aspecto em detrimento do que foi pedido no enunciado.

Tanto a API REST quanto a interface web estão nesse mesmo serviço. Configuramos o Spring de forma a possibilitar as duas formas de autentição. Explicaremos isso mais adiante, mas destaco o seguinte pacote, bem relevante para a avaliação dessa atividade:

[https://github.com/the-sidh/xtuff/tree/master/src/main/java/br/com/equals/xtuff/web/controllers/web]

É nele que se encontram os Controllers da interface Web e acreditamos ser o ponto ideal para iniciar o entendimento dessa camada.



## Atividade Fase 3 Capítulo 14

Implementamos nossa API Rest já bem extensivamente, essa sim de vital importância para nosso MVP.
No momento temos deployados 7 endpoints:

- /api/registration/  Utilizado para cadastrar um novo usuário
- /api/login  Verificar as credenciais e obter token de acesso
- /api/add-loja  Criar a loja, entidade sob a qual os produtos adicionados
- /api/add-produto Adiciona um produto na loja. Esse endpoint irá sofrer algumas alterações para integração com o app futuramente.
- /api//edit-produto Possibilita a ediçao de um produto
- /api/delete-produto/{id} Deleta um produto
- /api//produtos Lista todos os produtos do comerciante em questão
- /api/produto/{id} retorna informações de um determinado produto

#### Alguns pontos a destacar

 Achamos interessante destacar a capacidade do Spring Framework em prover mais de uma forma de autenticação.
 
 Enquanto nossa interface web utiliza um clássico login via formulário, a API Rest utiliza tokens, validados contra a mesma base de usuários. 
 
 Essa versatilidade é possível graças à possibilidade de implementar múltiplos WebSecurityConfigurerAdapter.
 
 No pacote [https://github.com/the-sidh/xtuff/tree/master/src/main/java/br/com/equals/xtuff/auth/spring/config] estão presente nossos múltiplos adaptadores.
 
 Assim como na atividade anterior, utilizamos amplamente os conceitos de clean coding e clean architecture para obter uma separação clara das diferentes camadas da nossa aplicação, deixando lógica de negócio separada de código de framework sempre que possível. Spring, infelizmente, é um pouco intrusivo nesse aspecto, fazendo uso amplo de anotações que acabam estando presentes em todas as camadas, 
 
 Nosso modelo de entidades faz uma distinção entre loja e endereço, para posteriormente podermos ter suporte a múltiplos endereços de um mesmo estabelimento. Para nosso MVP, entretanto, não fazia sentido criar múltiplos endpoints, pois iremos limitar por enquanto um estabelecimento a apenas um endereço. Para resolver isso, criamos uma entidade agregadora e um serviço que faz o papel de um adapter. Essas classes são, respectivamente [https://github.com/the-sidh/xtuff/blob/master/src/main/java/br/com/equals/xtuff/web/domain/entities/CadastroWrapper.java] e [https://github.com/the-sidh/xtuff/blob/master/src/main/java/br/com/equals/xtuff/domain/services/impl/CadastroServiceImpl.java]
 
 #### Postman
 
 Criamos uma colection do Postman para que o professor possa realizar testes na nossa API. A mesma se encontra em
 [https://github.com/the-sidh/xtuff/blob/master/Xtuff.postman_collection]
 
 Nossa collection faz uso de múltiplos ambientes, pois também usamos ela para nossos testes em ambiente locais.
 
 Disponibilizamos o arquivo com o ambiente de produçao em:
 [https://github.com/the-sidh/xtuff/blob/master/xtuff%20-%20heroku.postman_environment]
 
 Caso prefira, pode criar em seu postman sem importar o arquivo de ambientes. Basta ter com as seguintes informações:
 - key: URL
 - value: https://radiant-ravine-66287.herokuapp.com/api
 
 
 #### Roteiro sugerido de testes
 
 Sugerimos o seguinte roteiro de testes:
 1 - Registration
  Assim você criará sua entidade Comerciante
  
 2 - Get Token
  Preenchendo com seu email e senha, você terá no retorno um header com o Token. Deve ser copiado, incluindo a parte que conteem a palavra "Bearer"
  
3 - Add loja 
  A partir desse request, todos devem conter o token.
  
4 - Add produto
  Repetir este algumas vezes
  
5 - List all products

6 - Show product

7 - Delete product
  
  
 **Observações:**
 1 - A collection já está com alguns valores padrão, que podem não funcionar em produção. Sugerimos editar todos os rquests com informações novas, obedecendo a mesma estrutura
 2 - O Heroku gratuito é um pouco lento e algumas vezes retorna erro de timeout, então pode ser necessário repetir algum passo eventualmente

