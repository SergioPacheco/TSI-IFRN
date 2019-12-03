
DROP DATABASE cinema;

CREATE DATABASE cinema;

USE cinema;

CREATE TABLE horarios (
  id int primary key,
  dia varchar(30),
  hora time, 
  preco float
);
  
CREATE TABLE  filmes (
  id int PRIMARY KEY,
  titulo varchar(35),
  link varchar(35)
);

 
CREATE TABLE horarios_filmes (
  id_horarios_FK int, 
  id_filmes_FK int,
  CONSTRAINT FK_id_horarios FOREIGN KEY (id_horarios_FK) REFERENCES filmes(id),
  CONSTRAINT FK_id_filmes   FOREIGN KEY (id_filmes_FK)   REFERENCES horarios(id)
);
   
SELECT * FROM horarios_filmes;

SELECT * FROM filmes;

INSERT INTO filmes (id,titulo,link) VALUES (1,'Matrix','http://matrix.com');
INSERT INTO filmes (id,titulo,link) VALUES (2,'SuperMan','http://superman.com');
INSERT INTO filmes (id,titulo,link) VALUES (3,'Batman','http://batman.com');
INSERT INTO filmes (id,titulo,link) VALUES (4,'MIB','http://mib.com');

SELECT * FROM horarios;

-- exec sp_rename 'NomeTabela.[nomeCampo]', 'NovoNomeCampo', 'column'
-- EXEC SP_RENAME 'horarios.horainicio', 'hora', 'column';

INSERT INTO horarios (id, dia, hora, preco) VALUES ('1', 'Segunda','14:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('2', 'Segunda','15:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('3', 'Segunda','16:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('4', 'Segunda','17:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('5', 'Segunda','18:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('6', 'Segunda','19:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('7', 'Segunda','20:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('8', 'Segunda','21:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('9', 'Segunda','22:30:00', 20.00);


INSERT INTO horarios (id, dia, hora, preco) VALUES ('10', 'Terça','14:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('11', 'Terça','15:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('12', 'Terça','16:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('13', 'Terça','17:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('14', 'Terça','18:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('15', 'Terça','19:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('16', 'Terça','20:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('17', 'Terça','21:30:00', 20.00);
INSERT INTO horarios (id, dia, hora, preco) VALUES ('18', 'Terça','22:30:00', 20.00);



SELECT * FROM horarios_filmes;

INSERT INTO horarios_filmes (id_horarios_FK, id_filmes_FK) VALUES (1,1);
INSERT INTO horarios_filmes (id_horarios_FK, id_filmes_FK) VALUES (2,2);
INSERT INTO horarios_filmes (id_horarios_FK, id_filmes_FK) VALUES (3,3);
INSERT INTO horarios_filmes (id_horarios_FK, id_filmes_FK) VALUES (4,4);
INSERT INTO horarios_filmes (id_horarios_FK, id_filmes_FK) VALUES (1,1);
INSERT INTO horarios_filmes (id_horarios_FK, id_filmes_FK) VALUES (2,2);
INSERT INTO horarios_filmes (id_horarios_FK, id_filmes_FK) VALUES (3,3);
INSERT INTO horarios_filmes (id_horarios_FK, id_filmes_FK) VALUES (4,4);
 