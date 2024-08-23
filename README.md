# challenge-meli-country-spy

## Sumário
Este projeto tem como objetivo colocar em prática um desafio que enfrentei durante um processo seletivo. Inicialmente, a ideia era construir uma solução arquitetural a partir dos requisitos, na qual considero ter obtido relativo êxito.

Aqui, no entanto, a minha proposta é colocar na prática as ferramentas escolhidas para resolver o desafio, e observar o seu comportamento.

Abaixo, coloco uma descrição do desafio e,  em seguida, o diagrama da arquitetura que desenvolvi durante uma entrevista de 1h30.

## Desafio

Para coordenar as ações de resposta à fraude, é útil ter informações contextuais
disponíveis do local de origem detectado no momento da compra, pesquisa e pagamento.
Para isso, decide-se criar uma ferramenta que, dado um IP, obtém a informação associada.
O exercício consiste em construir uma API Rest que permita:
1. Dado um endereço IP, encontre o país ao qual pertence e exiba:
   a. Informações associadas ao país.
   b. Cotação em dólares da sua moeda.
2. Bloquear um IP: marcar o IP numa lista, não permitindo consultar a informação do
   ponto 1.
3. Informar os bloqueios de IP (ponto 2) feitos às equipes interessadas de forma
   descentralizada. Tenha em mente que as equipes para as quais queremos informar
   não são conhecidas.
   Para obter as informações, as seguintes APIs podem ser usadas:
   ● API do país
   ● API de cotação de moeda
   Considerações
   ● Tenha em mente que o ponto 1 pode receber flutuações de tráfego agressivas. Faça
   uso racional das APIs, evitando fazer chamadas desnecessárias.
   ● O aplicativo não deve perder seu estado antes de um desligamento.

## Diagrama de Solução
Para resolver o item 1 do desafio, a proposta mais lógica seria uma simples interface de programação que expusesse um endpoint com as informações pedidas. Por se tratar de uma operação de read-only, o verbo HTTP escolhido foi o GET.

Partindo do pressuposto que utilizaríamos as práticas RESTful, escolhi a nomenclatura de originCountry para o recurso principal da api. Teríamos então o primeiro endpoint:

- ".../api/v1/origin-country?ip=$ip"

* Inicialmente, por puro nervosismo, eu escolhi ir por um caminho diferente - "api/v1/countryOrigin/{ip}. - parando para
  pensar, poderíamos discutir se o recurso principal da API seria origin-country ou ipAddress, mas decidi que o segundo
  seria apenas um parâmetro de busca, e a nossa API ser especializada em demonstrar informações de países.

![Draw.io Diagram](https://raw.githubusercontent.com/raimiyashiro/raw-docs/master/Untitled%20Diagram%20(2).jpg)

Ainda durante o desenho da solução, para o item 1, foi solicitado que se considerasse a performance, evitando fazer
chamadas desnecessárias para os serviços externos. Logicamente, implementar uma estratégia de caching serviria para
alguns cenários:

- Um endereço de IP é consultado, fazemos uma chamada aos serviços externos, gravando o resultado no Cache.
- Quando o mesmo IP é consultado novamente, dentro de um período estabelecido, podemos utilizar a resposta gravada no
  Cache
- Após o término deste período, caso o mesmo IP seja passado como parâmetro, é necessário fazer um update nos dados do
  registro (por hora, apenas population e currencyRateInUSD)
- É incorreto assumir que vários IPs pertencem ao mesmo país apenas por possuírem um mesmo prefixo