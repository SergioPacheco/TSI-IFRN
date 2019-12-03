/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifrn.modelo;

public class Horarios {
	private int id;
	private String dia;
	private String hora;
	private Float preco;
	
	public Horarios () {
		
	}
	
	public int getId() {
		return id;
	}
        public String getIdString() {
		return Integer.toString(id);
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Float getPreco() {
		return preco;
	}
        public String getPrecoString() {
		return Float.toString(preco);
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	

}


