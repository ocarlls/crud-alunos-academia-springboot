package com.crudalunosacademia.controllers;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crudalunosacademia.models.Aluno;
import com.crudalunosacademia.models.Endereco;
import com.crudalunosacademia.repository.AlunoRepository;
import com.crudalunosacademia.repository.EnderecoRepository;
@Controller
public class AlunoController {
	
	//Faz a injeção de dependencia(instancia a interface "AlunoRepository" toda vez que for necessário)
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//Quando receber uma requisição da pagina /alunos, devolve pra view pelo thymeleaf a lista de alunos que foi obtida no BD
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView listaAlunos() {
		ModelAndView mv = new ModelAndView("index"); 
		Iterable<Aluno> alunos = alunoRepository.findAll();
		mv.addObject("alunos", alunos);
		return mv;
	}
	
	//Retorna o formulário toda vez que tiver uma requisição (GET)
	@RequestMapping(value="/cadastrarAluno", method=RequestMethod.GET)
	public String form() {
		return "AlunoForm";
	}
	
	//Faz a requisição de salvar os dados no BD (POST)
	@RequestMapping(value="/cadastrarAluno", method=RequestMethod.POST)
	public String form(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique todos os campos!!");
			return "redirect:/cadastrarAluno";
		}
		alunoRepository.save(aluno);
		attributes.addAttribute("matricula", aluno.getMatricula());
		return "redirect:/{matricula}";
	}
	
	@RequestMapping(value="/{matricula}", method=RequestMethod.GET)
	public ModelAndView enderecoForm(Endereco endereco) {
		ModelAndView mv = new ModelAndView("enderecoForm");
		mv.addObject("endereco", endereco);
		return mv;
	}
	
	@RequestMapping(value="/{matricula}", method=RequestMethod.POST)
	public String enderecoForm(@PathVariable("matricula") long matricula, @Valid Endereco endereco, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique todos os campos!!");
			return "redirect:/{matricula}";
		}
		Aluno aluno = alunoRepository.findByMatricula(matricula);
		aluno.setEndereco(endereco);
		enderecoRepository.save(endereco);
		attributes.addFlashAttribute("mensagem", "Aluno cadastrado com sucesso!!");
		return "redirect:";
	}
	
	//Apresenta a pagina de detalhes do aluno
	@RequestMapping(value="/detalhesAluno/{matricula}", method=RequestMethod.GET)
	public ModelAndView detalhesAluno(@PathVariable("matricula") long matricula) {
		Aluno aluno = alunoRepository.findByMatricula(matricula);
		ModelAndView mv = new ModelAndView("detalhesAluno");
		mv.addObject("aluno", aluno);
		return mv;
	}
	
	
}