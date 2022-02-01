package br.com.luanpaiva.controleasy.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.luanpaiva.controleasy.api.v1.model.dto.PontoDTO;
import br.com.luanpaiva.controleasy.api.v1.model.input.PontoInput;
import br.com.luanpaiva.controleasy.domain.model.Ponto;

@Component
public class PontoDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Ponto toDomainObject(PontoDTO pontoDTO) {
		return modelMapper.map(pontoDTO, Ponto.class);
	}

	public Ponto toDomainObject(PontoInput pontoInput) {
		return modelMapper.map(pontoInput, Ponto.class);
	}

	public void copyToDomainObject(PontoDTO pontoDTO, Ponto ponto) {
		modelMapper.map(pontoDTO, ponto);
	}

	public void copyToDomainObject(PontoInput pontoInput, Ponto ponto) {
		modelMapper.map(pontoInput, ponto);
	}
}
