package br.com.app.controleasy.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDTO {

	private Long   id;
	private String razaoSocial;
	private String cnpj;
	private String logradouro;
	private String complemento;
	private String numero;
	private String bairro;
	private String localidade;
	private String uf;
	private String cep;

}
