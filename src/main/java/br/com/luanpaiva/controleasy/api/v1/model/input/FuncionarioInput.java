package br.com.luanpaiva.controleasy.api.v1.model.input;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioInput {

	@NotNull
	private String nome;
	@NotNull
	private String cpf;
	@NotNull
	private String pis;
	@NotNull
	private String ctps;
	@NotNull
	private String funcao;
	@NotNull
	private String usuario;
	@NotNull
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
