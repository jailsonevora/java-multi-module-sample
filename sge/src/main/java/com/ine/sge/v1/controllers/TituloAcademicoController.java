package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.TituloAcademico;
import com.ine.sge.dao.ITituloAcademicoRepository;
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

@RestController("TituloAcademicoV1")
@RequestMapping("/api/v1")
@Api(value = "titulosacademicos", description = "Titulo Academico API")
public class TituloAcademicoController implements com.ine.sge.interfaces.resource.ITituloAcademico {


	private final ITituloAcademicoRepository tituloacademicoRepository;

	@Autowired
	public TituloAcademicoController(ITituloAcademicoRepository tituloacademicoRepository) {
		this.tituloacademicoRepository = tituloacademicoRepository;
	}


	@RequestMapping(value = "/titulosacademicos/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given degree", response= TituloAcademico.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (tituloacademicoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/titulosacademicos", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the degrees", response=TituloAcademico.class, responseContainer="List")
	public ResponseEntity<Page<TituloAcademico>> showall(Pageable pageable) {
		return new ResponseEntity<>(tituloacademicoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/titulosacademicos", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new degree", notes="The newly created degree Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody TituloAcademico newDegree){

		newDegree = tituloacademicoRepository.save(newDegree);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDegree.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/titulosacademicos/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given degree")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody TituloAcademico toUpdate){

		Optional<TituloAcademico> updatedOptionalClass = tituloacademicoRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			TituloAcademico afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setTitulo(toUpdate.getTitulo());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			tituloacademicoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Degree with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/titulosacademicos/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given degree")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<TituloAcademico> softDelete = tituloacademicoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			TituloAcademico afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			tituloacademicoRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		tituloacademicoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Degree with id " + id + " not found"));
	}
	// endregion



}
