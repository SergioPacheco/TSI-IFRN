package com.algaworks.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})//Onde deve ser aplicado: nos atributos, métodos e poder usar em outras anotações
@Retention(RetentionPolicy.RUNTIME)//Anotacao que sera avaliada em tempo de execucao
@Constraint(validatedBy = {})//È uma restrição: Para que faça validação podendo ser passado uma classe
@Pattern(regexp = "([a-zA-Z]{2}\\d{4})?")//Padrão realizado e ? para fazer validacao só se houver dados
public @interface SKU {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{com.algaworks.constraints.SKU.message}";
	
	Class<?>[] groups() default {};//Podemos agrupar validações
	Class<? extends Payload>[] payload() default {};//Posso carregar mais informações dessa anotação(uma classe)
}
