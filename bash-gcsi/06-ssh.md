# Servidor de Acesso remoto seguro (SSH)

### Instalando
sudo apt install openssh-server

#### Editando o arquivo /etc/ssh/sshd_config

sudo vi sshd_config




### Configurando o Serviço do SSH

### Verificando o serviço do SSH Server
sudo systemctl status ssh

### Verificando a porta de conexão do serviço do SSH
sudo netstat -tupenl | grep 22

### Acessando remotamente o servidor de SSH 
ssh estudante@192.168.1.10
sudo w
 
