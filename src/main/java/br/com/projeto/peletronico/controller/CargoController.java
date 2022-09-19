package br.com.projeto.peletronico.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.peletronico.controller.dto.CargoDto;
import br.com.projeto.peletronico.controller.dto.CargoForm;
import br.com.projeto.peletronico.domain.Cargo;
import br.com.projeto.peletronico.service.CargoService;

@RestController//->Sempre vai devolver um corpo(@ResponseBody)
@RequestMapping("/cargo")
public class CargoController {
	
	private CargoService cargoService;
	
	@Autowired //Injetar via construtor
	public CargoController(CargoService cargoService) {
		this.cargoService = cargoService;
	} 
	
	@PostMapping
	public ResponseEntity<CargoDto > cadastrar(@RequestBody @Valid CargoForm cargoForm) {
		Cargo cargo = this.cargoService.cadastrarCargo(cargoForm);
		CargoDto cargoDto = new CargoDto(cargo);
		return ResponseEntity.status(HttpStatus.CREATED).body(cargoDto);
		
		
	}
	@DeleteMapping("/{id}")
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
