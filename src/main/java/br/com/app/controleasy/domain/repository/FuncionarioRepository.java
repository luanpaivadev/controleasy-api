package br.com.app.controleasy.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.controleasy.domain.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	public Boolean existsByCpf(String cpf);
	public Boolean existsByUsuario(String usuario);
	public Optional<Funcionario> findByCpf(String cpf);
	public Optional<Funcionario> findByUsuario(String usuario);

}
