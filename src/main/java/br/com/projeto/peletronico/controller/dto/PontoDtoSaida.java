package br.com.projeto.peletronico.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.projeto.peletronico.domain.Ponto;

public class PontoDtoSaida {

	private LocalDate data;
	private LocalTime horaSaida;
	private LocalTime horaEntrada;
	private String nomeFuncionario;

	public PontoDtoSaida(Ponto ponto) {
		this.data = ponto.getData();
		this.horaEntrada = ponto.getHoraEntrada();
		this.horaSaida = ponto.getHoraSaida();
		this.nomeFuncionario = ponto.getFuncionario().getNome();
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHoraSaida() {
		return horaSaida;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

}
