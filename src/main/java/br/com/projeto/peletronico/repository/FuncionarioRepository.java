package br.com.projeto.peletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.peletronico.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome")
	Funcionario procurarPorNome(@Param("nome") String nomeFuncionario);
	
	//@Query("SELECT f FROM Funcionario f WHERE f.cpf = :cpf")
	Funcionario findByCpf(String cpf);
	
	boolean existsByCpf(String cpf);

}
