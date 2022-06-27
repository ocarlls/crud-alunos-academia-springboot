package com.crudalunosacademia.repository;
import org.springframework.data.repository.CrudRepository;

import com.crudalunosacademia.models.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, String>{
	Aluno findByMatricula(long matricula);
}
