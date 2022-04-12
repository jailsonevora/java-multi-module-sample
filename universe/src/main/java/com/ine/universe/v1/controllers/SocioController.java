package com.ine.universe.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.universe.models.Socio;
import com.ine.universe.dao.ISocioRepository;
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

@RestController("SocioV1")
@RequestMapping("/api/universe/v1")
@Api(value = "socios", description = "Universe Socio API")
public class SocioController implements com.ine.universe.interfaces.resource.ISocio {

	@PersistenceContext
	private EntityManager entityManager;
	private final ISocioRepository socioRepository;

	@Autowired
	public SocioController(ISocioRepository socioRepository) {
		this.socioRepository = socioRepository;
	}
	
	@Transactional
	@RequestMapping(value = "/socios/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Socio.class)
	public ResponseEntity<Page<Socio>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Socio.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onField( "nome")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Socio.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Socio> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/socios/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given partner", response= Socio.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (socioRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/socios", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the partners", response=Socio.class, responseContainer="List")
	public ResponseEntity<Page<Socio>> showall(Pageable pageable) {
		return new ResponseEntity<>(socioRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/socios", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new partner", notes="The newly created partner Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Socio newPartner){

		newPartner = socioRepository.save(newPartner);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPartner.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/socios/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given partner")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Socio toUpdate){

		Optional<Socio> updatedOptionalClass = socioRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Socio afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setNome(toUpdate.getNome());
			afterIsPresent.setNumero(toUpdate.getNumero());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			socioRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Partner with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/socios/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given partner")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Socio> softDelete = socioRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Socio afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			socioRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		socioRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Partner with id " + id + " not found"));
	}
	// endregion



}
