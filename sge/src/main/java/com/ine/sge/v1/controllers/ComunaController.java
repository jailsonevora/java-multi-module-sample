package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Comuna;
import com.ine.sge.models.Municipio;
import com.ine.sge.dao.IComunaRepository;
import com.ine.sge.dao.IMunicipioRepository;
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

@RestController("ComunaV1")
@RequestMapping("/api/v1")
@Api(value = "comunas", description = "Comuna API")
public class ComunaController implements com.ine.sge.interfaces.resource.IComuna{

	@PersistenceContext
	private EntityManager entityManager;
	private final IComunaRepository comunaRepository;
	private final IMunicipioRepository municipioRepository;

	@Autowired
	public ComunaController(IComunaRepository comunaRepository, IMunicipioRepository municipioRepository) {
		this.comunaRepository = comunaRepository;
		this.municipioRepository = municipioRepository;
	}

	@Transactional
	@RequestMapping(value = "/comunas/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Comuna.class)
	public ResponseEntity<Page<Comuna>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Comuna.class).get();

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
				fullTextEntityManager.createFullTextQuery(query, Comuna.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Comuna> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/comunas/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Comuna.class)
	public ResponseEntity<Page<Comuna>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Comuna.class).get();

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
				fullTextEntityManager.createFullTextQuery(query, Comuna.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Comuna> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/comunas/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given comuna", response= Comuna.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (comunaRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/comunas", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the comuna", response=Comuna.class, responseContainer="List")
	public ResponseEntity<Page<Comuna>> showall(@Valid Pageable pageable) {
		return new ResponseEntity<>(comunaRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/municipios/{municipioid}/comunas", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the comunas by county", response=Comuna.class, responseContainer="List")
	public ResponseEntity<Page<Comuna>> showallbyprovince(@Valid @PathVariable Long municipioid, @Valid Pageable pageable) {
		return new ResponseEntity<>(comunaRepository.findComunasByCounty(municipioid, pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/municipios/{municipioid}/comunas", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new comuna", notes="The newly created comuna Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @PathVariable Long municipioid, @Valid @RequestBody Comuna newComuna){

		Optional<Municipio> updatedOptionalClass = municipioRepository.findByIdAndEstado(municipioid, 1);

		if (updatedOptionalClass.isPresent()) {
			newComuna.setMunicipio(updatedOptionalClass.get());
			// Set the location header for the newly created resource
			return new ResponseEntity <>(null, getHttpHeaders(comunaRepository.save(newComuna)), HttpStatus.CREATED);
		}
		else
			throw new ResourceNotFoundException("County with id " + municipioid + " not found");

	}

	@Transactional
	@RequestMapping(value = "/municipios/{municipioid}/comunas/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given comuna")
	public ResponseEntity<Void> update(@Valid @PathVariable Long municipioid, @Valid @PathVariable Long id, @Valid @RequestBody Comuna toUpdate){

		if(!municipioRepository.existsById(municipioid)) {
			throw new ResourceNotFoundException("County " + municipioid + " not found");
		}

		Optional<Comuna> updatedOptionalClass = comunaRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Comuna afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setCodigo(toUpdate.getCodigo());
			afterIsPresent.setDesignacao(toUpdate.getDesignacao());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			comunaRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Comuna with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/municipios/{municipioid}/comunas/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given comuna")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long municipioid, @Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Comuna> softDelete = comunaRepository.findByIdAndEstado(id,1);
		if (softDelete.isPresent()) {
			Comuna afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			comunaRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");

	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		comunaRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Comuna with id " + id + " not found"));
	}

	private HttpHeaders getHttpHeaders(@Valid @RequestBody Comuna newObject) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri());
		return responseHeaders;
	}

	// endregion

}
