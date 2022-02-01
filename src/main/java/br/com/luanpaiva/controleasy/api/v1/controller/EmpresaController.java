package br.com.luanpaiva.controleasy.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luanpaiva.controleasy.api.v1.assembler.EmpresaDTOAssembler;
import br.com.luanpaiva.controleasy.api.v1.assembler.EmpresaDTODisassembler;
import br.com.luanpaiva.controleasy.api.v1.model.dto.EmpresaDTO;
import br.com.luanpaiva.controleasy.api.v1.model.input.EmpresaInput;
import br.com.luanpaiva.controleasy.core.security.CheckSecurity;
import br.com.luanpaiva.controleasy.domain.repository.EmpresaRepository;
import br.com.luanpaiva.controleasy.domain.service.EmpresaService;

@RestController
@RequestMapping("/v1/empresas")
public class EmpresaController {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private EmpresaDTOAssembler empresaDTOAssembler;

	@Autowired
	private EmpresaDTODisassembler empresaDTODisassembler;

	@GetMapping
	@CheckSecurity.Empresa.PodeConsultar
	public ResponseEntity<List<EmpresaDTO>> findAll() {
		var empresas = empresaRepository.findAll();
		if (!empresas.isEmpty()) {
			var empresasDTO = empresaDTOAssembler.toCollectionModel(empresas);
			return ResponseEntity.ok(empresasDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{empresaId}")
	@CheckSecurity.Empresa.PodeConsultar
	public ResponseEntity<EmpresaDTO> findById(@PathVariable Long empresaId) {
		var result = empresaRepository.findById(empresaId);
		if (result.isPresent()) {
			var empresa = result.get();
			var empresaDTO = empresaDTOAssembler.toModel(empresa);
			return ResponseEntity.ok(empresaDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@CheckSecurity.Empresa.PodeCadastrar
	public ResponseEntity<?> save(@Valid @RequestBody EmpresaInput empresaInput) {
		try {
			var empresa = empresaDTODisassembler.toDomainObject(empresaInput);
			if (!empresaRepository.existsByCnpj(empresa.getCnpj())) {
				empresa = empresaService.save(empresa);
				var empresaDTO = empresaDTOAssembler.toModel(empresa);
				return ResponseEntity.status(HttpStatus.CREATED).body(empresaDTO);
			}
			return ResponseEntity.badRequest().body("Empresa já cadastrada.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{empresaId}")
	@CheckSecurity.Empresa.PodeAtualizar
	public ResponseEntity<?> update(@PathVariable Long empresaId, @Valid @RequestBody EmpresaInput empresaInput) {
		try {
			var result = empresaRepository.findById(empresaId);
			if (result.isPresent()) {
				var empresaAtual = result.get();
				if (!empresaAtual.getCnpj().equals(empresaInput.getCnpj())
						&& empresaRepository.existsByCnpj(empresaInput.getCnpj())) {
					return ResponseEntity.badRequest().body("Empresa já cadastrada.");
				}
				empresaDTODisassembler.copyToDomainObject(empresaInput, empresaAtual);
				empresaAtual = empresaService.save(empresaAtual);
				var empresaDTO = empresaDTOAssembler.toModel(empresaAtual);
				return ResponseEntity.ok(empresaDTO);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{empresaId}")
	@CheckSecurity.Empresa.PodeDeletar
	public ResponseEntity<?> delete(@PathVariable Long empresaId) {
		try {
			var result = empresaRepository.findById(empresaId);
			if (result.isPresent()) {
				var empresa = result.get();
				empresaService.delete(empresa);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
