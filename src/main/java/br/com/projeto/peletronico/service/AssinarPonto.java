package br.com.projeto.peletronico.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.projeto.peletronico.modelo.Funcionario;
import br.com.projeto.peletronico.modelo.Ponto;
import br.com.projeto.peletronico.repository.FuncionarioRepository;
import br.com.projeto.peletronico.repository.PontoRepository;

@Service
public class AssinarPonto {
	// Injetei dependência;
	private PontoRepository pontoRepository;
	private FuncionarioRepository funcionarioRepository;
	private boolean system = true;
	private Ponto ponto;

	public AssinarPonto(PontoRepository pontoRepository, FuncionarioRepository funcionarioRepository) {
		this.pontoRepository = pontoRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.ponto = new Ponto();
	}


	public void inserirInformacoes() {
		Scanner scn = new Scanner(System.in);

		while (this.system) {
			
			System.out.println("1 - Marcar Hora de Entrada(Turno Matutino)");
			System.out.println("2 - Marcar Hora de Saida(Turno Matutino)");
			System.out.println("3 - Marcar Hora de Entrada(Turno Verpertino)");
			System.out.println("4 - Marcar Hora de Saida(Turno Verpertino)");
			System.out.println("0 - Sair");
			System.out.println();
			System.out.println("5 - Registrar no Sistema");
			

			int action = scn.nextInt();

			switch (action) {
			case 1:
				this.ponto.setData(LocalDate.now());
				this.ponto.setHoraEntrada_1(LocalTime.now());
				System.out.println("Inserido! Agora aperte 5 para Registrar no Sistema.");
				break;

			case 2:
				this.ponto.setHoraSaida_1(LocalTime.now().plusHours(3L));
				System.out.println("Inserido! Agora aperte 5 para Registrar no Sistema.");
				break;

			case 3:
				this.ponto.setHoraEntrada_2(LocalTime.now().plusHours(4L));
				System.out.println("Inserido! Agora aperte 5 para Registrar no Sistema.");
				break;

			case 4:
				this.ponto.setHoraSaida_2(LocalTime.now().plusHours(8L));
				System.out.println("Inserido! Agora aperte 5 para Registrar no Sistema.");
				break;

			case 5:
				/*System.out.println("Digite Primeiro Nome: ");
				String nomeFuncionario = scn.next();
				Funcionario funcionario = this.funcionarioRepository.procurarPorNome(nomeFuncionario);
				this.ponto.setFuncionario(funcionario);
				this.pontoRepository.save(this.ponto);
				System.out.println("Registrado.");*/
				break;

			default:
				this.system = false;
				break;
			}

		}

	}

}
