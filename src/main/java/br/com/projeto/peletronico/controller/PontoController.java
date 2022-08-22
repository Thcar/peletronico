package br.com.projeto.peletronico.controller;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.peletronico.controller.dto.PontoDtoSaida;
import br.com.projeto.peletronico.controller.dto.PontoRelatorioDto;
import br.com.projeto.peletronico.domain.Ponto;
import br.com.projeto.peletronico.service.PontoService;

@RestController//==@ResponseBody
@RequestMapping("/ponto")
public class PontoController {
	
	private PontoService pontoService;

	public PontoController(PontoService pontoService) {
		this.pontoService = pontoService;
	}
	
	@PostMapping("/funcionario/{idFuncionario}")
	public ResponseEntity<Object>baterPonto(@PathVariable(value = "idFuncionario")Long idFuncionario){
		Ponto ponto = this.pontoService.baterPonto(idFuncionario);
		PontoDtoSaida pontoDto = new PontoDtoSaida(ponto);
		return ResponseEntity.status(HttpStatus.CREATED).body(pontoDto);
		
	}
	
	@GetMapping("/funcionario/{idFuncionario}")
	public List<PontoRelatorioDto> RelatorioDeHorasPorFuncionario(@PathVariable(value = "idFuncionario") Long funcionario_id){
		List<Ponto> pontos = this.pontoService.localizarListaDePontos(funcionario_id);
		List<PontoRelatorioDto> relatorioPontos = pontos.stream().map(PontoRelatorioDto::new).collect(Collectors.toList());
		return relatorioPontos;
	
	}
	
	@GetMapping("/funcionario/{idFuncionario}/{mes}")
	public List<PontoRelatorioDto> RelatorioDeHorasPorFuncionarioNoMesEspecifico(@PathVariable("idFuncionario") Long idFuncionario,@PathVariable("mes") int mes){
		List<Ponto> pontos = this.pontoService.localizarListaDePontosPorMes(idFuncionario, Month.of(mes));
		List<PontoRelatorioDto> relatorioPontos = pontos.stream().map(PontoRelatorioDto::new).collect(Collectors.toList());
		return relatorioPontos;
		
	}
}
