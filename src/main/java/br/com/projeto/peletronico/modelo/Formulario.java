package br.com.projeto.peletronico.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Formulario {

	private String nome;
	private String cpf;
	private LocalDate data;
	private LocalTime horaEntrada_1;
	private LocalTime horaSaida_1;
	private LocalTime horaEntrada_2;
	private LocalTime horaSaida_2;
	private String nomeCargo;

	public Formulario(String nome, String cpf, LocalDate data, LocalTime horaEntrada_1, LocalTime horaSaida_1,
			LocalTime horaEntrada_2, LocalTime horaSaida_2, String nomeCargo) {
		this.data = data;
		this.horaEntrada_1 = horaEntrada_1;
		this.horaSaida_1 = horaSaida_1;
		this.horaEntrada_2 = horaEntrada_2;
		this.horaSaida_2 = horaSaida_2;
		this.nome = nome;
		this.cpf = cpf;
		this.nomeCargo = nomeCargo;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHoraEntrada_1() {
		return horaEntrada_1;
	}

	public LocalTime getHoraSaida_1() {
		return horaSaida_1;
	}

	public LocalTime getHoraEntrada_2() {
		return horaEntrada_2;
	}

	public LocalTime getHoraSaida_2() {
		return horaSaida_2;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	@Override
	public String toString() {
		return "Formulario [nome = " + nome + ", cpf = " + cpf + ", data = " + data + ", horaEntrada_1 = " + horaEntrada_1
				+ ", horaSaida_1 = " + horaSaida_1 + ", horaEntrada_2 = " + horaEntrada_2 + ", horaSaida_2 = " + horaSaida_2
				+ ", nomeCargo = " + nomeCargo + "]";
	}

}
