package com.ine.common.interfaces.dto;

public interface IAldeia<IComuna> {

	//region accessors for public property

	String getCodigo();

	void setCodigo(String codigo);

	String getDesignacao();

	void setDesignacao(String designacao);

	int getEstado();

	void setEstado(int estado);

	IComuna getComuna();

	void setComuna(IComuna comuna);

	//endregion
}
