package br.com.app.controleasy.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.controleasy.domain.model.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

	public Boolean existsByDataAndFuncionarioId(LocalDate localDate, Long funcionarioId);

	Optional<Ponto> findByDataAndFuncionarioId(LocalDate localDate, Long funcionarioId);

	List<Ponto> findByDataBetweenAndFuncionarioIdOrderByData(LocalDate dataInicio, LocalDate dataFim, Long funcionarioId);
}
