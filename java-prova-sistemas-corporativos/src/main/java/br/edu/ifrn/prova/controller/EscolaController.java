package br.edu.ifrn.prova.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.prova.model.Escola;
import br.edu.ifrn.prova.service.AlunoService;
import br.edu.ifrn.prova.service.EscolaService;

@Controller  
public class EscolaController {
	
	@Autowired
	private EscolaService escolaService;  
	
	@Autowired
	private AlunoService alunoService;  
	
	 
	@GetMapping("/escola")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/escola");
		mv.addObject("escolas", escolaService.findAll());
		return mv;
	}
	
	//Vai para tela de adição de escola
	@GetMapping("/addEscola")
	public ModelAndView add(Escola escola) {
		
		ModelAndView mv = new ModelAndView("/escolaAdd");
		mv.addObject("escola", escola);
		mv.addObject("listaAlunos", alunoService.findByEscola(escola.getId())); 
		
		return mv;
	}
	
	//Vai para tela de edição de escolas (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
	@GetMapping("/editEscola/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(escolaService.findOne(id));
	}
	
	//Exclui um escola por seu ID e redireciona para a tela principal
	@GetMapping("/deleteEscola/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		escolaService.delete(id);
		
		return findAll();
	}
	
	//Recebe um objeto preenchido do Thymeleaf e valida 
	//Se tudo estiver ok, salva e volta para tela principal
	//Se houver erro, retorna para tela atual exibindo as mensagens de erro
	@PostMapping("/saveEscola")
	public ModelAndView save(@Valid Escola escola, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(escola);
		}
		
		escolaService.save(escola);
		
		return findAll();
	}
	
}
