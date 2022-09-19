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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.peletronico.controller.dto.InserirFuncionarioForm;
import br.com.projeto.peletronico.domain.Funcionario;
import br.com.projeto.peletronico.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	private FuncionarioService funcionarioService;
	
	@Autowired
	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid InserirFuncionarioForm funcionarioForm) {
		Funcionario funcionario = this.funcionarioService.cadastrar(funcionarioForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);

	}
	@GetMapping
	public ResponseEntity<Object> visualizarFuncionarios(){
		List<Funcionario> funcionarios = this.funcionarioService.visualizarFuncionarios();
		return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarById(@PathVariable(value = "id") Long id){
		this.funcionarioService.deletarById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Funcionario Deletado");
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id, @RequestBody InserirFuncionarioForm inserirFuncionarioDto){
		Funcionario funcionario = this.funcionarioService.atualizar(id, inserirFuncionarioDto);
		return ResponseEntity.status(HttpStatus.OK).body(funcionario);
		
	}

}
