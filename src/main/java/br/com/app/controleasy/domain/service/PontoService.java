package br.com.app.controleasy.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.controleasy.domain.exception.EntidadeNaoEncontradaException;
import br.com.app.controleasy.domain.model.Ponto;
import br.com.app.controleasy.domain.repository.FuncionarioRepository;
import br.com.app.controleasy.domain.repository.PontoRepository;

@Service
public class PontoService {

	@Autowired
	private PontoRepository pontoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Transactional
	public Ponto save(Ponto ponto) {
		var funcionarioId = ponto.getFuncionario().getId();
		var funcionario = funcionarioRepository.findById(funcionarioId);
		if (!funcionario.isPresent()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de funcionario com código %d ", funcionarioId));
		}
		ponto.setFuncionario(funcionario.get());
		return pontoRepository.save(ponto);
	}

	@Transactional
	public void delete(Long pontoId) {
		try {
			pontoRepository.deleteById(pontoId);
			pontoRepository.flush();
		} catch (EmptyResultDataAccessException  e) {
			throw new EntidadeNaoEncontradaException("Não existe um registro de ponto com id " + pontoId);
		}
	}

}
