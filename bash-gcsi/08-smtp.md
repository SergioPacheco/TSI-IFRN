# Correio eletrônico (SMTP e POP3)

## Instalar postfix 
sudo apt-get install postfix

> Escolha a opção Site Internet
> Domínio na Internet

## Editar o arquivo /etc/postfix 
sudo vim /etc/postfix/main.cf

> mydestination = pplware-servidor-email.br, localhost.localdomain, localhost
> mynetworks = 127.0.0.0/8 192.168.1.0/24
> inet_protocols = ipv4 
> home_mailbox = Maildir/

## Reinicializar o serviço Postfix 
sudo systemctl start postfix

## Verificando o servidor de email 
netstat -tnl 

## Instalação do dovecot 
sudo apt-get install dovecot-imapd dovecot-pop3d

## Editar o arquivo /etc/dovecot/dovecot.conf
sudo vim /etc/dovecot/dovecot.conf

> protocols = imap pop3
> listen = * 

## Editar o arquivo /etc/dovecot/conf.d 
sudo vim /etc/dovecot/conf.d 

> disable_plaintext_auth = yes
> auth_mechanisms = plain login

## Editar o arquivo /etc/dovecot/conf.d 
sudo vim /etc/dovecot/conf.d 

## Editar o arquivo /etc/dovecot/10-mail.conf 
sudo vim /etc/dovecot/10-mail.conf 

> mail_location = maildir:/home/%u/Maildir 

## Editar o arquivo /etc/dovecot/10-master.conf 
sudo vim /etc/dovecot/10-master.conf
> service imap-login
> port = 143

> service pop3-login 
> port = 110

> unix_listener 
> mode = 0600 
> user = postfix 
> group = postfix 

# Reinicializando o serviço dovecot 
sudo systemctl restart dovecot

# Verificando as conexões de rede do servidor 
netstart -tnl 








