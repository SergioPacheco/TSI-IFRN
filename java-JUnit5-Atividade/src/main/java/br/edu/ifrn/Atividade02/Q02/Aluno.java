package br.edu.ifrn.Atividade02.Q02;

public class Aluno {
	
	private String nome;     // > len(3) 
	private int idade;       // >= 18  
	private double nota1;    // 0 a 100 
	private double nota2; 	 // 0 a 100
	private int faltas;      // >= 0
	
 	public boolean cadastrar(String nome, int idade, double nota1,
			double nota2, int faltas) { 
		if (nome == null || nome.trim().length() < 4) { 
			 throw new IllegalArgumentException
             ("Nome deve ter mais que 3 caracteres");
		}
		if (idade < 18) { 
			throw new IllegalArgumentException
            ("Aluno deve ser igual ou maior de 18 anos");
		}
		if (nota1 <0 || nota1 > 100) { 
			throw new IllegalArgumentException 
			("Nota1 deve estar entre 0 e 100"); 
			
		}
		if (nota2 <0 || nota2 > 100) { 
			throw new IllegalArgumentException 
			("Nota2 deve estar entre 0 e 100"); 
		}
		if (faltas < 0) { 
			throw new IllegalArgumentException 
			("Faltas devem positivas"); 
		}
		
		this.nome = nome;
		this.idade = idade; 
		this.nota1 = nota1; 
		this.nota2 = nota2; 
		this.faltas = faltas; 		
		return true; 
	}

 	public int media() { 
 		return (int) (nota1+nota2)/2; 
 	}
 	
 	public String aprovado() { 
 		return (media() >=60) ? "APROVADO" : "REPROVADO"; 
 	}
 	
	public String getNome() {
		return nome;
	}
	
	public int getIdade() {
		return idade;
	}

	public double getNota1() {
		return nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public int getFaltas() {
		return faltas;
	}


}
