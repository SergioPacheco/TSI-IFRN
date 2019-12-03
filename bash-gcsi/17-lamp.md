# Instalação do LAMP Server

vim /etc/hostname

vim /etc/hosts

apt-get update

apt-get upgrade

reboot

apt-get install lamp-server perl python links2

sudo service apache2 restart

phpinfo.php
<?php phpinfo(); ?>

# Instalação do PhpMyAdmin

apt-get install phpmyadmin php-mbstring php-gettext

phpenmod mcrypt

phpenmod mbstring

# Instalação do Wordpress

wget https://wordpress.org/latest.zip

unzip latest.zip

chmod -Rfv 755 wordpress/

chown -Rfv www-data.www-data wordpress/

mysql -u roo -p

SHOW DATABASES;

CREATE DATABASE wordpress;

CREATE USER 'wordpress' IDENTIFIED BY 'wordpress';

GRANT USAGE ON *.* TO 'wordpress' IDENTIFIED BY 'wordpress';

GRANT ALL PRIVILEGES ON wordpress.* TO 'wordpress';

FLUSH PRIVILEGES;

EXIT

mysql -u wordpress -p

SHOW DATABASES;

USE wordpress;

SHOW TABLES;

vim wp-config.php

DB_NAME='wordpress'       base de dados do MySQL"

DB_USER='wordpress'       usuário de conexão a bade de dados"

DB_PASSWORD='wordpress'   senha do usuário de conexão"

DB_HOST='localhost'       endereço do servidor, recomendado localhost"

DB_CHARSET='utf8'         configurações de caracteres"

DB_COLLATE=''             sem collate"
