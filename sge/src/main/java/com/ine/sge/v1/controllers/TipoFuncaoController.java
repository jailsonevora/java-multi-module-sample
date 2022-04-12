package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.TipoFuncao;
import com.ine.sge.dao.ITipoFuncaoRepository;
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

@RestController("TipoFuncaoV1")
@RequestMapping("/api/v1")
@Api(value = "tipofuncoes", description = "Tipo Funcao API")
public class TipoFuncaoController implements com.ine.sge.interfaces.resource.ITipoFuncao {


	private final ITipoFuncaoRepository tipofuncaoRepository;

	@Autowired
	public TipoFuncaoController(ITipoFuncaoRepository tipofuncaoRepository) {
		this.tipofuncaoRepository = tipofuncaoRepository;
	}


	@RequestMapping(value = "/tipofuncoes/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given function type", response= TipoFuncao.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (tipofuncaoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/tipofuncoes", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the functions type", response=TipoFuncao.class, responseContainer="List")
	public ResponseEntity<Page<TipoFuncao>> showall(Pageable pageable) {
		return new ResponseEntity<>(tipofuncaoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/tipofuncoes", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new function type", notes="The newly created function type Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody TipoFuncao newFunctionType){

		newFunctionType = tipofuncaoRepository.save(newFunctionType);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newFunctionType.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/tipofuncoes/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given function type")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody TipoFuncao toUpdate){

		Optional<TipoFuncao> updatedOptionalClass = tipofuncaoRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			TipoFuncao afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setTIPO(toUpdate.getTIPO());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			tipofuncaoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Function type with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/tipofuncoes/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given function type")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<TipoFuncao> softDelete = tipofuncaoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			TipoFuncao afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			tipofuncaoRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		tipofuncaoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Function type with id " + id + " not found"));
	}
	// endregion



}
