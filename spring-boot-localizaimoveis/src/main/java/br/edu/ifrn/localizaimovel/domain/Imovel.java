package br.edu.ifrn.localizaimovel.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class Imovel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* -- Exemplo do Objeto
	  {
	"tipo": "Casa",
    "descricao": "Casa, 90.50 de área total, 90.50 de área privativa, 248.41 de área do terreno, 2 qto(s), a.serv, WC, 1 sala(s), cozinha.",
    "valorAvaliacao": 96.667,01,
    "preco": 29.000,10,
    "modalidadeVenda": "Venda Direta Online",
    "desconto": 70.01,
    "foto": "",
    "bairro": "TANCREDO NEVES",
    "link": "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><u><span style=\"font-size:8.0pt;color:blue\"><a href=\"http://www1.caixa.gov.br/simov/detalhe-imovel.asp?hdnOrigem=index&amp;hdnimovel= \n                   \n 8053400008895 \"> Detalhes</a></span></u></p>",
    "endereco": "Rua Luiz Moraes, N. 253",
    "cidade": "RIO BRANCO",
    "estado": "AC"
  		}
	 * */
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@JsonProperty(value = "tipo")
	private String tipo;
	@JsonProperty(value = "descricao")
	private String descricao;
	@JsonProperty(value = "preco")
	private Double preco;
	@JsonProperty(value = "foto")
	private String foto;
	@JsonProperty(value = "link")
	private String link;
	@JsonProperty(value = "modalidadeVenda")
	private String modalidadeVenda;
	@JsonProperty(value = "valorAvaliacao")
	private Double valorAvaliacao;
	@JsonProperty(value = "desconto")
	private Double desconto;
	@JsonProperty(value = "cidade")
	private String cidade;
	@JsonProperty(value = "endereco")
	private String endereco;
	@JsonProperty(value = "bairro")
	private String bairro;
	@JsonProperty(value = "estado")
	private String estado;

	
	public Imovel() {
		
	}

	public Imovel(Integer id,String tipo, String descricao, Double preco, String foto, String link, String modalidadeVenda,
			Double valorAvaliacao, Double desconto, String cidade, String endereco, String bairro, String estado) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.preco = preco;
		this.foto = foto;
		this.link = link;
		this.modalidadeVenda = modalidadeVenda;
		this.valorAvaliacao = valorAvaliacao;
		this.desconto = desconto;
		this.cidade = cidade;
		this.endereco = endereco;
		this.bairro = bairro;
		this.estado = estado;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public Double getPreco() {
		return preco;
	}



	public void setPreco(Double preco) {
		this.preco = preco;
	}



	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	public String getModalidadeVenda() {
		return modalidadeVenda;
	}



	public void setModalidadeVenda(String modalidadeVenda) {
		this.modalidadeVenda = modalidadeVenda;
	}



	public Double getValorAvaliacao() {
		return valorAvaliacao;
	}



	public void setValorAvaliacao(Double valorAvaliacao) {
		this.valorAvaliacao = valorAvaliacao;
	}



	public Double getDesconto() {
		return desconto;
	}



	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	public String getEndereco() {
		return endereco;
	}



	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}



	public String getBairro() {
		return bairro;
	}



	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
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
		Imovel other = (Imovel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
