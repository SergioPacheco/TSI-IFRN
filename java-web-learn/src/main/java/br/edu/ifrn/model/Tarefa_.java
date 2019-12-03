package br.edu.ifrn.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-11T06:56:01.370-0300")
@StaticMetamodel(Tarefa.class)
public class Tarefa_ {
	public static volatile SingularAttribute<Tarefa, Long> id;
	public static volatile SingularAttribute<Tarefa, String> titulo;
	public static volatile SingularAttribute<Tarefa, String> descricao;
	public static volatile SingularAttribute<Tarefa, Status> status;
	public static volatile SingularAttribute<Tarefa, Date> criacao;
	public static volatile SingularAttribute<Tarefa, Date> edicao;
}
