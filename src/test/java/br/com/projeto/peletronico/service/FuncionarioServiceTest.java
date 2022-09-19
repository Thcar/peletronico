package br.com.projeto.peletronico.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.projeto.peletronico.controller.dto.InserirFuncionarioForm;
import br.com.projeto.peletronico.domain.Cargo;
import br.com.projeto.peletronico.domain.Funcionario;
import br.com.projeto.peletronico.exception.CadastrarException;
import br.com.projeto.peletronico.repository.CargoRepository;
import br.com.projeto.peletronico.repository.FuncionarioRepository;

class FuncionarioServiceTest {

	private FuncionarioService funcionarioService;
	@Mock
	private FuncionarioRepository funcionarioRepository;
	@Mock
	private CargoRepository cargoRepository;

	@BeforeEach
	void inicializacao() { 
		MockitoAnnotations.openMocks(this);
		this.funcionarioService = new FuncionarioService(this.cargoRepository, this.funcionarioRepository);

	}

	@Test
	@DisplayName("Dado um funcionarioDto, e ele já estiver cadastrado no BD, deve me devolver uma Exception")
	void test001() {
		
		Cargo cargo = new Cargo(); 
		cargo.setId(Long.parseLong("1"));
		cargo.setNome("TESTECARGO");
		
		InserirFuncionarioForm funcionarioForm = new InserirFuncionarioForm();
		funcionarioForm.setCpf("1234");
		funcionarioForm.setIdCargo(cargo.getId());
		
		this.funcionarioService.cadastrar(funcionarioForm);
		Funcionario funcionario = new Funcionario();

		Mockito.when(this.cargoRepository.findById(funcionarioForm.getIdCargo())).thenReturn(Optional.of(cargo));
		funcionario.setCargo(cargo);

		Mockito.when(this.funcionarioRepository.existsByCpf(funcionarioForm.getCpf())).thenReturn(true);

		assertThatThrownBy(() -> {
			throw new CadastrarException("Cpf já foi cadastrado");
		}).hasMessageContaining("Cpf já foi cadastrado");

	}

}
