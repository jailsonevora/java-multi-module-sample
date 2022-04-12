package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Sector;
import com.ine.sge.dao.ISectorRepository;
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

@RestController("SectorV1")
@RequestMapping("/api/v1")
@Api(value = "sectores", description = "Sector API")
public class SectorController implements com.ine.sge.interfaces.resource.ISector {


	private final ISectorRepository sectorRepository;

	@Autowired
	public SectorController(ISectorRepository sectorRepository) {
		this.sectorRepository = sectorRepository;
	}


	@RequestMapping(value = "/sectores/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given sector", response= Sector.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (sectorRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/sectores", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the sectors", response=Sector.class, responseContainer="List")
	public ResponseEntity<Page<Sector>> showall(Pageable pageable) {
		return new ResponseEntity<>(sectorRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/sectores", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new sector", notes="The newly created sector Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Sector newSector){

		newSector = sectorRepository.save(newSector);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newSector.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/sectores/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given sector")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Sector toUpdate){

		Optional<Sector> updatedOptionalClass = sectorRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Sector afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setSector(toUpdate.getSector());
			afterIsPresent.setSectorDsg(toUpdate.getSectorDsg());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			sectorRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Sector with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/sectores/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given sector")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Sector> softDelete = sectorRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Sector afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			sectorRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		sectorRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Sector with id " + id + " not found"));
	}
	// endregion


}
