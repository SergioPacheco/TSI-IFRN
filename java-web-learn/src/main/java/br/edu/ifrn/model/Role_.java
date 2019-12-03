package br.edu.ifrn.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-11T13:38:29.432-0300")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Long> id;
	public static volatile SingularAttribute<Role, String> nome;
	public static volatile ListAttribute<Role, User> user;
}
