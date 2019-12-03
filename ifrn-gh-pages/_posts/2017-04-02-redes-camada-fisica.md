---
title: Camada Física
header:
  teaser: assets/images/markup-syntax-highlighting-teaser.jpg
categories:
  - redes
tags:
  - OSI
  - redes
published: true
---

1. Camada Física
   1. Visão geral das técnicas e meios de transmissão de dados
   2. Dispositivos de camada física
   3. Cabos, conectores
   4. Normas de Cabeamento estruturado, projetos


## As principais funções e serviços executados pela camada física são:

- Entrega bit a bit ou símbolo a símbolo
- Especificação elétrica do nível do sinal da linha de transmissão e da impedância Interface de rádio, incluindo a especificação da intensidade do sinal, largura de banda analógica, etc. .
- Especificações para IR sobre fibra óptica ou um link de comunicação sem fio IR.
- Modulação
- Line coding
- Sincronização de bits em comunicação serial síncrona
- Sinalização start-stop e controle de fluxo em comunicação serial assíncrona
- Comutação de circuitos
- Multiplexação
- Estabelecimento e encerramento de ligações comutadas por circuitos
- Senso portador e detecção de colisão utilizados por alguns protocolos de acesso múltiplo de nível 2
- Filtragem de equalização, seqüências de treinamento, modelagem de impulsos e outros processamento de sinais físicos
- Correção de erro direta, por exemplo, codificação convolucional bit a bit.
- Intercalação de bits e outra codificação de canal

## The physical layer is also concerned with:

- Bit rate
- Point-to-point, multipoint or point-to-multipoint line configuration
- Physical network topology, for example bus, ring, mesh or star network
- Serial or parallel communication
- Simplex, half duplex or full duplex transmission mode
- Autonegotiation


## Tecnologias
  
A camada mais baixa do Modelo de Referência OSI é a camada 1, a camada física; A camada física é especial em comparação com as outras camadas do modelo, porque é a única onde os dados são movidos fisicamente através da interface de rede. Todas as outras camadas executam funções úteis para criar mensagens a serem enviadas, mas todas elas devem ser transmitidas pela pilha de protocolos para a camada física, onde são realmente enviadas pela rede.

## Compreendendo o papel da camada física

O nome "camada física" pode ser um pouco problemático. Devido a esse nome, e por causa do que eu acabei de dizer sobre a camada física realmente transmitir dados, muitas pessoas que estudam rede obter a impressão de que a camada física é apenas sobre hardware de rede real. Algumas pessoas podem dizer que a camada física é "os cartões de interface de rede e cabos". No entanto, este não é o caso. A camada física define várias funções de rede, não apenas cabos de hardware e cartões.

Uma noção relacionada é que "todo o hardware de rede pertence à camada física". Novamente, isso não é estritamente preciso. Todo o hardware deve ter alguma relação com a camada física para enviar dados através da rede, mas os dispositivos de hardware geralmente implementam várias camadas do modelo OSI, incluindo a camada física, mas também outros. Por exemplo, uma placa de interface de rede Ethernet executa funções tanto na camada física quanto na camada de enlace de dados.

## Physical Layer Functions

As seguintes são as principais responsabilidades da camada física no modelo de referência OSI:

Definição de Especificações de Hardware: Os detalhes de operação de cabos, conectores, transceptores de rádio sem fio, placas de interface de rede e outros dispositivos de hardware são geralmente uma função da camada física (embora também parcialmente a camada de enlace de dados, veja abaixo).

Codificação e Sinalização: A camada física é responsável por várias funções de codificação e sinalização que transformam os dados de bits que residem dentro de um computador ou outro dispositivo em sinais que podem ser enviados através da rede.

Transmissão e Recepção de Dados: Depois de codificar os dados adequadamente, a camada física realmente transmite os dados e, é claro, recebe-os. Observe que isso se aplica igualmente a redes com e sem fio, mesmo que não haja cabo tangível em uma rede sem fio!

Topologia e Design de Rede Física: A camada física também é considerada o domínio de muitos problemas de design relacionados a hardware, como a topologia LAN e WAN.
Em geral, as tecnologias de camada física são aqueles que estão no nível mais baixo e lidam com os zeros  e ums que são enviados através da rede. Por exemplo, ao considerar dispositivos de interconexão de rede, os mais simples operam na camada física: repetidores, hubs convencionais e transceptores. Esses dispositivos não têm absolutamente nenhum conhecimento do conteúdo de uma mensagem. Eles apenas recebem os bits de entrada e enviam como saída. Dispositivos como switches e roteadores operam em camadas mais altas e olham para os dados que recebem como sendo mais de tensão ou pulsos de luz que representam um ou zero.

## Relação entre a camada física e a camada de enlace

É importante ressaltar que, enquanto a camada física de uma tecnologia de rede define principalmente o hardware que ele usa, a camada física está intimamente relacionada à camada de enlace de dados. Assim, geralmente não é possível definir hardware na camada física "independentemente" da tecnologia que está a ser utilizada na camada de ligação de dados. Por exemplo, Ethernet é uma tecnologia que descreve tipos específicos de cabos e hardware de rede, mas a camada física da Ethernet só pode ser isolada de seus aspectos da camada de enlace de dados para um ponto. Enquanto os cabos Ethernet são "camada física", por exemplo, seu comprimento máximo está relacionado de perto com as regras de formato de mensagem que existem na camada de enlace de dados.

Além disso, algumas tecnologias executam funções na camada física que estão normalmente mais estreitamente associadas à camada de enlace de dados. Por exemplo, é comum que a camada física realize o reencaminhamento de baixo nível (nível de bits) de estruturas de camada de enlace de dados para transmissão. A detecção e correção de erros também pode ser feita na camada 1 em alguns casos. A maioria das pessoas consideraria essas "funções de camada dois".

Em muitas tecnologias, várias camadas físicas podem ser usadas com uma camada de enlace de dados. Novamente aqui, o exemplo clássico é Ethernet, onde existem dezenas de diferentes implementações de camada física, cada uma das quais usa a mesma camada de enlace de dados (possivelmente com pequenas variações).

## Subcamadas da camada física.

Finalmente, muitas tecnologias subdividem ainda mais a camada física em subcamadas. Para aumentar o desempenho, os métodos de codificação e transmissão da camada física tornaram-se mais complexos ao longo do tempo. A camada física pode ser dividida em camadas para permitir que diferentes mídias de rede sejam suportadas pela mesma tecnologia, enquanto compartilham outras funções na camada física que são comuns entre os vários meios. Um bom exemplo disto é a arquitetura de camada física usada para Fast Ethernet, Gigabit Ethernet e Ethernet de 10 Gigabit.
