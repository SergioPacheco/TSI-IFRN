---
title: "JSF: Cheat Sheet"
header:
  teaser: "markup-syntax-highlighting-teaser.jpg"
categories:
  - JSF
tags:
  - cheat sheet
---

### JSF Overview
### Processo de Desenvolvimento
### Ciclo de vida
### JSF Linguagem de Expressão (Expression Language)
### JSF Tags principais
### JSF HTML Tags e mais ... 


# JSF Visão Geral

JavaServer Faces (JSF) é a tecnologia de visão  component-based "oficial" do Java EE.
JSF inclui um conjunto de pré-definidos componentes UI, um modelo de programação direcionado a eventos, e a abilidade de adicionar componentes de terceiros. JSF é projetado para ser extendido, fácil uso. 

# Processo de Desenvolvimento

O programador especifica os componentes JSF na página JSF, combinando com tags HTML e CSS. 
Os componentes são ligados aos managed beans - Java Classes que contem representação lógica e conecta com a lógica de negócios e persistência no backend. 

servlet container

	web application 

		JSF Pages
			presentation
		Managed Beans
			application logic		   
			navigation
			validation
			event handling
		business logic
				
Em JSF 2.0 é recomendado usar facelets formato para páginas: 

```java
<?xml version=”1.0” encoding=”UTF-8”?>
<!DOCTYPE html PUBLIC “-//W3C//DTD XHTML 1.0 Strict//EN”
 “http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd”>
<html xmlns=”http://www.w3.org/1999/xhtml”
	 xmlns:f=”http://java.sun.com/jsf/core”
	 xmlns:h=”http://java.sun.com/jsf/html”
	 xmlns:ui=”http://java.sun.com/jsf/facelets”>
	 <h:head>...</h:head>
	 <h:body>
	 <h:form>
	 ...
	 </h:form>
	 </h:body>
</html>

``` 

Text Field 
page.xhtml 

```java 
	<h:inputText value=”#{bean1.luckyNumber}”>
```

WEB-INF/classes/com/corejsf/SampleBean.java.sun.com 
```java
	@ManagedBean(name=”bean1”)
	@SessionScoped
	public class SampleBean {
	 	public int getLuckyNumber() { ... }
	 	public void setLuckyNumber(int value) { ... }
	 ...
	}
```
Button

page.xhtml
```java 
<h:commandButton value=”press me” action=”#{bean1.login}”/>
```

WEB-INF/classes/com/corejsf/SampleBean.java 

```java 
@ManagedBean(name=”bean1”)
@SessionScoped
	public class SampleBean {
		public String login() {
			if (...) return “success”; else return “error”;
	}
	 ...
}
```

Radio Buttons

page.xhtml 

<h:selectOneRadio value=”#{form.condiment}>
 <f:selectItems value=”#{form.items}”/>
</h:selectOneRadio>


WEB-INF/classes/com/corejsf/SampleBean.java 

public class SampleBean {
 private static Map<String, Object> items;
 static {
	 items = new LinkedHashMap<String, Object>();
	 items.put(“Cheese”, 1); // label, value
	 items.put(“Pickle”, 2);
 ...
 }
 public Map<String, Object> getItems() { return items; }
 public int getCondiment() { ... }
 public void setCondiment(int value) { ... }
 ...
}


# Validação e Conversão 

Validação e conversão a nível de página 

<h:inputText value=”#{bean1.amount}” required=”true”>
 	<f:validateDoubleRange maximum=”1000”/>
</h:inputText>
<h:outputText value=”#{bean1.amount}”>
 	<f:convertNumber type=”currency”/>
</h:outputText>

O número é exibido com o símbolo $ e com separação de milhares: $1,000.00

Usando o Framework de validação de Bean (JSR 303) 2.0 

public class SampleBean {
	@Max(1000) private BigDecimal amount;
}

# Mensagens de erros


<h:outputText value=”Amount”/>
<h:inputText id=”amount” label=”Amount” value=”#{payment.amount}”/>
<h:message for=”amount”/>

# Recursos e Stilos

## page.xhtml

<h:outputStylesheet library=”css” name=”styles.css” target=”head”/>
 ...
<h:outputText value=”#{msgs.goodbye}!” styleClass=”greeting”>

## faces-config.xml

<application>
	 <resource-bundle>
 		<base-name>com.corejsf.messages</base-name>
 		<var>msgs</var>
 	</resource-bundle>
</application>

## WEB-INF/classes/com/corejsf/messages.properties

goodbye=Goodbye


## WEB-INF/classes/com/corejsf/messages_de.properties 

goodbye=Auf Wiedersehen

## resources/css/styles.css

.greeting {
 font-style: italic;
 font-size: 1.5em;
 color: #eee;
}

## Table with links 

page.xhtml

<h:dataTable value=”#{bean1.entries}” var=”row” styleClass=”table”
 	rowClasses=”even,odd”>
 	<h:column>
		<f:facet name=”header”>
			<h:outputText value=”Name”/>
		</f:facet>
		<h:outputText value=”#{row.name}”/>
 	</h:column>
 	<h:column>
		<h:commandLink value=”Delete” action=”#{bean1.deleteAction}”  immediate=”true”>
			<f:setPropertyActionListener target=”#{bean1.idToDelete}” value=”#{row.id}”/>
 		</h:commandLink>
 	</h:column>
</h:dataTable>

WEB-INF/classes/com/corejsf/SampleBean.java 

public class SampleBean {
	private int idToDelete;
	public void setIdToDelete(int value) { idToDelete = value; }
	public String deleteAction() {
		// delete the entry whose id is idToDelete
		return null;
	}
	public List<Entry> getEntries() { ... }
	...
}

# Ajax 2.0

<h:commandButton value=”Update”>
	<f:ajax execute=”input1 input2” render=”output1”/>
</h:commandButton>

REQUEST --> RESTORE VIEW --> APPLY REQUEST VALUES --> PROCESS EVENTS --> PROCESS VALIDATIONS

RESPONSE --> 


# The JSF Expression Language 

base[expr1][expr2].

header 
hearderValues
paramValues
cookie
initParam
requestScope
sessionScope
applicationScope
viewScope
facesContext
view
component
cc 
resource 

## Expressões EL contem funções JSTL

fn:contains(str, substr)
fn:containsIgnoreCase(str, substr)
fn:startsWith(str, substr)
fn:endsWith(str, substr)
fn:length(str)
fn:indexOf(str)
fn:join(strArray, separator)
fn:split(str, separator)
fn:substring(str, start, pastEnd)
fn:contains(str, substr)
fn:containsIgnoreCase(str, substr)
fn:startsWith(str, substr)
fn:endsWith(str, substr)
fn:length(str)
fn:indexOf(str)
fn:join(strArray, separator)
fn:split(str, separator)
fn:substring(str, start, pastEnd)


# JSF CORE Tags

|f:facet 				|  
|f:attribute			| 
|f:param 				| 
|f:actionListener 		| 
|f:valueChangeListener 	| 
|f:propertyAction		|
|f:phaseListener 		| 
|f:event  				| 
|f:converter 			|
|f:convertDateTime  	|
|f:convertNumber		| 
    



