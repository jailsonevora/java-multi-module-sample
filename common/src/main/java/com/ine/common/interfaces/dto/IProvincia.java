package com.ine.common.interfaces.dto;

public interface IProvincia<IPais> {

	//region accessors for public property


	String getCodigo();


	void setCodigo(String codigo);


	String getDesignacao();


	void setDesignacao(String designacao);


	int getEstado();


	void setEstado(int estado);


	String getSigla();


	void setSigla(String sigla);


	IPais getPais();


	void setPais(IPais pais);

	//endregion
}
