package br.com.projeto.peletronico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.peletronico.domain.Ponto;
import br.com.projeto.peletronico.repository.PontoRepository;

@Service
public class PontoService {

	@Autowired
	private PontoRepository pontoRepository;

	public void assinarPontoDeEntrada(Ponto ponto) {
		this.pontoRepository.save(ponto);
	}

	public Optional<Ponto> localizarPonto(Long id) {
		Optional<Ponto> optionalPonto = this.pontoRepository.findById(id);
		return optionalPonto;
	}

	public List<Ponto> localizarListaDePontos(Long funcionario_id) {
		return this.pontoRepository.localizarListaDePontos(funcionario_id);
	}


}
