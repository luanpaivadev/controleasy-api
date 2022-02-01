package br.com.luanpaiva.controleasy.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.luanpaiva.controleasy.api.v1.model.dto.PontoDTO;
import br.com.luanpaiva.controleasy.domain.model.Ponto;

@Component
public class PontoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PontoDTO toModel(Ponto ponto) {
		return modelMapper.map(ponto, PontoDTO.class);
	}

	public List<PontoDTO> toCollectionModel(List<Ponto> pontos) {
		return pontos.stream()
				.map(ponto -> toModel(ponto))
				.collect(Collectors.toList());
	}
}
