package br.com.luanpaiva.controleasy.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.luanpaiva.controleasy.domain.exception.EntidadeNaoEncontradaException;
import br.com.luanpaiva.controleasy.domain.model.Funcionario;
import br.com.luanpaiva.controleasy.domain.repository.EmpresaRepository;
import br.com.luanpaiva.controleasy.domain.repository.FuncionarioRepository;

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
		if (!empresa.isPresent()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de empresa com código %d ", empresaId));
		}
		funcionario.setSenha(encoder.encode(funcionario.getSenha()));
		funcionario.setEmpresa(empresa.get());
		return funcionarioRepository.save(funcionario);
	}

	@Transactional
	public void delete(Funcionario funcionario) {
		funcionarioRepository.delete(funcionario);
	}
}
