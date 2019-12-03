package br.edu.ifrn.Atividade02.Q01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class CalculoTeste {

	private Calculo calcula; 
	
	@BeforeEach
	void init() {
		calcula = new Calculo();
	}
		
	@TestFactory
	public Collection<DynamicTest> testeDobroNumeros() throws Exception {
        return Arrays.asList(
        	dynamicTest("Dobro de -1", () 
        			-> assertEquals(Integer.valueOf(-2), calcula.Dobro(-1))),
            dynamicTest("Dobro de 0",  () 
            		-> assertEquals(Integer.valueOf(0), calcula.Dobro(0))),
            dynamicTest("Dobro de 3",  () 
            		-> assertEquals(Integer.valueOf(6), calcula.Dobro(3)))
        );
    }

	@TestFactory
	public Collection<DynamicTest> testeAreaNumeros() throws Exception {
        return Arrays.asList(
        	dynamicTest("Area de 4.6 por 3.7",     () 
        			-> assertEquals((Double)17.02, calcula.Area(4.6D, 3.7D))),
            dynamicTest("Area de 0 por 5",         () 
            		-> assertEquals((Double)0.0,   calcula.Area(0D, 10D))),
            dynamicTest("Area de -4500 por 19200", () 
            		-> assertEquals((Double)0.0,   calcula.Area(-4500D, 19200D)))
        );
    }
	
	@TestFactory
	public Collection<DynamicTest> testeDescontoNumeros() throws Exception {
        return Arrays.asList(
        	dynamicTest("Desconto do valor   0.00 de  0%", () 
        			-> assertEquals((Double)0.0,   calcula.Area(-4500D, 19200D))),	
        	dynamicTest("Desconto do valor  80.00 de 10%", () 
        			-> assertEquals((Double)17.02, calcula.Area(4.6D, 3.7D))),
            dynamicTest("Desconto do valor 134.50 de -5%", () 
            		-> assertEquals((Double)0.0,   calcula.Area(0D, 10D))),
            dynamicTest("Desconto do valor -45.50 de 80%", () 
            		-> assertEquals((Double)0.0,   calcula.Area(-4500D, 19200D)))
        );
    }

	@TestFactory
	public Collection<DynamicTest> testeConsumoMedioNumeros() throws Exception {
        return Arrays.asList(
        	dynamicTest("300km por 26l Gas",  () 
        			-> assertEquals((Double)11.53, calcula.ConsumoMedio(300D, 26D))),	
        	dynamicTest("290km por 500l Gas", () 
        			-> assertEquals((Double)0.58,  calcula.ConsumoMedio(290D, 500D))),
            dynamicTest("100km por 0l Gas",   () 
            		-> assertEquals((Double)0.0,   calcula.ConsumoMedio(100D, 0D))),
            dynamicTest("800km por -20l Gas", () 
            		-> assertEquals((Double)0.0,   calcula.ConsumoMedio(800D, -20D)))
        );
    }

	@TestFactory
	public Collection<DynamicTest> testeMaiorNumeros() throws Exception {
        return Arrays.asList(
        	dynamicTest("30, 20, 10",  () -> assertEquals(30,  calcula.Maior(30, 20, 10))),	
        	dynamicTest("-10, -20, 0", () -> assertEquals(0,   calcula.Maior(-10, -20, 0))),
            dynamicTest("-2, -90, 0",  () -> assertEquals(0,   calcula.Maior(-2, -90, 0))),
            dynamicTest("10, 60, 400", () -> assertEquals(400, calcula.Maior(10, 60, 400)))
        );
    }
	
	
	@Test 
	@DisplayName("Método Maior") 
	public void testeMaior() {
		assertEquals(16, calcula.Maior(4,2,16) );   
	}
	
	@AfterEach
	void tearDown() {
		calcula = null;
	}

}
