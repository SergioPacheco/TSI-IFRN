---
title: "Testes: Fases de Testes de Software"
header:
  teaser: "markup-syntax-highlighting-teaser.jpg"
categories:
  - Teste de Software
tags:
  - Testes
---

# Fases de Testes de Software

Os testes devem estar presentes em todo o processo de desenvolvimento de software, geralmente são divididos nas seguintes fases:

## Testes de Unidade: 
É a fase de testes onde cada unidade do sistema é testada individualmente. O objetivo é isolar cada parte do sistema e garantir que elas estão funcionando conforme especificado, porém não garante que a integração dessas partes irá funcionar corretamente. Ex: no teste da função que valida CPF, o teste se resume apenas em checar se a função é capaz de “dizer” se o CPF é válido ou não.

## Testes de Integração: 
Nesta fase as unidades do sistema são testadas de forma combinada, o objetivo é detectar falhas na interação entre as unidades integradas. Não faz parte desta fase os testes de integração com outros sistemas. Ex: Na integração do cadastro de clientes com a função que valida CPF, as duas unidades já foram testadas individualmente na fase de testes de unidade, porém é neste momento que a interação entre elas é validada.

## Testes de Sistema: 
Nesta fase o sistema é testado completamente, ou seja, todas as funcionalidades do sistema são testadas, assim como as integrações com outros sistemas que possam existir. Os testes não se limitam apenas a requisitos funcionais, requisitos não funcionais também são testados nesta fase.

## Testes de aceitação: 
Nesta fase serão testadas as funcionalidades requeridas pelo cliente durante o projeto. Deve ser feito, preferencialmente, pelo usuário final.

Com os testes sendo executados em todas as fases do desenvolvimento é possível detectar falhas com antecedência e entregar o sistema com mais qualidade. Além disso, quanto antes a falha é detectada mais barato é para corrigi-la, evitando aumento de custo desnecessário no projeto.

Referências

http://www.tutorialspoint.com/software_testing/levels_of_testing.htm

http://softwaretestingfundamentals.com/software-testing-levels/