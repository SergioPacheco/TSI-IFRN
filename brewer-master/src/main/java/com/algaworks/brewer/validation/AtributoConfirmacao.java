package com.algaworks.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.algaworks.brewer.validation.validator.AtributoConfirmacaoValidator;

@Target({ ElementType.TYPE})//Onde deve ser aplicado: nos atributos, métodos e poder usar em outras anotações
@Retention(RetentionPolicy.RUNTIME)//Anotacao que sera avaliada em tempo de execucao
@Constraint(validatedBy = { AtributoConfirmacaoValidator.class })//È uma restrição: Para que faça validação podendo ser passado uma classe

public @interface AtributoConfirmacao {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Atributos não conferem";
	
	Class<?>[] groups() default {};//Podemos agrupar validações
	Class<? extends Payload>[] payload() default {};//Posso carregar mais informações dessa anotação(uma classe)
	
	String atributo();
	String atributoConfirmacao();
	
}
