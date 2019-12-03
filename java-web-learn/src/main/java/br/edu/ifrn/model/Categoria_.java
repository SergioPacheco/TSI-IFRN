package br.edu.ifrn.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-11T09:21:48.139-0300")
@StaticMetamodel(Categoria.class)
public class Categoria_ {
	public static volatile SingularAttribute<Categoria, Long> id;
	public static volatile SingularAttribute<Categoria, String> nome;
	public static volatile ListAttribute<Categoria, Questao> questoes;
}
