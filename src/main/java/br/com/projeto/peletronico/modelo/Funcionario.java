package br.com.projeto.peletronico.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class Funcionario {

	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private String cpf;
	@ManyToOne
	private Cargo cargo;
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private List<Ponto> pontos;

	public void cadastrar(Ponto ponto) {
		ponto.setFuncionario(this);
		this.pontos.add(ponto);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}
