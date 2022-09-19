package br.com.projeto.peletronico.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.projeto.peletronico.controller.dto.PontoDtoSaida;
import br.com.projeto.peletronico.controller.dto.PontoRelatorioDto;
import br.com.projeto.peletronico.domain.Funcionario;
import br.com.projeto.peletronico.domain.Ponto;
import br.com.projeto.peletronico.service.PontoService;

class PontoControllerTest {

	private PontoController pontoController;

	@Mock
	private PontoService pontoService;

	@BeforeEach
	void inicializacao() {
		MockitoAnnotations.openMocks(this);
		this.pontoController = new PontoController(this.pontoService);

	}

	@Test
	void deveriaBaterPonto() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("nome1");

		Ponto ponto = new Ponto();
		ponto.setFuncionario(funcionario);

		Mockito.when(this.pontoService.baterPonto(Long.parseLong("1"))).thenReturn(ponto);

		ResponseEntity<PontoDtoSaida> baterPonto = this.pontoController.baterPonto(Long.parseLong("1"));
		PontoDtoSaida bodyDto = baterPonto.getBody();

		Assertions.assertThat(baterPonto).as("Ponto Batido e Retornado")
				.isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(bodyDto));

	}

	@Test
	void deveriaCriarRelatorioDeHorasPorFuncionario() {
		List<Ponto> listaDePontos = listaDePontosFuncionario();
		Mockito.when(this.pontoService.localizarListaDePontos(Long.parseLong("1"))).thenReturn(listaDePontos);

		List<PontoRelatorioDto> relatorioDeHorasPorFuncionario = this.pontoController
				.RelatorioDeHorasPorFuncionario(Long.parseLong("1"));

		Assertions.assertThat(relatorioDeHorasPorFuncionario).as("Relatório de Horas Funcionario Criado")
				.isEqualTo(relatorioDeHorasPorFuncionario);

	}

	private List<Ponto> listaDePontosFuncionario() {
		Funcionario funcionario1 = new Funcionario();
		funcionario1.setNome("nome1");
		funcionario1.setId(Long.parseLong("1"));

		Ponto ponto = new Ponto();
		ponto.setFuncionario(funcionario1);
		ponto.setHoraEntrada(LocalTime.now());
		ponto.setHoraSaida(LocalTime.now().plusHours(Long.parseLong("2")));

		ArrayList<Ponto> listaDePontos = new ArrayList<>();
		listaDePontos.add(ponto);

		return listaDePontos;

	}

}
