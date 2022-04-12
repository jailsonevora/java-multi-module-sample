package com.ine.universe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IEstabelecimento;
import com.ine.common.interfaces.dto.ISituacao_ISP_Universe;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Indexed
@Entity
@Table(name="FUE_ESTABELECIMENTO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estabelecimento <IContacto extends com.ine.universe.models.Contacto,
		ICAE extends com.ine.universe.models.CAE>
		extends com.ine.universe.models.AuditModel<String>
		implements IEstabelecimento<IContacto, ICAE>, ISituacao_ISP_Universe, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_ESTABELE")
	@JsonIgnore
	private long id;

	@Field
	@Column(name = "DATA_CONSTITUICAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_constituicao;

	@Column(name = "ESTADO")
	private int estado = 1;

	@Field
	@Column(name = "NUM_FUNCIONARIO")
	private float numFunc;

	@Field
	@Column(name = "NUM_FUNCIONARIO_HOMEM")
	private float numFuncHomem;

	@Field
	@Column(name = "NUM_FUNCIONARIO_MULHER")
	private float numFuncMulher;

	@OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, targetEntity=com.ine.universe.models.Contacto.class)
	@JoinColumn(name = "CONTACTO", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private IContacto contacto;

	@Field
	@Column(name = "SEDE")
	private Boolean sede;

	@Field
	@Column(name = "CLASSNAME")
	@Size(max=50)
	private String className;

	@Field
	@Column(name = "num_estabele")
	@Size(max=12)
	private String numEstabelecimento;

	@Field
	@Column(name = "DESCOUTRAS")
	@Size(max=100)
	private String decoutras;

	@Field
	@Column(name = "LATITUDE")
	@Size(max=100)
	private String latitude;

	@Field
	@Column(name = "LONGITUDE")
	@Size(max=100)
	private String longitude;

	@Field
	@Column(name = "COMENTARIOESTAB")
	@Size(max=400)
	private String comentarios;

	@Size(max=50)
	@Column(name = "SITUACAO_EMPRESA")
	private String situacaoEmpresa;

	@Field
	@Column(name = "VOLUME_VENDAS")
	private float volumeVendas;

	@Column(name = "ACTI_ECO_PRINCIPAL")
	private long actiEcoPrincipal;

	@Field
	@Column(name = "NOME")
	@Size(max=120)
	private String nome;

	@Field
	@Column(name = "DATACRIACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataCriacao;

	@Field
	@Column(name = "DATA_DOCUMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataDocumento;

	@Field
	@Column(name = "DATASITUACAOACTIVIDADE")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataSituacaoAtividade;

	@Field
	@Column(name = "DATAULTIMAINSERCAO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataUltimaInsercao;

	//@Field
	@Column(name = "NUM_FUNC_REM")
	private float numFuncRem;

	//@Field
	@Column(name = "NUM_FUNC_HOMEM_REM")
	private float numFuncHomemRem;

	//@Field
	@Column(name = "NUM_FUNC_MULHER_REM")
	private float numFuncMulherRem;

	//@Field
	@Column(name = "NUM_FUNC_NREM")
	private float numFuncNRem;

	//@Field
	@Column(name = "NUM_FUNC_HOMEM_NREM")
	private float numFuncHomemNRem;

	//@Field
	@Column(name = "NUM_FUNC_MULHER_NREM")
	private float numFuncMulherNrem;

	//@Field
	@Column(name = "NUM_FUNC_NAC")
	private float numFuncNac;

	//@Field
	@Column(name = "NUM_FUNC_EST")
	private float numFuncEst;

	//@Field
	@Column(name = "NUM_FUNCIONARIO_H_NAC")
	private float numFuncionarioHNac;

	//@Field
	@Column(name = "NUM_FUNCIONARIO_H_EST")
	private float numFuncionarioHEst;

	//@Field
	@Column(name = "NUM_FUNCIONARIO_M_NAC")
	private float numFuncionarioMNac;

	//@Field
	@Column(name = "NUM_FUNCIONARIO_M_EST")
	private float numFuncionarioMEst;

	//@Field
	@Column(name = "NUM_FUNC_REM_NAC")
	private float numFuncRemNac;

	//@Field
	@Column(name = "NUM_FUNC_REM_EST")
	private float numFuncRemEst;

	//@Field
	@Column(name = "NUM_FUNC_H_REM_NAC")
	private float numFuncHRemNac;

	//@Field
	@Column(name = "NUM_FUNC_H_REM_EST")
	private float numFuncHRemEst;

	//@Field
	@Column(name = "NUM_FUNC_M_REM_NAC")
	private float numFuncMRemNac;

	//@Field
	@Column(name = "NUM_FUNC_M_REM_EST")
	private float numFuncMRemEst;

	//@Field
	@Column(name = "NUM_FUNC_NREM_NAC")
	private float numFuncNRemNac;

	//@Field
	@Column(name = "NUM_FUNC_NREM_EST")
	private float numFuncNRemEst;

	//@Field
	@Column(name = "NUM_FUNC_H_NREM_NAC")
	private float numFuncHNRemNac;

	//@Field
	@Column(name = "NUM_FUNC_H_NREM_EST")
	private float numFuncHNRemEst;

	//@Field
	@Column(name = "NUM_FUNC_M_NREM_NAC")
	private float numFuncMNRemNac;

	//@Field
	@Column(name = "NUM_FUNC_M_NREM_EST")
	private float numFuncMNRemEst;

	//FUE_ESTABELECIMENTO$CAE_SEC
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.ALL
			},
			targetEntity=com.ine.universe.models.CAE.class)
	@JoinTable(name = "FUE_ESTABELECIMENTO$CAE_SEC",
			joinColumns = { @JoinColumn(name = "ID_ESTABELE") },
			inverseJoinColumns = { @JoinColumn(name = "ID_CAE") })
	private Set<ICAE> cae = new HashSet<>();

	//FUE_ENTIDADE$ESTABELECIMENTOS
	/*@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "estabelecimento")
	@JsonIgnore
	private Set<Entidade> entidade = new HashSet<>();*/

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTIDADE", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	//@JsonIgnore
	private Entidade entidade;*/


	//region accessors for public property

	public long getActiEcoPrincipal() {
		return actiEcoPrincipal;
	}

	public void setActiEcoPrincipal(long actiEcoPrincipal) {
		this.actiEcoPrincipal = actiEcoPrincipal;
	}

	public Set<ICAE> getCae() {
		return cae;
	}

	public void setCae(Set<ICAE> cae) {
		this.cae = cae;
	}

	public float getVolumeVendas() {
		return volumeVendas;
	}

	public void setVolumeVendas(float volumeVendas) {
		this.volumeVendas = volumeVendas;
	}

	public String getSituacaoEmpresa() {
		return situacaoEmpresa;
	}

	public void setSituacaoEmpresa(String situacaoEmpresa) {
		this.situacaoEmpresa = situacaoEmpresa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData_constituicao() {
		return data_constituicao;
	}

	public void setData_constituicao(Date data_constituicao) {
		this.data_constituicao = data_constituicao;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public float getNumFunc() {
		return numFunc;
	}

	public void setNumFunc(float numFunc) {
		this.numFunc = numFunc;
	}

	public float getNumFuncHomem() {
		return numFuncHomem;
	}

	public void setNumFuncHomem(float numFuncHomem) {
		this.numFuncHomem = numFuncHomem;
	}

	public float getNumFuncMulher() {
		return numFuncMulher;
	}

	public void setNumFuncMulher(float numFuncMulher) {
		this.numFuncMulher = numFuncMulher;
	}

	public IContacto getContacto() {
		return contacto;
	}

	public void setContacto(IContacto contacto) {
		this.contacto = contacto;
	}

	public Boolean getSede() {
		return sede;
	}

	public void setSede(Boolean sede) {
		this.sede = sede;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getNumEstabelecimento() {
		return numEstabelecimento;
	}

	public void setNumEstabelecimento(String numEstabelecimento) {
		this.numEstabelecimento = numEstabelecimento;
	}

	public String getDecoutras() {
		return decoutras;
	}

	public void setDecoutras(String decoutras) {
		this.decoutras = decoutras;
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

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Date getDataSituacaoAtividade() {
		return dataSituacaoAtividade;
	}

	public void setDataSituacaoAtividade(Date dataSituacaoAtividade) {
		this.dataSituacaoAtividade = dataSituacaoAtividade;
	}

	public Date getDataUltimaInsercao() {
		return dataUltimaInsercao;
	}

	public void setDataUltimaInsercao(Date dataUltimaInsercao) {
		this.dataUltimaInsercao = dataUltimaInsercao;
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

	public float getNumFuncNRem() {
		return numFuncNRem;
	}

	public void setNumFuncNRem(float numFuncNRem) {
		this.numFuncNRem = numFuncNRem;
	}

	public float getNumFuncHomemNRem() {
		return numFuncHomemNRem;
	}

	public void setNumFuncHomemNRem(float numFuncHomemNRem) {
		this.numFuncHomemNRem = numFuncHomemNRem;
	}

	public float getNumFuncMulherNrem() {
		return numFuncMulherNrem;
	}

	public void setNumFuncMulherNrem(float numFuncMulherNrem) {
		this.numFuncMulherNrem = numFuncMulherNrem;
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
	//endregion

}
