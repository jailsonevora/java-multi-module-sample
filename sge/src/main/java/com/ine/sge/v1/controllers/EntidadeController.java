package com.ine.sge.v1.controllers;

import com.ine.common.dto.UniverseDefaultSchema.Contacto;
import com.ine.sge.dao.*;
import com.ine.common.exception.ResourceNotFoundException;
import com.ine.sge.kafka.KafkaProducer;
import com.ine.sge.models.Entidade;
import com.ine.sge.util.BindingSGEToUniverse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Date;
import java.util.Optional;


@RestController("entidadeControllerV1")
@RequestMapping("/api/v1")
@Api(value = "entidades", description = "Entidade API")
public class EntidadeController implements com.ine.sge.interfaces.resource.IEntidade {

	@PersistenceContext
	private EntityManager entityManager;

	private final IEntidadeRepository entidadeRepository;
	private final KafkaProducer producer;

	private final ISituacaoRepository situacaoRepository;
	private final IGeneroRepository generoRepository;
	private final IPaisRepository paisRepository;
	private final IFormaJuridicaRepository formaJuridicaRepository;
	private final IContabilidadeRepository contabilidadeRepository;
	private final ICanalRepository canalRepository;
	private final IActividadeComercialRepository actividadeComercialRepository;
	private final ISocioRepository iSocioRepository;


	private static final Logger LOG = LoggerFactory.getLogger(com.ine.common.dto.UniverseDefaultSchema.Entidade.class);

	@Autowired
	public EntidadeController(IEntidadeRepository entidadeRepository, KafkaProducer producer, ISituacaoRepository situacaoRepository, IGeneroRepository generoRepository, IPaisRepository paisRepository, IFormaJuridicaRepository formaJuridicaRepository, IContabilidadeRepository contabilidadeRepository, ICanalRepository canalRepository, IActividadeComercialRepository actividadeComercialRepository, ISocioRepository iSocioRepository) {
		this.entidadeRepository = entidadeRepository;
		this.producer = producer;
		this.situacaoRepository = situacaoRepository;
		this.generoRepository = generoRepository;
		this.paisRepository = paisRepository;
		this.formaJuridicaRepository = formaJuridicaRepository;
		this.contabilidadeRepository = contabilidadeRepository;
		this.canalRepository = canalRepository;
		this.actividadeComercialRepository = actividadeComercialRepository;
		this.iSocioRepository = iSocioRepository;
	}

	@Transactional
	@RequestMapping(value = "/entidades/{keyword}/search", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Entidade.class)
	public ResponseEntity<Page<Entidade>> search(@Valid @PathVariable String keyword){
		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Entidade.class).get();

		// a very basic query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.onFields("naturalId", "nome", "abreviatura", "profissao", "pessoa", "instituicao", "nif", "bi", "numRge", "comentarioEntidade", "grupoHolding", "codGrupo", "nomeGrupo", "nifGrupo", "nomeRespGrupo", "emailRespGrupo", "webUrlGrupo", "comentarioGrupo", "latitude", "longitude","gue", "siac", "webUrl")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Entidade.class);

		// execute search and return results (sorted by relevance as default)
		final Page<Entidade> results = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/entidades/search/advanced", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Entidade.class)
	public ResponseEntity<Page<Entidade>> advancedSearch(@Valid @RequestBody Entidade keyword){
		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Entidade.class).get();

		// a very basic query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.onFields("naturalId", "nome", "abreviatura", "profissao", "pessoa", "instituicao", "nif", "bi", "numRge", "comentarioEntidade", "grupoHolding", "codGrupo", "nomeGrupo", "nifGrupo", "nomeRespGrupo", "emailRespGrupo", "webUrlGrupo", "comentarioGrupo", "latitude", "longitude","gue", "siac", "webUrl")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Entidade.class);

		// execute search and return results (sorted by relevance as default)
		final Page<Entidade> results = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/entidades/{keyword}/search/{field}/byfield", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given keyword", response=Entidade.class)
	public ResponseEntity<Page<Entidade>> searchByfield(@Valid @PathVariable String keyword, @Valid @PathVariable String field){
		// get the full text entity manager
		FullTextEntityManager fullTextEntityManager =
				org.hibernate.search.jpa.Search.
						getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Entidade.class).get();

		// a very basic query by keywords
		org.apache.lucene.search.Query query =
				queryBuilder
						.keyword()
						.onField("+field+")
						.matching("*"+keyword+"*")
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Entidade.class);

		// execute search and return results (sorted by relevance as default)
		final Page<Entidade> results = new PageImpl<>(jpaQuery.getResultList());

		return new ResponseEntity<> (results, HttpStatus.OK);
	}

	@RequestMapping(value = "/entidades/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given entity", response=Entidade.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		return new ResponseEntity<> (entidadeRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the entities", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showall(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades/{naturalId}/porrenovar", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given entity to renew", response=Entidade.class, responseContainer="List")
	public ResponseEntity<?> showaPorRenovar(@Valid @PathVariable String naturalId) {
		return new ResponseEntity<>(entidadeRepository.findByNaturalIdAndEstadoAndPorRenovar(naturalId, 1,false), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades/porrenovar", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the entities to renew", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showallPorRenovar(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByEstadoAndPorRenovar(1, true, pageable), HttpStatus.OK);
	}

	// region Individual

	@RequestMapping(value="/entidades/individuais", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the individual entities", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showallIndividual(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByTipoEntidadeTipoLikeAndPorRenovarAndEstadoAprovacaoAndEstado("INDIVIDUAL", false, 0, 1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades/individuais/aprovadas", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the individual entities approved", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showallIndividualApproved(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByEstadoAprovacaoAndTipoEntidadeTipoLikeAndEstado(1, "INDIVIDUAL", 1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades/individuais/poraprovar", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the individual entities to approve", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showallIndividualToApprove(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByEstadoAprovacaoAndTipoEntidadeTipoLikeAndEstado(0, "INDIVIDUAL", 1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades/individuais/apagadas", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the individual entities deleted", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showallIndividualDeleted(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByEstadoAprovacaoAndTipoEntidadeTipoLikeAndEstado(0, "INDIVIDUAL", 1,  pageable), HttpStatus.OK);
	}

	// endregion

	// region Collective

	@RequestMapping(value="/entidades/collective", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the collective entities", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showallCollective(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByTipoEntidadeTipoLikeAndPorRenovarAndEstadoAprovacaoAndEstado("COLETIVO", false, 0, 1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades/collective/aprovadas", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the collective entities approved", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showallCollectiveApproved(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByEstadoAprovacaoAndTipoEntidadeTipoLikeAndEstado(1, "COLETIVO", 1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades/collective/poraprovar", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the collective entities to approve", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showallCollectiveToApprove(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByEstadoAprovacaoAndTipoEntidadeTipoLikeAndEstado(0, "COLETIVO", 1, pageable), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades/collective/apagadas", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the collective entities deleted", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showallCollectiveDeleted(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByEstadoAprovacaoAndTipoEntidadeTipoLikeAndEstado(0, "COLETIVO", 1, pageable), HttpStatus.OK);
	}

	// endregion

	@RequestMapping(value="/validaentidades/{key}", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Validate entry entity")
	public ResponseEntity<?> validate(@Valid @PathVariable String key) {

		if (entidadeRepository.existsEntidadeByNaturalId(key))
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Entity with validation code " + key + " not found");
	}

	@Transactional
	@RequestMapping(value = "/entidades", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Creates a new entity", notes="The newly created entity Id will be sent in the location response header")
	public ResponseEntity<Void> create(@Valid @RequestBody Entidade newEntity){

			// Set the location header for the newly created resource*/
			newEntity.setVersao(1);
			newEntity.setNaturalId(entidadeRepository.findDistinctTopByOrderByNaturalIdDesc().isPresent() ? entidadeRepository.findDistinctTopByOrderByNaturalIdDesc().get().getNaturalId()+1 : 1);
			return new ResponseEntity <>(null, getHttpHeaders(entidadeRepository.save(newEntity)), HttpStatus.CREATED);

	}

	@Transactional
	@RequestMapping(value = "/entidades/{idOfListedEntityBeforeTheApproval}/renovarentidades", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Renew a new entity", notes="The newly approves entity Id will be sent in the location response header")
	public ResponseEntity<Void> renewEntity(@Valid @PathVariable Long idOfListedEntityBeforeTheApproval,@Valid @RequestBody Entidade newEntity){

			Optional<Entidade> entityBeforeTheApproval = entidadeRepository.findByIdAndEstado(idOfListedEntityBeforeTheApproval,1);
			if (entityBeforeTheApproval.isPresent()) {
				Entidade afterIsPresent = entityBeforeTheApproval.get();
				// soft delete the actual record even if the record
				afterIsPresent.setEstado(0);
				entidadeRepository.save(afterIsPresent);
				//incremento a versao da entidade somando +1 ao anterior
				newEntity.setVersao(afterIsPresent.getVersao()+1);
			}
			// save new renewed entity record
			// Set the location header for the newly created resource*/

			HttpHeaders responseHeaders = getHttpHeaders(entidadeRepository.save(newEntity));

			return new ResponseEntity <>(null, null, HttpStatus.CREATED);
	}

	@Transactional
	@RequestMapping(value = "/entidades/{idOfListedEntityBeforeTheApproval}/aprovarentidades", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Approves a new entity", notes="The newly approved entity Id will be sent in the location response header")
	public ResponseEntity<Void> approvalEntity(@Valid @PathVariable Long idOfListedEntityBeforeTheApproval){

		Optional<Entidade> entityBeforeTheApproval = entidadeRepository.findByIdAndEstado(idOfListedEntityBeforeTheApproval,1);
		if (entityBeforeTheApproval.isPresent()) {
			Entidade afterIsPresent = entityBeforeTheApproval.get();

			afterIsPresent.setEstadoAprovacao(1);

			HttpHeaders headers = getHttpHeaders(entidadeRepository.save(afterIsPresent));

			com.ine.common.dto.UniverseDefaultSchema.Entidade commonEntidade;
			commonEntidade = new com.ine.common.dto.UniverseDefaultSchema.Entidade<
					com.ine.common.dto.UniverseDefaultSchema.Socio,
					com.ine.common.dto.UniverseDefaultSchema.EmpresaParticipada,
					com.ine.common.dto.UniverseDefaultSchema.Estabelecimento<Contacto, com.ine.common.dto.UniverseDefaultSchema.CAE>,
					com.ine.common.dto.UniverseDefaultSchema.CAE,
					Contacto>();

			// sendo to kafka
			BindingSGEToUniverse bindingUniverse = new BindingSGEToUniverse(situacaoRepository, generoRepository, paisRepository, formaJuridicaRepository, contabilidadeRepository, canalRepository, actividadeComercialRepository, iSocioRepository);
			com.ine.common.dto.UniverseDefaultSchema.Entidade result = bindingUniverse.Binding(commonEntidade, afterIsPresent);

			if (  result != null  )
				producer.send(result);

			return new ResponseEntity <>(null, headers, HttpStatus.CREATED);

		}
		else
			throw new ResourceNotFoundException("Entity with id " + idOfListedEntityBeforeTheApproval + " not found or not approved");

	}

	@Transactional
	@RequestMapping(value = "/entidades/{idOfListedEntityBeforeTheApproval}/reprovarentidades", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Refuse a new entity", notes="The newly approved entity Id will be sent in the location response header")
	public ResponseEntity<Void> refuseEntity(@Valid @PathVariable Long idOfListedEntityBeforeTheApproval, @Valid @RequestBody String comment, @Valid @RequestBody Date startDate, @Valid @RequestBody Date endDate){

		Optional<Entidade> entityBeforeRefuse = entidadeRepository.findByIdAndEstado(idOfListedEntityBeforeTheApproval,1);
		if (entityBeforeRefuse.isPresent()) {
			Entidade afterIsPresent = entityBeforeRefuse.get();

			afterIsPresent.setEstadoAprovacao(0);
			afterIsPresent.setComentarioEntidade(comment);
			afterIsPresent.setDataCostituicao(startDate);
			afterIsPresent.setDataCostituicaoFim(endDate);

			return new ResponseEntity <>(null, getHttpHeaders(entidadeRepository.save(afterIsPresent)), HttpStatus.CREATED);

		}
		else
			throw new ResourceNotFoundException("Entity with id " + idOfListedEntityBeforeTheApproval + " not found or not approved");

	}


	@Transactional
	@RequestMapping(value = "/entidades/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Updates given entity")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody Entidade toUpdate){

		Optional<Entidade> updatedOptionalClass = entidadeRepository.findByIdAndEstado(id, 1);
		if (updatedOptionalClass.isPresent()){

			Entidade afterIsPresent = updatedOptionalClass.get();

			afterIsPresent.setVersao(afterIsPresent.getVersao()+1);

			afterIsPresent.setEstado(toUpdate.getEstado());
			//afterIsPresent.setCae(toUpdate.getCasa());
			afterIsPresent.setContacto(toUpdate.getContacto());
			afterIsPresent.setSocio(toUpdate.getSocio());

			afterIsPresent.setVolume_vendas(toUpdate.getVolume_vendas());
			afterIsPresent.setDataCostituicaoFim(toUpdate.getDataCostituicaoFim());

			afterIsPresent.setEstabelecimento(toUpdate.getEstabelecimento());
			afterIsPresent.setAbreviatura(toUpdate.getAbreviatura());

			afterIsPresent.setActiComercial(toUpdate.getActiComercial());
			afterIsPresent.setWebUrlGrupo(toUpdate.getWebUrlGrupo());
			afterIsPresent.setVolumeNegocioGrupo(toUpdate.getVolumeNegocioGrupo());
			afterIsPresent.setVolume_vendas(toUpdate.getVolume_vendas());

			afterIsPresent.setTipoEntidade(toUpdate.getTipoEntidade());
			afterIsPresent.setTipoEntidade(toUpdate.getTipoEntidade());
			afterIsPresent.setTelefoneIndGrupo(toUpdate.getTelefoneIndGrupo());

			afterIsPresent.setTelefoneGrupo(toUpdate.getTelefoneGrupo());
			afterIsPresent.setState(toUpdate.getState());

			afterIsPresent.setSituacaoActivGrupo(toUpdate.getSituacaoActivGrupo());

			afterIsPresent.setSituacaoEmpresa(toUpdate.getSituacaoEmpresa());
			afterIsPresent.setSiac(toUpdate.getSiac());
			afterIsPresent.setSexo(toUpdate.getSexo());

			afterIsPresent.setSector(toUpdate.getSector());
			afterIsPresent.setProfissao(toUpdate.getProfissao());

			afterIsPresent.setPessoa(toUpdate.getPessoa());
			afterIsPresent.setPais(toUpdate.getPais());
			afterIsPresent.setOrigemDocGrupo(toUpdate.getOrigemDocGrupo());
			afterIsPresent.setOrigemDoc(toUpdate.getOrigemDoc());

			afterIsPresent.setNumPessoasServGrupo(toUpdate.getNumPessoasServGrupo());
			afterIsPresent.setNumEmpresasNacGrupo(toUpdate.getNumEmpresasNacGrupo());
			afterIsPresent.setNumEmpresasEstGrupo(toUpdate.getNumEmpresasEstGrupo());

			afterIsPresent.setNumRge(toUpdate.getNumRge());
			afterIsPresent.setNumFuncionarioMulher(toUpdate.getNumFuncionarioMulher());
			afterIsPresent.setNumFuncionarioMNac(toUpdate.getNumFuncionarioMNac());
			afterIsPresent.setNumFuncionarioMEst(toUpdate.getNumFuncionarioMEst());

			afterIsPresent.setNumFuncionarioMEst(toUpdate.getNumFuncionarioMEst());
			afterIsPresent.setNumFuncionarioHomem(toUpdate.getNumFuncionarioHomem());
			afterIsPresent.setNumFuncionarioHNac(toUpdate.getNumFuncionarioHNac());
			afterIsPresent.setNumFuncionarioHEst(toUpdate.getNumFuncionarioHEst());

			afterIsPresent.setNumFuncRemNac(toUpdate.getNumFuncRemNac());
			afterIsPresent.setNumFuncRemEst(toUpdate.getNumFuncRemEst());
			afterIsPresent.setNumFuncNRemNac(toUpdate.getNumFuncNRemNac());

			afterIsPresent.setNumFuncNRemEst(toUpdate.getNumFuncNRemEst());
			afterIsPresent.setNumFuncNac(toUpdate.getNumFuncNac());

			afterIsPresent.setNumFuncMulherRem(toUpdate.getNumFuncMulherRem());
			afterIsPresent.setNumFuncMulherNrem(toUpdate.getNumFuncMulherNrem());
			afterIsPresent.setNumFuncMRemNac(toUpdate.getNumFuncMRemNac());
			afterIsPresent.setNumFuncMRemEst(toUpdate.getNumFuncMRemEst());

			afterIsPresent.setNumFuncMNRemNac(toUpdate.getNumFuncMNRemNac());
			afterIsPresent.setNumFuncMNRemEst(toUpdate.getNumFuncMNRemEst());
			afterIsPresent.setNumFuncHomemRem(toUpdate.getNumFuncHomemRem());

			afterIsPresent.setNumFuncHomemNrem(toUpdate.getNumFuncHomemNrem());
			afterIsPresent.setNumFuncHRemNac(toUpdate.getNumFuncHRemNac());
			afterIsPresent.setNumFuncHRemEst(toUpdate.getNumFuncHRemEst());
			afterIsPresent.setNumFuncHNRemNac(toUpdate.getNumFuncHNRemNac());

			afterIsPresent.setNumFuncHNRemEst(toUpdate.getNumFuncHNRemEst());
			afterIsPresent.setNumFuncEst(toUpdate.getNumFuncEst());
			afterIsPresent.setNumEstabsTotal(toUpdate.getNumEstabsTotal());

			afterIsPresent.setNumEstabelecimentos(toUpdate.getNumEstabelecimentos());
			afterIsPresent.setNumDoc(toUpdate.getNumDoc());

			afterIsPresent.setNomeRespGrupo(toUpdate.getNomeRespGrupo());
			afterIsPresent.setNomeGrupo(toUpdate.getNomeGrupo());
			afterIsPresent.setNifGrupo(toUpdate.getNifGrupo());
			afterIsPresent.setNaturezaJurGrupo(toUpdate.getNaturezaJurGrupo());

			afterIsPresent.setNaturalId(toUpdate.getNaturalId());
			afterIsPresent.setMesesActividade(toUpdate.getMesesActividade());
			afterIsPresent.setLongitude(toUpdate.getLongitude());

			afterIsPresent.setLatitude(toUpdate.getLatitude());
			afterIsPresent.setInstituicao(toUpdate.getInstituicao());
			afterIsPresent.setIdade(toUpdate.getIdade());
			afterIsPresent.setGue(toUpdate.getGue());

			afterIsPresent.setGrupoHolding(toUpdate.getGrupoHolding());
			afterIsPresent.setFormaJuridica(toUpdate.getFormaJuridica());
			afterIsPresent.setFaxIndGrupo(toUpdate.getFaxIndGrupo());

			afterIsPresent.setFaxGrupo(toUpdate.getFaxGrupo());
			afterIsPresent.setEstadoAprovacao(toUpdate.getEstadoAprovacao());

			afterIsPresent.setEmailRespGrupo(toUpdate.getEmailRespGrupo());
			afterIsPresent.setEliminado(toUpdate.getEliminado());
			afterIsPresent.setDataultImaInsercao(toUpdate.getDataultImaInsercao());
			afterIsPresent.setDataSituacaoActividade(toUpdate.getDataSituacaoActividade());

			afterIsPresent.setDataInitactGrupo(toUpdate.getDataInitactGrupo());
			afterIsPresent.setDataFimGrupo(toUpdate.getDataFimGrupo());
			afterIsPresent.setDataFimActGrupo(toUpdate.getDataFimActGrupo());

			afterIsPresent.setDataConstGrupo(toUpdate.getDataConstGrupo());
			afterIsPresent.setDataSge(toUpdate.getDataSge());
			afterIsPresent.setDataDocumento(toUpdate.getDataDocumento());
			afterIsPresent.setDataCostituicao(toUpdate.getDataCostituicao());


			afterIsPresent.setContabilidade(toUpdate.getContabilidade());
			afterIsPresent.setComentarioGrupo(toUpdate.getComentarioGrupo());
			afterIsPresent.setComentarioEntidade(toUpdate.getComentarioEntidade());

			afterIsPresent.setCodGrupo(toUpdate.getCodGrupo());
			afterIsPresent.setCapitalSocial(toUpdate.getCapitalSocial());

			afterIsPresent.setCapPublico(toUpdate.getCapPublico());
			afterIsPresent.setCapPrivado(toUpdate.getCapPrivado());
			afterIsPresent.setCapEstrang(toUpdate.getCapEstrang());
			afterIsPresent.setCanal(toUpdate.getCanal());

			afterIsPresent.setCaePrincipalGrupo(toUpdate.getCaePrincipalGrupo());
			afterIsPresent.setBi(toUpdate.getBi());
			afterIsPresent.setAnoEliminacao(toUpdate.getAnoEliminacao());

			afterIsPresent.setAnoActividade(toUpdate.getAnoActividade());
			afterIsPresent.setActiEcoPrincipal(toUpdate.getActiEcoPrincipal());
			afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

			afterIsPresent.setContabilidadeGeral(toUpdate.getContabilidadeGeral());

			entidadeRepository.save(afterIsPresent);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");
	}

	@Transactional
	@RequestMapping(value = "/entidades/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Deletes given entity")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

		Optional<Entidade> softDelete = entidadeRepository.findByIdAndEstado(id, 1);
		if (softDelete.isPresent()) {
			Entidade afterIsPresent = softDelete.get();
			afterIsPresent.setEstado(0);
			afterIsPresent.setLastModifiedBy(lastModifiedBy);
			entidadeRepository.save(afterIsPresent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("Entity with id " + id + " not found");

	}

	// region private
	private HttpHeaders getHttpHeaders(@Valid @RequestBody Entidade newObject) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri());
		return responseHeaders;
	}

	private void verify(Long id) throws ResourceNotFoundException {
		entidadeRepository.findByIdAndEstado(id, 1).orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " not found"));
	}
	// endregion
}
