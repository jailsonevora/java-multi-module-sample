package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Contabilidade;
import com.ine.sge.dao.IContabilidadeRepository;
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

@RestController("ContabilidadeV1")
@RequestMapping("/api/v1")
@Api(value = "contabilidades", description = "Contabilidade API")
public class ContabilidadeController implements com.ine.sge.interfaces.resource.IContabilidade {

	@PersistenceContext
	private EntityManager entityManager;
	private final IContabilidadeRepository contabilidadeRepository;

	@Autowired
	public ContabilidadeController(IContabilidadeRepository contabilidadeRepository) {
		this.contabilidadeRepository = contabilidadeRepository;
	}

	@Transactional
	@RequestMapping(value = "/contabilidades/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Contabilidade.class)
	public ResponseEntity<Page<Contabilidade>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Contabilidade.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onFields("cntaDsg", "cnta")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Contabilidade.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Contabilidade> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}


	@RequestMapping(value = "/contabilidades/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given accounting", response= Contabilidade.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (contabilidadeRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/contabilidades", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the accounts", response=Contabilidade.class, responseContainer="List")
	public ResponseEntity<Page<Contabilidade>> showall(Pageable pageable) {
		return new ResponseEntity<>(contabilidadeRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/contabilidades", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new accounting", notes="The newly created accounting Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Contabilidade newAccounting){

		newAccounting = contabilidadeRepository.save(newAccounting);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAccounting.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/contabilidades/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given accounting")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Contabilidade toUpdate){

		Optional<Contabilidade> updatedOptionalClass = contabilidadeRepository.findByIdAndEstado(id,1);
		if (updatedOptionalClass.isPresent()){

			Contabilidade afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setCnta(toUpdate.getCnta());
			afterIsPresent.setCntaDsg(toUpdate.getCntaDsg());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			contabilidadeRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Accounting with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/contabilidades/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given accounting")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Contabilidade> softDelete = contabilidadeRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Contabilidade afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			contabilidadeRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");

	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		contabilidadeRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Accounting with id " + id + " not found"));
	}
	// endregion



}
