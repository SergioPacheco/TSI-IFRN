# Publicação no servidor Web

* Caminho local para publicação: `/var/www/html`
* Caminho remoto via SFTP: sftp://<usuario>@<servidor>/var/www/html

## Permissões do diretório raiz (DocumentRoot)

* Comando: `ls -ld /var/www/html`
* Saída: `drwxrwxrwt 11 root root 4096 abr  5 16:05 /var/www/html/`

| Permissões | Número de ligações | Usuário | Grupo | Tamanho | Mês | Dia | Hora | Caminho |
| ---        | ---                | ---     | ---   | ---     | --- | --- | ---  | --- |
| drwxrwxrwt | 11                 | root    | root  | 4096    | abr | 5   | 16:05| /var/www/html |

Antes de aprender os comandos, lembre-se das palavras em Inglês:
* Mudar: change
* Proprietário: owner
* Grupo: grp

Para descobrir os comandos, experimente: `ch<TAB><TAB>`

Para trocar, use os seguintes comandos:
* Usuário: `chown`
* Grupo: `chgrp`

Para descobrir como utilizar, experimente executar os comandos e ver a saída. 

