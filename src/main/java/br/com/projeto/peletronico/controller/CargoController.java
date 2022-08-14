package br.com.projeto.peletronico.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.peletronico.controller.dto.InserirCargoForm;
import br.com.projeto.peletronico.domain.Cargo;
import br.com.projeto.peletronico.service.CargoService;

@RestController
@RequestMapping("/cargo")
public class CargoController {
	
	private CargoService cargoService;

	public CargoController(CargoService cargoService) {
		this.cargoService = cargoService;
	} 
	
	@PostMapping
	@Transactional
	public ResponseEntity<Object> cadastrar(@RequestBody InserirCargoForm inserirCargoDto) {
		Cargo cargo = this.cargoService.cadastrarCargo(inserirCargoDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(cargo);
		
		
	}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deletar(@PathVariable(value = "id") Long id){
		this.cargoService.deletarById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Cargo Deletado");
		
	}
	@GetMapping
	public ResponseEntity<Object> visualizarCargos() {
		List<Cargo> cargos = this.cargoService.visualizar();
		return ResponseEntity.status(HttpStatus.OK).body(cargos);
		
	}
}
