package com.ine.universe.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.universe.models.Seccao;
import com.ine.universe.dao.ISeccaoRepository;
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

@RestController("SeccaoV1")
@RequestMapping("/api/universe/v1")
@Api(value = "seccoes", description = "Universe Seccao API")
public class SeccaoController implements com.ine.universe.interfaces.resource.ISeccao {


	private final ISeccaoRepository seccaoRepository;

	@Autowired
	public SeccaoController(ISeccaoRepository seccaoRepository) {
		this.seccaoRepository = seccaoRepository;
	}


	@RequestMapping(value = "/seccoes/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given section", response= Seccao.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (seccaoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/seccoes", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the sections", response=Seccao.class, responseContainer="List")
	public ResponseEntity<Page<Seccao>> showall(Pageable pageable) {
		return new ResponseEntity<>(seccaoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/seccoes", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new section", notes="The newly created section Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Seccao newSection){

		newSection = seccaoRepository.save(newSection);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newSection.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/seccoes/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given section")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Seccao toUpdate){

		Optional<Seccao> updatedOptionalClass = seccaoRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Seccao afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setAldeia(toUpdate.getAldeia());
			afterIsPresent.setArea(toUpdate.getArea());
			afterIsPresent.setComuna(toUpdate.getComuna());
			afterIsPresent.setMunicipio(toUpdate.getMunicipio());
			afterIsPresent.setProvincia(toUpdate.getProvincia());
			afterIsPresent.setSeccao(toUpdate.getSeccao());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			seccaoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Section with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/seccoes/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given section")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Seccao> softDelete = seccaoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Seccao afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			seccaoRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		seccaoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Section with id " + id + " not found"));
	}
	// endregion


}
