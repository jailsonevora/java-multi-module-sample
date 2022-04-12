package com.ine.universe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.ISeccao;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Indexed
@Entity
@Table(name="FUE_SECCAO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seccao extends com.ine.universe.models.AuditModel<String> implements ISeccao, Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_SECC")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "SECCAO")
	private float seccao;

	@Field
	@Column(name = "AREA")
	private long area;
	
	@Column(name = "ALDEIA")
	private long aldeia;

	@Size(max=50)
	@Column(name = "COMUNA")
	private String comuna;

	@Size(max=50)
	@Column(name = "MUNICIPIO")
	private String municipio;

	@Size(max=50)
	@Column(name = "PROVINCIA")
	private String provincia;
	
	
	@Column(name = "ESTADO")
	private int estado = 1;

	//region accessors for public property

	public void setId(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
	}


	public float getSeccao() {
		return seccao;
	}


	public void setSeccao(float seccao) {
		this.seccao = seccao;
	}


	public long getArea() {
		return area;
	}


	public void setArea(long area) {
		this.area = area;
	}


	public long getAldeia() {
		return aldeia;
	}


	public void setAldeia(long aldeia) {
		this.aldeia = aldeia;
	}


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


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	//endregion
}
