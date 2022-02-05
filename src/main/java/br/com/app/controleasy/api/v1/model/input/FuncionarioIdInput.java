package br.com.app.controleasy.api.v1.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioIdInput {

	@NotNull
	private Long id;
	
}
