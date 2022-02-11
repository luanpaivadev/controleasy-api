package br.com.app.controleasy.api.v1.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import br.com.app.controleasy.api.v1.assembler.PontoDTOAssembler;
import br.com.app.controleasy.api.v1.assembler.PontoDTODisassembler;
import br.com.app.controleasy.api.v1.model.dto.PontoDTO;
import br.com.app.controleasy.api.v1.model.input.PontoInput;
import br.com.app.controleasy.core.security.CheckSecurity;
import br.com.app.controleasy.domain.exception.EntidadeNaoEncontradaException;
import br.com.app.controleasy.domain.model.Ponto;
import br.com.app.controleasy.domain.repository.PontoRepository;
import br.com.app.controleasy.domain.service.PontoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Pontos")
@RestController
@RequestMapping("/v1/pontos")
public class PontoController {

	private static final Logger LOG = LoggerFactory.getLogger(PontoController.class);

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private PontoRepository pontoRepository;

	@Autowired
	private PontoService pontoService;

	@Autowired
	private PontoDTOAssembler pontoDTOAssembler;

	@Autowired
	private PontoDTODisassembler pontoDTODisassembler;

	@ApiOperation("Retorna uma lista com todos os registros de ponto")
	@GetMapping
	@CheckSecurity.Ponto.PodeConsultar
	public ResponseEntity<Page<PontoDTO>> findAll(Pageable pageable) {
		Page<Ponto> pontos = pontoRepository.findAll(pageable);
		if (!pontos.isEmpty()) {
			var pontosDTO = pontoDTOAssembler.toCollectionModel(pontos.getContent());
			return ResponseEntity.ok(new PageImpl<>(pontosDTO, pageable, pontos.getTotalElements()));
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Retorna o registro do ponto com base no ID")
	@GetMapping("/{pontoId}")
	@CheckSecurity.Ponto.PodeConsultar
	public ResponseEntity<PontoDTO> findById(@ApiParam("ID do ponto") @PathVariable Long pontoId) {
		var result = pontoRepository.findById(pontoId);

		if (result.isPresent()) {
			var ponto = result.get();
			var pontoDTO = pontoDTOAssembler.toModel(ponto);
			return ResponseEntity.ok(pontoDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Retorna o registro do ponto com base na data e ID do funcionário")
	@GetMapping("/ponto-data-funcionario")
	@CheckSecurity.Ponto.PodeConsultar
	public ResponseEntity<?> findByDataAndFuncionarioId(final String localDate, final String funcionarioId) {

		try {
			var result = pontoRepository.findByDataAndFuncionarioId(LocalDate.parse(localDate, formatter),
					Long.parseLong(funcionarioId));

			if (result.isPresent()) {
				var ponto = result.get();
				var pontoDTO = pontoDTOAssembler.toModel(ponto);
				return ResponseEntity.ok(pontoDTO);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}

	}

	@ApiOperation("Retorna uma lista de registros de ponto com base no período e ID do funcionário")
	@GetMapping("/ponto-por-periodo-funcionario")
	@CheckSecurity.Ponto.PodeConsultar
	public ResponseEntity<List<PontoDTO>> findByDataBetweenAndFuncionarioIdOrderByData(final String dataInicio,
			final String dataFim, final String funcionarioId) {

		try {
			var result = pontoRepository.findByDataBetweenAndFuncionarioIdOrderByData(
					LocalDate.parse(dataInicio, formatter), LocalDate.parse(dataFim, formatter),
					Long.parseLong(funcionarioId));
			if (!result.isEmpty()) {
				List<PontoDTO> pontosDTO = pontoDTOAssembler.toCollectionModel(result);
				return ResponseEntity.ok(pontosDTO);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}

	}

	@ApiOperation("Salva um novo registro de ponto")
	@PostMapping()
	@CheckSecurity.Ponto.PodeCadastrar
	public ResponseEntity<?> save(@Valid @RequestBody PontoInput pontoInput) {
		try {
			var ponto = pontoDTODisassembler.toDomainObject(pontoInput);
			if (!pontoRepository.existsByDataAndFuncionarioId(ponto.getData(), ponto.getFuncionario().getId())) {
				ponto = pontoService.save(ponto);
				var pontoDTO = pontoDTOAssembler.toModel(ponto);
				return ResponseEntity.status(HttpStatus.CREATED).body(pontoDTO);
			}
			return ResponseEntity.badRequest().body("Ponto de usuário já cadastrado.");
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation("Atualiza um registro de ponto com base no ID")
	@PutMapping("/{pontoId}")
	@CheckSecurity.Ponto.PodeAtualizar
	public ResponseEntity<?> update(@ApiParam("ID do ponto") @PathVariable Long pontoId,
			@Valid @RequestBody PontoInput pontoInput) {
		try {
			var result = pontoRepository.findById(pontoId);
			if (result.isPresent()) {
				var pontoAtual = result.get();
				if (!pontoAtual.getData().equals(pontoInput.getData()) && pontoRepository
						.existsByDataAndFuncionarioId(pontoInput.getData(), pontoInput.getFuncionario().getId())) {
					return ResponseEntity.badRequest().body("Ponto de usuário já cadastrado.");
				}
				pontoDTODisassembler.copyToDomainObject(pontoInput, pontoAtual);
				pontoAtual = pontoService.save(pontoAtual);
				var pontoDTO = pontoDTOAssembler.toModel(pontoAtual);
				return ResponseEntity.ok(pontoDTO);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation("Deleta um registro de ponto com base no ID")
	@DeleteMapping("/{pontoId}")
	@CheckSecurity.Ponto.PodeDeletar
	public ResponseEntity<?> delete(@ApiParam("ID do ponto") @PathVariable Long pontoId) {
		return null;
	}

}