package br.com.projeto.peletronico.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.peletronico.controller.dto.InserirPontoEntradaForm;
import br.com.projeto.peletronico.controller.dto.InserirPontoSaidaForm;
import br.com.projeto.peletronico.controller.dto.PontoDtoEntrada;
import br.com.projeto.peletronico.controller.dto.PontoDtoSaida;
import br.com.projeto.peletronico.controller.dto.PontoRelatorioDto;
import br.com.projeto.peletronico.domain.Ponto;
import br.com.projeto.peletronico.service.FuncionarioService;
import br.com.projeto.peletronico.service.PontoService;

@RestController//==@ResponseBody
@RequestMapping("/ponto")
public class PontoController {
	
	private PontoService pontoService;
	private FuncionarioService funcionarioService;

	public PontoController(PontoService pontoService, FuncionarioService funcionarioService) {
		this.pontoService = pontoService;
		this.funcionarioService = funcionarioService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Object> assinarPontoEntrada(@RequestBody InserirPontoEntradaForm inserirPontoEntradaForm, UriComponentsBuilder uriBuilder) {
		Ponto ponto = inserirPontoEntradaForm.toPonto(this.funcionarioService);
		URI uri = uriBuilder.path("/ponto/{id}").buildAndExpand(ponto.getId()).toUri();
		this.pontoService.assinarPontoDeEntrada(ponto);
		return ResponseEntity.created(uri).body(new PontoDtoEntrada(ponto));
		
	}
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> assinarPontoSaida(@PathVariable(value = "id") Long id){
		Optional<Ponto> optionalPonto = this.pontoService.localizarPonto(id);
		Ponto ponto = optionalPonto.get();
		InserirPontoSaidaForm inserirPontoSaidaForm = new InserirPontoSaidaForm();
		ponto.setHoraSaida(inserirPontoSaidaForm.getHoraSaida());
		return ResponseEntity.status(HttpStatus.OK).body(new PontoDtoSaida(ponto));
		
	}
	@GetMapping("/{id}")
	public List<PontoRelatorioDto> RelatorioDeHorasPorFuncionario(@PathVariable(value = "id") Long funcionario_id){
		List<Ponto> pontos = this.pontoService.localizarListaDePontos(funcionario_id);
		List<PontoRelatorioDto> relatorioPontos = pontos.stream().map(PontoRelatorioDto::new).collect(Collectors.toList());
		return relatorioPontos;
	
	}
}
