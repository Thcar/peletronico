package br.com.projeto.peletronico.service;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.projeto.peletronico.modelo.Cargo;
import br.com.projeto.peletronico.modelo.Funcionario;
import br.com.projeto.peletronico.repository.CargoRepository;
import br.com.projeto.peletronico.repository.FuncionarioRepository;

@Service
public class CadastrarFuncionario {
	// Injetei dependência;
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private boolean system = true;

	public CadastrarFuncionario(CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
	}

	public void cadastrar() {
		Scanner scn = new Scanner(System.in);
		
		while(this.system) {
			
			System.out.println("Digite o nome: ");
			String nome = scn.next();

			System.out.println("Digite o CPF: ");
			String cpf = scn.next();

			System.out.println("Digite o Data Nascimento (yyyy-MM-dd): ");
			String dataNascimento = scn.next();

			System.out.println("Digite o Cargo: ");
			String cargo = scn.next();

			Cargo funcao = new Cargo();
			funcao.setNome(cargo.toString());

			Funcionario funcionario = new Funcionario();
			funcionario.setCargo(funcao);
			funcionario.setNome(nome.toString());
			funcionario.setCpf(cpf.toString());
			String dataString = dataNascimento.toString();
			LocalDate data = LocalDate.parse(dataString);
			funcionario.setDataNascimento(data);

			/*this.cargoRepository.save(funcao);
			this.funcionarioRepository.save(funcionario);*/
			
			System.out.println("1 - Continuar");
			System.out.println("0 - Sair");
			int action = scn.nextInt();
			
			if(action != 1) {
				this.system = false;
				
			}
		}
	}
}
