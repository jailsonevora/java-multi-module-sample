package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Indexed
@Entity
@Table(name="SGE_CONTACTO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contacto extends com.ine.sge.models.AuditModel<String> implements IContacto, IComuna_ISP_SGE, IMunicipio_ISP_SGE, IProvincia_ISP_SGE, IPais_ISP_SGE, Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_CONTACTO")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "EMAIL")
	@Email
	@Size(max=60)
	private String email;

	@Field
	@Column(name = "MORADA")
	@Size(max=120)
	private String morada;

	@Field
	@Column(name = "COD_POSTAL")
	@Size(max=15)
	private String codPostal;


	@Column(name = "COMUNA")
	private long comuna;

	@Column(name = "MUNICIPIO")
	private long municipio;

	@Column(name = "PROVINCIA")
	private long provincia;

	@Column(name = "PAIS")
	private long pais;


	@Field
	@Column(name = "TELEFONE_IND")
	@Size(max=3)
	private String telefoneInd;

	@Field
	@Column(name = "TELEFONE")
	@Size(max=40)
	private String telefone;

	@Field
	@Column(name = "TELEMOVEL_IND")
	@Size(max=3)
	private String telemovelInd;

	@Field
	@Column(name = "TELEMOVEL")
	@Size(max=40)
	private String telemovel;

	@Field
	@Column(name = "FAX")
	@Size(max=40)
	private String fax;

	@Field
	@Column(name = "FAX_IND")
	@Size(max=3)
	private String faxInd;

	@Field
	@Column(name = "PORTA")
	@Size(max=15)
	private String porta;

	@Field
	@Column(name = "CASA")
	@Size(max=4)
	private String casa;

	@Field
	@Column(name = "PISO")
	@Size(max=2)
	private String piso;

	@Field
	@Column(name = "PORTAPISO")
	@Size(max=3)
	private String portaPiso;

	@Field
	@Column(name = "WEBURL")
	@Size(max=100)
	private String webUrl;
	
	@Column(name = "ESTADO")
	private int estado = 1;


	//region accessors for private property

	public long getComuna() {
		return comuna;
	}

	public void setComuna(long comuna) {
		this.comuna = comuna;
	}

	public long getMunicipio() {
		return municipio;
	}

	public void setMunicipio(long municipio) {
		this.municipio = municipio;
	}

	public long getProvincia() {
		return provincia;
	}

	public void setProvincia(long provincia) {
		this.provincia = provincia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getPais() {
		return pais;
	}

	public void setPais(long pais) {
		this.pais = pais;
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
