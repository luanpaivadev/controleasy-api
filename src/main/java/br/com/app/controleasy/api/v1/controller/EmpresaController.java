package br.com.app.controleasy.api.v1.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.controleasy.api.v1.assembler.EmpresaDTOAssembler;
import br.com.app.controleasy.api.v1.assembler.EmpresaDTODisassembler;
import br.com.app.controleasy.api.v1.model.dto.EmpresaDTO;
import br.com.app.controleasy.api.v1.model.input.EmpresaInput;
import br.com.app.controleasy.core.security.CheckSecurity;
import br.com.app.controleasy.domain.repository.EmpresaRepository;
import br.com.app.controleasy.domain.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Empresas")
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

	@ApiOperation("Retorna uma lista com todas as empresas cadastradas")
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

	@ApiOperation("Retorna o cadastro da empresa com base no ID")
	@GetMapping("/{empresaId}")
	@CheckSecurity.Empresa.PodeConsultar
	public ResponseEntity<EmpresaDTO> findById(@ApiParam("ID da empresa") @PathVariable Long empresaId) {
		var result = empresaRepository.findById(empresaId);
		if (result.isPresent()) {
			var empresa = result.get();
			var empresaDTO = empresaDTOAssembler.toModel(empresa);
			return ResponseEntity.ok(empresaDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Salva um novo registro de empresa")
	@PostMapping
	@CheckSecurity.Empresa.PodeCadastrar
	public ResponseEntity<?> save(@RequestBody @Valid EmpresaInput empresaInput) {
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

	@ApiOperation("Atualiza o cadastro da empresa com base no ID")
	@PutMapping("/{empresaId}")
	@CheckSecurity.Empresa.PodeAtualizar
	public ResponseEntity<?> update(@ApiParam("ID da empresa") @PathVariable Long empresaId, @RequestBody @Valid EmpresaInput empresaInput) {
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

	@ApiOperation("Deleta o cadastro da empresa com base no ID")
	@DeleteMapping("/{empresaId}")
	@CheckSecurity.Empresa.PodeDeletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@ApiParam("ID da empresa") @PathVariable Long empresaId) {
		empresaService.delete(empresaId);
	}

}