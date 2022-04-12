package com.ine.universe.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.universe.models.Contacto;
import com.ine.universe.dao.IContactoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController("ContactoV1")
@RequestMapping("/api/universe/v1")
@Api(value = "contactos", description = "Universe Contacto API")
public class ContactoController implements com.ine.universe.interfaces.resource.IContacto {

	@PersistenceContext
	private EntityManager entityManager;
	private final IContactoRepository contactoRepository;

	@Autowired
	public ContactoController(IContactoRepository contactoRepository) {
		this.contactoRepository = contactoRepository;
	}

	@Transactional
	@RequestMapping(value = "/contactos/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Contacto.class)
	public ResponseEntity<Page<Contacto>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Contacto.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onFields("email", "morada", "codPostal", "telefoneInd", "telefone", "telemovelInd",
						"telemovel", "fax", "faxInd", "porta", "casa", "piso", "portaPiso", "webUrl")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Contacto.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Contacto> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/contactos/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given address", response= Contacto.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (contactoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/contactos", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the addresses", response=Contacto.class, responseContainer="List")
	public ResponseEntity<Page<Contacto>> showall(Pageable pageable) {
		return new ResponseEntity<>(contactoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/contactos", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new address", notes="The newly created address Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Contacto newContact){

		newContact = contactoRepository.save(newContact);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newContact.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/contactos/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given address")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Contacto toUpdate){

		Optional<Contacto> updatedOptionalClass = contactoRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Contacto afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setCasa(toUpdate.getCasa());
			afterIsPresent.setCodPostal(toUpdate.getCodPostal());

			afterIsPresent.setEmail(toUpdate.getEmail());
			afterIsPresent.setFax(toUpdate.getFax());
			afterIsPresent.setFaxInd(toUpdate.getFaxInd());

			afterIsPresent.setMorada(toUpdate.getMorada());
			afterIsPresent.setPais(toUpdate.getPais());
			afterIsPresent.setPiso(toUpdate.getPiso());

			afterIsPresent.setPorta(toUpdate.getPorta());
			afterIsPresent.setPortaPiso(toUpdate.getPortaPiso());
			afterIsPresent.setTelefone(toUpdate.getTelefone());

			afterIsPresent.setTelefoneInd(toUpdate.getTelefoneInd());
			afterIsPresent.setTelemovel(toUpdate.getTelemovel());
			afterIsPresent.setTelemovelInd(toUpdate.getTelemovelInd());
			afterIsPresent.setWebUrl(toUpdate.getWebUrl());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			afterIsPresent.setComuna(toUpdate.getComuna());
			afterIsPresent.setMunicipio(toUpdate.getMunicipio());
			afterIsPresent.setProvincia(toUpdate.getProvincia());

			contactoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Address with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/contactos/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given address")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Contacto> softDelete = contactoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Contacto afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			contactoRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");

	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		contactoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Address with id " + id + " not found"));
	}
	// endregion


}
