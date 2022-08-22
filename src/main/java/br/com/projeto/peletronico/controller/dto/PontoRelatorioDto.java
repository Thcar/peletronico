package br.com.projeto.peletronico.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import br.com.projeto.peletronico.domain.Ponto;

public class PontoRelatorioDto {

	private String nomeFuncionario;
	private LocalDate data;
	private LocalTime horaEntrada;
	private LocalTime horaSaida;
	private Long horasTrabalhadas;

	public PontoRelatorioDto(Ponto ponto) {
		this.nomeFuncionario = ponto.getFuncionario().getNome();
		this.data = ponto.getData();
		this.horaEntrada = ponto.getHoraEntrada();
		this.horaSaida = ponto.getHoraSaida();
		this.horasTrabalhadas = ChronoUnit.HOURS.between(ponto.getHoraEntrada(), ponto.getHoraSaida());
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public LocalTime getHoraSaida() {
		return horaSaida;
	}

	public Long getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

}
