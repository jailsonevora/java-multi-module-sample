package com.ine.common.interfaces.dto;

import java.util.Date;
import java.util.Set;

public interface IEstabelecimento<IContacto, ICAE> {

	//region accessors for public property

	long getActiEcoPrincipal();

	void setActiEcoPrincipal(long actiEcoPrincipal);

	Set<ICAE> getCae();

	void setCae(Set <ICAE> cae);

	float getVolumeVendas();

	void setVolumeVendas(float volumeVendas);

	Date getData_constituicao();

	void setData_constituicao(Date data_constituicao);

	int getEstado();

	void setEstado(int estado);

	float getNumFunc();

	void setNumFunc(float numFunc);

	float getNumFuncHomem();

	void setNumFuncHomem(float numFuncHomem);

	float getNumFuncMulher();

	void setNumFuncMulher(float numFuncMulher);

	IContacto getContacto();

	void setContacto(IContacto contacto);

	Boolean getSede();

	void setSede(Boolean sede);

	String getClassName();

	void setClassName(String className);

	String getNumEstabelecimento();

	void setNumEstabelecimento(String numEstabelecimento);

	String getDecoutras();

	void setDecoutras(String decoutras);

	String getLatitude();

	void setLatitude(String latitude);

	String getLongitude();

	void setLongitude(String longitude);

	String getComentarios();

	void setComentarios(String comentarios);

	String getNome();

	void setNome(String nome);

	Date getDataCriacao();

	void setDataCriacao(Date dataCriacao);

	Date getDataDocumento();

	void setDataDocumento(Date dataDocumento);

	Date getDataSituacaoAtividade();

	void setDataSituacaoAtividade(Date dataSituacaoAtividade);

	Date getDataUltimaInsercao();

	void setDataUltimaInsercao(Date dataUltimaInsercao);

	float getNumFuncRem();

	void setNumFuncRem(float numFuncRem);

	float getNumFuncHomemRem();

	void setNumFuncHomemRem(float numFuncHomemRem);

	float getNumFuncMulherRem();

	void setNumFuncMulherRem(float numFuncMulherRem);

	float getNumFuncNRem();

	void setNumFuncNRem(float numFuncNRem);

	float getNumFuncHomemNRem();

	void setNumFuncHomemNRem(float numFuncHomemNRem);

	float getNumFuncMulherNrem();

	void setNumFuncMulherNrem(float numFuncMulherNrem);

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

	//endregion

}
