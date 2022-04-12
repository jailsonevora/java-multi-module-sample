package com.ine.sge.util;


import com.ine.sge.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

public class BindingSGEToUniverse {

	private final ISituacaoRepository situacaoRepository;
	private final IGeneroRepository generoRepository;
	private final IPaisRepository paisRepository;
	private final IFormaJuridicaRepository formaJuridicaRepository;
	private final IContabilidadeRepository contabilidadeRepository;
	private final ICanalRepository canalRepository;
	private final IActividadeComercialRepository actividadeComercialRepository;
	private final ISocioRepository iSocioRepository;

	@Autowired
	public BindingSGEToUniverse(ISituacaoRepository situacaoRepository, IGeneroRepository generoRepository, IPaisRepository paisRepository, IFormaJuridicaRepository formaJuridicaRepository, IContabilidadeRepository contabilidadeRepository, ICanalRepository canalRepository, IActividadeComercialRepository actividadeComercialRepository, ISocioRepository iSocioRepository) {
		this.situacaoRepository = situacaoRepository;
		this.generoRepository = generoRepository;
		this.paisRepository = paisRepository;
		this.formaJuridicaRepository = formaJuridicaRepository;
		this.contabilidadeRepository = contabilidadeRepository;
		this.canalRepository = canalRepository;
		this.actividadeComercialRepository = actividadeComercialRepository;
		this.iSocioRepository = iSocioRepository;
	}

	public com.ine.common.dto.UniverseDefaultSchema.Entidade Binding( com.ine.common.dto.UniverseDefaultSchema.Entidade<
			com.ine.sge.models.Socio,
			com.ine.sge.models.EmpresaParticipada,
			com.ine.sge.models.Estabelecimento<com.ine.sge.models.Contacto, com.ine.sge.models.CAE>,
			com.ine.sge.models.CAE,
			com.ine.sge.models.Contacto> UniverseDefaultSchema_Entidade,

            com.ine.sge.models.Entidade<
            com.ine.sge.models.Socio,
            com.ine.sge.models.EmpresaParticipada,
            com.ine.sge.models.Estabelecimento<com.ine.sge.models.Contacto, com.ine.sge.models.CAE>,
            com.ine.sge.models.CAE,
            com.ine.sge.models.Contacto,
		    com.ine.sge.models.TituloAcademico,
	        com.ine.sge.models.TipoEntidade,
            com.ine.sge.models.Sector> SGE_Entidade){

		//using ternary to validate

		//GENERO
		String genero = ( generoRepository.findByIdAndEstado(SGE_Entidade.getSexo(), 1).isPresent() ) ? generoRepository.findByIdAndEstado(SGE_Entidade.getSexo(), 1).get().getSexo() : null;

		//SITUACAO_EMPRESA
		String situacaoEmpresa = ( situacaoRepository.findByIdAndEstado(SGE_Entidade.getSituacaoEmpresa(), 1).isPresent() ) ? situacaoRepository.findByIdAndEstado(SGE_Entidade.getSituacaoEmpresa(), 1).get().getSta() : null;

		//PAIS
		String pais = ( paisRepository.findByIdAndEstado(SGE_Entidade.getPais(), 1).isPresent() ) ? paisRepository.findByIdAndEstado(SGE_Entidade.getPais(), 1).get().getPais() : null;

		//FORMA_JURIDICA
		String formaJuridica = ( formaJuridicaRepository.findByIdAndEstado(SGE_Entidade.getFormaJuridica(), 1).isPresent() ) ? formaJuridicaRepository.findByIdAndEstado(SGE_Entidade.getFormaJuridica(), 1).get().getFormaJuridica() : null;

		//CONTABILIDADE
		String contabilidade = ( contabilidadeRepository.findByIdAndEstado(SGE_Entidade.getContabilidade(), 1).isPresent() ) ? contabilidadeRepository.findByIdAndEstado(SGE_Entidade.getContabilidade(), 1).get().getCnta() : null;

		//CANAL
		String canal = ( canalRepository.findByIdAndEstado(SGE_Entidade.getCanal(), 1).isPresent() ) ? canalRepository.findByIdAndEstado(SGE_Entidade.getCanal(), 1).get().getCanal() : null;

		//ATIVIDADE_COMERCIAL
		String atividadeComercial = ( actividadeComercialRepository.findByIdAndEstado(SGE_Entidade.getActiComercial(), 1).isPresent() ) ? actividadeComercialRepository.findByIdAndEstado(SGE_Entidade.getActiComercial(), 1).get().getCac() : null;

		UniverseDefaultSchema_Entidade.setSexo(genero);
		UniverseDefaultSchema_Entidade.setSituacaoEmpresa(situacaoEmpresa);
		UniverseDefaultSchema_Entidade.setPais(pais);
		UniverseDefaultSchema_Entidade.setFormaJuridica(formaJuridica);
		UniverseDefaultSchema_Entidade.setContabilidade(contabilidade);
		UniverseDefaultSchema_Entidade.setCanal(canal);
		UniverseDefaultSchema_Entidade.setActiComercial(atividadeComercial);

		UniverseDefaultSchema_Entidade.setWebUrlGrupo(SGE_Entidade.getWebUrlGrupo());
		UniverseDefaultSchema_Entidade.setVolumeVendas(SGE_Entidade.getVolumeVendas());
		UniverseDefaultSchema_Entidade.setVolumeNegocioGrupo(SGE_Entidade.getVolumeNegocioGrupo());
		UniverseDefaultSchema_Entidade.setVolume_vendas(SGE_Entidade.getVolume_vendas());
		UniverseDefaultSchema_Entidade.setVersao(SGE_Entidade.getVersao());
		UniverseDefaultSchema_Entidade.setTituloAcademico(SGE_Entidade.getTituloAcademicoToString());
		UniverseDefaultSchema_Entidade.setTipoEntidade(SGE_Entidade.getTipoEntidadeToString());
		UniverseDefaultSchema_Entidade.setTelefoneIndGrupo(SGE_Entidade.getTelefoneIndGrupo());
		UniverseDefaultSchema_Entidade.setTelefoneGrupo(SGE_Entidade.getTelefoneGrupo());
		UniverseDefaultSchema_Entidade.setState(SGE_Entidade.getState());
		UniverseDefaultSchema_Entidade.setSocio(SGE_Entidade.getSocio());
		UniverseDefaultSchema_Entidade.setSituacaoActivGrupo(SGE_Entidade.getSituacaoActivGrupo());
		UniverseDefaultSchema_Entidade.setSiac(SGE_Entidade.getSiac());
		UniverseDefaultSchema_Entidade.setSector(SGE_Entidade.getSectorToString());
		UniverseDefaultSchema_Entidade.setProfissao(SGE_Entidade.getProfissao());
		UniverseDefaultSchema_Entidade.setPessoa(SGE_Entidade.getPessoa());
		UniverseDefaultSchema_Entidade.setOrigemDocGrupo(SGE_Entidade.getOrigemDocGrupo());
		UniverseDefaultSchema_Entidade.setNumRge(SGE_Entidade.getNumRge());
		UniverseDefaultSchema_Entidade.setNumPessoasServGrupo(SGE_Entidade.getNumPessoasServGrupo());
		UniverseDefaultSchema_Entidade.setNumFuncRemNac(SGE_Entidade.getNumFuncRemNac());
		UniverseDefaultSchema_Entidade.setNumFuncRemEst(SGE_Entidade.getNumFuncRemEst());
		UniverseDefaultSchema_Entidade.setNumFuncNRemNac(SGE_Entidade.getNumFuncNRemNac());
		UniverseDefaultSchema_Entidade.setNumFuncNRemEst(SGE_Entidade.getNumFuncNRemEst());
		UniverseDefaultSchema_Entidade.setNumFuncNrem(SGE_Entidade.getNumFuncNrem());
		UniverseDefaultSchema_Entidade.setNumFuncNac(SGE_Entidade.getNumFuncNac());
		UniverseDefaultSchema_Entidade.setNumFuncMulherRem(SGE_Entidade.getNumFuncMulherRem());
		UniverseDefaultSchema_Entidade.setNumFuncMulherNrem(SGE_Entidade.getNumFuncMulherNrem());
		UniverseDefaultSchema_Entidade.setNumFuncMRemNac(SGE_Entidade.getNumFuncMRemNac());
		UniverseDefaultSchema_Entidade.setNumFuncMRemEst(SGE_Entidade.getNumFuncMRemEst());
		UniverseDefaultSchema_Entidade.setNumFuncMNRemNac(SGE_Entidade.getNumFuncMNRemNac());
		UniverseDefaultSchema_Entidade.setNumFuncMNRemEst(SGE_Entidade.getNumFuncMNRemEst());
		UniverseDefaultSchema_Entidade.setNumFuncionarioMulher(SGE_Entidade.getNumFuncionarioMulher());
		UniverseDefaultSchema_Entidade.setNumFuncionarioMNac(SGE_Entidade.getNumFuncionarioMNac());
		UniverseDefaultSchema_Entidade.setNumFuncionarioMEst(SGE_Entidade.getNumFuncionarioMEst());
		UniverseDefaultSchema_Entidade.setNumFuncionarioHomem(SGE_Entidade.getNumFuncionarioHomem());
		UniverseDefaultSchema_Entidade.setNumFuncionarioHNac(SGE_Entidade.getNumFuncionarioHNac());
		UniverseDefaultSchema_Entidade.setNumFuncionarioHEst(SGE_Entidade.getNumFuncionarioHEst());
		UniverseDefaultSchema_Entidade.setNumFuncHRemNac(SGE_Entidade.getNumFuncHRemNac());
		UniverseDefaultSchema_Entidade.setNumFuncHRemEst(SGE_Entidade.getNumFuncHRemEst());
		UniverseDefaultSchema_Entidade.setNumFuncHomemRem(SGE_Entidade.getNumFuncHomemRem());
		UniverseDefaultSchema_Entidade.setNumFuncHomemNrem(SGE_Entidade.getNumFuncHomemNrem());
		UniverseDefaultSchema_Entidade.setNumFuncHNRemNac(SGE_Entidade.getNumFuncHNRemNac());
		UniverseDefaultSchema_Entidade.setNumFuncHNRemEst(SGE_Entidade.getNumFuncHNRemEst());
		UniverseDefaultSchema_Entidade.setNumFuncEst(SGE_Entidade.getNumFuncEst());
		UniverseDefaultSchema_Entidade.setNumEstabsTotal(SGE_Entidade.getNumEstabsTotal());
		UniverseDefaultSchema_Entidade.setNumEstabelecimentos(SGE_Entidade.getNumEstabelecimentos());
		UniverseDefaultSchema_Entidade.setNumEmpresasNacGrupo(SGE_Entidade.getNumEmpresasNacGrupo());
		UniverseDefaultSchema_Entidade.setNumEmpresasEstGrupo(SGE_Entidade.getNumEmpresasEstGrupo());
		UniverseDefaultSchema_Entidade.setNumDoc(SGE_Entidade.getNumDoc());
		UniverseDefaultSchema_Entidade.setNomeRespGrupo(SGE_Entidade.getNomeRespGrupo());
		UniverseDefaultSchema_Entidade.setNomeGrupo(SGE_Entidade.getNomeGrupo());
		UniverseDefaultSchema_Entidade.setNifGrupo(SGE_Entidade.getNifGrupo());
		UniverseDefaultSchema_Entidade.setNaturezaJurGrupo(SGE_Entidade.getNaturezaJurGrupo());
		UniverseDefaultSchema_Entidade.setNaturalId(SGE_Entidade.getNaturalId());
		UniverseDefaultSchema_Entidade.setMesesActividade(SGE_Entidade.getMesesActividade());
		UniverseDefaultSchema_Entidade.setLongitude(SGE_Entidade.getLongitude());
		UniverseDefaultSchema_Entidade.setLatitude(SGE_Entidade.getLatitude());
		UniverseDefaultSchema_Entidade.setInstituicao(SGE_Entidade.getInstituicao());
		UniverseDefaultSchema_Entidade.setIdade(SGE_Entidade.getIdade());
		UniverseDefaultSchema_Entidade.setGue(SGE_Entidade.getGue());
		UniverseDefaultSchema_Entidade.setGrupoHolding(SGE_Entidade.getGrupoHolding());
		UniverseDefaultSchema_Entidade.setFaxIndGrupo(SGE_Entidade.getFaxIndGrupo());
		UniverseDefaultSchema_Entidade.setFaxGrupo(SGE_Entidade.getFaxGrupo());
		UniverseDefaultSchema_Entidade.setEstadoAprovacao(SGE_Entidade.getEstadoAprovacao());
		UniverseDefaultSchema_Entidade.setEstabelecimento(SGE_Entidade.getEstabelecimento());
		UniverseDefaultSchema_Entidade.setEmpresaParticipada(SGE_Entidade.getEmpresaParticipada());
		UniverseDefaultSchema_Entidade.setEmailRespGrupo(SGE_Entidade.getEmailRespGrupo());
		UniverseDefaultSchema_Entidade.setEliminado(SGE_Entidade.getEliminado());
		UniverseDefaultSchema_Entidade.setDataultImaInsercao(SGE_Entidade.getDataultImaInsercao());
		UniverseDefaultSchema_Entidade.setDataSituacaoActividade(SGE_Entidade.getDataSituacaoActividade());
		UniverseDefaultSchema_Entidade.setDataSge(SGE_Entidade.getDataSge());
		UniverseDefaultSchema_Entidade.setDataInitactGrupo(SGE_Entidade.getDataInitactGrupo());
		UniverseDefaultSchema_Entidade.setDataFimGrupo(SGE_Entidade.getDataFimGrupo());
		UniverseDefaultSchema_Entidade.setDataFimActGrupo(SGE_Entidade.getDataFimActGrupo());
		UniverseDefaultSchema_Entidade.setDataDocumento(SGE_Entidade.getDataDocumento());
		UniverseDefaultSchema_Entidade.setDataCostituicaoFim(SGE_Entidade.getDataCostituicaoFim());
		UniverseDefaultSchema_Entidade.setDataConstGrupo(SGE_Entidade.getDataConstGrupo());
		UniverseDefaultSchema_Entidade.setContacto(SGE_Entidade.getContacto());
		UniverseDefaultSchema_Entidade.setComentarioGrupo(SGE_Entidade.getComentarioGrupo());
		UniverseDefaultSchema_Entidade.setComentarioEntidade(SGE_Entidade.getComentarioEntidade());
		UniverseDefaultSchema_Entidade.setCodGrupo(SGE_Entidade.getCodGrupo());
		UniverseDefaultSchema_Entidade.setCapPublico(SGE_Entidade.getCapPublico());
		UniverseDefaultSchema_Entidade.setCapPrivado(SGE_Entidade.getCapPrivado());
		UniverseDefaultSchema_Entidade.setCapitalSocial(SGE_Entidade.getCapitalSocial());
		UniverseDefaultSchema_Entidade.setCapEstrang(SGE_Entidade.getCapEstrang());
		UniverseDefaultSchema_Entidade.setCae(SGE_Entidade.getCae());
		UniverseDefaultSchema_Entidade.setCaePrincipalGrupo(SGE_Entidade.getCaePrincipalGrupo());
		UniverseDefaultSchema_Entidade.setBi(SGE_Entidade.getBi());
		UniverseDefaultSchema_Entidade.setAnoEliminacao(SGE_Entidade.getAnoEliminacao());
		UniverseDefaultSchema_Entidade.setAnoActividade(SGE_Entidade.getAnoActividade());
		UniverseDefaultSchema_Entidade.setActiEcoPrincipal(SGE_Entidade.getActiEcoPrincipal());
		UniverseDefaultSchema_Entidade.setAbreviatura(SGE_Entidade.getAbreviatura());

		return UniverseDefaultSchema_Entidade;
	}

}
