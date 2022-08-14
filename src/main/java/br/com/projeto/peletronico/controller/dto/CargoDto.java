package br.com.projeto.peletronico.controller.dto;

import br.com.projeto.peletronico.domain.Cargo;

public class CargoDto {

	private String nome;
	
	public CargoDto(Cargo cargo) {
		this.nome = cargo.getNome();
	}
	
	public String getNome() {
		return nome;
	}
	

}
