package br.com.projeto.peletronico.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.projeto.peletronico.controller.dto.CargoForm;
import br.com.projeto.peletronico.domain.Cargo;
import br.com.projeto.peletronico.exception.CadastrarException;
import br.com.projeto.peletronico.exception.NotFoundException;
import br.com.projeto.peletronico.repository.CargoRepository;

@Service
public class CargoService {
	
	private CargoRepository cargoRepository;

	public CargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	@Transactional
	public Cargo cadastrarCargo(CargoForm cargoForm) {
		Cargo cargo  = new Cargo();
		BeanUtils.copyProperties(cargoForm, cargo);
		boolean existsByNome = this.cargoRepository.existsByNome(cargoForm.getNome());
		if(existsByNome) {
			throw new CadastrarException("Cargo já cadastrado");
		}
		Cargo cargoSalvo = this.cargoRepository.save(cargo);
		return cargoSalvo;
		
	}

	@Transactional
	public void deletarById(Long id) {
		boolean existsById = this.cargoRepository.existsById(id);
		if(!existsById) {
			throw new NotFoundException("Cargo não existe");
		}
		this.cargoRepository.deleteById(id);
		
	}

	public List<Cargo> visualizar() {
		List<Cargo> cargos = this.cargoRepository.findAll();
		return cargos;
	}
}
