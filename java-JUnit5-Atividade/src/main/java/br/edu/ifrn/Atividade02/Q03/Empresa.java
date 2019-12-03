package br.edu.ifrn.Atividade02.Q03;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Empresa {
 
	List<Corretor> corretores = new ArrayList<>();
	Calendar today = Calendar.getInstance();
	double totalVendas = 0.0; 
		
	public void cadastrarCorretor(String nome, Calendar dtContratacao, Double vendaMensal) { 
		
		if (nome == null || nome.trim().length() < 4) { 
			 throw new IllegalArgumentException
             ("Nome deve ter mais que 3 caracteres");
		}
		today.setTime(new Date());
		if(today.before(dtContratacao) || dtContratacao == null) {
			throw new IllegalArgumentException
             ("Data de Contratacao não poder ser nula ou maior que a data atual");
		}
		if (vendaMensal <0) { 
			throw new IllegalArgumentException
             ("Valor de Vendas não pode ser menor que 0");
		}		
		// 
		Corretor novoCorretor = new Corretor();
		novoCorretor.nome = nome; 
		novoCorretor.dtContratacao = dtContratacao; 
		novoCorretor.vendaMensal = vendaMensal; 
		novoCorretor.salario = calculaComissao(vendaMensal); 
		corretores.add(novoCorretor);  
		
	}
	
	public Double calculaComissao(Double vendas) {
		Double comissao = 1000.0;
		
		if (vendas < 5000.0 || vendas > 50000.0) {
			comissao+=vendas*0.07; 	
		} else if (vendas >= 5000.0 && vendas < 30000.0) {
					comissao+=vendas*0.12;
				} else {
					comissao+=vendas*0.095; 
				}
		return comissao; 
	}
	
	public void relatorio() {
		corretores.forEach((corretor) -> {
			totalVendas+=corretor.getVendaMensal();
			System.out.println(corretor.toString());
		});	
		System.out.println("Total Vendas = R$"+totalVendas);
	}
	
	public Double getSalarioCorretor(int index) { 
		return corretores.get(index).salario;
	}
	public Double getTotalVendas(int index) { 
		return this.totalVendas;
	}
	
	public class Corretor {
		String nome; 
		Calendar dtContratacao; 
		Double vendaMensal; 
		Double salario;
						
		public Double getSalario() {
			return salario;
		}
		public Double getVendaMensal() {
			return vendaMensal;
		}

		@Override
		public String toString() {
			return "Corretor [nome=" + nome + ", dt.Contratacao=" + dtContratacao + ", venda.Mensal=" + vendaMensal
					+ ", salario=" + salario + "]";
		}		
	}
} 

