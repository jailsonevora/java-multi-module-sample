package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Canal;
import com.ine.sge.dao.ICanalRepository;
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

@RestController("CanalRecepcaoV1")
@RequestMapping("/api/v1")
@Api(value = "canaisrecepcoes", description = "Canal Recepcao API")
public class CanalRecepcaoController implements com.ine.sge.interfaces.resource.ICanalRecepcao{

	private final ICanalRepository canalRecepcaoRepository;

	@Autowired
	public CanalRecepcaoController(ICanalRepository canalRecepcaoRepository) {
		this.canalRecepcaoRepository = canalRecepcaoRepository;
	}


	@RequestMapping(value = "/canaisrecepcoes/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given reception channel", response= Canal.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (canalRecepcaoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/canaisrecepcoes", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the reception channel", response=Canal.class, responseContainer="List")
	public ResponseEntity<Page<Canal>> showall(Pageable pageable) {
		return new ResponseEntity<>(canalRecepcaoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/canaisrecepcoes", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new reception channel", notes="The newly created reception channel Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Canal newReceptionChannel){

		newReceptionChannel = canalRecepcaoRepository.save(newReceptionChannel);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newReceptionChannel.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/canaisrecepcoes/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given reception channel")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Canal toUpdate){

		Optional<Canal> updatedOptionalClass = canalRecepcaoRepository.findByIdAndEstado(id, 1);

		if (updatedOptionalClass.isPresent()){

			Canal afterIsPresent = updatedOptionalClass.get();

			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			afterIsPresent.setCanal(toUpdate.getCanal());
			afterIsPresent.setEstado(toUpdate.getEstado());

			canalRecepcaoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			throw new ResourceNotFoundException("Reception channel with id" + id + " not found");

		}//endif
	}

	@Transactional
	@RequestMapping(value = "/canaisrecepcoes/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given reception channel")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){
//		verify(id);
//		canalRecepcaoRepository.deleteById(id);

		Optional<Canal> softDelete = canalRecepcaoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Canal afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			canalRecepcaoRepository.save(afterIsPresent);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		canalRecepcaoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Reception channel with id " + id + "not found"));
	}
	// endregion


}
