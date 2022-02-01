package br.com.luanpaiva.controleasy.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luanpaiva.controleasy.domain.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	public Boolean existsByCnpj(String cnpj);
}
