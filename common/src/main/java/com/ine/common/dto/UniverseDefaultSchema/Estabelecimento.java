package com.ine.common.dto.UniverseDefaultSchema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ine.common.interfaces.dto.ICAE;
import com.ine.common.interfaces.dto.IContacto;
import com.ine.common.interfaces.dto.IEstabelecimento;
import com.ine.common.interfaces.dto.ISituacao_ISP_Universe;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estabelecimento<IContacto , ICAE> extends com.ine.common.dto.UniverseDefaultSchema.AuditModel<String> implements IEstabelecimento<IContacto, ICAE>, ISituacao_ISP_Universe, Serializable{

	@JsonProperty("data_constituicao")
	private Date data_constituicao;

	@JsonProperty("estado")
	private int estado = 1;

	@JsonProperty("numFunc")
	private float numFunc;

	@JsonProperty("numFuncHomem")
	private float numFuncHomem;

	@JsonProperty("numFuncMulher")
	private float numFuncMulher;

	@JsonProperty("contacto")
	private IContacto contacto;

	@JsonProperty("sede")
	private Boolean sede;

	@JsonProperty("className")
	private String className;

	@JsonProperty("numEstabelecimento")
	private String numEstabelecimento;

	@JsonProperty("decoutras")
	private String decoutras;

	@JsonProperty("latitude")
	private String latitude;

	@JsonProperty("longitude")
	private String longitude;

	@JsonProperty("comentarios")
	private String comentarios;

	@JsonProperty("situacaoEmpresa")
	private String situacaoEmpresa;

	@JsonProperty("volumeVendas")
	private float volumeVendas;

	@JsonProperty("actiEcoPrincipal")
	private long actiEcoPrincipal;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("dataCriacao")
	private java.util.Date dataCriacao;

	@JsonProperty("dataDocumento")
	private java.util.Date dataDocumento;

	@JsonProperty("dataSituacaoAtividade")
	private java.util.Date dataSituacaoAtividade;

	@JsonProperty("dataUltimaInsercao")
	private java.util.Date dataUltimaInsercao;

	@JsonProperty("numFuncRem")
	private float numFuncRem;

	@JsonProperty("numFuncHomemRem")
	private float numFuncHomemRem;

	@JsonProperty("numFuncMulherRem")
	private float numFuncMulherRem;

	@JsonProperty("numFuncNRem")
	private float numFuncNRem;

	@JsonProperty("numFuncHomemNRem")
	private float numFuncHomemNRem;

	@JsonProperty("numFuncMulherNrem")
	private float numFuncMulherNrem;

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

	//FUE_ESTABELECIMENTO$CAE_SEC
	//ManyToMany
	@JsonProperty("cae")
	private Set<ICAE> cae = new HashSet<>();

	//region accessors for public property

	public long getActiEcoPrincipal() {
		return actiEcoPrincipal;
	}

	public void setActiEcoPrincipal(long actiEcoPrincipal) {
		this.actiEcoPrincipal = actiEcoPrincipal;
	}

	public Set <ICAE> getCae() {
		return cae;
	}

	public void setCae(Set <ICAE> cae) {
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
