drop database ifrndb; 
create database ifrndb; 
use ifrndb; 

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;



CREATE TABLE IF NOT EXISTS `resposta` (
  `falsa1` varchar(45) DEFAULT NULL,
  `falsa2` varchar(45) DEFAULT NULL,
  `falsa3` varchar(45) DEFAULT NULL,
  `questao_id` int(11) NOT NULL,
  `verdadeira1` varchar(45) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_resposta_questao1_idx` (`questao_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=26 ;


INSERT INTO `resposta` (`falsa1`, `falsa2`, `falsa3`, `questao_id`, `verdadeira1`, `id`) VALUES
('Prata', 'Azul', 'Verde', 26, 'Ouro', 1),
('Madri', 'Valencia', 'Sevilia', 27, 'Barcelona', 2),
('Vincent Van Gogh', 'Rembrandt', 'Michelangelo', 28, 'Leonardo Da Vinci', 3),
('Turquia', 'Alemanha', 'Albania', 29, 'Grecia', 4),
('Nice', 'Lyon', 'Monaco', 30, 'Paris', 5),
('Duritz', 'Scott', 'Johnny ', 31, 'Aaron', 6),
('AC / DC', 'Metallica', 'Backstreet Boys', 32, 'The Beatles', 7),
('Johnny Logan', 'Madonna', 'Adam Duritz', 33, 'Michael Jackson', 8),
('Cyrus', 'Perry', 'Istrefi', 34, 'Fenty', 9),
('Tommy Lee', 'Max Roach', 'Neil Peart', 35, 'Lars Ulrich', 10),
('12', '13', '10', 36, '11', 11),
('Tres Filmes', 'Quatro Filmes', 'Um Filme', 37, 'Dois Filmes', 12),
('Numero 741', 'Numero 740', 'Numero 743', 38, 'Numero 742', 13),
('Tushu', 'Fushu', 'Gushu', 39, 'Mushu', 14),
('54', '55', '56', 40, '53', 15),
('1987', '1988', '1989', 41, '1986', 16),
('90 Minutos', '70 Minutos', '100 Minutos', 42, '80 Minutos', 17),
('Chupre', 'Macedonia', 'Albania', 43, 'Grecia', 18),
('Tres jogadores', 'Quatro jogadores', 'Cinco jogadores', 44, 'Dois Jogadores', 19),
('Dois', 'Tres', 'Quatro', 45, 'Somente Um', 20),
('Aerium', 'Burj Khalifa', 'CITIC Plaza', 46, 'Pentagon', 21),
('China', 'Korea do Sul', 'Africa do Sul', 47, 'Suiça', 22),
('Na Alemanha', 'Em Paris', 'Em Tirana', 48, 'Em Londres', 23),
('Peru', 'Colombia', 'Chile', 49, 'Brasil', 24),
('Naira', 'Kes', 'Lek', 50, 'Rupee', 25);


CREATE TABLE IF NOT EXISTS `categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;


INSERT INTO `categoria` (`id`, `nome`) VALUES
(1, 'Arte'),
(2, 'Musica'),
(3, 'Filme / TV'),
(4, 'Esports'),
(5, 'Economia');


CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `questao`
--

CREATE TABLE IF NOT EXISTS `questao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `data` date NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `imagem` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_questao_user1_idx` (`user_id`),
  KEY `fk_questao_categoria1_idx` (`categoria_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=51 ;


INSERT INTO `questao` (`id`, `nome`, `data`, `user_id`, `categoria_id`, `imagem`) VALUES
(26, 'Qual é a cor predominante dos domos da igreja na Russia?', '2016-07-13', 3, 1, 'rusia.jpg'),
(27, 'Em qual cidade da Espanha o Museu Joan Miro foi aberto em 1975?', '2016-07-13', 3, 1, 'spain-museums.jpg'),
(28, 'Quem pintou a Mona Lisa?', '2016-07-13', 3, 1, 'mona-lisa.jpg'),
(29, 'Em quae pais nasceu o fomoso pintor El Greco nasceu?', '2016-07-13', 3, 1, 'el_greco.jpg'),
(30, 'Em qual cidade o compositor Frédéric Chopin foi enterrado?', '2016-07-13', 3, 1, 'frederic.jpg'),
(31, 'Qual o nome do meio de Elvis Presley?', '2016-07-13', 3, 2, 'elvis-presley.jpg'),
(32, 'Qual grupo musical foi chamado inicialmente de The Quarrymen?', '2016-07-13', 3, 2, NULL),
(33, 'Quem mais venceu o Grammy Awards em 1980?', '2016-07-13', 3, 2, 'Grammy-Awards.jpg'),
(34, 'Qual é o sobrenome da cantora Rihanna?', '2016-07-13', 3, 2, 'Rihanna.jpg'),
(35, 'Quem é o Baterista do Metallica?', '2016-07-13', 3, 2, 'Metallica.jpg'),
(36, 'Quantos Oscars o filme Titanic ganhou?', '2016-07-13', 3, 3, 'titanic.jpg'),
(37, 'Quantos filmes Tomb Raider foram feitos?', '2016-07-13', 3, 3, 'Tomb-Raider.jpg'),
(38, 'Qual é o número da casa dos Simpsons?', '2016-07-13', 3, 3, 'Simpsons-house.jpg'),
(39, 'Qual é o nome dragaozinho no filme animado Mulan?', '2016-07-13', 3, 3, 'mushu.jpg'),
(40, 'Quak é o numero do Herbie o fusca?', '2016-07-13', 3, 3, NULL),
(41, 'Em que ano Maradona marcou um gol com a mão?', '2016-07-13', 3, 4, NULL),
(42, 'Quantos minutos dura uma partida de rugby?', '2016-07-13', 3, 4, NULL),
(43, 'Em pais foi realizada os primeiros Jogos Olimpicos?', '2016-07-13', 3, 4, 'Olympic-Games.png'),
(44, 'Quantos jogadores estão em cada lado da rede em uma partida de volleyball?', '2016-07-13', 3, 4, NULL),
(45, 'Quantas partidas Mohammed Ali perdeu em sua carreira?', '2016-07-13', 3, 4, 'Mohammed-Ali.jpg'),
(46, 'Qual é o prédio mais alto do mundo?', '2016-07-13', 3, 5, 'office-building.png'),
(47, 'A terminação de domínio .CH é de qual país?', '2016-07-13', 3, 5, NULL),
(48, 'Onde foi construido o primeiro subway?', '2016-07-13', 3, 5, 'subway.png'),
(49, 'Em que pais é a Linhas Aereas Varig?', '2016-07-13', 3, 5, 'Varig-Airlines.jpg'),
(50, 'Qual é a moeda oficial do Nepal?', '2016-07-13', 3, 5, 'Nepal.jpg');

-- --------------------------------------------------------


CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;


INSERT INTO `role` (`id`, `nome`) VALUES
(1, 'admin'),
(2, 'User');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role1_idx` (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role_id`) VALUES
(3, 'administrador', '1234', 1),
(4, 'usuario', '1234', 2);


ALTER TABLE `resposta`
  ADD CONSTRAINT `fk_resposta_questao1` FOREIGN KEY (`questao_id`) REFERENCES `questao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;


ALTER TABLE `questao`
  ADD CONSTRAINT `fk_questao_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_questao_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;


ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;





