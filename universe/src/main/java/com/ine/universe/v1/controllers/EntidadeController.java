package com.ine.universe.v1.controllers;

import com.ine.universe.dao.IEntidadeRepository;
import com.ine.common.exception.ResourceNotFoundException;
import com.ine.universe.models.Entidade;
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


@RestController("entidadeControllerV1")
@RequestMapping("/api/universe/v1")
@Api(value = "entidades", description = "Universe Entidade API")
public class EntidadeController implements com.ine.universe.interfaces.resource.IEntidade {

	@PersistenceContext
	private EntityManager entityManager;
	private final IEntidadeRepository entidadeRepository;

	@Autowired
	public EntidadeController(IEntidadeRepository entidadeRepository) {
		this.entidadeRepository = entidadeRepository;
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

	@RequestMapping(value = "/entidades/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves given entity", response=Entidade.class)
	public ResponseEntity<?> show(@Valid @PathVariable Long id){
		verify(id);
		return new ResponseEntity<> (entidadeRepository.findByIdAndEstado(id, 1), HttpStatus.OK);
	}

	@RequestMapping(value="/entidades", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the entities", response=Entidade.class, responseContainer="List")
	public ResponseEntity<Page<Entidade>> showall(@Valid Pageable pageable) {
		return new ResponseEntity<>(entidadeRepository.findAllByEstado(1, pageable), HttpStatus.OK);
	}

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
			return new ResponseEntity <>(null, getHttpHeaders(entidadeRepository.save(newEntity)), HttpStatus.CREATED);

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
