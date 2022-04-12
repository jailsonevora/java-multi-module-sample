package com.ine.sge.v1.controllers;

import com.ine.sge.dao.IEstabelecimentoRepository;
import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Estabelecimento;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@RestController("EstabelecimentoV1")
@RequestMapping("/api/v1")
@Api(value = "estabelecimentos", description = "Estabelecimento API")
public class EstabelecimentoController implements com.ine.sge.interfaces.resource.IEstabelecimento {

	@PersistenceContext
	private EntityManager entityManager;
	private final IEstabelecimentoRepository estabelecimentoRepository;

	@Autowired
	public EstabelecimentoController(IEstabelecimentoRepository estabelecimentoRepository) {
		this.estabelecimentoRepository = estabelecimentoRepository;
	}

	@Transactional
	@RequestMapping(value = "/estabelecimentos/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Estabelecimento.class)
	public ResponseEntity<Page<Estabelecimento>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Estabelecimento.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onFields("sede", "className", "decoutras", "latitude", "longitude", "comentarios", "nome")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Estabelecimento.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Estabelecimento> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/estabelecimentos/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Estabelecimento.class)
	public ResponseEntity<Page<Estabelecimento>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Estabelecimento.class).get();

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
				fullTextEntityManager.createFullTextQuery(query, Estabelecimento.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Estabelecimento> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/estabelecimentos/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given establishment", response= Estabelecimento.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (estabelecimentoRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/estabelecimentos", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the establishment", response=Estabelecimento.class, responseContainer="List")
	public ResponseEntity<Page<Estabelecimento>> showall(Pageable pageable) {
		return new ResponseEntity<>(estabelecimentoRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}


	@Transactional
	@RequestMapping(value = "/estabelecimentos", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new establishment", notes="The newly created establishment ID will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Estabelecimento newEstablishment){

		newEstablishment = estabelecimentoRepository.save(newEstablishment);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEstablishment.getId()).toUri());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/estabelecimentos/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given establishment")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Estabelecimento toUpdate){

		Optional<Estabelecimento> updatedOptionalClass = estabelecimentoRepository.findByIdAndEstado(id, 1);

		if (updatedOptionalClass.isPresent()){

			Estabelecimento afterIsPresent = updatedOptionalClass.get();

			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			afterIsPresent.setClassName(toUpdate.getClassName());
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setSede(toUpdate.getSede());
			afterIsPresent.setSituacaoEmpresa(toUpdate.getSituacaoEmpresa());
			afterIsPresent.setVolumeVendas(toUpdate.getVolumeVendas());
			afterIsPresent.setActiEcoPrincipal(toUpdate.getActiEcoPrincipal());
			afterIsPresent.setCae(toUpdate.getCae());
			afterIsPresent.setComentarios(toUpdate.getComentarios());
			afterIsPresent.setContacto(toUpdate.getContacto());
			afterIsPresent.setDecoutras(toUpdate.getDecoutras());
			afterIsPresent.setLatitude(toUpdate.getLatitude());
			afterIsPresent.setLongitude(toUpdate.getLongitude());
			afterIsPresent.setNumEstabelecimento(toUpdate.getNumEstabelecimento());

			afterIsPresent.setData_constituicao(toUpdate.getData_constituicao());
			afterIsPresent.setDataCriacao(toUpdate.getDataCriacao());
			afterIsPresent.setDataSituacaoAtividade(toUpdate.getDataSituacaoAtividade());
			afterIsPresent.setDataUltimaInsercao(toUpdate.getDataUltimaInsercao());
			afterIsPresent.setDataDocumento(toUpdate.getDataDocumento());

			afterIsPresent.setNumFuncNac(toUpdate.getNumFuncNac());
			afterIsPresent.setNumFuncRemNac(toUpdate.getNumFuncRemNac());

			afterIsPresent.setNumFuncNRem(toUpdate.getNumFuncNRem());
			afterIsPresent.setNumFuncNRemEst(toUpdate.getNumFuncNRemEst());

			afterIsPresent.setNumFunc(toUpdate.getNumFunc());
			afterIsPresent.setNumFuncEst(toUpdate.getNumFuncEst());
			afterIsPresent.setNumFuncRemEst(toUpdate.getNumFuncRemEst());


			afterIsPresent.setNumFuncHomem(toUpdate.getNumFuncHomem());
			afterIsPresent.setNumFuncHomemRem(toUpdate.getNumFuncHomemRem());
			afterIsPresent.setNumFuncHomemNRem(toUpdate.getNumFuncHomemNRem());

			afterIsPresent.setNumFuncHNRemEst(toUpdate.getNumFuncHNRemEst());
			afterIsPresent.setNumFuncHNRemNac(toUpdate.getNumFuncHNRemNac());
			afterIsPresent.setNumFuncHRemNac(toUpdate.getNumFuncHRemNac());
			afterIsPresent.setNumFuncHRemEst(toUpdate.getNumFuncHRemEst());

			afterIsPresent.setNumFuncionarioHNac(toUpdate.getNumFuncionarioHNac());
			afterIsPresent.setNumFuncionarioHEst(toUpdate.getNumFuncionarioHEst());


			afterIsPresent.setNumFuncMulher(toUpdate.getNumFuncMulher());
			afterIsPresent.setNumFuncMulherRem(toUpdate.getNumFuncMulherRem());
			afterIsPresent.setNumFuncMulherNrem(toUpdate.getNumFuncMulherNrem());


			afterIsPresent.setNumFuncMNRemEst(toUpdate.getNumFuncMNRemEst());
			afterIsPresent.setNumFuncMNRemNac(toUpdate.getNumFuncMNRemNac());
			afterIsPresent.setNumFuncMRemNac(toUpdate.getNumFuncMRemNac());
			afterIsPresent.setNumFuncMRemEst(toUpdate.getNumFuncMRemEst());

			afterIsPresent.setNumFuncionarioMNac(toUpdate.getNumFuncionarioMNac());
			afterIsPresent.setNumFuncionarioMEst(toUpdate.getNumFuncionarioMEst());


			estabelecimentoRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			throw new ResourceNotFoundException("Establishment with id " + id + " not found");

		}//endif
	}

	@Transactional
	@RequestMapping(value = "/estabelecimentos/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given establishment")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Estabelecimento> softDelete = estabelecimentoRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Estabelecimento afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			estabelecimentoRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");

	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		estabelecimentoRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Establishment with id " + id + " not found"));
	}
	// endregion
}
