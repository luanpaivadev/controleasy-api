package br.com.app.controleasy.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.controleasy.api.v1.model.dto.FuncionarioDTO;
import br.com.app.controleasy.domain.model.Funcionario;

@Component
public class FuncionarioDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public FuncionarioDTO toModel(Funcionario funcionario) {
		return modelMapper.map(funcionario, FuncionarioDTO.class);
	}

	public List<FuncionarioDTO> toCollectionModel(List<Funcionario> funcionarios) {
		return funcionarios.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
}
