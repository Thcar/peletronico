package br.com.projeto.peletronico.controller;

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

import br.com.projeto.peletronico.controller.dto.InserirFuncionarioForm;
import br.com.projeto.peletronico.domain.Funcionario;
import br.com.projeto.peletronico.service.FuncionarioService;

class FuncionarioControllerTest {

	private FuncionarioController funcionarioController;
	@Mock
	private FuncionarioService funcionarioService;

	@BeforeEach
	void inicializacao() {
		MockitoAnnotations.openMocks(this);
		this.funcionarioController = new FuncionarioController(this.funcionarioService);
	}

	@Test
	void deveriaCadastrarFuncionarioNoBancoDeDados() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Fernando");

		InserirFuncionarioForm funcionarioForm = new InserirFuncionarioForm();
		funcionarioForm.setNome("Fernando");

		Mockito.when(this.funcionarioService.cadastrar(funcionarioForm)).thenReturn(funcionario);

		Assertions.assertThat(this.funcionarioController.cadastrar(funcionarioForm))
				.as("Retornar Funcionario Cadastrado")
				.isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(funcionario));

	}

	@Test
	void deveriaVisualizarFuncionarios() {
		List<Funcionario> listaFuncionario = listaFuncionario();

		Mockito.when(this.funcionarioService.visualizarFuncionarios()).thenReturn(listaFuncionario);

		Assertions.assertThat(this.funcionarioController.visualizarFuncionarios())
				.as("Retornar Listas de Funcionários Cadastrados")
				.isEqualTo(ResponseEntity.status(HttpStatus.OK).body(listaFuncionario));

	}

	@Test
	void deveriaAtualizarFuncionarios() {
		Long parseLong = Long.parseLong("1");

		InserirFuncionarioForm funcionarioForm = new InserirFuncionarioForm();
		funcionarioForm.setNome("Fernando");

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Fernando");

		Mockito.when(this.funcionarioService.atualizar(parseLong, funcionarioForm)).thenReturn(funcionario);

		Assertions.assertThat(this.funcionarioController.atualizar(parseLong, funcionarioForm))
				.as("Atualizado Funcionario")
				.isEqualTo(ResponseEntity.status(HttpStatus.OK).body(funcionario));
	}

	private List<Funcionario> listaFuncionario() {
		Funcionario funcionario1 = new Funcionario();
		funcionario1.setNome("funcionario1");

		Funcionario funcionario2 = new Funcionario();
		funcionario2.setNome("funcionario2");

		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios.add(funcionario1);
		funcionarios.add(funcionario1);

		return funcionarios;
	}

}
