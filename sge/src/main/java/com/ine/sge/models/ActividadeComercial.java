package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IActividadeComercial;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Indexed
@Entity
@Table(name = "SGE_ACTIV_COMERCIAL")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
		allowGetters = true)
public class ActividadeComercial extends com.ine.sge.models.AuditModel<String> implements IActividadeComercial, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_ACTV")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "CAC_DSG")
	@Size(max=50)
	private String cacDsg;

	@Field
	@Column(name = "CAC")
	@Size(max=2)
	private String cac;


	@Column(name = "ESTADO")
	private int estado = 1;


	//region accessors for public property

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCacDsg() {
		return cacDsg;
	}


	public void setCacDsg(String cacDsg) {
		this.cacDsg = cacDsg;
	}


	public String getCac() {
		return cac;
	}


	public void setCac(String cac) {
		this.cac = cac;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	//endregion
	
}
