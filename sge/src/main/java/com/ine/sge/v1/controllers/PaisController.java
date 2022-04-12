package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Pais;
import com.ine.sge.dao.IPaisRepository;

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

@RestController("paisControllerV1")
@RequestMapping("/api/v1")
@Api(value = "paises", description = "Pais API")
public class PaisController implements com.ine.sge.interfaces.resource.IPais {

	@PersistenceContext
	private EntityManager entityManager;
	private final IPaisRepository paisRepository;

	@Autowired
	public PaisController(IPaisRepository paisRepository) {
		this.paisRepository = paisRepository;
	}

	@Transactional
	@RequestMapping(value = "/paises/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Pais.class)
	public ResponseEntity<Page<Pais>> search(@Valid @PathVariable String keyword){
		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Pais.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onField("pais")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Pais.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Pais> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/paises/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Pais.class)
	public ResponseEntity<Page<Pais>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){
		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Pais.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onField(""+field+"")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Pais.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Pais> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/paises/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given country", response=Pais.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (paisRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/paises", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the countries", response=Pais.class, responseContainer="List")
	public ResponseEntity<Page<Pais>> showall(Pageable pageable) {
		return new ResponseEntity<>(paisRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/paises", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new country", notes="The newly created country Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Pais newCountry){

		newCountry = paisRepository.save(newCountry);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCountry.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/paises/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given country")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Pais toUpdate){

		Optional<Pais> paisUpdatedOptional = paisRepository.findByIdAndEstado(id, 1);
		if (paisUpdatedOptional.isPresent()){

			Pais pais = paisUpdatedOptional.get();
			pais.setEstado(toUpdate.getEstado());
			pais.setPais(toUpdate.getPais());
			pais.setLastModifiedBy(toUpdate.getLastModifiedBy());

			paisRepository.save(pais);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Province with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/paises/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given country")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Pais> softDelete = paisRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Pais afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			paisRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		paisRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " not found"));
	}
	/*private void verify(Long id) throws ResourceNotFoundException {
		Optional<Pais> pais = paisRepository.findById(id);
		if(!pais.isPresent()) {
			throw new ResourceNotFoundException("Country with id " + id + " not found");
		}
	}*/
	// endregion

}
