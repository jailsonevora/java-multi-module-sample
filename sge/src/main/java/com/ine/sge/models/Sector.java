package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.ISector;
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
@Table(name="SGE_SECTOR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sector extends com.ine.sge.models.AuditModel<String> implements ISector, Serializable  {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_SECTOR")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "SECTOR_DSG")
	@Size(max=100)
	private String sectorDsg;

	@Field
	@Column(name = "SECTOR")
	@Size(max=5)
	private String sector;

	@Column(name = "ESTADO")
	private int estado = 1;

	//region accessors for public property

	public void setId(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
	}


	public String getSectorDsg() {
		return sectorDsg;
	}


	public void setSectorDsg(String sectorDsg) {
		this.sectorDsg = sectorDsg;
	}


	public String getSector() {
		return sector;
	}


	public void setSector(String sector) {
		this.sector = sector;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}

	//endregion
}
