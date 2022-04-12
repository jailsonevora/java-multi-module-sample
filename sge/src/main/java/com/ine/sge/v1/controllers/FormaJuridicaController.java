package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.FormaJuridica;
import com.ine.sge.dao.IFormaJuridicaRepository;
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

@RestController("FormaJuridicaV1")
@RequestMapping("/api/v1")
@Api(value = "formasjuridicas", description = "Forma Juridica API")
public class FormaJuridicaController implements com.ine.sge.interfaces.resource.IFormaJuridica {

	private final IFormaJuridicaRepository formaJuridicaRepository;

	@Autowired
	public FormaJuridicaController(IFormaJuridicaRepository formaJuridicaRepository) {
		this.formaJuridicaRepository = formaJuridicaRepository;
	}


	@RequestMapping(value = "/formasjuridicas/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given legal form", response= FormaJuridica.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (formaJuridicaRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/formasjuridicas", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the legal form", response=FormaJuridica.class, responseContainer="List")
	public ResponseEntity<Page<FormaJuridica>> showall(Pageable pageable) {
		return new ResponseEntity<>(formaJuridicaRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/formasjuridicas", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new legal form", notes="The newly created legal form Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody FormaJuridica newLegalForm){

		newLegalForm = formaJuridicaRepository.save(newLegalForm);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newLegalForm.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/formasjuridicas/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given legal form")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody FormaJuridica toUpdate){

		Optional<FormaJuridica> updatedOptionalClass = formaJuridicaRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			FormaJuridica afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setFjr(toUpdate.getFjr());
			afterIsPresent.setFormaJuridica(toUpdate.getFormaJuridica());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			formaJuridicaRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Legal form with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/formasjuridicas/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given legal form")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<FormaJuridica> softDelete = formaJuridicaRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			FormaJuridica afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			formaJuridicaRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");

	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		formaJuridicaRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Legal form with id " + id + " not found"));
	}
	// endregion

}
