package br.edu.ifrn.Atividade02.Q02;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class TesteAluno {

	private Aluno aluno; 
	
	String nome = "Sergio Pacheco"; 
	int idade = 65; 
	int nota1 = 90; 
	int nota2 = 80; 
	int faltas = 12;
	
	@BeforeEach
	void init() {
		aluno = new Aluno();
	}
	
	@TestFactory
	public Collection<DynamicTest> testeNomeString() throws Exception {
        return Arrays.asList(
        		
        	dynamicTest("Nome Invalido", () -> assertThrows(IllegalArgumentException.class, () -> {
        		aluno.cadastrar("S", idade, nota1, nota2, faltas); 
        	})), 
        	dynamicTest("Nome Nulo",     () -> assertThrows(IllegalArgumentException.class, () -> {
      		    aluno.cadastrar(null, idade, nota1, nota2, faltas);   
        	})),
            dynamicTest("Nome em Branco",() -> assertThrows(IllegalArgumentException.class, () -> {
                aluno.cadastrar("    ", idade, nota1, nota2, faltas); 
            }))	
        );
	}

	@TestFactory
	public Collection<DynamicTest> testeIdadeNumero() throws Exception {
        return Arrays.asList(
        		
        	dynamicTest("Idade Invalida 10 ", () -> assertThrows(IllegalArgumentException.class, () -> {
        		aluno.cadastrar(nome, (int)10, nota1, nota2, faltas); 
        	})), 
        	dynamicTest("Idade Negativa -10 ", () -> assertThrows(IllegalArgumentException.class, () -> {
        		aluno.cadastrar(nome, (int)-10, nota1, nota2, faltas); 
        	})) 
        );
	}

	@TestFactory
	public Collection<DynamicTest> testeNota1Numero() throws Exception {
        return Arrays.asList(
        		
        	dynamicTest("Nota1 Negativa -90", () -> assertThrows(IllegalArgumentException.class, () -> {
        		aluno.cadastrar(nome, idade, (int)-90, nota2, faltas); 
        	})), 
        	dynamicTest("Nota1 Invalida 120", () -> assertThrows(IllegalArgumentException.class, () -> {
        		aluno.cadastrar(nome, idade, (int)120, nota2, faltas); 
        	})) 
        );
    }
	 
 
	@TestFactory
    public Collection<DynamicTest> testeNota2Numero() throws Exception {
        return Arrays.asList(
        		
        	dynamicTest("Nota2 Negativa -90", () -> assertThrows(IllegalArgumentException.class, () -> {
        		aluno.cadastrar(nome, idade, nota1, (int)-90, faltas); 
        	})), 
        	dynamicTest("Nota2 Invalida 120", () -> assertThrows(IllegalArgumentException.class, () -> {
        		aluno.cadastrar(nome, idade, nota1, (int)120, faltas); 
        	})) 
        );
	}
	
	
	@Test 
	@DisplayName("Falta Negativa") 
	public void testeFaltasNegativa() {
		assertThrows(IllegalArgumentException.class, () -> {
    		aluno.cadastrar(nome, idade, nota1, nota2, (int)-12); 
    	});  
	}

	@Test 
	@DisplayName("Media aritmética") 
	public void testeMediaAritmetica() {
		aluno.cadastrar(nome, idade, (int)60, (int)60, faltas);
		assertEquals((int)60, aluno.media());   
	}
	
	
	@Test 
	@DisplayName("Aluno Aprovado") 
	public void testeAlunoAprovado() {
		aluno.cadastrar(nome, idade, (int)60, (int)60, faltas);
		assertEquals("APROVADO", aluno.aprovado());   
	}
	@Test 
	@DisplayName("Aluno Reprovado") 
	public void testeAlunoReprovado() {
		aluno.cadastrar(nome, idade, (int)60, (int)59, faltas);
		assertEquals("REPROVADO", aluno.aprovado());   
	}
	
	
	 
	@Test
	@DisplayName("Testa Cadastro Valido")
	public void testeCadastroInValida() {	
		aluno.cadastrar(nome, idade, nota1, nota2, faltas); 
		assertEquals("Sergio Pacheco", aluno.getNome() ); 
		assertEquals((int)65, aluno.getIdade());
		assertEquals((int)90, aluno.getNota1()); 
		assertEquals((int)80, aluno.getNota2());
		assertEquals((int)12, aluno.getFaltas()); 
	}
	
	@AfterEach
	void tearDown() {
		aluno = null;
	}

}
