package br.com.projeto.peletronico.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.peletronico.domain.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

	@Query("SELECT p FROM Ponto p WHERE p.funcionario.id=:id")
	List<Ponto> localizarListaDePontos(@Param("id") Long funcionario_id);

	@Query("SELECT p FROM Ponto p WHERE p.id=:idFuncionario AND p.data=:data")
	Optional<Ponto> existePontoDeEntradaHoje(@Param("idFuncionario") Long idFuncionario, @Param("data") LocalDate data);

	@Query("SELECT p FROM Ponto p WHERE p.id=:idFuncionario AND p.horaSaida=:hora")
	Optional<Ponto> existePontoDeSaidaHoje(@Param("idFuncionario") Long idFuncionario, @Param("hora") LocalTime hora);

	@Query("SELECT p FROM Ponto p WHERE p.id=:idFuncionario AND p.horaEntrada=:horaEntrada")
	Ponto localizarPonto(@Param("idFuncionario") Long idFuncionario,@Param("horaEntrada") LocalTime horaEntrada);
	
//	@Query("SELECT new br.com.projeto.peletronico.modelo.Formulario(f.nome, f.cpf, p.data, p.horaEntrada_1,p.horaSaida_1, p.horaEntrada_2, p.horaSaida_2, c.nome)"
//			+ "FROM Ponto p JOIN p.funcionario f JOIN f.cargo c where f.cpf = :cpf")
//	List<Formulario> criarFormulario(@Param("cpf") String cpf);

//	@Query("SELECT p.data FROM Ponto p WHERE p.id = :id")
//	LocalDate localizarDataDeEntrada(@Param("id") Long id);


	

}
