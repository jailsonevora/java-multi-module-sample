package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.dao.IAldeiaRepository;
import com.ine.sge.dao.IComunaRepository;
import com.ine.sge.dao.IMunicipioRepository;
import com.ine.sge.models.Aldeia;
import com.ine.sge.models.Comuna;
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

@RestController("AldeiaV1")
@RequestMapping("/api/v1")
@Api(value = "aldeia", description = "AldeiaController API")
public class AldeiaController implements com.ine.sge.interfaces.resource.IAldeia{

	@PersistenceContext
	private EntityManager entityManager;

	private final IAldeiaRepository aldeiaRepository;
	private final IComunaRepository comunaRepository;

	@Autowired
	public AldeiaController(IAldeiaRepository aldeiaRepository, IComunaRepository comunaRepository) {
		this.aldeiaRepository = aldeiaRepository;
		this.comunaRepository = comunaRepository;
	}

	@Transactional
	@RequestMapping(value = "/aldeia/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response= Aldeia.class)
	public ResponseEntity<Page<Aldeia>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Aldeia.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onFields("codigo", "designacao")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Aldeia.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Aldeia> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/aldeia/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response= Aldeia.class)
	public ResponseEntity<Page<Aldeia>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Aldeia.class).get();

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
				fullTextEntityManager.createFullTextQuery(query, Aldeia.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Aldeia> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}


	@RequestMapping(value = "/aldeia/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given hamlet", response= Aldeia.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (aldeiaRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/aldeia", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the hamlet", response= Aldeia.class, responseContainer="List")
	public ResponseEntity<Page<Aldeia>> showall(@Valid Pageable pageable) {
		return new ResponseEntity<>(aldeiaRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/municipios/{comunaid}/aldeia", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the aldeia by county", response= Aldeia.class, responseContainer="List")
	public ResponseEntity<Page<Aldeia>> showallbycomuna(@Valid @PathVariable Long comunaid, @Valid Pageable pageable) {
		return new ResponseEntity<>(aldeiaRepository.findAldeiaByComuna(comunaid, pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/municipios/{comunaid}/aldeia", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new hamlet", notes="The newly created hamlet Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @PathVariable Long comunaid, @Valid @RequestBody Aldeia newAldeia){

		Optional<Comuna> updatedOptionalClass = comunaRepository.findByIdAndEstado(comunaid, 1);

		if (updatedOptionalClass.isPresent()) {
			newAldeia.setComuna(updatedOptionalClass.get());
			// Set the location header for the newly created resource
			return new ResponseEntity <>(null, getHttpHeaders(aldeiaRepository.save(newAldeia)), HttpStatus.CREATED);
		}
		else
			throw new ResourceNotFoundException("County with id " + comunaid + " not found");

	}

	@Transactional
	@RequestMapping(value = "/municipios/{comunaid}/aldeia/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given hamlet")
	public ResponseEntity<Void> update(@Valid @PathVariable Long comunaid, @Valid @PathVariable Long id, @Valid @RequestBody Aldeia toUpdate){

		if(!comunaRepository.existsById(comunaid)) {
			throw new ResourceNotFoundException("County " + comunaid + " not found");
		}

		Optional<Aldeia> updatedOptionalClass = aldeiaRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Aldeia afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setCodigo(toUpdate.getCodigo());
			afterIsPresent.setDesignacao(toUpdate.getDesignacao());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			aldeiaRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Hamlet with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/municipios/{comunaid}/aldeia/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given hamlet")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long comunaid, @Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Aldeia> softDelete = aldeiaRepository.findByIdAndEstado(id,1);
		if (softDelete.isPresent()) {
			Aldeia afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			aldeiaRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");

	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		aldeiaRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Hamlet with id " + id + " not found"));
	}

	private HttpHeaders getHttpHeaders(@Valid @RequestBody Aldeia newObject) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri());
		return responseHeaders;
	}

	// endregion

}
