package br.com.luanpaiva.controleasy.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luanpaiva.controleasy.domain.model.Empresa;
import br.com.luanpaiva.controleasy.domain.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Transactional
	public Empresa save(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	@Transactional
	public void delete(Empresa empresa) {
		empresaRepository.delete(empresa);
	}

}
