package br.edu.ifrn.Atividade02.Q03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;


class TesteEmpresa {
	
	private Empresa empresa; 
	
	String nome = "Sergio"; 
	Calendar dtContratacao = Calendar.getInstance(); 
	Double vendaMensal = (Double) 35000.0; 
	Double salario =  38325.0; 
	 
	@BeforeEach
	void init() {
		empresa = new Empresa();
	}
	
	@TestFactory
	public Collection<DynamicTest> testeNomeCorretorInvalido() throws Exception {
		dtContratacao.setTime(new Date());	
        return Arrays.asList(
        	dynamicTest("Menor que 3 letras", () -> assertThrows(IllegalArgumentException.class, () -> {
        		empresa.cadastrarCorretor("Se", dtContratacao, vendaMensal); 
        	})), 
        	dynamicTest("Nulo",     () -> assertThrows(IllegalArgumentException.class, () -> {
        		empresa.cadastrarCorretor(null, dtContratacao, vendaMensal);    
        	})),
            dynamicTest("Em Branco",() -> assertThrows(IllegalArgumentException.class, () -> {
            	empresa.cadastrarCorretor("     ", dtContratacao, vendaMensal); 
            }))	
        );
	}
	
	@TestFactory
	public Collection<DynamicTest> testeDataContratacaoCorretorInvalida() throws Exception {
		dtContratacao.setTime(new Date());	
        return Arrays.asList(
        	dynamicTest("Data Contratacao superior data atual", () -> assertThrows(IllegalArgumentException.class, () -> {
        		dtContratacao.add(Calendar.DATE, 1);	
        		empresa.cadastrarCorretor(nome, dtContratacao, vendaMensal); 
        	})), 
        	dynamicTest("Nome Nulo",     () -> assertThrows(IllegalArgumentException.class, () -> {
        		dtContratacao = null;
        		empresa.cadastrarCorretor(nome, null, vendaMensal);    
        	}))
        );
	}
	
	@Test 
	@DisplayName("Venda Mensal do corretor valor Negativo") 
	public void testeVendaMensalCorretorInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			empresa.cadastrarCorretor(nome, dtContratacao, -500D);
    	}); 
	}
	

	@Test 
	@DisplayName("Salario do Corretor sem vendas") 
	public void testeSalarioCorretorSemVendas() {
		empresa.cadastrarCorretor(nome, dtContratacao, 0D);
		assertEquals((Double)1000.0, empresa.getSalarioCorretor(0) );   
	}
		
	@Test 
	@DisplayName("Salario do Corretor vendas = 4000") 
	public void testeSalarioCorretorVendas4000() {
		this.vendaMensal = 4000.0;
		empresa.cadastrarCorretor(nome, dtContratacao, vendaMensal);
		assertEquals((Double)(1000.0+(vendaMensal*0.07)), empresa.getSalarioCorretor(0) );   
	}
	
	@Test 
	@DisplayName("Salario do Corretor vendas = 5000") 
	public void testeSalarioCorretorVendas5000() {
		this.vendaMensal = 5000.0; 
		empresa.cadastrarCorretor(nome, dtContratacao, vendaMensal);
		assertEquals((Double)(1000.0+(vendaMensal*0.12)), empresa.getSalarioCorretor(0) );   
	}
	
	@Test 
	@DisplayName("Salario do Corretor vendas = 6000") 
	public void testeSalarioCorretorVendas6000() {
		this.vendaMensal = 6000.0; 
		empresa.cadastrarCorretor(nome, dtContratacao, vendaMensal);
		assertEquals((Double)(1000.0+(vendaMensal*0.12)), empresa.getSalarioCorretor(0) );   
	}
	
	@Test 
	@DisplayName("Salario do Corretor vendas = 25000") 
	public void testeSalarioCorretorVendas25000() {
		this.vendaMensal = 25000.0;
		empresa.cadastrarCorretor(nome, dtContratacao, vendaMensal);
		assertEquals((Double)(1000.0+(vendaMensal*0.12)), empresa.getSalarioCorretor(0) );   
	}
	@Test 
	@DisplayName("Salario do Corretor vendas = 30000") 
	public void testeSalarioCorretorVendas30000() {
		this.vendaMensal = 30000.0;
		empresa.cadastrarCorretor(nome, dtContratacao, vendaMensal);
		assertEquals((Double)(1000.0+(vendaMensal*0.095)), empresa.getSalarioCorretor(0) );   
	}
	@Test 
	@DisplayName("Salario do Corretor vendas = 50000") 
	public void testeSalarioCorretorVendas50000() {
		this.vendaMensal = 50000.0; 
		empresa.cadastrarCorretor(nome, dtContratacao, vendaMensal);
		assertEquals((Double)(1000.0+(vendaMensal*0.095)), empresa.getSalarioCorretor(0) );   
	}
	
	@Test 
	@DisplayName("Salario do Corretor vendas = 55000") 
	public void testeSalarioCorretorVendas55000() {
		this.vendaMensal = 55000.0; 
		empresa.cadastrarCorretor(nome, dtContratacao, vendaMensal);
		assertEquals((Double)(1000.0+(vendaMensal*0.07)), empresa.getSalarioCorretor(0) );   
	}

}
