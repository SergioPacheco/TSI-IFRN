# Configurando interfaces


## Atualizando o sistema
sudo apt update
sudo apt upgrade
sudo apt dist-upgrade
sudo apt clean

## Configurando interfaces: /etc/network/interfaces 

```
auto lo 
iface lo inet loopback
  
auto enp0s3 
iface enp0s3 inet dhcp 

auto enp0s8 
iface enp0s8 inet static 
	address 192.168.5.1 
	netmask 255.255.255.0

```






