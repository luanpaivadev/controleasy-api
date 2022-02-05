package br.com.app.controleasy.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaInput {

	@NotBlank
	private String razaoSocial;
	@NotBlank
	private String cnpj;
	@NotBlank
	private String logradouro;
	private String complemento;
	@NotBlank
	private String numero;
	@NotBlank
	private String bairro;
	@NotBlank
	private String localidade;
	@NotBlank
	private String uf;
	@NotBlank
	private String cep;

}
