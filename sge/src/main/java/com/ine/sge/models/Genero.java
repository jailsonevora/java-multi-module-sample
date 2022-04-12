package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IGenero;
import com.ine.common.interfaces.dto.IGenero_ISP_Universe;
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
@Table(name="SGE_GENERO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Genero extends com.ine.sge.models.AuditModel<String> implements IGenero, IGenero_ISP_Universe, Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_GENERO")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "SEXO")
	@Size(max=50)
	private String sexo;

	@Column(name = "ESTADO")
	private int estado = 1;

	//region accessors for public property

	public void setId(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}

	//endregion
}
