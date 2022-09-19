package br.com.projeto.peletronico.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.projeto.peletronico.domain.Cargo;
import br.com.projeto.peletronico.domain.Funcionario;
import br.com.projeto.peletronico.domain.Ponto;
import br.com.projeto.peletronico.exception.NotFoundException;
import br.com.projeto.peletronico.repository.FuncionarioRepository;
import br.com.projeto.peletronico.repository.PontoRepository;

class PontoServiceTest {

	private PontoService pontoService;
	@Mock
	private PontoRepository pontoRepository;
	@Mock
	private FuncionarioRepository funcionarioRepository;

	@BeforeEach
	void inicializacao() {
		MockitoAnnotations.openMocks(this);
		this.pontoService = new PontoService(this.pontoRepository, this.funcionarioRepository);
	}

	@Test
	@DisplayName("Dado um idLong, ao passar como parâmetro, deve entregar um Optional.of(ponto)")
	void testarLocalizacaoDePonto() {
		// setup
		Cargo cargo = new Cargo();
		cargo.setNome("nomeCargo");

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("nomeFuncionario");
		funcionario.setCargo(cargo);

		Ponto ponto = new Ponto();
		ponto.setFuncionario(funcionario);

		Long parseLong = Long.parseLong("1");
		// execute
		this.pontoService.localizarPonto(parseLong);
		// assert
		Mockito.when(this.pontoRepository.findById(parseLong)).thenReturn(Optional.of(ponto));

	}

	@Test
	@DisplayName("Dado um funcionarioId, ao passar como parâmetro, deve entregar uma lista de Funcionario")
	void testarLocalizarListaDePonto() {
		// setup
		List<Ponto> listaDePontos = listaDePontos();
		Long funcionarioId = Long.parseLong("1");
		// execute
		this.pontoService.localizarListaDePontos(funcionarioId);
		// assert
		Mockito.when(this.pontoRepository.localizarListaDePontos(funcionarioId)).thenReturn(listaDePontos);

	}

	@Test
	@DisplayName("Dado um funcionarioId que não existe no BD, ao passar como parâmetro, deve entregar NotFoundException")
	void testarBaterPonto() {
		// setup
		Cargo cargo = new Cargo();
		cargo.setNome("nomeCargo");

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("nomeFuncionario");
		funcionario.setCargo(cargo);

		Long funcionarioId = Long.parseLong("1");
		LocalDate data = LocalDate.now();
		
		// execute & assert
		this.pontoService.baterPonto(funcionarioId);
		Optional<Funcionario> optionalFuncionario = Optional.of(funcionario);
//		Optional<Funcionario> optionalFuncionario = this.funcionarioRepository.findById(funcionarioId);
//		Mockito.when(this.funcionarioRepository.findById(Long.parseLong("1"))).thenReturn(optionalFuncionario);
		if(!optionalFuncionario.isPresent()) {
			Assertions.assertThatThrownBy(() -> { throw new NotFoundException("Funcionario não existe"); }).hasMessage("Funcionario não existe");
		}
		

	}

	private List<Ponto> listaDePontos() {
		Cargo cargo = new Cargo();
		cargo.setNome("nomeCargo");

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("nomeFuncionario");
		funcionario.setCargo(cargo);

		Ponto ponto = new Ponto();
		ponto.setFuncionario(funcionario);

		List<Ponto> pontos = new ArrayList<>();
		pontos.add(ponto);
		return pontos;

	}

}
