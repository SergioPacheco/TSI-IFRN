package br.edu.ifrn.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrn.dao.CategoriaDAO;
import br.edu.ifrn.dao.QuestaoDAO;
import br.edu.ifrn.model.Categoria;
import br.edu.ifrn.model.Questao;
import br.edu.ifrn.service.UserService;
import br.edu.ifrn.util.FacesUtil;

@Named
@ViewScoped
public class TesteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuestaoDAO questaoDao;
	
	@Inject
	private CategoriaDAO categoriaDao;
	
	private List<Questao> questoes;
	private HashMap<Questao, String[]> testes = new HashMap<Questao, String[]>();
	private HashMap<Questao, String> selecoes = new HashMap<Questao, String>();
	private HashMap<Questao, String> mensagens = new HashMap<Questao, String>();
	private int pontos;

	@PostConstruct
	public void init() {

		questoes = criaTeste();
		testes = preenche(testes);

	}

	public List<Questao> getQuestoes() {
		questoes = criaTeste();
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	// ****
	public QuestaoDAO getQuestaoDao() {
		return questaoDao;
	}

	public void setQuestaoDao(QuestaoDAO questaoDao) {
		this.questaoDao = questaoDao;
	}

	public HashMap<Questao, String[]> getTestes() {
		return testes;
	}

	public void setTestes(HashMap<Questao, String[]> testes) {
		this.testes = testes;
	}

	public HashMap<Questao, String> getSelecoes() {
		return selecoes;
	}

	public void setSelecoes(HashMap<Questao, String> selecoes) {
		this.selecoes = selecoes;
	}

	public int getPontos() {
		pontos = corrige();
		return pontos;
	}

	public void setPoints(int pontos) {
		this.pontos = pontos;
	}

	public HashMap<Questao, String> getMensagens() {

		return mensagens;
	}

	public void setMessages(HashMap<Questao, String> messages) {
		this.mensagens = messages;
	}

	// @SuppressWarnings("rawtypes")
	public int corrige() {
		int k = 0;
		Set set = selecoes.entrySet();
		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {

			Map.Entry entrada = (Map.Entry) iterator.next();

			if (((Questao) entrada.getKey()).getResposta()
											.getVerdadeira1().equals(entrada.getValue())) {
				k++;
				mensagens.put((Questao) entrada.getKey(), "Resposta Certa!");

			} else {
				mensagens.put((Questao) entrada.getKey(), "Resposta Errada! : " + ((Questao) entrada.getKey()).getResposta().getVerdadeira1());
			}

		}
		return k;
	}

	public String[] randomize(String[] array) {
		Random gerador = new Random();
		for (int i = 0; i < array.length; i++) {
			{
				int rnr = gerador.nextInt(array.length);
				String m = array[i];
				array[i] = array[rnr];
				array[rnr] = m;

			}
		}
		return array;
	}

	// @SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap preenche(HashMap hm) {

		for (int i = 0; i < questoes.size(); i++) {
			{
				String s1 = questoes.get(i).getResposta().getVerdadeira1();
				String s2 = questoes.get(i).getResposta().getFalsa1();
				String s3 = questoes.get(i).getResposta().getFalsa2();
				String s4 = questoes.get(i).getResposta().getFalsa3();
				String[] m = { s1, s2, s3, s4 };
				m = randomize(m);
				Questao q = questoes.get(i);
				hm.put(q, m);
			}
		}
		return hm;

	}
	
	public List<Questao> criaTeste() {
		
		int m;
		
		List<Categoria> categorias = categoriaDao.listAll();
		List<Questao> sorteio = new ArrayList<Questao>();
		
		if(categorias.size()>20)
			m = 1;
		else
			m = 20 / categorias.size();
		for (int i = 0; i < categorias.size(); i++) {
			sorteio.addAll(questaoDao.getQuestaoRandomica(categorias.get(i).getId(), m));
		}
		return sorteio;
	}

}
