package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Genero;
import com.ine.sge.dao.IGeneroRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController("GeneroV1")
@RequestMapping("/api/v1")
@Api(value = "generos", description = "Genero API")
public class GeneroController implements com.ine.sge.interfaces.resource.IGenero {

	private final IGeneroRepository generoRepository;

	@Autowired
	public GeneroController(IGeneroRepository generoRepository) {
		this.generoRepository = generoRepository;
	}


	@RequestMapping(value = "/generos/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given gender", response= Genero.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (generoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/generos", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the gender", response=Genero.class, responseContainer="List")
	public ResponseEntity<Page<Genero>> showall(Pageable pageable) {
		return new ResponseEntity<>(generoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/generos", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new gender", notes="The newly created gender Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Genero newGender){

		newGender = generoRepository.save(newGender);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newGender.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/generos/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given gender")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Genero toUpdate){

		Optional<Genero> updatedOptionalClass = generoRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Genero afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setSexo(toUpdate.getSexo());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			generoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Gender with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/generos/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given gender")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Genero> softDelete = generoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Genero afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			generoRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		generoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Gender with id " + id + " not found"));
	}
	// endregion
}
