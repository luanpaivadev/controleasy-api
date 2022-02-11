package br.com.app.controleasy.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.controleasy.domain.exception.EntidadeEmUsoException;
import br.com.app.controleasy.domain.model.Empresa;
import br.com.app.controleasy.domain.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Transactional
	public Empresa save(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	@Transactional
	public void delete(Long empresaId) throws EntidadeEmUsoException {
		try {
			empresaRepository.deleteById(empresaId);
			empresaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeEmUsoException("Não existe um registro de empresa com id " + empresaId);
		} catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException("Não é possível deletar a empresa com id " + empresaId);
		}
	}

}
