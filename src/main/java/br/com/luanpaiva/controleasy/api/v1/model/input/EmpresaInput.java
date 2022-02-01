package br.com.luanpaiva.controleasy.api.v1.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaInput {

	@NotNull
	private String razaoSocial;
	@NotNull
	private String cnpj;
	@NotNull
	private String logradouro;
	
	private String complemento;
	@NotNull
	private String numero;
	@NotNull
	private String bairro;
	@NotNull
	private String localidade;
	@NotNull
	private String uf;
	@NotNull
	private String cep;

}
