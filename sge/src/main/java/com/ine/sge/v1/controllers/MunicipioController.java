package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Municipio;
import com.ine.sge.models.Provincia;
import com.ine.sge.dao.IMunicipioRepository;
import com.ine.sge.dao.IProvinciaRepository;
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

@RestController("municipioControllerV1")
@RequestMapping("/api/v1")
@Api(value = "municipios", description = "Municipio API")
public class MunicipioController implements com.ine.sge.interfaces.resource.IMunicipio {

	@PersistenceContext
	private EntityManager entityManager;
	private final IMunicipioRepository municipioRepository;
	private final IProvinciaRepository provinciaRepository;

	@Autowired
	public MunicipioController(IMunicipioRepository municipioRepository, IProvinciaRepository provinciaRepository) {
		this.municipioRepository = municipioRepository;
		this.provinciaRepository = provinciaRepository;
	}

	@Transactional
	@RequestMapping(value = "/municipios/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Municipio.class)
	public ResponseEntity<Page<Municipio>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Municipio.class).get();

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
				fullTextEntityManager.createFullTextQuery(query, Municipio.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Municipio> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/municipios/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Municipio.class)
	public ResponseEntity<Page<Municipio>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Municipio.class).get();

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
				fullTextEntityManager.createFullTextQuery(query, Municipio.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Municipio> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}


	@RequestMapping(value = "/municipios/{municipioid}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given county", response=Municipio.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long municipioid){
		verify(municipioid);
		return new ResponseEntity<> (municipioRepository.findByIdAndEstado(municipioid, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/municipios", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the counties", response=Municipio.class, responseContainer="List")
	public ResponseEntity<Page<Municipio>> showall(@Valid Pageable pageable) {
		return new ResponseEntity<>(municipioRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/provincias/{provinciaid}/municipios", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the counties by province", response=Municipio.class, responseContainer="List")
	public ResponseEntity<Page<Municipio>> showallbyprovince(@Valid @PathVariable Long provinciaid, @Valid Pageable pageable) {
		return new ResponseEntity<>(municipioRepository.findCountiesByProvince(provinciaid, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/provincias/{provinciaid}/municipios", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new county", notes="The newly created county Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @PathVariable Long provinciaid, @Valid @RequestBody Municipio newCounty){

		Optional<Provincia> updatedOptionalClass = provinciaRepository.findByIdAndEstado(provinciaid, 1);

		if (updatedOptionalClass.isPresent()) {
			newCounty.setProvincia(updatedOptionalClass.get());
			// Set the location header for the newly created resource
			return new ResponseEntity <>(null, getHttpHeaders(municipioRepository.save(newCounty)), HttpStatus.CREATED);
		}
		else
			throw new ResourceNotFoundException("Province with id " + provinciaid + " not found");
	}

	@Transactional
	@RequestMapping(value = "/provincias/{provinciaid}/municipios/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Updates given county")
	public ResponseEntity<Void> update(@Valid @PathVariable Long provinciaid, @Valid @PathVariable Long id, @Valid @RequestBody Municipio toUpdate){

		if(!provinciaRepository.existsById(provinciaid)) {
			throw new ResourceNotFoundException("Province " + provinciaid + " not found");
		}

		Optional<Municipio> municipioUpdatedOptional = municipioRepository.findByIdAndEstado(id, 1);
		if (municipioUpdatedOptional.isPresent()){

			Municipio municipio = municipioUpdatedOptional.get();
			municipio.setCodigo(toUpdate.getCodigo());
			municipio.setDesignacao(toUpdate.getDesignacao());
			municipio.setEstado(toUpdate.getEstado());
			municipio.setLastModifiedBy(toUpdate.getLastModifiedBy());

			municipioRepository.save(toUpdate);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("County with id " + id + " not found");

	}

	@Transactional
	@RequestMapping(value = "/provincias/{provinciaid}/municipios/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given county")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long provinciaid, @Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Municipio> softDelete = municipioRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Municipio afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			municipioRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		municipioRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " not found"));
	}

	private HttpHeaders getHttpHeaders(@Valid @RequestBody Municipio newObject) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri());
		return responseHeaders;
	}

	/*private void verify(Long id) throws ResourceNotFoundException {
		Optional<Pais> pais = paisRepository.findById(id);
		if(!pais.isPresent()) {
			throw new ResourceNotFoundException("Country with id " + id + " not found");
		}
	}*/

	// endregion

}
