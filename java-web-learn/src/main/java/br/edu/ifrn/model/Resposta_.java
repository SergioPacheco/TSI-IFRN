package br.edu.ifrn.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-11T09:28:50.737-0300")
@StaticMetamodel(Resposta.class)
public class Resposta_ {
	public static volatile SingularAttribute<Resposta, Long> id;
	public static volatile SingularAttribute<Resposta, String> verdadeira1;
	public static volatile SingularAttribute<Resposta, String> falsa1;
	public static volatile SingularAttribute<Resposta, String> falsa2;
	public static volatile SingularAttribute<Resposta, String> falsa3;
	public static volatile SingularAttribute<Resposta, Questao> questao;
}
