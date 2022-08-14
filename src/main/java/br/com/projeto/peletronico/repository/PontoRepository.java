package br.com.projeto.peletronico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.peletronico.domain.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

	@Query("SELECT p FROM Ponto p WHERE p.funcionario.id=:id")
	List<Ponto> localizarListaDePontos(@Param("id") Long funcionario_id);
	
//	@Query("SELECT new br.com.projeto.peletronico.modelo.Formulario(f.nome, f.cpf, p.data, p.horaEntrada_1,p.horaSaida_1, p.horaEntrada_2, p.horaSaida_2, c.nome)"
//			+ "FROM Ponto p JOIN p.funcionario f JOIN f.cargo c where f.cpf = :cpf")
//	List<Formulario> criarFormulario(@Param("cpf") String cpf);

//	@Query("SELECT p.data FROM Ponto p WHERE p.id = :id")
//	LocalDate localizarDataDeEntrada(@Param("id") Long id);


	

}
