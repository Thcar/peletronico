package br.com.projeto.peletronico.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	

	public Optional<Ponto> localizarPonto(Long id) {
		Optional<Ponto> optionalPonto = this.pontoRepository.findById(id);
		return optionalPonto;
	}

	public List<Ponto> localizarListaDePontos(Long funcionario_id) {
		return this.pontoRepository.localizarListaDePontos(funcionario_id);
	}

	@Transactional
	public Ponto baterPonto(Long idFuncionario) {
		LocalDate data = LocalDate.now();
		Optional<Funcionario> optionalFuncionario = this.funcionarioRepository.findById(idFuncionario);
		Funcionario funcionario = optionalFuncionario.get();
		
		if (optionalFuncionario.isPresent()) {
			List<Ponto> pontosDeHoje = this.pontoRepository.existePontoDeEntradaHoje(idFuncionario, data);

			// Salvar
			if (pontosDeHoje.isEmpty()) {
				Ponto ponto = new Ponto();
				ponto.setFuncionario(funcionario);
				ponto.setData(data);
				ponto.setHoraEntrada(LocalTime.now());
				ponto.setAno(data.getYear());
				ponto.setMes(data.getMonth());
				this.pontoRepository.save(ponto);
				return ponto;
			
			} else {
				Ponto pontoDeEntradaMaisRecente = pontosDeHoje.get(0);
				if (pontoDeEntradaMaisRecente.getHoraSaida() == null) {
					pontoDeEntradaMaisRecente.setHoraSaida(LocalTime.now());
					this.pontoRepository.save(pontoDeEntradaMaisRecente);
					return pontoDeEntradaMaisRecente;
					
				} else {
					Ponto ponto = new Ponto();
					ponto.setFuncionario(funcionario);
					ponto.setData(data);
					ponto.setHoraEntrada(LocalTime.now());
					this.pontoRepository.save(ponto);
					ponto.setAno(data.getYear());
					ponto.setMes(data.getMonth());
					return ponto;
					
				}
			}
		}
			throw new NotFoundException("Funcionario não existe");
//		// Validar se usuario existe
//		// 1 Caso não haja ponto hoje para o funcionario, criar um ponto com a data de
//		// entrada atual(AGORA)
//		// 2 Caso haja um ponto hoje sem data de saida , alterar a data de saida com a
//		// hora atual(AGORA)
//		// 3 Caso haja um ponto pra hoje e pra esse funcionario, e esse ponto possui
//		// data de entrada e saida preenchida, cria um ponto novo
//
	}

	public List<Ponto> localizarListaDePontosPorMes(Long idFuncionario, Month mes) {
		List<Ponto> pontos = this.pontoRepository.localizarListaDePontoNoMesEspecifico(idFuncionario, mes);
		return pontos;
	}

}
