# Contêineres LXD/LXD

## Alternar para usuário *jurandy*
Switch User: `su - jurandy`

## Remoção de um pacote expurgando configurações
`apt purge <pacote>`

## Registrar todos os comandos digitados
`asciinema rec -t "Título"`

## Instalação do LXD/LXC
`apt install lxd`

## Configuração inicial do LXD
`lxd init`

## Listar contêineres
`lxc list`

## Listar imagens de contêineres
`lxc image list images:`

## Lançar um contêiner a partir de um imagem
```
lxc launch images:ubuntu/16.04 ubuntu-16-04
            \_________________/ \_________/
                    1               2
                    
1: Caminho da imagem
2: Nome do contêiner que será criado
```
## Copiar contêiner
`lxc copy <orig> <dest>`

## Iniciar contêiner
`lxc start <conteiner>`

## Executar comandos no contêiner
`lxc exec <conteiner> <comando>`

Ex.:

* `lxc exec caio-teste ip addr`
* `lxc exec caio-teste bash`

## Permitir usuário executar lxc
`adduser <usuario> lxd`

Ex.: `adduser tsi5 lxd`


[![LXD/LXC: Instalação](https://asciinema.org/a/CwkWfKXuC4lK4XIIpIRpfkKJR.png)](https://asciinema.org/a/CwkWfKXuC4lK4XIIpIRpfkKJR)




lxc