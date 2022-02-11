package br.com.app.controleasy.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.controleasy.domain.exception.EntidadeEmUsoException;
import br.com.app.controleasy.domain.exception.EntidadeNaoEncontradaException;
import br.com.app.controleasy.domain.model.Funcionario;
import br.com.app.controleasy.domain.repository.EmpresaRepository;
import br.com.app.controleasy.domain.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Transactional
	public Funcionario save(Funcionario funcionario) {
		var empresaId = funcionario.getEmpresa().getId();
		var empresa = empresaRepository.findById(empresaId);
		if (empresa.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de empresa com id %d ", empresaId));
		}
		funcionario.setSenha(encoder.encode(funcionario.getSenha()));
		funcionario.setEmpresa(empresa.get());
		return funcionarioRepository.save(funcionario);
	}

	@Transactional
	public void delete(Long funcionarioId) {
		try {
			funcionarioRepository.deleteById(funcionarioId);
			funcionarioRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um registro de funcionário com %d ", funcionarioId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Não é possível deletar o funcionário com %d ", funcionarioId));
		}
	}
}
