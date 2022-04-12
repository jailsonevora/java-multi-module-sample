package com.ine.sge.v1.controllers;

import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.models.Entidade;
import com.ine.sge.models.Mensagem;
import com.ine.sge.dao.IEntidadeRepository;
import com.ine.sge.dao.IMensagemRepository;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController("MensagemV1")
@RequestMapping("/api/v1")
@Api(value = "mensagens", description = "Mensagem API")
public class MensagemController implements com.ine.sge.interfaces.resource.IMensagem {

	@PersistenceContext
	private EntityManager entityManager;
	private final IMensagemRepository mensagemRepository;
	private final IEntidadeRepository entidadeRepository;

	@Autowired
	public MensagemController(IMensagemRepository mensagemRepository, IEntidadeRepository entidadeRepository) {
		this.mensagemRepository = mensagemRepository;
		this.entidadeRepository = entidadeRepository;
	}

	@Transactional
	@RequestMapping(value = "/mensagens/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Mensagem.class)
	public ResponseEntity<Page<Mensagem>> search(@Valid @PathVariable String keyword){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Mensagem.class).get();

		// a query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.wildcard()
						.onFields("remetente", "destinatario", "titulo", "corpoMensagem")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Mensagem.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Mensagem> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/mensagens/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Mensagem.class)
	public ResponseEntity<Page<Mensagem>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){

		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Mensagem.class).get();

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
				fullTextEntityManager.createFullTextQuery(query, Mensagem.class);

		// execute search and return results (sorted by relevance as default)

		final Page<Mensagem> results  = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/mensagens/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given message", response= Mensagem.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (mensagemRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/mensagens", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the messages", response=Mensagem.class, responseContainer="List")
	public ResponseEntity<Page<Mensagem>> showall(Pageable pageable) {
		return new ResponseEntity<>(mensagemRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/mensagens/novas", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the new messages", response=Mensagem.class, responseContainer="List")
	public ResponseEntity<Page<Mensagem>> showallNew(Pageable pageable) {
		return new ResponseEntity<>(mensagemRepository.findAllByLidaAndEstado(false, 1, pageable), HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/mensagens", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new message", notes="The newly created message Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Mensagem newMessage){

		Optional<Entidade> updatedOptionalClass = entidadeRepository.findByIdAndEstado(newMessage.getEntidade().getId(), 1);
		Optional<Mensagem> updatedOptionalClassAsMessageResponse = mensagemRepository.findByIdAndEstado(newMessage.getIdRespostaAMensagem(), 1);

		if (updatedOptionalClass.isPresent()) {
			// Set the location header for the newly created resource
			return new ResponseEntity <>(null, getHttpHeaders(mensagemRepository.save(newMessage)), HttpStatus.CREATED);
		}
		// cria mensagem sem associar a uma entidade
		else if (Objects.isNull(newMessage.getEntidade().getId()) || Objects.isNull(newMessage.getIdRespostaAMensagem())){
			return new ResponseEntity <>(null, getHttpHeaders(mensagemRepository.save(newMessage)), HttpStatus.CREATED);
		}
		else if (updatedOptionalClassAsMessageResponse.isPresent()){
			return new ResponseEntity <>(null, getHttpHeaders(mensagemRepository.save(newMessage)), HttpStatus.CREATED);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + newMessage.getEntidade().getId() + " not found");
	}

	@Transactional
	@RequestMapping(value = "/mensagens/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given message")
	public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody Mensagem toUpdate){

		if(!entidadeRepository.existsById(toUpdate.getEntidade().getId())) {
			throw new ResourceNotFoundException("Country " + toUpdate.getEntidade().getId() + " not found");
		}

		Optional<Mensagem> updatedOptionalClass = mensagemRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Mensagem afterIsPresent = updatedOptionalClass.get();
			afterIsPresent.setCorpoMensagem(toUpdate.getCorpoMensagem());
			afterIsPresent.setDestinatario(toUpdate.getDestinatario());
			afterIsPresent.setRemetente(toUpdate.getRemetente());
			afterIsPresent.setTitulo(toUpdate.getTitulo());
			afterIsPresent.setEstado(toUpdate.getEstado());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			mensagemRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Message with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/mensagens/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given message")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Mensagem> softDelete = mensagemRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Mensagem afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);

			mensagemRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	// region private
	private void verify(Long id) throws ResourceNotFoundException {
		mensagemRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Message with id " + id + " not found"));
	}

	private HttpHeaders getHttpHeaders(@Valid @RequestBody Mensagem newObject) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri());
		return responseHeaders;
	}
	// endregion
}
