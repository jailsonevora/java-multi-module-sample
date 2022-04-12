package com.ine.common.interfaces.dto;

public interface IComuna<IMunicipio> {

	//region accessors for public property

	String getCodigo();

	void setCodigo(String codigo);

	String getDesignacao();

	void setDesignacao(String designacao);

	int getEstado();

	void setEstado(int estado);

	IMunicipio getMunicipio();

	void setMunicipio(IMunicipio municipio);

	//endregion
}
