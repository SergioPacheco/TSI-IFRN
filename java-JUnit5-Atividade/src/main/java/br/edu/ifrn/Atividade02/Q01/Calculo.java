package br.edu.ifrn.Atividade02.Q01;

import java.util.Arrays;

public class Calculo {
	
	public Integer Dobro(Integer numero) { 
		return numero * 2;  
	}
	
	public Double Area(Double largura, Double comprimento) {
		if (largura <0 || comprimento <0) { 
			throw new IllegalArgumentException
            ("Largura e comprimento devem ser números positivos"); 
		}
		return largura * comprimento; 
	}
	
	public Double Desconto(Double valor, Double percentual) {
		if (percentual <0 || valor<0) { 
			throw new IllegalArgumentException
            ("Percentual e Valor devem ser um números positivos"); 
		}
		return valor *  (1 - (Math.abs(percentual)/100) ); 
	}
	
	public Double ConsumoMedio(Double distancia, Double totalGas) {
		if (distancia <0 || totalGas<0) { 
			throw new IllegalArgumentException
            ("Distancia e totalGasolina devem ser um números positivos"); 
		}
		return distancia / totalGas; 
	}
	
	public int Maior(int a, int b, int c) { 
		int v[] = {a, b, c};
		Arrays.sort(v);
		return (int)(v[2]); 
	}
	
}
