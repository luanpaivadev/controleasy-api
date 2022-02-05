package br.com.app.controleasy.api.v1.model.input;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PontoInput {

	@NotNull
	private LocalDate data;
	private LocalTime entrada;
	private LocalTime inicioIntervalo;
	private LocalTime fimIntervalo;
	private LocalTime saida;
	private LocalTime horasTrabalhadas;
	private LocalTime horasExtras;
	private Boolean   feriado;
	private Boolean   folga;
	@NotNull
	private FuncionarioIdInput funcionario;
}
