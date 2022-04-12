package com.ine.common.dto.UniverseDefaultSchema;




import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.ine.common.interfaces.dto.*;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Entidade<
		ISocio,
		IEmpresaParticipada,
		IEstabelecimento,
		ICAE ,
		IContacto>
		extends com.ine.common.dto.UniverseDefaultSchema.AuditModel<String>
		implements IEntidade<ISocio, IEmpresaParticipada, IEstabelecimento, ICAE, IContacto>,
		ITituloAcademico_ISP_Universe,
		ITipoEntidade_ISP_Universe,
		IGenero_ISP_Universe,
		ISector_ISP_Universe,
		ISituacao_ISP_Universe,
		IFormaJuridica_ISP_Universe,
		IContabilidade_ISP_Universe,
		IActividadeComercial_ISP_Universe,
		IOrigemDocumento_ISP_Universe,
		ICanal_ISP_Universe,
		IPais_ISP_Universe,
		Serializable
{

	@JsonProperty("porRenovar")
	private boolean porRenovar;

	@JsonProperty("naturalId")
	private long naturalId;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("abreviatura")
	private String abreviatura;


	// region OneToOne
	@JsonProperty("contacto")
	private IContacto contacto;

	// endregion OneToOne

	// region ManyToMany

	//FUE_ENTIDADE$SOCIOS
	//ManyToMany
	@JsonProperty("socio")
	private Set<ISocio> socio = new HashSet<>();

	//FUE_ENTIDADE$ESTABELECIMENTOS
	//ManyToMany
	@JsonProperty("estabelecimento")
	private Set<IEstabelecimento> estabelecimento = new HashSet<>();

	//FUE_ENTIDADE$EMPSPARTICIP
	//ManyToMany
	@JsonProperty("empresaParticipada")
	private Set<IEmpresaParticipada> empresaParticipada = new HashSet<>();



	//FUE_ENTIDADE$CAE_SEC
	//ManyToMany
	@JsonProperty("cae")
	private Set<ICAE> cae = new HashSet<>();

	// endregion ManyToMany


	// region Entidades Simplificadas
	@JsonProperty("tituloAcademico")
	private String tituloAcademico;

	@JsonProperty("tipoEntidade")
	private String tipoEntidade;

	@JsonProperty("sector")
	private String sector;

	@JsonProperty("sexo")
	private String sexo;

	@JsonProperty("situacaoEmpresa")
	private String situacaoEmpresa;

	@JsonProperty("contabilidade")
	private String contabilidade;

	@JsonProperty("actiComercial")
	private String actiComercial;

	@JsonProperty("origemDoc")
	private String origemDoc;

	@JsonProperty("canal")
	private String canal;

	@JsonProperty("paisOrigem")
	private String paisOrigem;

	@JsonProperty("formaJuridica")
	private String formaJuridica;

	@JsonProperty("actiEcoPrincipal")
	private long actiEcoPrincipal;

	// endregion

	@JsonProperty("situacaoActivGrupo")
	private long situacaoActivGrupo;

	@JsonProperty("naturezaJurGrupo")
	private long naturezaJurGrupo;

	@JsonProperty("caePrincipalGrupo")
	private long caePrincipalGrupo;

	@JsonProperty("origemDocGrupo")
	private long origemDocGrupo;

	@JsonProperty("idade")
	private int idade;

	@JsonProperty("volumeVendas")
	private float volumeVendas;

	@JsonProperty("profissao")
	private String profissao;

	@JsonProperty("instituicao")
	private String instituicao;

	@JsonProperty("pessoa")
	private String pessoa;

	@JsonProperty("numFuncionario")
	private float numFuncionario;

	@JsonProperty("numFuncionarioHomem")
	private float numFuncionarioHomem;

	@JsonProperty("numFuncionarioMulher")
	private float numFuncionarioMulher;

	@JsonProperty("estado")
	private int estado = 1;

	@JsonProperty("estadoAprovacao")
	private float estadoAprovacao;

	@JsonProperty("dataCostituicao")
	private java.util.Date dataCostituicao;

	@JsonProperty("dataCostituicaoFim")
	private java.util.Date dataCostituicaoFim;

	@JsonProperty("versao")
	private int versao;

	@JsonProperty("capitalSocial")
	private float capitalSocial;

	@JsonProperty("capPublico")
	private float capPublico;

	@JsonProperty("capPrivado")
	private float capPrivado;

	@JsonProperty("capEstrang")
	private float capEstrang;

	@JsonProperty("dataDocumento")
	private java.util.Date dataDocumento;

	@JsonProperty("numEstabelecimentos")
	private float numEstabelecimentos;

	@JsonProperty("nif")
	private String nif;

	@JsonProperty("numDoc")
	private float numDoc;

	@JsonProperty("eliminado")
	private String eliminado;

	@JsonProperty("anoEliminacao")
	private long anoEliminacao;

	@JsonProperty("state")
	private float state;

	@JsonProperty("bi")
	private String bi;

	@JsonProperty("numRge")
	private String numRge;

	@JsonProperty("numFuncRem")
	private float numFuncRem;

	@JsonProperty("numFuncHomemRem")
	private float numFuncHomemRem;

	@JsonProperty("numFuncMulherRem")
	private float numFuncMulherRem;

	@JsonProperty("numFuncNrem")
	private float numFuncNrem;

	@JsonProperty("numFuncHomemNrem")
	private float numFuncHomemNrem;

	@JsonProperty("numFuncMulherNrem")
	private float numFuncMulherNrem;

	@JsonProperty("dataSge")
	private java.util.Date dataSge;

	@JsonProperty("comentarioEntidade")
	private String comentarioEntidade;

	@JsonProperty("dataSituacaoActividade")
	private java.util.Date dataSituacaoActividade;

	@JsonProperty("grupoHolding")
	private String grupoHolding;

	@JsonProperty("codGrupo")
	private String codGrupo;

	@JsonProperty("dataConstGrupo")
	private java.util.Date dataConstGrupo;

	@JsonProperty("dataFimGrupo")
	private java.util.Date dataFimGrupo;

	@JsonProperty("nomeGrupo")
	private String nomeGrupo;

	@JsonProperty("nifGrupo")
	private String nifGrupo;

	@JsonProperty("dataInitactGrupo")
	private java.util.Date dataInitactGrupo;

	@JsonProperty("dataFimActGrupo")
	private java.util.Date dataFimActGrupo;

	@JsonProperty("numEmpresasNacGrupo")
	private float numEmpresasNacGrupo;

	@JsonProperty("numEmpresasEstGrupo")
	private float numEmpresasEstGrupo;

	@JsonProperty("numPessoasServGrupo")
	private float numPessoasServGrupo;

	@JsonProperty("volumeNegocioGrupo")
	private float volumeNegocioGrupo;

	@JsonProperty("nomeRespGrupo")
	private String nomeRespGrupo;

	@JsonProperty("telefoneIndGrupo")
	private String telefoneIndGrupo;

	@JsonProperty("telefoneGrupo")
	private String telefoneGrupo;

	@JsonProperty("faxGrupo")
	private String faxGrupo;

	@JsonProperty("faxIndGrupo")
	private String faxIndGrupo;

	@JsonProperty("emailRespGrupo")
	private String emailRespGrupo;

	@JsonProperty("webUrlGrupo")
	private String webUrlGrupo;

	@JsonProperty("comentarioGrupo")
	private String comentarioGrupo;

	@JsonProperty("dataultImaInsercao")
	private java.util.Date dataultImaInsercao;

	@JsonProperty("numEstabsTotal")
	private float numEstabsTotal;

	@JsonProperty("numFuncNac")
	private float numFuncNac;

	@JsonProperty("numFuncEst")
	private float numFuncEst;

	@JsonProperty("numFuncionarioHNac")
	private float numFuncionarioHNac;

	@JsonProperty("numFuncionarioHEst")
	private float numFuncionarioHEst;

	@JsonProperty("numFuncionarioMNac")
	private float numFuncionarioMNac;

	@JsonProperty("numFuncionarioMEst")
	private float numFuncionarioMEst;

	@JsonProperty("numFuncRemNac")
	private float numFuncRemNac;

	@JsonProperty("numFuncRemEst")
	private float numFuncRemEst;

	@JsonProperty("numFuncHRemNac")
	private float numFuncHRemNac;

	@JsonProperty("numFuncHRemEst")
	private float numFuncHRemEst;

	@JsonProperty("numFuncMRemNac")
	private float numFuncMRemNac;

	@JsonProperty("numFuncMRemEst")
	private float numFuncMRemEst;

	@JsonProperty("numFuncNRemNac")
	private float numFuncNRemNac;

	@JsonProperty("numFuncNRemEst")
	private float numFuncNRemEst;

	@JsonProperty("numFuncHNRemNac")
	private float numFuncHNRemNac;

	@JsonProperty("numFuncHNRemEst")
	private float numFuncHNRemEst;

	@JsonProperty("numFuncMNRemNac")
	private float numFuncMNRemNac;

	@JsonProperty("numFuncMNRemEst")
	private float numFuncMNRemEst;

	@JsonProperty("latitude")
	private String latitude;

	@JsonProperty("longitude")
	private String longitude;

	@JsonProperty("anoActividade")
	private float anoActividade;

	@JsonProperty("mesesActividade")
	private float mesesActividade;

	@JsonProperty("gue")
	private String gue;

	@JsonProperty("siac")
	private String siac;

	@JsonProperty("webUrl")
	private String webUrl;

	//region accessors for public property

	public String getTituloAcademico() {
		return tituloAcademico;
	}

	public void setTituloAcademico(String tituloAcademico) {
		this.tituloAcademico = tituloAcademico;
	}

	public String getTipoEntidade() {
		return tipoEntidade;
	}

	public void setTipoEntidade(String tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
	}

	public float getVolumeVendas() {
		return volumeVendas;
	}

	public void setVolumeVendas(float volumeVendas) {
		this.volumeVendas = volumeVendas;
	}

	public Date getDataCostituicaoFim() {
		return dataCostituicaoFim;
	}

	public void setDataCostituicaoFim(Date dataCostituicaoFim) {
		this.dataCostituicaoFim = dataCostituicaoFim;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public long getNaturalId() {
		return naturalId;
	}

	public void setNaturalId(long naturalId) {
		this.naturalId = naturalId;
	}

	public Set <ISocio> getSocio() {
		return socio;
	}

	public void setSocio(Set <ISocio> socio) {
		this.socio = socio;
	}

	public Set <IEmpresaParticipada> getEmpresaParticipada() {
		return empresaParticipada;
	}

	public void setEmpresaParticipada(Set <IEmpresaParticipada> empresaParticipada) {
		this.empresaParticipada = empresaParticipada;
	}

	public Set <ICAE> getCae() {
		return cae;
	}

	public void setCae(Set <ICAE> cae) {
		this.cae = cae;
	}

	public Set <IEstabelecimento> getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Set <IEstabelecimento> estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAbreviatura() {
		return abreviatura;
	}


	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public int getIdade() {
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}


	public float getVolume_vendas() {
		return volumeVendas;
	}


	public void setVolume_vendas(float volume_vendas) {
		this.volumeVendas = volume_vendas;
	}


	public String getSector() {
		return sector;
	}


	public void setSector(String sector) {
		this.sector = sector;
	}


	public String getProfissao() {
		return profissao;
	}


	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}


	public String getInstituicao() {
		return instituicao;
	}


	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}


	public String getPessoa() {
		return pessoa;
	}


	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}


	public float getNumFuncionario() {
		return numFuncionario;
	}


	public void setNumFuncionario(float numFuncionario) {
		this.numFuncionario = numFuncionario;
	}


	public float getNumFuncionarioHomem() {
		return numFuncionarioHomem;
	}


	public void setNumFuncionarioHomem(float numFuncionarioHomem) {
		this.numFuncionarioHomem = numFuncionarioHomem;
	}


	public float getNumFuncionarioMulher() {
		return numFuncionarioMulher;
	}


	public void setNumFuncionarioMulher(float numFuncionarioMulher) {
		this.numFuncionarioMulher = numFuncionarioMulher;
	}


	public IContacto getContacto() {
		return contacto;
	}


	public void setContacto(IContacto contacto) {
		this.contacto = contacto;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public float getEstadoAprovacao() {
		return estadoAprovacao;
	}


	public void setEstadoAprovacao(float estadoAprovacao) {
		this.estadoAprovacao = estadoAprovacao;
	}


	public java.util.Date getDataCostituicao() {
		return dataCostituicao;
	}


	public void setDataCostituicao(java.util.Date dataCostituicao) {
		this.dataCostituicao = dataCostituicao;
	}


	public String getSituacaoEmpresa() {
		return situacaoEmpresa;
	}


	public void setSituacaoEmpresa(String situacaoEmpresa) {
		this.situacaoEmpresa = situacaoEmpresa;
	}


	public String getFormaJuridica() {
		return formaJuridica;
	}


	public void setFormaJuridica(String formaJuridica) {
		this.formaJuridica = formaJuridica;
	}


	public float getCapitalSocial() {
		return capitalSocial;
	}


	public void setCapitalSocial(float capitalSocial) {
		this.capitalSocial = capitalSocial;
	}


	public String getContabilidade() {
		return contabilidade;
	}


	public void setContabilidade(String contabilidade) {
		this.contabilidade = contabilidade;
	}


	public float getCapPublico() {
		return capPublico;
	}


	public void setCapPublico(float capPublico) {
		this.capPublico = capPublico;
	}


	public float getCapPrivado() {
		return capPrivado;
	}


	public void setCapPrivado(float capPrivado) {
		this.capPrivado = capPrivado;
	}


	public float getCapEstrang() {
		return capEstrang;
	}


	public void setCapEstrang(float capEstrang) {
		this.capEstrang = capEstrang;
	}


	public String getActiComercial() {
		return actiComercial;
	}


	public void setActiComercial(String actiComercial) {
		this.actiComercial = actiComercial;
	}


	public java.util.Date getDataDocumento() {
		return dataDocumento;
	}


	public void setDataDocumento(java.util.Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}


	public float getNumEstabelecimentos() {
		return numEstabelecimentos;
	}


	public void setNumEstabelecimentos(float numEstabelecimentos) {
		this.numEstabelecimentos = numEstabelecimentos;
	}


	public long getActiEcoPrincipal() {
		return actiEcoPrincipal;
	}


	public void setActiEcoPrincipal(long actiEcoPrincipal) {
		this.actiEcoPrincipal = actiEcoPrincipal;
	}


	public String getOrigemDoc() {
		return origemDoc;
	}


	public void setOrigemDoc(String origemDoc) {
		this.origemDoc = origemDoc;
	}


	public String getCanal() {
		return canal;
	}


	public void setCanal(String canal) {
		this.canal = canal;
	}


	public String getNif() {
		return nif;
	}


	public void setNif(String nif) {
		this.nif = nif;
	}


	public float getNumDoc() {
		return numDoc;
	}


	public void setNumDoc(float numDoc) {
		this.numDoc = numDoc;
	}


	public String getEliminado() {
		return eliminado;
	}


	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}


	public long getAnoEliminacao() {
		return anoEliminacao;
	}


	public void setAnoEliminacao(long anoEliminacao) {
		this.anoEliminacao = anoEliminacao;
	}


	public float getState() {
		return state;
	}


	public void setState(float state) {
		this.state = state;
	}


	public String getBi() {
		return bi;
	}


	public void setBi(String bi) {
		this.bi = bi;
	}


	public String getNumRge() {
		return numRge;
	}


	public void setNumRge(String numRge) {
		this.numRge = numRge;
	}


	public float getNumFuncRem() {
		return numFuncRem;
	}


	public void setNumFuncRem(float numFuncRem) {
		this.numFuncRem = numFuncRem;
	}


	public float getNumFuncHomemRem() {
		return numFuncHomemRem;
	}


	public void setNumFuncHomemRem(float numFuncHomemRem) {
		this.numFuncHomemRem = numFuncHomemRem;
	}


	public float getNumFuncMulherRem() {
		return numFuncMulherRem;
	}


	public void setNumFuncMulherRem(float numFuncMulherRem) {
		this.numFuncMulherRem = numFuncMulherRem;
	}


	public float getNumFuncNrem() {
		return numFuncNrem;
	}


	public void setNumFuncNrem(float numFuncNrem) {
		this.numFuncNrem = numFuncNrem;
	}


	public float getNumFuncHomemNrem() {
		return numFuncHomemNrem;
	}


	public void setNumFuncHomemNrem(float numFuncHomemNrem) {
		this.numFuncHomemNrem = numFuncHomemNrem;
	}


	public float getNumFuncMulherNrem() {
		return numFuncMulherNrem;
	}


	public void setNumFuncMulherNrem(float numFuncMulherNrem) {
		this.numFuncMulherNrem = numFuncMulherNrem;
	}


	public java.util.Date getDataSge() {
		return dataSge;
	}


	public void setDataSge(java.util.Date dataSge) {
		this.dataSge = dataSge;
	}


	public String getComentarioEntidade() {
		return comentarioEntidade;
	}


	public void setComentarioEntidade(String comentarioEntidade) {
		this.comentarioEntidade = comentarioEntidade;
	}


	public java.util.Date getDataSituacaoActividade() {
		return dataSituacaoActividade;
	}


	public void setDataSituacaoActividade(java.util.Date dataSituacaoActividade) {
		this.dataSituacaoActividade = dataSituacaoActividade;
	}


	public String getGrupoHolding() {
		return grupoHolding;
	}


	public void setGrupoHolding(String grupoHolding) {
		this.grupoHolding = grupoHolding;
	}


	public String getCodGrupo() {
		return codGrupo;
	}


	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}


	public java.util.Date getDataConstGrupo() {
		return dataConstGrupo;
	}


	public void setDataConstGrupo(java.util.Date dataConstGrupo) {
		this.dataConstGrupo = dataConstGrupo;
	}


	public java.util.Date getDataFimGrupo() {
		return dataFimGrupo;
	}


	public void setDataFimGrupo(java.util.Date dataFimGrupo) {
		this.dataFimGrupo = dataFimGrupo;
	}


	public String getNomeGrupo() {
		return nomeGrupo;
	}


	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}


	public String getNifGrupo() {
		return nifGrupo;
	}


	public void setNifGrupo(String nifGrupo) {
		this.nifGrupo = nifGrupo;
	}


	public long getSituacaoActivGrupo() {
		return situacaoActivGrupo;
	}


	public void setSituacaoActivGrupo(long situacaoActivGrupo) {
		this.situacaoActivGrupo = situacaoActivGrupo;
	}


	public java.util.Date getDataInitactGrupo() {
		return dataInitactGrupo;
	}


	public void setDataInitactGrupo(java.util.Date dataInitactGrupo) {
		this.dataInitactGrupo = dataInitactGrupo;
	}


	public java.util.Date getDataFimActGrupo() {
		return dataFimActGrupo;
	}


	public void setDataFimActGrupo(java.util.Date dataFimActGrupo) {
		this.dataFimActGrupo = dataFimActGrupo;
	}


	public long getNaturezaJurGrupo() {
		return naturezaJurGrupo;
	}


	public void setNaturezaJurGrupo(long naturezaJurGrupo) {
		this.naturezaJurGrupo = naturezaJurGrupo;
	}


	public long getCaePrincipalGrupo() {
		return caePrincipalGrupo;
	}


	public void setCaePrincipalGrupo(long caePrincipalGrupo) {
		this.caePrincipalGrupo = caePrincipalGrupo;
	}


	public float getNumEmpresasNacGrupo() {
		return numEmpresasNacGrupo;
	}


	public void setNumEmpresasNacGrupo(float numEmpresasNacGrupo) {
		this.numEmpresasNacGrupo = numEmpresasNacGrupo;
	}


	public float getNumEmpresasEstGrupo() {
		return numEmpresasEstGrupo;
	}


	public void setNumEmpresasEstGrupo(float numEmpresasEstGrupo) {
		this.numEmpresasEstGrupo = numEmpresasEstGrupo;
	}


	public float getNumPessoasServGrupo() {
		return numPessoasServGrupo;
	}


	public void setNumPessoasServGrupo(float numPessoasServGrupo) {
		this.numPessoasServGrupo = numPessoasServGrupo;
	}


	public float getVolumeNegocioGrupo() {
		return volumeNegocioGrupo;
	}


	public void setVolumeNegocioGrupo(float volumeNegocioGrupo) {
		this.volumeNegocioGrupo = volumeNegocioGrupo;
	}


	public String getNomeRespGrupo() {
		return nomeRespGrupo;
	}


	public void setNomeRespGrupo(String nomeRespGrupo) {
		this.nomeRespGrupo = nomeRespGrupo;
	}


	public String getTelefoneIndGrupo() {
		return telefoneIndGrupo;
	}


	public void setTelefoneIndGrupo(String telefoneIndGrupo) {
		this.telefoneIndGrupo = telefoneIndGrupo;
	}


	public String getTelefoneGrupo() {
		return telefoneGrupo;
	}


	public void setTelefoneGrupo(String telefoneGrupo) {
		this.telefoneGrupo = telefoneGrupo;
	}


	public String getFaxGrupo() {
		return faxGrupo;
	}


	public void setFaxGrupo(String faxGrupo) {
		this.faxGrupo = faxGrupo;
	}


	public String getFaxIndGrupo() {
		return faxIndGrupo;
	}


	public void setFaxIndGrupo(String faxIndGrupo) {
		this.faxIndGrupo = faxIndGrupo;
	}


	public String getEmailRespGrupo() {
		return emailRespGrupo;
	}


	public void setEmailRespGrupo(String emailRespGrupo) {
		this.emailRespGrupo = emailRespGrupo;
	}


	public String getWebUrlGrupo() {
		return webUrlGrupo;
	}


	public void setWebUrlGrupo(String webUrlGrupo) {
		this.webUrlGrupo = webUrlGrupo;
	}


	public long getOrigemDocGrupo() {
		return origemDocGrupo;
	}


	public void setOrigemDocGrupo(long origemDocGrupo) {
		this.origemDocGrupo = origemDocGrupo;
	}


	public String getComentarioGrupo() {
		return comentarioGrupo;
	}


	public void setComentarioGrupo(String comentarioGrupo) {
		this.comentarioGrupo = comentarioGrupo;
	}


	public java.util.Date getDataultImaInsercao() {
		return dataultImaInsercao;
	}


	public void setDataultImaInsercao(java.util.Date dataultImaInsercao) {
		this.dataultImaInsercao = dataultImaInsercao;
	}


	public float getNumEstabsTotal() {
		return numEstabsTotal;
	}


	public void setNumEstabsTotal(float numEstabsTotal) {
		this.numEstabsTotal = numEstabsTotal;
	}


	public float getNumFuncNac() {
		return numFuncNac;
	}


	public void setNumFuncNac(float numFuncNac) {
		this.numFuncNac = numFuncNac;
	}


	public float getNumFuncEst() {
		return numFuncEst;
	}


	public void setNumFuncEst(float numFuncEst) {
		this.numFuncEst = numFuncEst;
	}


	public float getNumFuncionarioHNac() {
		return numFuncionarioHNac;
	}


	public void setNumFuncionarioHNac(float numFuncionarioHNac) {
		this.numFuncionarioHNac = numFuncionarioHNac;
	}


	public float getNumFuncionarioHEst() {
		return numFuncionarioHEst;
	}


	public void setNumFuncionarioHEst(float numFuncionarioHEst) {
		this.numFuncionarioHEst = numFuncionarioHEst;
	}


	public float getNumFuncionarioMNac() {
		return numFuncionarioMNac;
	}


	public void setNumFuncionarioMNac(float numFuncionarioMNac) {
		this.numFuncionarioMNac = numFuncionarioMNac;
	}


	public float getNumFuncionarioMEst() {
		return numFuncionarioMEst;
	}


	public void setNumFuncionarioMEst(float numFuncionarioMEst) {
		this.numFuncionarioMEst = numFuncionarioMEst;
	}


	public float getNumFuncRemNac() {
		return numFuncRemNac;
	}


	public void setNumFuncRemNac(float numFuncRemNac) {
		this.numFuncRemNac = numFuncRemNac;
	}


	public float getNumFuncRemEst() {
		return numFuncRemEst;
	}


	public void setNumFuncRemEst(float numFuncRemEst) {
		this.numFuncRemEst = numFuncRemEst;
	}


	public float getNumFuncHRemNac() {
		return numFuncHRemNac;
	}


	public void setNumFuncHRemNac(float numFuncHRemNac) {
		this.numFuncHRemNac = numFuncHRemNac;
	}


	public float getNumFuncHRemEst() {
		return numFuncHRemEst;
	}


	public void setNumFuncHRemEst(float numFuncHRemEst) {
		this.numFuncHRemEst = numFuncHRemEst;
	}


	public float getNumFuncMRemNac() {
		return numFuncMRemNac;
	}


	public void setNumFuncMRemNac(float numFuncMRemNac) {
		this.numFuncMRemNac = numFuncMRemNac;
	}


	public float getNumFuncMRemEst() {
		return numFuncMRemEst;
	}


	public void setNumFuncMRemEst(float numFuncMRemEst) {
		this.numFuncMRemEst = numFuncMRemEst;
	}


	public float getNumFuncNRemNac() {
		return numFuncNRemNac;
	}


	public void setNumFuncNRemNac(float numFuncNRemNac) {
		this.numFuncNRemNac = numFuncNRemNac;
	}


	public float getNumFuncNRemEst() {
		return numFuncNRemEst;
	}


	public void setNumFuncNRemEst(float numFuncNRemEst) {
		this.numFuncNRemEst = numFuncNRemEst;
	}


	public float getNumFuncHNRemNac() {
		return numFuncHNRemNac;
	}


	public void setNumFuncHNRemNac(float numFuncHNRemNac) {
		this.numFuncHNRemNac = numFuncHNRemNac;
	}


	public float getNumFuncHNRemEst() {
		return numFuncHNRemEst;
	}


	public void setNumFuncHNRemEst(float numFuncHNRemEst) {
		this.numFuncHNRemEst = numFuncHNRemEst;
	}


	public float getNumFuncMNRemNac() {
		return numFuncMNRemNac;
	}


	public void setNumFuncMNRemNac(float numFuncMNRemNac) {
		this.numFuncMNRemNac = numFuncMNRemNac;
	}


	public float getNumFuncMNRemEst() {
		return numFuncMNRemEst;
	}


	public void setNumFuncMNRemEst(float numFuncMNRemEst) {
		this.numFuncMNRemEst = numFuncMNRemEst;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public float getAnoActividade() {
		return anoActividade;
	}


	public void setAnoActividade(float anoActividade) {
		this.anoActividade = anoActividade;
	}


	public float getMesesActividade() {
		return mesesActividade;
	}


	public void setMesesActividade(float mesesActividade) {
		this.mesesActividade = mesesActividade;
	}


	public String getGue() {
		return gue;
	}


	public void setGue(String gue) {
		this.gue = gue;
	}


	public String getSiac() {
		return siac;
	}


	public void setSiac(String siac) {
		this.siac = siac;
	}


	public String getWebUrl() {
		return webUrl;
	}


	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}


	public String getPais() {
		return paisOrigem;
	}


	public void setPais(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}


	public boolean isPorRenovar() {
		return porRenovar;
	}


	public void setPorRenovar(boolean porRenovar) {
		this.porRenovar = porRenovar;
	}

	//endregion
}
