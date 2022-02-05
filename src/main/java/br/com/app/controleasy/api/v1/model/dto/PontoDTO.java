package br.com.app.controleasy.api.v1.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PontoDTO {

	private Long id;
	private LocalDate data;
	private LocalTime entrada;
	private LocalTime inicioIntervalo;
	private LocalTime fimIntervalo;
	private LocalTime saida;
	private LocalTime horasTrabalhadas;
	private LocalTime horasExtras;
	private Boolean   feriado;
	private Boolean   folga;
	private FuncionarioDTO funcionario;
}
