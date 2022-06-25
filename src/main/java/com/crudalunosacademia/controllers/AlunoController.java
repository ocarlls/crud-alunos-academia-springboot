package com.crudalunosacademia.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crudalunosacademia.models.AlunoModel;
import com.crudalunosacademia.repository.AlunoRepository;

@Controller
public class AlunoController {
	
	//Faz a injeção de dependencia(instancia a interface "AlunoRepository" toda vez que for necessário)
	@Autowired
	private AlunoRepository alunoRepository;
	
	//Retorna o formulário toda vez que tiver uma requisição (GET)
	@RequestMapping(value="/cadastrarAluno", method=RequestMethod.GET)
	public String form() {
		return "AlunoForm";
	}
	
	//Faz a requisição de salvar os dados no BD (POST)
	@RequestMapping(value="/cadastrarAluno", method=RequestMethod.POST)
	public String form(AlunoModel aluno) {
		
		alunoRepository.save(aluno);
		
		return "redirect:/alunos";
	}
	
	//Quando receber uma requisição da pagina /alunos, devolve pra view pelo thymeleaf a lista de alunos que foi obtida no BD
	@RequestMapping("/alunos")
	public ModelAndView listaAlunos() {
		ModelAndView mv = new ModelAndView("index"); 
		Iterable<AlunoModel> alunos = alunoRepository.findAll();
		mv.addObject("alunos", alunos);
		return mv;
	}
}