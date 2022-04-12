package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Situacao;
import com.ine.sge.dao.ISituacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController("SituacaoV1")
@RequestMapping("/api/v1")
@Api(value = "situacoes", description = "Situacao API")
public class SituacaoController implements com.ine.sge.interfaces.resource.ISituacao {


	private final ISituacaoRepository situacaoRepository;

	@Autowired
	public SituacaoController(ISituacaoRepository situacaoRepository) {
		this.situacaoRepository = situacaoRepository;
	}


	@RequestMapping(value = "/situacoes/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given position", response= Situacao.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (situacaoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/situacoes", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the positions", response=Situacao.class, responseContainer="List")
	public ResponseEntity<Page<Situacao>> showall(Pageable pageable) {
		return new ResponseEntity<>(situacaoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/situacoes", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new position", notes="The newly created position Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Situacao newPosition){

		newPosition = situacaoRepository.save(newPosition);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPosition.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/situacoes/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given position")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Situacao toUpdate){

		Optional<Situacao> updatedOptionalClass = situacaoRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Situacao afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setDesignacao(toUpdate.getDesignacao());
			afterIsPresent.setSta(toUpdate.getSta());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			situacaoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Position with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/situacoes/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given position")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Situacao> softDelete = situacaoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Situacao afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			situacaoRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		situacaoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Position with id " + id + " not found"));
	}
	// endregion



}
