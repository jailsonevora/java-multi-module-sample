package com.ine.universe.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IActividadeComercial_ISP_Universe;
import com.ine.common.interfaces.dto.ICanal_ISP_Universe;
import com.ine.common.interfaces.dto.IContabilidade_ISP_Universe;
import com.ine.common.interfaces.dto.IEntidade;
import com.ine.common.interfaces.dto.IFormaJuridica_ISP_Universe;
import com.ine.common.interfaces.dto.IGenero_ISP_Universe;
import com.ine.common.interfaces.dto.IOrigemDocumento_ISP_Universe;
import com.ine.common.interfaces.dto.IPais_ISP_Universe;
import com.ine.common.interfaces.dto.ISector_ISP_Universe;
import com.ine.common.interfaces.dto.ISituacao_ISP_Universe;
import com.ine.common.interfaces.dto.ITipoEntidade_ISP_Universe;
import com.ine.common.interfaces.dto.ITituloAcademico_ISP_Universe;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
@Table(name="FUE_ENTIDADE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Entidade<
		ISocio extends com.ine.universe.models.Socio,
		IEmpresaParticipada extends com.ine.universe.models.EmpresaParticipada,
		IEstabelecimento extends com.ine.universe.models.Estabelecimento<IContacto, ICAE>,
		ICAE extends com.ine.universe.models.CAE,
		IContacto extends com.ine.universe.models.Contacto> extends com.ine.universe.models.AuditModel<String>
		implements
		IEntidade<ISocio, IEmpresaParticipada, IEstabelecimento, ICAE, IContacto>,
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
		Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_ENTIDADE", updatable = false, nullable = false)
	//@JsonIgnore
	private long id;

	//@NaturalId
	//@NotEmpty
	//@NotNull
	@Field
	@Column(name = "NATURAL_ID", updatable = false, nullable = false)
	private long naturalId;

	@Field
	@Column(name = "NOME")
	@Size(min = 1, max = 400)
	private String nome;

	@Field
	@Column(name = "ABREVIATURA")
	@Size(min = 1, max = 400)
	private String abreviatura;


	// region OneToOne

	@OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, targetEntity=com.ine.universe.models.Contacto.class)
	@JoinColumn(name = "CONTACTO", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private IContacto contacto;

	// endregion OneToOne

	// region ManyToMany

	//FUE_ENTIDADE$SOCIOS
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.ALL
			},
			targetEntity=com.ine.universe.models.Socio.class
			)
	@JoinTable(name = "FUE_ENTIDADE$SOCIOS",
			joinColumns = { @JoinColumn(name = "ID_ENTIDADE") },
			inverseJoinColumns = { @JoinColumn(name = "ID_SOCIO") })
	private Set<ISocio> socio = new HashSet<>();

	//FUE_ENTIDADE$ESTABELECIMENTOS
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.ALL
			},
			targetEntity=com.ine.universe.models.Estabelecimento.class)
	@JoinTable(name = "FUE_ENTIDADE$ESTABELECIMENTOS",
			joinColumns = { @JoinColumn(name = "ID_ENTIDADE") },
			inverseJoinColumns = { @JoinColumn(name = "ID_ESTABELE") })
	private Set<IEstabelecimento> estabelecimento = new HashSet<>();

	//FUE_ENTIDADE$EMPSPARTICIP
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.ALL
			},
			targetEntity=com.ine.universe.models.EmpresaParticipada.class
	)
	@JoinTable(name = "FUE_ENTIDADE$EMPSPARTICIP",
			joinColumns = { @JoinColumn(name = "ID_ENTIDADE") },
			inverseJoinColumns = { @JoinColumn(name = "ID_EMPAR") })
	private Set<IEmpresaParticipada> empresaParticipada = new HashSet<>();

	//FUE_ENTIDADE$CAE_SEC
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.ALL
			},
			targetEntity=com.ine.universe.models.CAE.class)
	@JoinTable(name = "FUE_ENTIDADE$CAE_SEC",
			joinColumns = { @JoinColumn(name = "ID_ENTIDADE") },
			inverseJoinColumns = { @JoinColumn(name = "ID_CAE") })
	private Set<ICAE> cae = new HashSet<>();

	// endregion ManyToMany


	// region Entidades Simplificadas

	@Column(name = "TITULO_ACADEMICO")
	@Size(max=50)
	private String tituloAcademico;

	@Column(name = "TIPO_ENTIDADE")
	@Size(max=20)
	private String tipoEntidade;

	@Column(name = "SECTOR")
	@Size(max=50)
	private String sector;

	@Size(max=20)
	@Column(name = "SEXO")
	private String sexo;

	@Size(max=50)
	@Column(name = "SITUACAO_EMPRESA")
	private String situacaoEmpresa;

	@Size(max=50)
	@Column(name = "CONTABILIDADE")
	private String contabilidade;

	@Size(max=50)
	@Column(name = "ACTI_COMERCIAL")
	private String actiComercial;

	@Size(max=50)
	@Column(name = "ORIGEM_DOC")
	private String origemDoc;

	@Size(max=50)
	@Column(name = "CANAL")
	private String canal;

	@Size(max=50)
	@Column(name = "PAIS_ORIGEM")
	private String paisOrigem;

	@Size(max=50)
	@Column(name = "FORMA_JURIDICA")
	private String formaJuridica;

	@Column(name = "ACTI_ECO_PRINCIPAL")
	private long actiEcoPrincipal;

	// endregion

	@Column(name = "Por_RENOVAR")
	private boolean porRenovar;

	@NotNull
	@Column(name = "SITUACAOACTIVGRUPO")
	private long situacaoActivGrupo;

	@NotNull
	@Column(name = "NATUREZAJURGRUPO")
	private long naturezaJurGrupo;

	@NotNull
	@Column(name = "CAEPRINCIPALGRUPO")
	private long caePrincipalGrupo;

	@Column(name = "ORIGEMDOCGRUPO")
	private long origemDocGrupo;


	@Field
	@Column(name = "IDADE")
	private int idade;

	@Field
	@Column(name = "VOLUME_VENDAS")
	private float volumeVendas;

	@Field
	@Column(name = "PROFISSAO")
	@Size(min = 1, max = 500)
	private String profissao;

	@Field
	@Column(name = "INSTITUICAO")
	@Size(min = 1, max = 1)
	private String instituicao;

	@Field
	@Column(name = "PESSOA")
	@Size(min = 1, max = 400)
	private String pessoa;

	@Field
	@Column(name = "NUM_FUNCIONARIO")
	private float numFuncionario;

	@Field
	@Column(name = "NUM_FUNCIONARIO_HOMEM")
	private float numFuncionarioHomem;

	@Field
	@Column(name = "NUM_FUNCIONARIO_MULHER")
	private float numFuncionarioMulher;

	@Column(name = "ESTADO")
	private int estado = 1;

	@Column(name = "ESTADO_APROVACAO")
	private float estadoAprovacao;

	//@Field
	@Column(name = "DATA_CONSTITUICAO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataCostituicao;


	@Column(name = "DATA_CONSTITUICAO_FIM")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataCostituicaoFim;


	@Column(name = "VERSAO_ENTIDADE")
	private int versao;
		

	@Column(name = "CAPITAL_SOCIAL")
	private float capitalSocial;


	@Column(name = "CAP_PUBLICO")
	private float capPublico;

	@Column(name = "CAP_PRIVADO")
	private float capPrivado;

	@Column(name = "CAP_ESTRANG")
	private float capEstrang;

	@Field
	@Column(name = "DATA_DOCUMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataDocumento;


	@Column(name = "NUM_ESTABELECIMENTOS")
	private float numEstabelecimentos;


	@Field
	@Column(name = "NIF")
	@Size(min = 1, max = 50)
	private String nif;


	@Column(name = "NUM_DOC")
	private float numDoc;


	@Field
	@Column(name = "ELIMINADO")
	@Size(min = 1, max = 1)
	private String eliminado;
	

	@Column(name = "ANO_ELIMINACAO")
	private long anoEliminacao;
	
	
	@Column(name = "STATE")
	private float state;


	@Field
	@Column(name = "BI")
	@Size(max = 20)
	private String bi;

	@Field
	@Column(name = "NUM_RGE")
	@Size(max = 8)
	private String numRge;
	
	
	@Column(name = "NUM_FUNC_REM")
	private float numFuncRem;
	
	
	
	@Column(name = "NUM_FUNC_HOMEM_REM")
	private float numFuncHomemRem;
	
	
	
	@Column(name = "NUM_FUNC_MULHER_REM")
	private float numFuncMulherRem;
	

	@Column(name = "NUM_FUNC_NREM")
	private float numFuncNrem;
	
	@Column(name = "NUM_FUNC_HOMEM_NREM")
	private float numFuncHomemNrem;
	
	
	@Column(name = "NUM_FUNC_MULHER_NREM")
	private float numFuncMulherNrem;

	@Field
	@Column(name = "DATA_SGE")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataSge;

	@Field
	@Column(name = "COMENTARIOENT")
	@Size(max = 400)
	private String comentarioEntidade;

	@Field
	@Column(name = "DATASITUACAOACTIVIDADE")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataSituacaoActividade;

	@Field
	@Column(name = "GRUPOHOLDING")
	@Size(max = 1)
	private String grupoHolding;


	@Field
	@Column(name = "CODGRUPO")
	@Size(max = 4)
	private String codGrupo;

	@Field
	@Column(name = "DATACONSTGRUPO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataConstGrupo;


	@Field
	@Column(name = "DATAFIMGRUPO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataFimGrupo;


	@Field
	@Column(name = "NOMEGRUPO")
	@Size(max = 160)
	private String nomeGrupo;

	@Field
	@Column(name = "NIFGRUPO")
	@Size(max = 10)
	private String nifGrupo;

	//@Field
	@Column(name = "DATAINITACTGRUPO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataInitactGrupo;

	//@Field
	@Column(name = "DATAFIMACTGRUPO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataFimActGrupo;


	@Column(name = "NUMEMPRESASNACGRUPO")
	private float numEmpresasNacGrupo;
	

	@Column(name = "NUMEMPRESASESTGRUPO")
	private float numEmpresasEstGrupo;
	

	@Column(name = "NUMPESSOASSERVGRUPO")
	private float numPessoasServGrupo;
	

	@Column(name = "VOLUMENEGOCIOGRUPO")
	private float volumeNegocioGrupo;

	@Field
	@Column(name = "NOMERESPGRUPO")
	@Size(max = 160)
	private String nomeRespGrupo;


	@Field
	@Column(name = "TELEFONEINDGRUPO")
	@Size(max = 3)
	private String telefoneIndGrupo;

	@Field
	@Column(name = "TELEFONEGRUPO")
	@Size(max = 12)
	private String telefoneGrupo;


	@Field
	@Column(name = "FAXGRUPO")
	@Size(max = 12)
	private String faxGrupo;



	@Field
	@Column(name = "FAXINDGRUPO")
	@Size(max = 3)
	private String faxIndGrupo;


	@Field
	@Column(name = "EMAILRESPGRUPO")
	@Size(max = 60)
	private String emailRespGrupo;


	@Field
	@Column(name = "WEBURLGRUPO")
	@Size(max = 100)
	private String webUrlGrupo;


	@Field
	@Column(name = "COMENTARIOGRUPO")
	@Size(max = 400)
	private String comentarioGrupo;


	@Field
	@Column(name = "DATAULTIMAINSERCAO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataultImaInsercao;
	

	@Column(name = "NUM_ESTABS_TOTAL")
	private float numEstabsTotal;


	@Column(name = "NUM_FUNC_NAC")
	private float numFuncNac;
	

	@Column(name = "NUM_FUNC_EST")
	private float numFuncEst;
	

	@Column(name = "NUM_FUNCIONARIO_H_NAC")
	private float numFuncionarioHNac;
	

	@Column(name = "NUM_FUNCIONARIO_H_EST")
	private float numFuncionarioHEst;
	

	@Column(name = "NUM_FUNCIONARIO_M_NAC")
	private float numFuncionarioMNac;
	

	@Column(name = "NUM_FUNCIONARIO_M_EST")
	private float numFuncionarioMEst;


	@Column(name = "NUM_FUNC_REM_NAC")
	private float numFuncRemNac;
	

	@Column(name = "NUM_FUNC_REM_EST")
	private float numFuncRemEst;
	
	
	@Column(name = "NUM_FUNC_H_REM_NAC")
	private float numFuncHRemNac;
	

	@Column(name = "NUM_FUNC_H_REM_EST")
	private float numFuncHRemEst;

	@Column(name = "NUM_FUNC_M_REM_NAC")
	private float numFuncMRemNac;

	@Column(name = "NUM_FUNC_M_REM_EST")
	private float numFuncMRemEst;
	

	@Column(name = "NUM_FUNC_NREM_NAC")
	private float numFuncNRemNac;
	
	
	@Column(name = "NUM_FUNC_NREM_EST")
	private float numFuncNRemEst;
	
	

	@Column(name = "NUM_FUNC_H_NREM_NAC")
	private float numFuncHNRemNac;
	
	
	@Column(name = "NUM_FUNC_H_NREM_EST")
	private float numFuncHNRemEst;
	

	@Column(name = "NUM_FUNC_M_NREM_NAC")
	private float numFuncMNRemNac;


	@Column(name = "NUM_FUNC_M_NREM_EST")
	private float numFuncMNRemEst;

	@Field
	@Column(name = "LATITUDE")
	@Size(max = 100)
	private String latitude;

	@Field
	@Column(name = "LONGITUDE")
	@Size(max = 100)
	private String longitude;

	@Column(name = "ANO_ACTIVIDADE")
	private float anoActividade;
	

	@Column(name = "MESES_ACTIVIDADE")
	private float mesesActividade;

	@Field
	@Column(name = "GUE")
	@Size(max = 1)
	private String gue;

	@Field
	@Column(name = "SIAC")
	@Size(max = 1)
	private String siac;

	@Field
	@Column(name = "WEBURL")
	@Size(max = 100)
	private String webUrl;


	//region accessors for public property

	public boolean isPorRenovar() {
		return porRenovar;
	}

	public void setPorRenovar(boolean porRenovar) {
		this.porRenovar = porRenovar;
	}

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

	public void setId(long id) {
		this.id = id;
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

	public Long getId() {
		return id;
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
	//endregion
}
