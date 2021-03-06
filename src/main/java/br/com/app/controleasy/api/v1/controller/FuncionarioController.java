package br.com.app.controleasy.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.controleasy.api.v1.assembler.FuncionarioDTOAssembler;
import br.com.app.controleasy.api.v1.assembler.FuncionarioDTODisassembler;
import br.com.app.controleasy.api.v1.model.dto.FuncionarioDTO;
import br.com.app.controleasy.api.v1.model.input.FuncionarioInput;
import br.com.app.controleasy.core.security.CheckSecurity;
import br.com.app.controleasy.domain.exception.EntidadeNaoEncontradaException;
import br.com.app.controleasy.domain.model.Funcionario;
import br.com.app.controleasy.domain.repository.FuncionarioRepository;
import br.com.app.controleasy.domain.service.FuncionarioService;

@RestController
@RequestMapping("/v1/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private FuncionarioDTOAssembler funcionarioDTOAssembler;

	@Autowired
	private FuncionarioDTODisassembler funcionarioDTODisassembler;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping
	@CheckSecurity.Funcionario.PodeConsultar
	public ResponseEntity<Page<FuncionarioDTO>> findAll(Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		if (!funcionarios.isEmpty()) {
			var funcionariosDTO = funcionarioDTOAssembler.toCollectionModel(funcionarios.getContent());
			return ResponseEntity.ok(new PageImpl<>(funcionariosDTO, pageable, funcionarios.getTotalElements()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{funcionarioId}")
	@CheckSecurity.Funcionario.PodeConsultar
	public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long funcionarioId) {
		var result = funcionarioRepository.findById(funcionarioId);
		if (result.isPresent()) {
			var funcionario = result.get();
			var funcionarioDTO = funcionarioDTOAssembler.toModel(funcionario);
			return ResponseEntity.ok(funcionarioDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/login")
	public ResponseEntity<FuncionarioDTO> login(final String cpf, final String senha) {
		var result = funcionarioRepository.findByCpf(cpf);
		if (result.isPresent()) {
			var funcionario = result.get();
			if (encoder.matches(senha, funcionario.getSenha())) {
				var funcionarioDTO = funcionarioDTOAssembler.toModel(funcionario);
				return ResponseEntity.ok(funcionarioDTO);
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@CheckSecurity.Funcionario.PodeCadastrar
	public ResponseEntity<?> save(@RequestBody @Valid FuncionarioInput funcionarioInput) {
		try {
			var funcionario = funcionarioDTODisassembler.toDomainObject(funcionarioInput);
			if (!funcionarioRepository.existsByCpf(funcionario.getCpf())) {
				if (!funcionarioRepository.existsByUsuario(funcionario.getUsuario())) {
					funcionario = funcionarioService.save(funcionario);
					var funcionarioDTO = funcionarioDTOAssembler.toModel(funcionario);
					return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioDTO);
				}
				return ResponseEntity.badRequest().body("Nome de usu??rio j?? cadastrado.");
			}
			return ResponseEntity.badRequest().body("Funcionario j?? cadastrado.");
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{funcionarioId}")
	@CheckSecurity.Funcionario.PodeAtualizar
	public ResponseEntity<?> update(@PathVariable Long funcionarioId,
			@Valid @RequestBody FuncionarioInput funcionarioInput) {
		try {
			var result = funcionarioRepository.findById(funcionarioId);
			if (result.isPresent()) {
				var funcionarioAtual = result.get();

				if ((funcionarioRepository.existsByCpf(funcionarioInput.getCpf()))
						&& !(funcionarioAtual.getCpf().equals(funcionarioInput.getCpf()))) {
					return ResponseEntity.badRequest().body("CPF j?? cadastrado.");
				}

				if ((funcionarioRepository.existsByUsuario(funcionarioInput.getUsuario()))
						&& !(funcionarioAtual.getUsuario().equals(funcionarioInput.getUsuario()))) {
					return ResponseEntity.badRequest().body("Nome de usu??rio j?? cadastrado.");
				}

				funcionarioDTODisassembler.copyToDomainObject(funcionarioInput, funcionarioAtual);
				funcionarioAtual = funcionarioService.save(funcionarioAtual);
				var funcionarioDTO = funcionarioDTOAssembler.toModel(funcionarioAtual);
				return ResponseEntity.ok(funcionarioDTO);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{funcionarioId}")
	@CheckSecurity.Funcionario.PodeDeletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long funcionarioId) {
		funcionarioService.delete(funcionarioId);
	}

}