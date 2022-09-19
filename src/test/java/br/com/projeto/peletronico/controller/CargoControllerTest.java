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

import br.com.projeto.peletronico.controller.dto.CargoDto;
import br.com.projeto.peletronico.controller.dto.CargoForm;
import br.com.projeto.peletronico.domain.Cargo;
import br.com.projeto.peletronico.service.CargoService;

class CargoControllerTest {

	private CargoController cargoController;

	@Mock
	private CargoService cargoService;

	@BeforeEach
	void inicializacao() {
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void deveriaCadastrarCargoUtilizandoOController() {
		CargoForm cargoForm = new CargoForm();
		cargoForm.setNome("ENFERMEIRO");

		Cargo cargo = new Cargo();
		cargo.setNome("ENFERMEIRO");
		cargo.setId(Long.parseLong("1"));

		Mockito.when(this.cargoService.cadastrarCargo(cargoForm)).thenReturn(cargo);

		this.cargoController = new CargoController(this.cargoService);
		
		ResponseEntity<CargoDto> resultado = this.cargoController.cadastrar(cargoForm);
		
		Assertions.assertThat(resultado.getBody()).as("Devolve um cargo Cadastrado")
		.usingRecursiveComparison()
		.isEqualTo(new CargoDto(cargo));
		
//		ResponseEntity<Object> resultado = this.cargoController.cadastrar(cargoForm);
//		CargoDto resultadoCargoDto = (CargoDto) resultado.getBody();
//		Assertions.assertThat(resultadoCargoDto.getNome()).as("Devolve um cargo Cadastrado")
//				.isEqualTo(new CargoDto(cargo).getNome());

	}
	
	@Test
	void deveriaVisualizarTodosOsCargosCadastrados() {
		List<Cargo> listaDeCargos = listaDeCargos();
		
		Mockito.when(this.cargoService.visualizar()).thenReturn(listaDeCargos);
		this.cargoController = new CargoController(this.cargoService);
		
		Assertions.assertThat(this.cargoController.visualizarCargos())
		.as("Devolver uma lista deCargos").isEqualTo(ResponseEntity.status(HttpStatus.OK).body(listaDeCargos));

	}
	
	private List<Cargo> listaDeCargos() {
		Cargo cargo1 = new Cargo();
		cargo1.setNome("cargo1");
		
		Cargo cargo2 = new Cargo();
		cargo2.setNome("cargo2");
		
		List<Cargo> cargos = new ArrayList<>();
		cargos.add(cargo1);
		cargos.add(cargo2);
		
		return cargos;
	}

}
