package com.ine.sge.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.*;
import org.hibernate.annotations.NaturalId;
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
@Table(name="SGE_ENTIDADE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Entidade<
		ISocio extends com.ine.sge.models.Socio,
		IEmpresaParticipada extends com.ine.sge.models.EmpresaParticipada,
		IEstabelecimento extends com.ine.sge.models.Estabelecimento<IContacto, ICAE>,
		ICAE extends com.ine.sge.models.CAE,
		IContacto extends com.ine.sge.models.Contacto,
		ITituloAcademico extends com.ine.sge.models.TituloAcademico,
		ITipoEntidade extends com.ine.sge.models.TipoEntidade,
		ISector extends com.ine.sge.models.Sector>
		extends com.ine.sge.models.AuditModel<String>
		implements
		IEntidade<ISocio, IEmpresaParticipada, IEstabelecimento, ICAE, IContacto>,
		ITituloAcademico_ISP_SGE<ITituloAcademico>,
		ITipoEntidade_ISP_SGE<ITipoEntidade>,
		IGenero_ISP_SGE,
		ISector_ISP_SGE<ISector>,
		ISituacao_ISP_SGE,
		IFormaJuridica_ISP_SGE,
		IContabilidade_ISP_SGE,
		IActividadeComercial_ISP_SGE,
		IOrigemDocumento_ISP_SGE,
		ICanal_ISP_SGE,
		IPais_ISP_SGE,
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

	@OneToOne(fetch = FetchType.EAGER, targetEntity=com.ine.sge.models.TituloAcademico.class)
    @JoinColumn(name = "TITULO_ACADEMICO")
	private ITituloAcademico tituloAcademico;

	@OneToOne(fetch = FetchType.EAGER, targetEntity=com.ine.sge.models.Sector.class)
	@JoinColumn(name = "SECTOR")
	private ISector sector;

	@OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, targetEntity=com.ine.sge.models.Contacto.class)
	@JoinColumn(name = "CONTACTO", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private IContacto contacto;

	@OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, targetEntity=com.ine.sge.models.ContabilidadeGeral.class)
	@JoinColumn(name = "CONTABILIDADEGERAL")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ContabilidadeGeral contabilidadeGeral;

	// endregion OneToOne

	// region OneToMany

	/*@OneToMany(fetch = FetchType.LAZY,
            mappedBy = "entidade")
	@JsonIgnore
    private Set<Mensagem> mensagem = new HashSet<>();*/

	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity=com.ine.sge.models.TipoEntidade.class)
	@JoinColumn(name = "TIPO_ENTIDADE", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private ITipoEntidade tipoEntidade;

	// endregion OneToMany

	// region ManyToMany

	//FUE_ENTIDADE$SOCIOS
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.ALL
			},
			targetEntity=com.ine.sge.models.Socio.class
	)
	@JoinTable(name = "SGE_ENTIDADE$SOCIOS",
			joinColumns = { @JoinColumn(name = "ID_ENTIDADE") },
			inverseJoinColumns = { @JoinColumn(name = "ID_SOCIO") })
	private Set<ISocio> socio = new HashSet<>();

	//FUE_ENTIDADE$ESTABELECIMENTOS
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.ALL
			},
			targetEntity=com.ine.sge.models.Estabelecimento.class
	)
	@JoinTable(name = "SGE_ENTIDADE$ESTABELECIMENTOS",
			joinColumns = { @JoinColumn(name = "ID_ENTIDADE") },
			inverseJoinColumns = { @JoinColumn(name = "ID_ESTABELE") })
	private Set<IEstabelecimento> estabelecimento = new HashSet<>();

	//FUE_ENTIDADE$EMPSPARTICIP
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.ALL
			},
			targetEntity=com.ine.sge.models.EmpresaParticipada.class
	)
	@JoinTable(name = "SGE_ENTIDADE$EMPSPARTICIP",
			joinColumns = { @JoinColumn(name = "ID_ENTIDADE") },
			inverseJoinColumns = { @JoinColumn(name = "ID_EMPAR") })
	private Set<IEmpresaParticipada> empresaParticipada = new HashSet<>();

	//FUE_ENTIDADE$CAE_SEC
	@ManyToMany(fetch = FetchType.LAZY, targetEntity=com.ine.sge.models.CAE.class)
	@JoinTable(name = "SGE_ENTIDADE$CAE_SEC",
			joinColumns = { @JoinColumn(name = "ID_ENTIDADE") },
			inverseJoinColumns = { @JoinColumn(name = "ID_CAE") })
	private Set<ICAE> cae = new HashSet<>();

	/*@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "entidade")
	@JsonIgnore
    private Set<Estabelecimento> estabelecimento = new HashSet<>();
*/
	// endregion ManyToMany


	@Column(name = "SEXO")
	private long sexo;

	@Column(name = "IDADE")
	private int idade;

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

	@Column(name = "NUM_FUNCIONARIO")
	private float numFuncionario;


	@Column(name = "NUM_FUNCIONARIO_HOMEM")
	private float numFuncionarioHomem;

	@Column(name = "NUM_FUNCIONARIO_MULHER")
	private float numFuncionarioMulher;



	@Column(name = "ESTADO")
	private int estado = 1;
	
	

	@Column(name = "ESTADO_APROVACAO")
	private float estadoAprovacao;


	@Column(name = "Por_RENOVAR", columnDefinition = "BOOLEAN")
	private boolean porRenovar;


	@Column(name = "DATA_CONSTITUICAO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataCostituicao;


	@Column(name = "DATA_CONSTITUICAO_FIM")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataCostituicaoFim;


	@Column(name = "VERSAO_ENTIDADE")
	private int versao;


	@Column(name = "SITUACAO_EMPRESA")
	private long situacaoEmpresa;
	
	

	@Column(name = "FORMA_JURIDICA")
	private long formaJuridica;
		

	@Column(name = "CAPITAL_SOCIAL")
	private float capitalSocial;
	
	

	@Column(name = "CONTABILIDADE")
	private long contabilidade;
	
	

	@Column(name = "CAP_PUBLICO")
	private float capPublico;
	
	

	@Column(name = "CAP_PRIVADO")
	private float capPrivado;
	
	

	@Column(name = "CAP_ESTRANG")
	private float capEstrang;
	
	

	@Column(name = "ACTI_COMERCIAL")
	private long actiComercial;

	@Column(name = "DATA_DOCUMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataDocumento;


	@Column(name = "NUM_ESTABELECIMENTOS")
	private float numEstabelecimentos;
	

	@Column(name = "ACTI_ECO_PRINCIPAL")
	private long actiEcoPrincipal;
	
	

	@Column(name = "ORIGEM_DOC")
	private long origemDoc;
	
	

	@Column(name = "CANAL")
	private long canal;


	@Field
	@Column(name = "NIF")
	@Size(min = 1, max = 50)
	private String nif;


	@Column(name = "NUM_DOC")
	private float numDoc;


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

	@Column(name = "DATA_SGE")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataSge;

	@Field
	@Column(name = "COMENTARIOENT")
	@Size(max = 400)
	private String comentarioEntidade;

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

	@Column(name = "DATACONSTGRUPO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataConstGrupo;

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

	@NotNull
	@Column(name = "SITUACAOACTIVGRUPO")
	private long situacaoActivGrupo;

	@Column(name = "DATAINITACTGRUPO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataInitactGrupo;

	@Column(name = "DATAFIMACTGRUPO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataFimActGrupo;

	@NotNull
	@Column(name = "NATUREZAJURGRUPO")
	private long naturezaJurGrupo;
	
	
	@NotNull
	@Column(name = "CAEPRINCIPALGRUPO")
	private long caePrincipalGrupo;
	
	

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

	@Column(name = "TELEFONEINDGRUPO")
	@Size(max = 3)
	private String telefoneIndGrupo;

	@Column(name = "TELEFONEGRUPO")
	@Size(max = 12)
	private String telefoneGrupo;

	@Column(name = "FAXGRUPO")
	@Size(max = 12)
	private String faxGrupo;


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


	@Column(name = "ORIGEMDOCGRUPO")
	private long origemDocGrupo;

	@Field
	@Column(name = "COMENTARIOGRUPO")
	@Size(max = 400)
	private String comentarioGrupo;


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

	@Column(name = "PAIS_ORIGEM")
	private long paisOrigem;

	//region accessors for public property

	public boolean isPorRenovar() {
		return porRenovar;
	}

	public void setPorRenovar(boolean porRenovar) {
		this.porRenovar = porRenovar;
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

	public ITituloAcademico getTituloAcademico() {
		return tituloAcademico;
	}

	//@JsonIgnore
	public String getTituloAcademicoToString() {
		return tituloAcademico.getTitulo();
	}

	public void setTituloAcademico(ITituloAcademico tituloAcademico) {
		this.tituloAcademico = tituloAcademico;
	}

	public long getSexo() {
		return sexo;
	}


	public void setSexo(long sexo) {
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


	public ISector getSector() {
		return sector;
	}

	//@JsonIgnore
	public String getSectorToString() {
		return sector.getSector();
	}


	public void setSector(ISector sector) {
		this.sector = sector;
	}


	public ITipoEntidade getTipoEntidade() {
		return tipoEntidade;
	}

	//@JsonIgnore
	public String getTipoEntidadeToString() {
		return tipoEntidade.getTipo();
	}


	public void setTipoEntidade(ITipoEntidade tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
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


	public long getSituacaoEmpresa() {
		return situacaoEmpresa;
	}


	public void setSituacaoEmpresa(long situacaoEmpresa) {
		this.situacaoEmpresa = situacaoEmpresa;
	}


	public long getFormaJuridica() {
		return formaJuridica;
	}


	public void setFormaJuridica(long formaJuridica) {
		this.formaJuridica = formaJuridica;
	}


	public float getCapitalSocial() {
		return capitalSocial;
	}


	public void setCapitalSocial(float capitalSocial) {
		this.capitalSocial = capitalSocial;
	}


	public long getContabilidade() {
		return contabilidade;
	}


	public void setContabilidade(long contabilidade) {
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


	public long getActiComercial() {
		return actiComercial;
	}


	public void setActiComercial(long actiComercial) {
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


	public long getOrigemDoc() {
		return origemDoc;
	}


	public void setOrigemDoc(long origemDoc) {
		this.origemDoc = origemDoc;
	}


	public long getCanal() {
		return canal;
	}


	public void setCanal(long canal) {
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


	public long getPais() {
		return paisOrigem;
	}


	public void setPais(long paisOrigem) {
		this.paisOrigem = paisOrigem;
	}


	public ContabilidadeGeral getContabilidadeGeral() {
		return contabilidadeGeral;
	}

	public void setContabilidadeGeral(ContabilidadeGeral contabilidadeGeral) {
		this.contabilidadeGeral = contabilidadeGeral;
	}

	//endregion
}
