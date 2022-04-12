package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Classificacao;
import com.ine.sge.dao.IClassificacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController("Classificacaov1")
@RequestMapping("/api/v1")
@Api(value = "classificacoes", description = "Classificacao API")
public class ClassificacaoController implements com.ine.sge.interfaces.resource.IClassificacao {

	private final IClassificacaoRepository classificacoesRepository;

	@Autowired
	public ClassificacaoController(IClassificacaoRepository classificacoesRepository) {
		this.classificacoesRepository = classificacoesRepository;
	}


	@RequestMapping(value = "/classificacoes/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given ranking", response= Classificacao.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (classificacoesRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/classificacoes", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the ranking", response=Classificacao.class, responseContainer="List")
	public ResponseEntity<Page<Classificacao>> showall(Pageable pageable) {
		return new ResponseEntity<>(classificacoesRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/classificacoes", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new classification", notes="The newly created classification ID will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Classificacao newClassification){

		newClassification = classificacoesRepository.save(newClassification);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClassification.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/classificacoes/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given classification")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Classificacao toUpdate){

		Optional<Classificacao> updatedOptionalClass = classificacoesRepository.findByIdAndEstado(id, 1);

		if (updatedOptionalClass.isPresent()){

			Classificacao afterIsPresent = updatedOptionalClass.get();

			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			afterIsPresent.setClasificacao(toUpdate.getClasificacao());
			afterIsPresent.setClassName(toUpdate.getClassName());
			afterIsPresent.setDesignacao(toUpdate.getDesignacao());
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setNiveis(toUpdate.getNiveis());

			classificacoesRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			throw new ResourceNotFoundException("Classification with id " + id + " not found");

		}//endif
	}

	@Transactional
	@RequestMapping(value = "/classificacoes/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given classification")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){
		//		verify(id);
		//		classificacoesRepository.deleteById(id);

		Optional<Classificacao> softDelete = classificacoesRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Classificacao afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			classificacoesRepository.save(afterIsPresent);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		classificacoesRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Classification with id " + id + " not found"));
	}
	// endregion



}
