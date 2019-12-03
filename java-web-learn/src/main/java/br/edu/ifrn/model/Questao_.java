package br.edu.ifrn.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-11T13:41:52.088-0300")
@StaticMetamodel(Questao.class)
public class Questao_ {
	public static volatile SingularAttribute<Questao, Long> id;
	public static volatile SingularAttribute<Questao, String> nome;
	public static volatile SingularAttribute<Questao, Date> data;
	public static volatile SingularAttribute<Questao, Categoria> categoria;
	public static volatile SingularAttribute<Questao, User> useri;
	public static volatile SingularAttribute<Questao, Resposta> resposta;
	public static volatile SingularAttribute<Questao, String> imagem;
}
