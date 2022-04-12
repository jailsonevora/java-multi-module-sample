package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.dao.ICAERepository;
import com.ine.sge.models.CAE;
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

@RestController("CAEV1")
@RequestMapping("/api/v1")
@Api(value = "caes", description = "CAE API")
public class CAEController implements com.ine.sge.interfaces.resource.ICAE {

	@PersistenceContext
	private EntityManager entityManager;
	private final ICAERepository caeRepository;

	@Autowired
	public CAEController(ICAERepository caeRepository) {
		this.caeRepository = caeRepository;
	}

	@Transactional
	@RequestMapping(value = "/caes/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=CAE.class)
	public ResponseEntity<Page<CAE>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(CAE.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onFields("cae", "designacao", "className")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, CAE.class);

		// execute search and return results (sorted by relevance as default)

		final Page<CAE> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/caes/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=CAE.class)
	public ResponseEntity<Page<CAE>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(CAE.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onField( ""+field+"" )
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, CAE.class);

		// execute search and return results (sorted by relevance as default)

		final Page<CAE> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/caes/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given CAE", response= CAE.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (caeRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/caes", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the CAE", response=CAE.class, responseContainer="List")
	public ResponseEntity<Page<CAE>> showall(Pageable pageable) {
		return new ResponseEntity<>(caeRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/caes", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new cae", notes="The newly created cae Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody CAE newActivity){

		newActivity = caeRepository.save(newActivity);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newActivity.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}


	@Transactional
	@RequestMapping(value = "/caes/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given entity")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody CAE toUpdate){

		Optional<CAE> updatedOptionalClass = caeRepository.findByIdAndEstado(id, 1);

		if (updatedOptionalClass.isPresent()){

			CAE afterIsPresent = updatedOptionalClass.get();

			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			afterIsPresent.setCae(toUpdate.getCae());
			afterIsPresent.setClassName(toUpdate.getClassName());
			afterIsPresent.setDesignacao(toUpdate.getDesignacao());
			afterIsPresent.setEstado(toUpdate.getEstado());

			caeRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/caes/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given entity")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){
		//verify(id);
		//caeRepository.deleteById(id);

		Optional<CAE> softDelete = caeRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			CAE afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			caeRepository.save(afterIsPresent);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		caeRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("CAE with id " + id + " not found"));
	}
	// endregion




}
