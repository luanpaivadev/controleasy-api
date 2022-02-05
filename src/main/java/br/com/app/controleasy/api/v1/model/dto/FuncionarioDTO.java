package br.com.app.controleasy.api.v1.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO {

	private Long   id;
	private String nome;
	private String cpf;
	private String pis;
	private String ctps;
	private String funcao;
	private String usuario;
	private String senha;
	private LocalDate admissao;
	private LocalTime entrada;
	private LocalTime inicioIntervalo;
	private LocalTime fimIntervalo;
	private LocalTime saida;
	private LocalTime cargaHoraria;
	private EmpresaDTO empresa;
}
