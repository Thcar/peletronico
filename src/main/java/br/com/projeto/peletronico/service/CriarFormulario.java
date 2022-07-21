package br.com.projeto.peletronico.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.projeto.peletronico.modelo.Formulario;
import br.com.projeto.peletronico.repository.PontoRepository;

@Service
public class CriarFormulario {

	private final PontoRepository pontoRepository;

	public CriarFormulario(PontoRepository pontoRepository) {
		this.pontoRepository = pontoRepository;
	}

	public void criar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("CPF:");
		String cpf = scanner.next();

		List<Formulario> formularios = this.pontoRepository.criarFormulario(cpf);
		for (Formulario formulario : formularios) {
			System.out.println(formulario);

		}

	}

}
