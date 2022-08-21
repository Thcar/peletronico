package br.com.projeto.peletronico.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.peletronico.domain.Funcionario;
import br.com.projeto.peletronico.domain.Ponto;
import br.com.projeto.peletronico.exception.NotFoundException;
import br.com.projeto.peletronico.repository.FuncionarioRepository;
import br.com.projeto.peletronico.repository.PontoRepository;

@Service
public class PontoService {

	@Autowired
	private PontoRepository pontoRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	private LocalDate data = LocalDate.now();
	private LocalTime time = null;

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

	public Ponto baterPonto(Long idFuncionario) {
		Optional<Funcionario> optionalFuncionario = this.funcionarioRepository.findById(idFuncionario);
		Funcionario funcionario = optionalFuncionario.get();
		
		if (optionalFuncionario.isPresent()) {

			Optional<Ponto> existeDataDeEntrada = this.pontoRepository.existePontoDeEntradaHoje(idFuncionario, this.data);
			Optional<Ponto> existePontoDeSaida = this.pontoRepository.existePontoDeSaidaHoje(idFuncionario, this.time);

			// Salvar
			if (!existeDataDeEntrada.isPresent()) {
				Ponto ponto = new Ponto();
				ponto.setFuncionario(funcionario);
				ponto.setData(this.data);
				ponto.setHoraEntrada(LocalTime.now());
				this.pontoRepository.save(ponto);
				return ponto;
			}
			// Atualização
			if (existeDataDeEntrada.isPresent() && !existePontoDeSaida.isPresent()) {
				Ponto ponto = this.pontoRepository.localizarPonto(idFuncionario,
						existeDataDeEntrada.get().getHoraEntrada());
				ponto.setHoraSaida(LocalTime.now());
				this.pontoRepository.save(ponto);
				return ponto;
			}
			if (existeDataDeEntrada.isPresent() && existePontoDeSaida.isPresent()) {
				Ponto ponto = new Ponto();
				ponto.setFuncionario(funcionario);
				ponto.setData(this.data);
				ponto.setHoraEntrada(LocalTime.now());
				this.pontoRepository.save(ponto);
				return ponto;

			}

		}
			throw new NotFoundException("Funcionario não existe");
		// Validar se usuario existe
		// 1 Caso não haja ponto hoje para o funcionario, criar um ponto com a data de
		// entrada atual(AGORA)
		// 2 Caso haja um ponto hoje sem data de saida , alterar a data de saida com a
		// hora atual(AGORA)
		// 3 Caso haja um ponto pra hoje e pra esse funcionario, e esse ponto possui
		// data de entrada e saida preenchida, cria um ponto novo

	}

}
