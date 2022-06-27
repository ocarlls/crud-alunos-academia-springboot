package com.crudalunosacademia.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crudalunosacademia.models.Aluno;
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
	public String form(Aluno aluno) {
		
		alunoRepository.save(aluno);
		
		return "redirect:/";
	}
	
	//Quando receber uma requisição da pagina /alunos, devolve pra view pelo thymeleaf a lista de alunos que foi obtida no BD
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView listaAlunos() {
		ModelAndView mv = new ModelAndView("index"); 
		Iterable<Aluno> alunos = alunoRepository.findAll();
		mv.addObject("alunos", alunos);
		return mv;
	}
	
	@RequestMapping("detalhesAluno/{matricula}")
	public ModelAndView detalhesAluno(@PathVariable("matricula") long matricula) {
		Aluno aluno = alunoRepository.findByMatricula(matricula);
		ModelAndView mv = new ModelAndView("detalhesAluno");
		mv.addObject("detalhesAluno", aluno);
		return mv;
	}
}