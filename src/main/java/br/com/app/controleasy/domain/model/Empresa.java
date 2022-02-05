package br.com.app.controleasy.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
