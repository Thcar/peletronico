package br.com.projeto.peletronico.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.projeto.peletronico.domain.Ponto;

public class PontoDtoEntrada {

	private LocalDate data;
	private LocalTime horaEntrada;
	private String nomeFuncionario;

	public PontoDtoEntrada(Ponto ponto) {
		this.data = ponto.getData();
		this.horaEntrada = ponto.getHoraEntrada();
		this.nomeFuncionario = ponto.getFuncionario().getNome();
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

}
