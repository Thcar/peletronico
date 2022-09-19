package br.com.projeto.peletronico.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.projeto.peletronico.domain.Cargo;

public class CargoForm {

	@NotNull
	@NotBlank
	private String nome;
	private Cargo cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cargo criarCargo() {
		this.cargo = new Cargo();
		cargo.setNome(nome);
		return cargo;
	}

	public Cargo getCargo() {
		return cargo;
	}

}
