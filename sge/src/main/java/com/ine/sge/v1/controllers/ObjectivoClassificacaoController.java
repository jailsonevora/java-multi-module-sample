package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.ObjectivoClassificacao;
import com.ine.sge.dao.IObjectivoClassificacaoRepository;
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

@RestController("ObjectivoClassificacaoV1")
@RequestMapping("/api/v1")
@Api(value = "objectivosclassificacoes", description = "Objectivo Classificacao API")
public class ObjectivoClassificacaoController implements com.ine.sge.interfaces.resource.IObjectivoClassificacao {

	private final IObjectivoClassificacaoRepository objectivoclassificacaoRepository;

	@Autowired
	public ObjectivoClassificacaoController(IObjectivoClassificacaoRepository objectivoclassificacaoRepository) {
		this.objectivoclassificacaoRepository = objectivoclassificacaoRepository;
	}


	@RequestMapping(value = "/objectivosclassificacoes/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given goal classification", response= ObjectivoClassificacao.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (objectivoclassificacaoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/objectivosclassificacoes", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the goal classification", response=ObjectivoClassificacao.class, responseContainer="List")
	public ResponseEntity<Page<ObjectivoClassificacao>> showall(Pageable pageable) {
		return new ResponseEntity<>(objectivoclassificacaoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/objectivosclassificacoes", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new goal classification", notes="The newly created goal classification Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody ObjectivoClassificacao newGoal){

		newGoal = objectivoclassificacaoRepository.save(newGoal);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newGoal.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/objectivosclassificacoes/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given goal classification")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody ObjectivoClassificacao toUpdate){

		Optional<ObjectivoClassificacao> updatedOptionalClass = objectivoclassificacaoRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			ObjectivoClassificacao afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setCodigo(toUpdate.getCodigo());
			afterIsPresent.setDesignacao(toUpdate.getDesignacao());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			objectivoclassificacaoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Goal classification with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/objectivosclassificacoes/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given goal classification")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<ObjectivoClassificacao> softDelete = objectivoclassificacaoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			ObjectivoClassificacao afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			objectivoclassificacaoRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		objectivoclassificacaoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Goal classification with id " + id + " not found"));
	}
	// endregion

}
