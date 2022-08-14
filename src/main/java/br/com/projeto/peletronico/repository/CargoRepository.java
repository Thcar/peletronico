package br.com.projeto.peletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.peletronico.domain.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
	
	@Query("SELECT c FROM Funcionario f JOIN f.cargo c WHERE f.cpf = :cpf")
	Cargo procurarPorCpfDoFuncionario(@Param("cpf") String cpf);

	boolean existsByNome(String nome);
}
