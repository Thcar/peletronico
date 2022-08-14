package br.com.projeto.peletronico.controller.dto;

import java.time.LocalTime;

public class InserirPontoSaidaForm {

	private LocalTime horaSaida = LocalTime.now();

	public LocalTime getHoraSaida() {
		return horaSaida;
	}

}
