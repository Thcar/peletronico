package br.com.projeto.peletronico.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CargoTest {

	private Cargo cargo;

	@Test
	void testarAdicionarFuncionario() {
		this.cargo = new Cargo();
		Funcionario funcionario = new Funcionario();
		cargo.adicionarFuncionario(funcionario);
		assertThat(cargo.getFuncionarios()).as("Lista deveria ter um funcionario").isNotEmpty();
	}

	@DisplayName("Dado um Cargo sem Funcionario, ao adicionar nulo, naõ deveria adicionar dentro da lista ")
	@Test
	void test001() {
		// Setup
		this.cargo = new Cargo();
		//Execute/ Assert
		assertThatThrownBy(() -> {cargo.adicionarFuncionario(null); })
		.isInstanceOf(Exception.class)
        .hasMessageContaining("Funcionario não pode ser Nulo");
		
		assertThat(cargo.getFuncionarios()).as("Lista deve continuar vazia").isEmpty();

	}

}
