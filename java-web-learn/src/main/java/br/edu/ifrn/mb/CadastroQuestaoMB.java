package br.edu.ifrn.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.model.Questao;
import br.edu.ifrn.model.Resposta;
import br.edu.ifrn.service.CategoriaService;
import br.edu.ifrn.service.QuestaoService;
import br.edu.ifrn.service.UserService;

import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class CadastroQuestaoMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idQuestao;
	private Questao questao = new Questao();
	
	// private Resposta resposta = new Resposta();
	private List<Categoria> categorias; 
	private List<Questao> todasQuestoes;   //usado pelo admin
	private List<Questao> questoes;		   //usado pelo user	
	private UploadedFile arquivo;
	
	
	@Inject
	private QuestaoService questaoService;
	
	@Inject
	private CategoriaService categoriaService;
	
	public void inicializar() {
		if (idQuestao != null) {
			questao = questaoService.porId(idQuestao);
		}
		categorias = categoriaService.listAll(); 
		 
	}
	
	public String salvar() {
		 
		questaoService.salvar(questao);
		return "lista-questao.xhtml?faces-redirect=true";
	}
	
	public String excluir() {
		questaoService.excluir(questao);
		return "lista-questao.xhtml?faces-redirect=true";
	}

	
	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}
	
	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	

}
