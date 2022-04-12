package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Pais;
import com.ine.sge.models.Provincia;
import com.ine.sge.dao.IPaisRepository;
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

@RestController("provinciaControllerV1")
@RequestMapping("/api/v1")
@Api(value = "provincias", description = "Provincia API")
public class ProvinciaController implements com.ine.sge.interfaces.resource.IProvincia{

	@PersistenceContext
	private EntityManager entityManager;
	private final IProvinciaRepository provinciaRepository;
	private final  IPaisRepository paisRepository;

	@Autowired
	public ProvinciaController(IProvinciaRepository provinciaRepository, IPaisRepository paisRepository) {
		this.provinciaRepository = provinciaRepository;
		this.paisRepository = paisRepository;
	}

	@Transactional
	@RequestMapping(value = "/provincias/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Provincia.class)
	public ResponseEntity<Page<Provincia>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Provincia.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onFields("codigo", "designacao", "sigla")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Provincia.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Provincia> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/provincias/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Provincia.class)
	public ResponseEntity<Page<Provincia>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Provincia.class).get();

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
				fullTextEntityManager.createFullTextQuery(query, Provincia.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Provincia> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/provincias/{provinciaid}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given province", response=Provincia.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long provinciaid){
		verify(provinciaid);
		return new ResponseEntity<> (provinciaRepository.findByIdAndEstado(provinciaid, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/provincias", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the provinces", response=Provincia.class, responseContainer="List")
	public ResponseEntity<Page<Provincia>> showall(@Valid Pageable pageable) {
		return new ResponseEntity<>(provinciaRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/paises/{paisid}/provincias", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the provinces by country", response=Provincia.class, responseContainer="List")
	public ResponseEntity<Page<Provincia>> showallbycountry(@Valid @PathVariable Long paisid, @Valid Pageable pageable) {
		return new ResponseEntity<>(provinciaRepository.findProvincesByCountry(paisid, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/paises/{paisid}/provincias", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new province", notes="The newly created province Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @PathVariable Long paisid, @Valid @RequestBody Provincia newProvince){

		Optional<Pais> updatedOptionalClass = paisRepository.findByIdAndEstado(paisid, 1);

		if (updatedOptionalClass.isPresent()) {
			newProvince.setPais(updatedOptionalClass.get());
			// Set the location header for the newly created resource
			return new ResponseEntity <>(null, getHttpHeaders(provinciaRepository.save(newProvince)), HttpStatus.CREATED);
		}
		else
			throw new ResourceNotFoundException("Country with id " + paisid + " not found");
	}

	@Transactional
	@RequestMapping(value = "/paises/{paisid}/provincias/{provinciaid}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given province")
	public ResponseEntity<Void> update(@Valid @PathVariable Long paisid, @Valid @PathVariable Long provinciaid, @Valid @RequestBody Provincia toUpdate){

		if(!paisRepository.existsById(paisid)) {
			throw new ResourceNotFoundException("Country " + paisid + " not found");
		}

		Optional<Provincia> provinciaUpdatedOptional = provinciaRepository.findByIdAndEstado(provinciaid, 1);
		if (provinciaUpdatedOptional.isPresent()){

			Provincia provincia = provinciaUpdatedOptional.get();
			provincia.setCodigo(toUpdate.getCodigo());
			provincia.setDesignacao(toUpdate.getDesignacao());
			provincia.setEstado(toUpdate.getEstado());
			provincia.setSigla(toUpdate.getSigla());
			provincia.setLastModifiedBy(toUpdate.getLastModifiedBy());

			provinciaRepository.save(provincia);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Province with id " + provinciaid + " not found");

	}

	@Transactional
	@RequestMapping(value = "/paises/{paisid}/provincias/{provinciaid}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given province")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long paisid, @Valid @PathVariable Long provinciaid, @Valid @RequestBody String lastModifiedBy){

		Optional<Provincia> softDelete = provinciaRepository.findByIdAndEstado(provinciaid, 1);
		if (softDelete.isPresent()) {
			Provincia afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			provinciaRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with provinciaid " + provinciaid + " not found");
	}

	//region private
	private HttpHeaders getHttpHeaders(@Valid @RequestBody Provincia newObject) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri());
		return responseHeaders;
	}

	private void verify(Long id) throws ResourceNotFoundException {
		provinciaRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Province with id " + id + " not found"));
	}
	//endregion

}
