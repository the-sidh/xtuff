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

Para nosso MVP, estamos apenas apresentando a solução e subindo os dados de produto no nosso ambiente, onde um consumidor pode reservar o produto e retirar na loja. Mas nossa solução vai muito além disso. Nosso objetivo é integrar com outros MarketPlaces, como Magazine Luiza, Mercado Livre, Rappi, entre outros. O Comerciante poderá, a partir do nosso App, em poucos minutos, com uma facilidade sem igual, colocar seu produto disponível em diversas plataformas de venda.
