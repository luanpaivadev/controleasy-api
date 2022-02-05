package br.com.app.controleasy.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.controleasy.api.v1.model.dto.FuncionarioDTO;
import br.com.app.controleasy.api.v1.model.input.FuncionarioInput;
import br.com.app.controleasy.domain.model.Funcionario;

@Component
public class FuncionarioDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Funcionario toDomainObject(FuncionarioDTO funcionarioDTO) {
		return modelMapper.map(funcionarioDTO, Funcionario.class);
	}
	
	public Funcionario toDomainObject(FuncionarioInput funcionarioInput) {
		return modelMapper.map(funcionarioInput, Funcionario.class);
	}

	public void copyToDomainObject(FuncionarioDTO funcionarioDTO, Funcionario funcionario) {
		modelMapper.map(funcionarioDTO, funcionario);
	}
	
	public void copyToDomainObject(FuncionarioInput funcionarioInput, Funcionario funcionario) {
		modelMapper.map(funcionarioInput, funcionario);
	}

}
