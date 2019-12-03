# Servidores de arquivo com Samba

### Atualizando  
sudo apt update 
sudo apt upgrade
sudo apt dist-upgrade
sudo apt clean

### Editando o arquivo /etc/fstab, acrescentando **acl** na ext4

### Trocar nome do hostname 
sudo hostnamectl set-hostname london 

### Instalando dependencias 

sudo apt install samba krb5-user krb5-config winbind libpam-winbind libnss-winbind

### Parando o serviço do SAMBA4
sudo systemctl stop samba-ad-dc.service smbd.service nmbd.service winbind.service
sudo systemctl disable samba-ad-dc.service smbd.service nmbd.service winbind.service

### Fazendo o backup do arquivo de configuração do SAMBA4 smb.conf
sudo mv /etc/samba/smb.conf /etc/samba/smb.conf.initial

### Provisionando o SAMBA4 como Active Directory Domain Controller
sudo samba-tool domain provision --use-rfc2307 --interactive

### Informações do REALM, DOMAIN, SERVER ROLE e DNS
``` 
Realm:	SERGIO.LAB
Domain:	sergio
Server Role:	dc
DNS Backend:	SAMBA_INTERNAL
DNS Forwarder:	192.168.10.1
Senha:	Senha Forte
``` 

### Fazendo o backup do arquivo de configuração do Kerberos krb5.conf
sudo mv /etc/krb5.conf /etc/krb5.conf.initial
sudo ln –s /var/lib/samba/private/krb5.conf /etc/

### Editando e verificando as configurações do Kerberos no arquivo krb5.conf
sudo vim /etc/krb5.conf
Realm:	SERGIO.LAB <-- verificar essa linha


### Inicializando e habilitando o Samba Active Domain Controller daemons
sudo systemctl start samba-ad-dc.service
sudo systemctl status samba-ad-dc.service
sudo systemctl enable samba-ad-dc.service
sudo netstat -tulpen | more

### Verificando o nível funcional do domínio do SAMBA4
sudo samba-tool domain level show

### Pós-Provisionamento do SAMBA4
sudo samba -V
sudo service ntp status
sudo ps -auxf | grep -i samba
sudo netstat -tulpen | egrep 53

### Informações dos Serviços de Rede integrado com o SAMBA4
SSH (Secure Shell – Porta 22)
DNS (Domain Name System – Porta 53)
SMB (Server Message Block NetBIOS – Portas padrão: 137 até 139)
RPC/ECM (Porta padrão 135)
CIFS (Common Internet File System – Porta padrão 445)
LDAP (Lightweight Directory Access Protocol – Porta padrão 389)
LDAPS (Lightweight Directory Access Protocol Secure – Porta padrão 636)
GC (Global Catalog – Porta padrão 3268 até 3269)
Kerberos (Porta padrão 88/464)
NTP (Network Time Protocol – Porta padrão 123)
DHCP (Dynamic Host Configuration Protocol – Porta padrão 67 até 68)
NFS (Network File System – Porta 2049)
CUPS (Common Unix Print System – Porta 631) 

### Verificações importantes do SAMBA4 pós instalação
sudo samba-tool testparm
sudo samba-tool domain info sergio.lab
sudo samba-tool dbcheck
sudo samba-tool time
sudo samba-tool drs showrepl
sudo samba-tool drs bind
sudo samba-tool dns serverinfo sergio.lab
sudo samba-tool gpo listall
sudo samba-tool group list
sudo samba-tool user list
sudo samba-tool domain passwordsettings show
sudo samba-tool fsmo show

### Testando o acesso ao cliente do SAMBA no servidor local
sudo smbclient -L localhost -U%
sudo smbclient -L 192.168.1.10 -U%
sudo smbclient -L sergio.lab -U%
sudo smbclient -L london -U%
sudo smbclient -L london.sergo.lab -U%
sudo smbclient //localhost/netlogon -U 'administrator'

### Testando as configurações de resolução DNS local utilizando o comando host
sudo host -t A london
sudo host -t A london.sergio.lab
sudo host -t A 192.168.1.10
sudo host -t A sergio.lab

### Testando as configurações de resolução DNS local utilizando o comando nslookup
sudo nslookup london
sudo nslookup london.sergio.lab
sudo nslookup 192.168.1.10

### Testando a conectividade
ping london.sergio.lab
ping sergio.lab
ping www.google.com


# Gerenciando o SAMBA4 pelo samba-tool

#Criando usuários com samba-tool
sudo samba-tool user add sergio.pacheco
sudo samba-tool user list

#Criando grupos com o samba-tool
sudo samba-tool group add G-Corretores
sudo samba-tool group list

#Adicionando usuários ou grupo com samba-tool
sudo samba-tool group addmembers G-Corretores sergio.pacheco
sudo samba-tool group listmembers G-Corretores

#Listando informações de SPN
sudo samba-tool spn list sergio.pacheco
sudo samba-tool spn list G-Corretores

#Criando usuários com samba-tool com opções
sudo samba-tool user add selma.beccegato
sudo samba-tool group add G-Diretoria --description=”Grupo da diretoria”
sudo samba-tool group addmembers G-Diretoria selma.beccegato
sudo samba-tool group listmembers G-Diretoria
sudo samba-tool spn list selma.beccegato

#Criando usuários e grupos com samba-tool com opções avançadas
sudo samba-tool ?
sudo samba-tool user add --help
sudo samba-tool group add --help
sudo samba-tool user add antonio.pacheco --surname=”Pacheco” --given-name=”Costa” --use-username-as-cn --userou=OU=Usuarios,OU=Vendas,OU=LOREM

sudo samba-tool group add G-Vendas --group-scope=Global --groupou=OU=Grupos,OU=Vendas,OU=sergio.lab

sudo samba-tool group add DL-PastaVendas --group-scope=Domain --groupou=OU=Grupos,OU=Vendas,OU=sergio.lab

sudo samba-tool group list
sudo samba-tool group addmembers G-Vendas antonio.pacheco
sudo samba-tool group addmembers DL-PastaVendas G-Vendas

#Mais informações sobre o samba-tool
sudo man samba-tool
sudo samba-tool user ?
sudo samba-tool user add --help
Opções de ? e --help do comando


# Criando compartilhamento 

[vendas]
comment = Pasta Pública
path = /arquivos/sergio.lab/vendas
read only = no

#Reiniciando o serviço do SAMBA4
sudo testparm
sudo testparm -v | more
sudo samba-tool testparm
sudo systemctl restart samba-ad-dc
sudo systemctl restart samba 
sudo smbcontrol all reload-config



