package br.com.luanpaiva.controleasy.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

	@ManyToMany
	@JoinTable(name = "funcionario_grupo", joinColumns = @JoinColumn(name = "funcionario_id"), 
	inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();

	@ManyToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;

}
