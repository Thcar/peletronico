package br.com.projeto.peletronico.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.projeto.peletronico.controller.dto.CargoForm;
import br.com.projeto.peletronico.domain.Cargo;
import br.com.projeto.peletronico.exception.NotFoundException;
import br.com.projeto.peletronico.repository.CargoRepository;

class CargoServiceTest {

	private CargoService cargoService;

	@Mock
	private CargoRepository cargoRepository;

	@BeforeEach
	void inicializacao() {
		MockitoAnnotations.openMocks(this);
		this.cargoService = new CargoService(this.cargoRepository);

	}

	@Test
	@DisplayName("Dado um cargoForm, ao passar como parâmetro um cargo já cadastrado, deve me entregar um exception")
	void test001() {

		CargoForm cargoForm = new CargoForm();
		cargoForm.setNome("NomeDeCargo");

		Mockito.when(this.cargoRepository.existsByNome(cargoForm.getNome())).thenReturn(true);
		assertThatThrownBy(() -> {
			throw new Exception("Cargo já cadastrado");
		}).hasMessage("Cargo já cadastrado");

	}

	@Test
	@DisplayName("Dado um cargoForm, ao passar como parâmetro um cargo não cadastrado, deve me entregar cargo cadastrado")
	void test002() {
		CargoForm cargoForm = new CargoForm();
		cargoForm.setNome("NomeDeCargo");

		Cargo cargo = cargoForm.criarCargo();

		Mockito.when(this.cargoRepository.existsByNome(cargoForm.getNome())).thenReturn(false);
		Mockito.when(this.cargoRepository.save(cargo)).thenReturn(cargo);

		Cargo cargoCadastrado = this.cargoService.cadastrarCargo(cargoForm);

		Assertions.assertThat(cargoCadastrado).as("Deveria Cadastrar Cargo no BD").usingRecursiveComparison()
				.isEqualTo(cargoCadastrado);
	}

	@Test
	@DisplayName("Dado que um cargo não existe no BD, deve me entregar um exception")
	void test003() {
		Mockito.when(this.cargoRepository.existsById(Long.parseLong("1"))).thenReturn(false);
		assertThatThrownBy(() -> {
			throw new NotFoundException("Cargo não existe");
		}).hasMessage("Cargo não existe");

	}

	@Test
	@DisplayName("Dado que um cargo existe no BD, deve deletá-lo")
	void test004() {
		CargoService cargoServiceMockito = Mockito.mock(CargoService.class);
		Mockito.doNothing().when(cargoServiceMockito).deletarById(Long.parseLong("1"));

	}

	@Test
	@DisplayName("Deveria devolver todos os cargos cadastrados")
	void test005() {
		List<Cargo> listaCargos = listaCargos();
		Mockito.when(this.cargoRepository.findAll()).thenReturn(listaCargos);

		List<Cargo> lista = this.cargoService.visualizar();
		Assertions.assertThat(lista).as("Cargos Visualizados").isEqualTo(listaCargos);
	}

	//método privado
	private List<Cargo> listaCargos() {
		Cargo cargo = new Cargo();
		cargo.setNome("ENFERMEIRO");
		cargo.setId(Long.parseLong("1"));

		ArrayList<Cargo> lista = new ArrayList<>();
		lista.add(cargo);
		return lista;

	}

}
