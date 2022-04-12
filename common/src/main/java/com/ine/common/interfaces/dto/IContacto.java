package com.ine.common.interfaces.dto;

public interface IContacto {

	//region accessors for private property

	String getEmail();

	void setEmail(String email);

	String getMorada();

	void setMorada(String morada);

	String getCodPostal();

	void setCodPostal(String codPostal);

	String getTelefoneInd();

	void setTelefoneInd(String telefoneInd);

	String getTelefone();

	void setTelefone(String telefone);

	String getTelemovelInd();

	void setTelemovelInd(String telemovelInd);

	String getTelemovel();

	void setTelemovel(String telemovel);

	String getFax();

	void setFax(String fax);

	String getFaxInd();

	void setFaxInd(String faxInd);

	String getPorta();

	void setPorta(String porta);

	String getCasa();

	void setCasa(String casa);

	String getPiso();

	void setPiso(String piso);

	String getPortaPiso();

	void setPortaPiso(String portaPiso);

	String getWebUrl();

	void setWebUrl(String webUrl);

	int getEstado();

	void setEstado(int estado);

	//endregion
}
