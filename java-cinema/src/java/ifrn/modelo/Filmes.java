/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifrn.modelo;

public class Filmes {

	private int id;
	private String titulo;
	private String link;

	public Filmes() {
	}

	@Override
	public String toString() {
		return "Id: " + getId() + "\n" + "Titulo: " + getTitulo();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	

}