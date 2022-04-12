package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.ActividadeComercial;
import com.ine.sge.dao.IActividadeComercialRepository;
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

@RestController("ActividadeComercialControllerV1")
@RequestMapping("/api/v1")
@Api(value = "actividadescomerciais", description = "Actividade Comercial API")
public class ActividadeComercialController implements  com.ine.sge.interfaces.resource.IActividadeComercial{

	private final IActividadeComercialRepository actividadeComercialRepository;

	@Autowired
	public ActividadeComercialController(IActividadeComercialRepository actividadeComercialRepository) {
		this.actividadeComercialRepository = actividadeComercialRepository;
	}


	@RequestMapping(value = "/actividadescomerciais/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given business activity", response= ActividadeComercial.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (actividadeComercialRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/actividadescomerciais", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the business activities", response=ActividadeComercial.class, responseContainer="List")
	public ResponseEntity<Page<ActividadeComercial>> showall(Pageable pageable) {
		return new ResponseEntity<>(actividadeComercialRepository.findAllByEstado(1,pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/actividadescomerciais", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new comercial activity", notes="The newly created comercial activity Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody ActividadeComercial newActivity){

		newActivity = actividadeComercialRepository.save(newActivity);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newActivity.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/actividadescomerciais/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given business activity")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody ActividadeComercial toUpdate){

		Optional<ActividadeComercial> updatedOptionalClass = actividadeComercialRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			ActividadeComercial afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setCac(toUpdate.getCac());
			afterIsPresent.setCacDsg(toUpdate.getCacDsg());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			actividadeComercialRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Business activity with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/actividadescomerciais/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given Business activity")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){
		//		verify(id);
		//		actividadeComercialRepository.deleteById(id);

		Optional<ActividadeComercial> softDelete = actividadeComercialRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			ActividadeComercial afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			actividadeComercialRepository.save(afterIsPresent);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		actividadeComercialRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Business activity with id " + id + " not found"));
	}
	// endregion




}
