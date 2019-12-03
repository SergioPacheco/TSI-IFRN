# Configuração do Serviço de DHCP Server

# Verificando o status do Servidor DHCPD
sudo service isc-dhcp-server status

# Fazendo o backup do arquivo de configuração do DHCP Server dhcpd.conf
sudo cp /etc/dhcp/dhcpd.conf /etc/dhcp/dhcpd.conf.bkp

# Editando o arquivo de configuração do DHCP Server dhcpd.conf
sudo vim /etc/dhcp/dhcpd.conf

# Verificando as configurações do arquivo dhcpd.conf
sudo dhcpd -cf /etc/dhcp/dhcpd.conf

# Parando e iniciando o serviço do DHCP Server
sudo service isc-dhcp-server stop
sudo service isc-dhcp-server start
sudo service isc-dhcp-server status

# Verificando a porta de conexão do DHCP Server
sudo netstat -tupenl | grep -i 67

# Verificando o arquivo de Lease do DHCP Server
sudo cat /var/lib/dhcp/dhcpd.lease

# Testando DHCP Server no Cliente  

# Comandos utilizados no Ubuntu Desktop  
sudo ifconfig
sudo router -n
sudo cat /etc/resolv.conf
ping sergio.lab
nslookup sergio.lab

# Verificação do Lease no servidor
sudo cat /var/lib/dhcp/dhcpd.lease

# Verificação do Lease no cliente 
sudo cat /var/lib/dhcp/dhclient.lease
