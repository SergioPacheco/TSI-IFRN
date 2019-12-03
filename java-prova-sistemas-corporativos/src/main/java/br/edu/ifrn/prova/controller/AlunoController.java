package br.edu.ifrn.prova.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.prova.model.Aluno;
import br.edu.ifrn.prova.service.AlunoService;
import br.edu.ifrn.prova.service.EscolaService;

@Controller  
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;  
	
	@Autowired
	private EscolaService escolaService;  
	 
	@GetMapping("/aluno")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/aluno");
		mv.addObject("alunos", alunoService.findAll());
		return mv;
	}
	
	//Vai para tela de adição de aluno
	@GetMapping("/addAluno")
	public ModelAndView add(Aluno aluno) {
		
		ModelAndView mv = new ModelAndView("/alunoAdd");
		mv.addObject("aluno", aluno);
		mv.addObject("escolas", escolaService.findAll());
		
		return mv;
	}
	
	//Vai para tela de edição de alunos (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
	@GetMapping("/editAluno/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(alunoService.findOne(id));
	}
	
	//Exclui um aluno por seu ID e redireciona para a tela principal
	@GetMapping("/deleteAluno/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		alunoService.delete(id);
		
		return findAll();
	}
	
	//Recebe um objeto preenchido do Thymeleaf e valida 
	//Se tudo estiver ok, salva e volta para tela principal
	//Se houver erro, retorna para tela atual exibindo as mensagens de erro
	@PostMapping("/saveAluno")
	public ModelAndView save(@Valid Aluno aluno, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(aluno);
		}
		
		alunoService.save(aluno);
		
		return findAll();
	}
	
}
