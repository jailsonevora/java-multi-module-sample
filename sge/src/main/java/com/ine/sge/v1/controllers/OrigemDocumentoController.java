package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.OrigemDocumento;
import com.ine.sge.dao.IOrigemDocumentoRepository;
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

@RestController("OrigemDocumentoV1")
@RequestMapping("/api/v1")
@Api(value = "origemdocumentos", description = "Origem Documento API")
public class OrigemDocumentoController implements com.ine.sge.interfaces.resource.IOrigemDocumento {

	@PersistenceContext
	private EntityManager entityManager;
	private final IOrigemDocumentoRepository origemdocumentoRepository;

	@Autowired
	public OrigemDocumentoController(IOrigemDocumentoRepository origemdocumentoRepository) {
		this.origemdocumentoRepository = origemdocumentoRepository;
	}

	@Transactional
	@RequestMapping(value = "/origemdocumentos/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=OrigemDocumento.class)
	public ResponseEntity<Page<OrigemDocumento>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(OrigemDocumento.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onFields("origem", "origemDsg")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, OrigemDocumento.class);

		// execute search and return results (sorted by relevance as default)

		final Page<OrigemDocumento> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/origemdocumentos/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=OrigemDocumento.class)
	public ResponseEntity<Page<OrigemDocumento>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(OrigemDocumento.class).get();

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
				fullTextEntityManager.createFullTextQuery(query, OrigemDocumento.class);

		// execute search and return results (sorted by relevance as default)

		final Page<OrigemDocumento> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/origemdocumentos/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given document source", response= OrigemDocumento.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (origemdocumentoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/origemdocumentos", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the document sources", response=OrigemDocumento.class, responseContainer="List")
	public ResponseEntity<Page<OrigemDocumento>> showall(Pageable pageable) {
		return new ResponseEntity<>(origemdocumentoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/origemdocumentos", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new document source", notes="The newly created document source Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody OrigemDocumento newSource){

		newSource = origemdocumentoRepository.save(newSource);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newSource.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/origemdocumentos/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given document source")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody OrigemDocumento toUpdate){

		Optional<OrigemDocumento> updatedOptionalClass = origemdocumentoRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			OrigemDocumento afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setOrigemDoc(toUpdate.getOrigemDoc());
			afterIsPresent.setOrigemDsg(toUpdate.getOrigemDsg());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			origemdocumentoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Document source with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/origemdocumentos/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given document source")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<OrigemDocumento> softDelete = origemdocumentoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			OrigemDocumento afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);

			origemdocumentoRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		origemdocumentoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Document source with id " + id + " not found"));
	}
	// endregion


}
