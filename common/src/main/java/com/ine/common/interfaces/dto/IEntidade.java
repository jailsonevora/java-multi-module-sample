package com.ine.common.interfaces.dto;

import java.util.Date;
import java.util.Set;

public interface IEntidade<ISocio, IEmpresaParticipada, IEstabelecimento, ICAE, IContacto> {

	//region accessors for public property

	boolean isPorRenovar();

	void setPorRenovar(boolean porRenovar);

	float getVolumeVendas();

	void setVolumeVendas(float volumeVendas);

	Date getDataCostituicaoFim();

	void setDataCostituicaoFim(Date dataCostituicaoFim);

	int getVersao();

	void setVersao(int versao);

	long getNaturalId();

	void setNaturalId(long naturalId);

	Set <ISocio> getSocio();

	void setSocio(Set <ISocio> socio);

	Set <IEmpresaParticipada> getEmpresaParticipada();

	void setEmpresaParticipada(Set <IEmpresaParticipada> empresaParticipada);

	Set <ICAE> getCae();

	void setCae(Set<ICAE> cae);

	Set <IEstabelecimento> getEstabelecimento();

	void setEstabelecimento(Set <IEstabelecimento> estabelecimento);

	String getNome();

	void setNome(String nome);


	String getAbreviatura() ;


	void setAbreviatura(String abreviatura);


	int getIdade();


	void setIdade(int idade);


	float getVolume_vendas();


	void setVolume_vendas(float volume_vendas);


	String getProfissao();


	void setProfissao(String profissao);


	String getInstituicao();


	void setInstituicao(String instituicao);


	String getPessoa();


	void setPessoa(String pessoa);


	float getNumFuncionario();


	void setNumFuncionario(float numFuncionario);


	float getNumFuncionarioHomem();


	void setNumFuncionarioHomem(float numFuncionarioHomem);


	float getNumFuncionarioMulher();


	void setNumFuncionarioMulher(float numFuncionarioMulher);


	IContacto getContacto();


	void setContacto(IContacto contacto);


	int getEstado();


	void setEstado(int estado);


	float getEstadoAprovacao();


	void setEstadoAprovacao(float estadoAprovacao);


	java.util.Date getDataCostituicao();


	void setDataCostituicao(java.util.Date dataCostituicao);


	float getCapitalSocial();


	void setCapitalSocial(float capitalSocial);


	float getCapPublico();


	void setCapPublico(float capPublico);


	float getCapPrivado();


	void setCapPrivado(float capPrivado);


	float getCapEstrang();


	void setCapEstrang(float capEstrang);


	java.util.Date getDataDocumento();


	void setDataDocumento(java.util.Date dataDocumento);


	float getNumEstabelecimentos();


	void setNumEstabelecimentos(float numEstabelecimentos);


	long getActiEcoPrincipal();


	void setActiEcoPrincipal(long actiEcoPrincipal);


	String getNif();


	void setNif(String nif);


	float getNumDoc();


	void setNumDoc(float numDoc);


	String getEliminado();


	void setEliminado(String eliminado);


	long getAnoEliminacao();


	void setAnoEliminacao(long anoEliminacao);


	float getState();


	void setState(float state);


	String getBi();


	void setBi(String bi);


	String getNumRge();


	void setNumRge(String numRge);


	float getNumFuncRem();


	void setNumFuncRem(float numFuncRem);


	float getNumFuncHomemRem();


	void setNumFuncHomemRem(float numFuncHomemRem);


	float getNumFuncMulherRem();


	void setNumFuncMulherRem(float numFuncMulherRem);


	float getNumFuncNrem();


	void setNumFuncNrem(float numFuncNrem);


	float getNumFuncHomemNrem();


	void setNumFuncHomemNrem(float numFuncHomemNrem);


	float getNumFuncMulherNrem();


	void setNumFuncMulherNrem(float numFuncMulherNrem);


	java.util.Date getDataSge();


	void setDataSge(java.util.Date dataSge);


	String getComentarioEntidade();


	void setComentarioEntidade(String comentarioEntidade);


	java.util.Date getDataSituacaoActividade();


	void setDataSituacaoActividade(java.util.Date dataSituacaoActividade);

	String getGrupoHolding();


	void setGrupoHolding(String grupoHolding);


	String getCodGrupo();


	void setCodGrupo(String codGrupo);


	java.util.Date getDataConstGrupo();


	void setDataConstGrupo(java.util.Date dataConstGrupo);


	java.util.Date getDataFimGrupo();


	void setDataFimGrupo(java.util.Date dataFimGrupo);


	String getNomeGrupo();


	void setNomeGrupo(String nomeGrupo);


	String getNifGrupo();


	void setNifGrupo(String nifGrupo);


	long getSituacaoActivGrupo();


	void setSituacaoActivGrupo(long situacaoActivGrupo);


	java.util.Date getDataInitactGrupo();


	void setDataInitactGrupo(java.util.Date dataInitactGrupo);


	java.util.Date getDataFimActGrupo();


	void setDataFimActGrupo(java.util.Date dataFimActGrupo);


	long getNaturezaJurGrupo();


	void setNaturezaJurGrupo(long naturezaJurGrupo);


	long getCaePrincipalGrupo();


	void setCaePrincipalGrupo(long caePrincipalGrupo);


	float getNumEmpresasNacGrupo();


	void setNumEmpresasNacGrupo(float numEmpresasNacGrupo);


	float getNumEmpresasEstGrupo();


	void setNumEmpresasEstGrupo(float numEmpresasEstGrupo);


	float getNumPessoasServGrupo();


	void setNumPessoasServGrupo(float numPessoasServGrupo);


	float getVolumeNegocioGrupo();


	void setVolumeNegocioGrupo(float volumeNegocioGrupo);


	String getNomeRespGrupo();


	void setNomeRespGrupo(String nomeRespGrupo);


	String getTelefoneIndGrupo();


	void setTelefoneIndGrupo(String telefoneIndGrupo);


	String getTelefoneGrupo();


	void setTelefoneGrupo(String telefoneGrupo);


	String getFaxGrupo();


	void setFaxGrupo(String faxGrupo);


	String getFaxIndGrupo();


	void setFaxIndGrupo(String faxIndGrupo);


	String getEmailRespGrupo();


	void setEmailRespGrupo(String emailRespGrupo);


	String getWebUrlGrupo();


	void setWebUrlGrupo(String webUrlGrupo);


	long getOrigemDocGrupo();


	void setOrigemDocGrupo(long origemDocGrupo);


	String getComentarioGrupo();


	void setComentarioGrupo(String comentarioGrupo);


	java.util.Date getDataultImaInsercao();


	void setDataultImaInsercao(java.util.Date dataultImaInsercao);


	float getNumEstabsTotal();


	void setNumEstabsTotal(float numEstabsTotal);


	float getNumFuncNac();


	void setNumFuncNac(float numFuncNac);


	float getNumFuncEst();


	void setNumFuncEst(float numFuncEst);


	float getNumFuncionarioHNac();


	void setNumFuncionarioHNac(float numFuncionarioHNac);


	float getNumFuncionarioHEst();


	void setNumFuncionarioHEst(float numFuncionarioHEst);


	float getNumFuncionarioMNac();


	void setNumFuncionarioMNac(float numFuncionarioMNac);


	float getNumFuncionarioMEst();


	void setNumFuncionarioMEst(float numFuncionarioMEst);


	float getNumFuncRemNac();


	void setNumFuncRemNac(float numFuncRemNac);


	float getNumFuncRemEst();


	void setNumFuncRemEst(float numFuncRemEst);


	float getNumFuncHRemNac();


	void setNumFuncHRemNac(float numFuncHRemNac);


	float getNumFuncHRemEst();


	void setNumFuncHRemEst(float numFuncHRemEst);


	float getNumFuncMRemNac();


	void setNumFuncMRemNac(float numFuncMRemNac);


	float getNumFuncMRemEst();


	void setNumFuncMRemEst(float numFuncMRemEst);


	float getNumFuncNRemNac();


	void setNumFuncNRemNac(float numFuncNRemNac);


	float getNumFuncNRemEst();


	void setNumFuncNRemEst(float numFuncNRemEst);


	float getNumFuncHNRemNac();


	void setNumFuncHNRemNac(float numFuncHNRemNac);


	float getNumFuncHNRemEst();


	void setNumFuncHNRemEst(float numFuncHNRemEst);


	float getNumFuncMNRemNac();


	void setNumFuncMNRemNac(float numFuncMNRemNac);


	float getNumFuncMNRemEst();


	void setNumFuncMNRemEst(float numFuncMNRemEst);


	String getLatitude();


	void setLatitude(String latitude);


	String getLongitude();


	void setLongitude(String longitude);


	float getAnoActividade();


	void setAnoActividade(float anoActividade);


	float getMesesActividade();


	void setMesesActividade(float mesesActividade);


	String getGue();


	void setGue(String gue);


	String getSiac();


	void setSiac(String siac);


	String getWebUrl();


	void setWebUrl(String webUrl);

	//endregion
}
