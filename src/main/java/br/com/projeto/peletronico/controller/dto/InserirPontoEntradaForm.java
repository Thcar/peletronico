package br.com.projeto.peletronico.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import br.com.projeto.peletronico.domain.Funcionario;
import br.com.projeto.peletronico.domain.Ponto;
import br.com.projeto.peletronico.service.FuncionarioService;

public class InserirPontoEntradaForm {

	private Integer ano;
	private Month mes;
	private LocalDate data = LocalDate.now();
	private LocalTime horaEntrada = LocalTime.now();
	private Long funcionario_id;

	public InserirPontoEntradaForm() {
		this.ano = this.data.getYear();
		this.mes = this.data.getMonth();
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Month getMes() {
		return mes;
	}

	public void setMes(Month mes) {
		this.mes = mes;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Long getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Long funcionario_id) {
		this.funcionario_id = funcionario_id;
	}

	public Ponto toPonto(FuncionarioService funcionarioService) {
		Ponto ponto = new Ponto();
		Optional<Funcionario> optionalFuncionario = funcionarioService.localizarFuncionarioById(this.funcionario_id);
		ponto.setFuncionario(optionalFuncionario.get());
		ponto.setData(this.data);
		ponto.setHoraEntrada(this.horaEntrada);
		ponto.setAno(this.ano);
		ponto.setMes(this.mes);
		
		return ponto;
	}
}
