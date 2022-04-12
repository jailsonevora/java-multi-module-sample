package com.ine.universe.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.universe.models.EmpresaParticipada;
import com.ine.universe.dao.IEmpresaParticipadaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController("EmpresaParticipadaV1")
@RequestMapping("/api/universe/v1")
@Api(value = "empresasparticipadas", description = "Universe Empresa Participada API")
public class EmpresaParticipadaController implements com.ine.universe.interfaces.resource.IEmpresaParticipada {

	private final IEmpresaParticipadaRepository empresaParticipadaRepository;

	@Autowired
	public EmpresaParticipadaController(IEmpresaParticipadaRepository empresaParticipadaRepository) {
		this.empresaParticipadaRepository = empresaParticipadaRepository;
	}


	@RequestMapping(value = "/empresasparticipadas/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given affiliated company", response= EmpresaParticipada.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (empresaParticipadaRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/empresasparticipadas", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the affiliated company", response=EmpresaParticipada.class, responseContainer="List")
	public ResponseEntity<Page<EmpresaParticipada>> showall(Pageable pageable) {
		return new ResponseEntity<>(empresaParticipadaRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/empresasparticipadas", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new AffiliatedCompany", notes="The newly created AffiliatedCompany Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody EmpresaParticipada newAffiliatedCompany){

		newAffiliatedCompany = empresaParticipadaRepository.save(newAffiliatedCompany);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAffiliatedCompany.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/empresasparticipadas/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given AffiliatedCompany")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody EmpresaParticipada toUpdate){

		Optional<EmpresaParticipada> updatedOptionalClass = empresaParticipadaRepository.findByIdAndEstado(id, 1);

		if (updatedOptionalClass.isPresent()){

			EmpresaParticipada afterIsPresent = updatedOptionalClass.get();

			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			afterIsPresent.setComentario(toUpdate.getComentario());
			afterIsPresent.setClassName(toUpdate.getClassName());
			afterIsPresent.setContasConsolidada(toUpdate.getContasConsolidada());
			afterIsPresent.setDataIntegracao(toUpdate.getDataIntegracao());
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setPercParticipacao(toUpdate.getPercParticipacao());

			empresaParticipadaRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			throw new ResourceNotFoundException("AffiliatedCompany with id " + id + " not found");

		}//endif
	}

	@Transactional
	@RequestMapping(value = "/empresasparticipadas/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given AffiliatedCompany")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<EmpresaParticipada> softDelete = empresaParticipadaRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			EmpresaParticipada afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			empresaParticipadaRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");

	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		empresaParticipadaRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Affiliated company with id " + id + " not found"));
	}
	// endregion

}
