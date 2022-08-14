package br.com.projeto.peletronico.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.projeto.peletronico.controller.dto.InserirFuncionarioForm;
import br.com.projeto.peletronico.domain.Cargo;
import br.com.projeto.peletronico.domain.Funcionario;
import br.com.projeto.peletronico.exception.CadastrarException;
import br.com.projeto.peletronico.repository.CargoRepository;
import br.com.projeto.peletronico.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	// Injetei dependência;
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
//	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public FuncionarioService(CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
	}
	@Transactional
	public Funcionario cadastrar(InserirFuncionarioForm funcionarioDto) {
		Funcionario funcionario = new Funcionario();
		Optional<Cargo> optionalCargo = this.cargoRepository.findById(funcionarioDto.getIdCargo());
		funcionario.setCargo(optionalCargo.get());
		
		boolean existsByCpf = this.funcionarioRepository.existsByCpf(funcionarioDto.getCpf());
		if(existsByCpf) {
			throw new CadastrarException("Cpf já foi cadastrado");
			
		}
		//funcionario.setDataNascimento(funcionarioDto.getDataNascimento());
		BeanUtils.copyProperties(funcionarioDto, funcionario);
		return this.funcionarioRepository.save(funcionario);
		
	}

	public List<Funcionario> visualizarFuncionarios() {
		List<Funcionario> funcionarios = this.funcionarioRepository.findAll();
		return funcionarios;
	}
	@Transactional
	public void deletarById(Long id) {
		this.funcionarioRepository.deleteById(id);
		
	}
	
	public Funcionario atualizar(Long id, InserirFuncionarioForm inserirFuncionarioDto) {
		Optional<Funcionario> optionalFuncionario = this.funcionarioRepository.findById(id);
		Optional<Cargo> optionalCargo = this.cargoRepository.findById(inserirFuncionarioDto.getIdCargo());
		
		Funcionario funcionario = optionalFuncionario.get();
		
		funcionario.setNome(inserirFuncionarioDto.getNome());
		funcionario.setDataNascimento(inserirFuncionarioDto.getDataNascimento());
		funcionario.setCpf(inserirFuncionarioDto.getCpf());
		funcionario.setCargo(optionalCargo.get());
		
		return this.funcionarioRepository.save(funcionario);
		
	}
	public Optional<Funcionario> localizarFuncionarioById(Long id) {
		Optional<Funcionario> optionalFuncionario = this.funcionarioRepository.findById(id);
		return optionalFuncionario;
		
	}
	
}
