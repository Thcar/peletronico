package br.com.projeto.peletronico.service;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.projeto.peletronico.modelo.Cargo;
import br.com.projeto.peletronico.modelo.Funcionario;
import br.com.projeto.peletronico.repository.CargoRepository;
import br.com.projeto.peletronico.repository.FuncionarioRepository;

@Service
public class EditarCadastro {
	// Injetei
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private Funcionario funcionario;
	private boolean system = true;

	public EditarCadastro(CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.funcionario = new Funcionario();
	}

	public void editar() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite CPF do Funcionario: ");
		String cpf = scanner.next();
		
		this.funcionario = this.funcionarioRepository.procurarPorCpf(cpf);

		while (this.system) {
			
			System.out.println("1 - Editar Nome");
			System.out.println("2 - Editar Data Nascimento");
			System.out.println("3 - Editar Cargo");
			System.out.println("4 - Salvar");
			System.out.println("5 - Sair");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				System.out.println("Digite Nome: ");
				String nome = scanner.next();
				this.funcionario.setNome(nome);
				System.out.println("Editado. Agora Salve");
				break;

			case 2:
				System.out.println("Digite Data Nascimento(yyyy-MM-dd): ");
				String dataNascimento = scanner.next();
				LocalDate data = LocalDate.parse(dataNascimento);
				this.funcionario.setDataNascimento(data);
				System.out.println("Editado. Agora Salve");
				break;

			case 3:
				System.out.println("Digite Cargo:");
				String nomeCargo = scanner.next();
				Cargo cargo = this.cargoRepository.procurarPorCpfDoFuncionario(cpf);
				cargo.setNome(nomeCargo);
				this.cargoRepository.save(cargo);
				System.out.println("Editado. Agora Salve");
				break;

			case 4:
				this.funcionarioRepository.save(funcionario);
				System.out.println("Salvo");
				break;

			case 5:
				this.system = false;
				break;

			default:
				System.out.println("Opcao Invalida");
				this.system = true;
				break;
			}

		}

	}

}
