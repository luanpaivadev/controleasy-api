package br.com.luanpaiva.controleasy.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.luanpaiva.controleasy.api.v1.model.dto.EmpresaDTO;
import br.com.luanpaiva.controleasy.api.v1.model.input.EmpresaInput;
import br.com.luanpaiva.controleasy.domain.model.Empresa;

@Component
public class EmpresaDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Empresa toDomainObject(EmpresaDTO empresaDTO) {
		return modelMapper.map(empresaDTO, Empresa.class);
	}
	
	public Empresa toDomainObject(EmpresaInput empresaInput) {
		return modelMapper.map(empresaInput, Empresa.class);
	}

	public void copyToDomainObject(EmpresaDTO empresaDTO, Empresa empresa) {
		modelMapper.map(empresaDTO, empresa);
	}
	
	public void copyToDomainObject(EmpresaInput empresaInput, Empresa empresa) {
		modelMapper.map(empresaInput, empresa);
	}

}
