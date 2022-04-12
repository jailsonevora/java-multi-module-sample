package com.ine.common.interfaces.dto;

public interface IMunicipio<IProvincia> {

	//region accessors for public property

	String getCodigo();

	void setCodigo(String codigo);

	String getDesignacao();

	void setDesignacao(String designacao);

	int getEstado();

	void setEstado(int estado);

	IProvincia getProvincia();

	void setProvincia(IProvincia provincia);

	//endregion
}
