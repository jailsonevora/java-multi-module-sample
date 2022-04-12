package com.ine.common.dto.UniverseDefaultSchema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ine.common.interfaces.dto.*;

import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contacto extends com.ine.common.dto.UniverseDefaultSchema.AuditModel<String> implements IContacto,IComuna_ISP_Universe, IMunicipio_ISP_Universe, IProvincia_ISP_Universe, IPais_ISP_Universe, Serializable{

	@JsonProperty("email")
	private String email;

	@JsonProperty("morada")
	private String morada;

	@JsonProperty("codPostal")
	private String codPostal;

	@JsonProperty("comuna")
	private String comuna;

	@JsonProperty("municipio")
	private String municipio;

	@JsonProperty("provincia")
	private String provincia;

	@JsonProperty("pais")
	private String pais;

	@JsonProperty("telefoneInd")
	private String telefoneInd;

	@JsonProperty("telefone")
	private String telefone;

	@JsonProperty("telemovelInd")
	private String telemovelInd;

	@JsonProperty("telemovel")
	private String telemovel;

	@JsonProperty("fax")
	private String fax;

	@JsonProperty("faxInd")
	private String faxInd;

	@JsonProperty("porta")
	private String porta;

	@JsonProperty("estado")
	private String casa;

	@JsonProperty("piso")
	private String piso;

	@JsonProperty("portaPiso")
	private String portaPiso;

	@JsonProperty("webUrl")
	private String webUrl;

	@JsonProperty("estado")
	private int estado = 1;


	//region accessors for private property

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getTelefoneInd() {
		return telefoneInd;
	}

	public void setTelefoneInd(String telefoneInd) {
		this.telefoneInd = telefoneInd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelemovelInd() {
		return telemovelInd;
	}

	public void setTelemovelInd(String telemovelInd) {
		this.telemovelInd = telemovelInd;
	}

	public String getTelemovel() {
		return telemovel;
	}

	public void setTelemovel(String telemovel) {
		this.telemovel = telemovel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFaxInd() {
		return faxInd;
	}

	public void setFaxInd(String faxInd) {
		this.faxInd = faxInd;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getPortaPiso() {
		return portaPiso;
	}

	public void setPortaPiso(String portaPiso) {
		this.portaPiso = portaPiso;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	//endregion
}
