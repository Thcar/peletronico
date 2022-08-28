package br.com.projeto.peletronico.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class Ponto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private LocalTime horaEntrada_1;
	private LocalTime horaSaida_1;
	private LocalTime horaEntrada_2;
	private LocalTime horaSaida_2;
	@ManyToOne
	private Funcionario funcionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHoraEntrada_1() {
		return horaEntrada_1;
	}

	public void setHoraEntrada_1(LocalTime horaEntrada_1) {
		this.horaEntrada_1 = horaEntrada_1;
	}

	public LocalTime getHoraSaida_1() {
		return horaSaida_1;
	}

	public void setHoraSaida_1(LocalTime horaSaida_1) {
		this.horaSaida_1 = horaSaida_1;
	}

	public LocalTime getHoraEntrada_2() {
		return horaEntrada_2;
	}

	public void setHoraEntrada_2(LocalTime horaEntrada_2) {
		this.horaEntrada_2 = horaEntrada_2;
	}

	public LocalTime getHoraSaida_2() {
		return horaSaida_2;
	}

	public void setHoraSaida_2(LocalTime horaSaida_2) {
		this.horaSaida_2 = horaSaida_2;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
