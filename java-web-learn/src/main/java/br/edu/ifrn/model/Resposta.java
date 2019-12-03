package br.edu.ifrn.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resposta")
public class Resposta implements Serializable  {
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "verdadeira1", nullable= false, length = 100)
	private String verdadeira1;

	@Column(name = "falsa1", nullable= false, length = 100)
	private String falsa1;

	@Column(name = "falsa2", nullable= false, length = 100)
	private String falsa2;

	@Column(name = "falsa3", nullable= false, length = 100)
	private String falsa3;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "questao_id")
	private Questao questao;

	public Resposta() {
		super();
	}

	public String getVerdadeira1() {
		return verdadeira1;
	}

	public void setVerdadeira1(String verdadeira1) {
		this.verdadeira1 = verdadeira1;
	}

	public String getFalsa1() {
		return falsa1;
	}

	public void setFalsa1(String falsa1) {
		this.falsa1 = falsa1;
	}

	public String getFalsa2() {
		return falsa2;
	}

	public void setFalsa2(String falsa2) {
		this.falsa2 = falsa2;
	}

	public String getFalsa3() {
		return falsa3;
	}

	public void setFalsa3(String falsa3) {
		this.falsa3 = falsa3;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
	public boolean isInclusao() {
		return getId() == null ? true : false;
	}
	
	public boolean isEdicao() {
		return !isInclusao();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resposta other = (Resposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
