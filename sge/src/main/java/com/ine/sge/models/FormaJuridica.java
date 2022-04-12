package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IFormaJuridica;
import com.ine.common.interfaces.dto.IFormaJuridica_ISP_Universe;
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
@Table(name="SGE_FORMAJURIDICA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FormaJuridica extends com.ine.sge.models.AuditModel<String> implements IFormaJuridica, IFormaJuridica_ISP_Universe, Serializable {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_FORJURI")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "FORMA_JURIDICA")
	@Size(max=100)
	private String formaJuridica;

	@Field
	@Column(name = "FJR")
	@Size(max=2)
	private String fjr;
	

	@Column(name = "ESTADO")
	private int estado = 1;


	//region accessors for public property

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getFormaJuridica() {
		return formaJuridica;
	}


	public void setFormaJuridica(String formaJuridica) {
		this.formaJuridica = formaJuridica;
	}


	public String getFjr() {
		return fjr;
	}


	public void setFjr(String fjr) {
		this.fjr = fjr;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	//endregion
}
