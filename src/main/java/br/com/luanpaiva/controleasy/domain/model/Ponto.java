package br.com.luanpaiva.controleasy.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ponto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private LocalTime entrada;
	private LocalTime inicioIntervalo;
	private LocalTime fimIntervalo;
	private LocalTime saida;
	private LocalTime horasTrabalhadas;
	private LocalTime horasExtras;
	private Boolean feriado = false;
	private Boolean folga = false;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

}
