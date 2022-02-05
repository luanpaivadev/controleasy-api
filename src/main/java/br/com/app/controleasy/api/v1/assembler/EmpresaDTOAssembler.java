package br.com.app.controleasy.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.controleasy.api.v1.model.dto.EmpresaDTO;
import br.com.app.controleasy.domain.model.Empresa;

@Component
public class EmpresaDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EmpresaDTO toModel(Empresa empresa) {
		return modelMapper.map(empresa, EmpresaDTO.class);
	}

	public List<EmpresaDTO> toCollectionModel(List<Empresa> empresas) {
		return empresas.stream()
				.map(empresa -> toModel(empresa))
				.collect(Collectors.toList());
	}
}
