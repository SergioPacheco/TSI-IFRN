package com.algaworks.brewer.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.algaworks.brewer.mail.Mailer;

@Configuration
@ComponentScan(basePackageClasses = Mailer.class)
@PropertySource({ "classpath:env/mail-${ambiente:local}.properties" })
@PropertySource(value = { "file://${HOME}/.brewer-mail.properties" }, ignoreResourceNotFound = true)
public class MailConfig {

	@Autowired
	private Environment env;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.sendgrid.net");
		//mailSender.setHost("correio.cciex.eb.mil.br");
		mailSender.setPort(587);
		//mailSender.setPort(25);
		mailSender.setUsername(env.getProperty("email.username"));
		mailSender.setPassword(env.getProperty("email.password"));
		
		String arquivo = env.getProperty("HOME");
		
		System.out.println("user:"+ env.getProperty("email.username"));
		System.out.println("password:"+ env.getProperty("email.password"));
		System.out.println("HOME:"+ arquivo);
		System.out.println("email máquina:"+ mailSender.getUsername());
		System.out.println("senha máquina:"+ mailSender.getPassword());
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");//Protocolo definido
		props.put("mail.smtp.auth", true);           //Usar autenticação
		props.put("mail.smtp.starttls.enable", true);//Usar tls para encapsular Porta 587
		props.put("mail.debug", false);
		//props.put("mail.smtp.ssl.trust", "correio.cciex.eb.mil.br"); //Para porta 465 e para o  CCIEx
		props.put("mail.smtp.connectiontimeout", 10000); // tempo em miliseconds para tentar conectar com o servidor

		mailSender.setJavaMailProperties(props);//Adiciona as propriedades no mailSender

		return mailSender;
	}
}
