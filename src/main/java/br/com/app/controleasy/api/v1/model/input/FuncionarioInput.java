package br.com.app.controleasy.api.v1.model.input;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioInput {

	@NotBlank
	private String nome;
	@NotBlank
	private String cpf;
	@NotBlank
	private String pis;
	@NotBlank
	private String ctps;
	@NotBlank
	private String funcao;
	@NotBlank
	private String usuario;
	@NotBlank
	private String senha;
	@NotNull
	private LocalDate admissao;
	@NotNull
	private LocalTime entrada;
	@NotNull
	private LocalTime inicioIntervalo;
	@NotNull
	private LocalTime fimIntervalo;
	@NotNull
	private LocalTime saida;
	@NotNull
	private LocalTime cargaHoraria;
	@NotNull
	private EmpresaIdInput empresa;
}
