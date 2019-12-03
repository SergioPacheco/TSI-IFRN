package br.edu.ifrn.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.model.Questao;
import br.edu.ifrn.service.QuestaoService;
import br.edu.ifrn.util.FacesUtil;

@Named
@ViewScoped
public class ListaQuestaoMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private QuestaoService questaoService;
	private List<Questao> questaos = new ArrayList<>();
	private List<Questao> questaoSelecionadas = new ArrayList<>();
	
	
	@PostConstruct
	public void inicializar() {
		questaos = questaoService.listAll();
	}
	
	public void excluirSelecionados() {
		for (Questao questao : questaoSelecionadas) {
			questaoService.excluir(questao);
			questaos.remove(questao);
		}
		
		FacesUtil.addInfoMessage("Questão excluida com sucesso!");
	}

	public List<Questao> getQuestaos() {
		return questaos;
	}

	public void setQuestaos(List<Questao> questaos) {
		this.questaos = questaos;
	}

	public List<Questao> getQuestaoSelecionadas() {
		return questaoSelecionadas;
	}

	public void setQuestaoSelecionadas(List<Questao> questaoSelecionadas) {
		this.questaoSelecionadas = questaoSelecionadas;
	}	

}
